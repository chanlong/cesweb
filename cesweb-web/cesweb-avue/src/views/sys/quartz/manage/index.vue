<template>
  <basic-container>
    <avue-crud ref="crud" v-model="form" :page="page"
                                         :data="tableData"
                                         :option="tableOption"
                                         :table-loading="tableLoading"
                                         @on-load="getList"
                                         @row-save="save"
                                         @row-update="update"
                                         @size-change="sizeChange"
                                         @refresh-change="refreshChange"
                                         @current-change="currentChange"
                                         @search-change="handleFilter"
                                         @search-reset="handleSearchReset">
      <template slot-scope="scope" slot="jobStatus">
        <div v-if="scope.row.jobStatus === '1'">
          <el-tag type="info">{{ getDicNameJobStatus(scope.row.jobStatus) }}</el-tag>
        </div>
        <div v-else-if="scope.row.jobStatus === '2'">
          <el-tag type="success">{{ getDicNameJobStatus(scope.row.jobStatus) }}</el-tag>
        </div>
        <div v-else-if="scope.row.jobStatus === '3'">
          <el-tag type="danger">{{ getDicNameJobStatus(scope.row.jobStatus) }}</el-tag>
        </div>
      </template>
      <template slot-scope="scope" slot="jobExecuteStatus">
        <div v-if="scope.row.jobExecuteStatus === '0'">
          <el-tag type="success">{{ getDicNameJobExecuteStatus(scope.row.jobExecuteStatus) }}</el-tag>
        </div>
        <div v-else>
          <el-tag type="danger">{{ getDicNameJobExecuteStatus(scope.row.jobExecuteStatus) }}</el-tag>
        </div>
      </template>
      <template slot="cronExpressionForm" slot-scope="scope">
        <div class="cron">
          <el-popover v-model="cronPopover">
            <cron i18n="cn" @change="changeCron" @close="cronPopover=false"/>
            <el-input slot="reference" v-model="form.cronExpression" placeholder="请输入定时策略" @click="cronPopover=true"/>
          </el-popover>
        </div>
      </template>
      <template slot="menuLeft">
        <el-button v-if="permissions.job_sys_job_add" type="primary" @click="handleAdd">
          新建任务
        </el-button>
        <el-tooltip content="暂停全部运行状态的定时任务" placement="top">
          <el-button v-if="permissions.job_sys_job_shutdown_job" type="warning" @click="shutdownJobs">
            暂停全部任务
          </el-button>
        </el-tooltip>
        <el-tooltip content="启动全部暂停状态的定时任务" placement="top">
          <el-button v-if="permissions.job_sys_job_start_job" type="success" @click="startJobs">
            启动全部任务
          </el-button>
        </el-tooltip>
        <el-tooltip content="谨慎使用" placement="top">
          <el-button v-if="permissions.job_sys_job_refresh_job" type="danger" @click="refreshJobs">重置全部任务
          </el-button>
        </el-tooltip>
      </template>
      <template slot="menu" slot-scope="scope">
        <el-button type="success" icon="el-icon-info" @click="handleJobLog(scope.row)" :style="'margin-right:5px;'">日志</el-button>
        <el-dropdown @command="handleCommand">
          <el-button type="primary">
            更多功能<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-if="permissions.job_sys_job_start_job" icon="el-icon-caret-right" :command="{data:[scope.row], invoke:handleStartJob}">启动</el-dropdown-item>
            <el-dropdown-item v-if="permissions.job_sys_job_shutdown_job" icon="el-icon-error" :command="{data:[scope.row], invoke:handleShutDownJob}">暂停</el-dropdown-item>
            <el-dropdown-item v-if="permissions.job_sys_job_edit" icon="el-icon-edit" :command="{data:[scope.row, scope.index], invoke:handleUpdate}">修改</el-dropdown-item>
            <el-dropdown-item v-if="permissions.job_sys_job_run_job" icon="el-icon-s-promotion" :command="{data:[scope.row], invoke:handleRunJob}">执行</el-dropdown-item>
            <el-dropdown-item v-if="permissions.job_sys_job_del" icon="el-icon-delete" :command="{data:[scope.row, scope.index], invoke:handleDelete}">删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </template>
    </avue-crud>

    <logger v-if="dialogFormVisible" ref="logRef"></logger>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { tableOption } from './option'
  import { mapGetters } from 'vuex'
  import { cron } from 'vue-cron'
  import logger from './logger'

  export default {
    name: 'jobIndex',
    components: {cron, logger},
    data() {
      return {
        page: pageOption,
        form: {},
        jobId: '',
        cronPopover: false,
        queryParams: {}, // 全局检索条件
        dialogFormVisible: false,
        tableData: [],
        tableOption: tableOption,
        tableLoading: false,
        JobStatusDicCache: [],
        JobExecuteStatusDicCache: []
      }
    },
    computed: {
      ...mapGetters(['permissions'])
    },
    mounted: function () {
      this.getDicJobExecuteStatusCache('job_execute_status')// 获取定时任务运行时状态
      this.getDicJobStatusCache('job_status')// 获取定时任务状态
    },
    methods: {
      changeCron(val) {
        this.form.cronExpression = val
      },
      /**
       * 定时任务分页查询
       */
      getList() {
        this.tableLoading = true
        GlobalRequest.server('/job/sys-job').fetchPage(Object.assign({
          descs: 'create_time',
          current: this.page.currentPage,
          size: this.page.pageSize
        }, this.queryParams)).then(response => {
          this.tableData = response.data.data.records
          this.page.pageSize = response.data.data.pageSize
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      /**
       * 清除全局检索条件
       */
      handleSearchReset() {
        this.queryParams = {}
      },
      /**
       * 定时任务检索查询分页查询
       */
      handleFilter(params, done) {
        this.queryParams = params
        this.page.currentPage = 1
        this.getList(this.page)
        done()
      },
      /**
       * 列表操作栏点击事件
       */
      handleCommand(cmd){
        cmd.invoke(...cmd.data)
      },
      /**
       * 启动定时任务
       */
      handleStartJob(row) {
        const jobStatus = row.jobStatus
        if (jobStatus === '1' || jobStatus === '3') {
          this.$confirm(
            '即将发布或启动(任务名称:' + row.jobName + '), 是否继续?',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
          ).then(() => {
            GlobalRequest.server('/job/sys-job/start-job').post(row.jobId).then(response => {
              const code = response.data.code
              if (code === 0) {
                this.$notify({
                  title: '成功',
                  message: '启动成功',
                  type: 'success'
                })
                this.refreshChange()
              }
            }).catch(() => {
              this.$notify.error({
                title: '错误',
                message: '启动失败'
              })
            })
          })
        } else {
          this.$notify.error({
            title: '错误',
            message: '定时任务已运行'
          })
        }
      },
      /**
       * 执行定时任务
       */
      handleRunJob(row) {
        this.$confirm(
          '立刻执行一次任务(任务名称:' + row.jobName + '), 是否继续?',
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          GlobalRequest.server('/job/sys-job/run-job').post(row.jobId).then(response => {
            const code = response.data.code
            if (code === 0) {
              this.$notify({
                title: '成功',
                message: '执行成功',
                type: 'success'
              })
              this.refreshChange()
            }
          }).catch(() => {
            this.$notify.error({
              title: '错误',
              message: '执行失败'
            })
          })
        })

      },
      handleAdd() {
        this.$refs.crud.rowAdd()
      },
      handleUpdate(row, index) {
        const jobStatus = row.jobStatus
        if (jobStatus === '1' || jobStatus === '3') {
          this.$refs.crud.rowEdit(row, index)
        } else {
          this.$notify.error({
            title: '错误',
            message: '运行中定时任务不可修改，请先暂停后操作'
          })
        }
      },
      /**
       * 暂停定时任务
       */
      handleShutDownJob(row) {
        const jobStatus = row.jobStatus
        if (jobStatus === '2') {
          this.$confirm(
            '即将暂停(任务名称:' + row.jobName + '), 是否继续?',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
          ).then(() => {
            GlobalRequest.server('/job/sys-job/shutdown-job').post(row.jobId).then(response => {
              const code = response.data.code
              if (code === 0) {
                this.getList(this.page)
                this.$notify({
                  title: '成功',
                  message: '暂停成功',
                  type: 'success'
                })
              }
              this.refreshChange()
            }).catch(() => {
              this.$notify.error({
                title: '错误',
                message: '暂停失败'
              })
            })
          })
        } else {
          this.$notify.error({
            title: '错误',
            message: '已暂停，不要重复操作'
          })
        }
      },
      /**
       * 刷新回调
       */
      refreshChange() {
        this.getList(this.page)
      },
      sizeChange(pageSize){
        this.page.pageSize = pageSize
      },
      currentChange(current){
        this.page.currentPage = current
      },
      shutdownJobs() {
        this.$confirm(
          '即将暂停全部运行中定时任务, 是否继续?',
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          GlobalRequest.server('/job/sys-job/shutdown-jobs').post().then(response => {
            const code = response.data.code
            const msg = response.data.msg
            if (code === 0) {
              this.getList(this.page)
              this.$notify.success(msg)
            } else {
              this.$notify.error('暂停失败')
            }
          }).catch(() => {
            this.$notify.error('暂停失败')
          })
        })
      },
      /**
       * 启动全部暂停定时任务
       */
      startJobs() {
        this.$confirm(
          '即将启动全部暂定中定时任务, 是否继续?',
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          GlobalRequest.server('/job/sys-job/start-jobs').post().then(response => {
            const code = response.data.code
            if (code === 0) {
              this.getList(this.page)
              this.$notify.success('启动成功')
            } else {
              this.$notify.error('启动失败')
            }
          }).catch(() => {
            this.$notify.error('启动失败')
          })
        })
      },
      /**
       * 刷新定时任务
       */
      refreshJobs() {
        this.$confirm(
          '即将刷新全部定时任务, 是否继续?',
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          GlobalRequest.server('/job/sys-job/refresh-jobs').post().then(response => {
            const code = response.data.code
            if (code === 0) {
              this.getList(this.page)
              this.$notify.success('重置成功')
            } else {
              this.$notify.error('重置失败')
            }
          }).catch(() => {
              this.$notify.error('重置失败')
            }
          )
        })
      },
      /**
       * 新增定时任务持久化处理
       */
      save(row, done, loading) {
        GlobalRequest.server('/job/sys-job/is-valid-task-name').fetch({
          'jobName': row.jobName,
          'jobGroup': row.jobGroup
        }).then(response => {
          const result = response.data.code
          if (result !== 0) {
            this.$notify.error('任务名称与任务组重复，请确认后重新添加')
          } else {
            GlobalRequest.server('/job/sys-job').create(row).then(() => {
              this.$notify.success('创建成功')
              this.getList(this.page)
            }).catch(() => {
              loading()
            })
          }
          done()
        })
      },
      /**
       * 更新定时任务持久化处理
       */
      update(row, index, done, loading) {
        GlobalRequest.server('/job/sys-job').update(row).then(() => {
          this.getList(this.page)
          done()
          this.$notify.success('修改成功')
        }).catch(() => {
          loading()
        })
      },
      /**
       * 删除定时任务持久化处理
       */
      handleDelete(row) {
        const jobStatus = row.jobStatus
        if (jobStatus === '1' || jobStatus === '3') {
          this.$confirm('是否确认删除(任务名称:' + row.jobName + '), 是否继续?删除后不可恢复', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            return GlobalRequest.server('/job/sys-job').delete(row.jobId)
          }).then(() => {
            this.getList(this.page)
            this.$notify.success('删除成功')
          }).catch(function () {
          })
        } else {
          this.$notify.error('运行中定时任务不可删除，请先暂停后操作')
        }
      },
      handleJobLog(row) {
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs.logRef.getJobLog(row)
        })
      },
      /**
       * 获取字典显示名称并缓存
       */
      getDicJobExecuteStatusCache(type) {
        GlobalRequest.server('/system/dict/type').fetchPath(type).then(response => {
          const code = response.data.code
          if (code === 0) {
            const _data = response.data.data
            this.JobExecuteStatusDicCache = _data
          }
        })
      },
      /**
       * 获取字典显示名称并缓存
       */
      getDicJobStatusCache(type) {
        GlobalRequest.server('/system/dict/type').fetchPath(type).then(response => {
          const code = response.data.code
          if (code === 0) {
            const _data = response.data.data
            this.JobStatusDicCache = _data
          }
        })
      },
      /**
       * 获取字典定时任务执行状态字典值显示名称
       */
      getDicNameJobExecuteStatus(value) {
        let re = ''
        this.JobExecuteStatusDicCache.forEach(obj => {
          if (obj.value === value) {
            re = obj.label
            return
          }
        })
        return re
      },
      /**
       * 获取字典定时任务状态字典值显示名称
       */
      getDicNameJobStatus(value) {
        let re = ''
        this.JobStatusDicCache.forEach(obj => {
          if (obj.value === value) {
            re = obj.label
            return
          }
        })
        return re
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
