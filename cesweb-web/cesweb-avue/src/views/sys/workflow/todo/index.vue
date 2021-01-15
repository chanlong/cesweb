<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :table-loading="tableLoading"
                          @on-load="getList"
                          @size-change="sizeChange"
                          @current-change="currentChange"
                          @refresh-change="refreshChange">

      <template slot-scope="scope" slot="menuBtn">
        <el-dropdown-item v-if="permissions.act_task_manage" divided @click.native="audit(scope.row,scope.index)">审批</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_task_manage" divided @click.native="comment(scope.row,scope.index)">批注</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_task_manage" divided @click.native="viewPic(scope.row,scope.index)">流程图</el-dropdown-item>
      </template>
    </avue-crud>

    <el-dialog :visible.sync="showTask" title="任务办理">
      <avue-form ref="form" v-model="obj" :option="formOption">
        <template slot-scope="scope" slot="menuForm">
          <el-button v-for="flag in flagList" :key="flag" icon="el-icon-check" plain @click="handleTask(scope.row,flag)">{{ flag }}</el-button>
        </template>
      </avue-form>
    </el-dialog>

    <el-dialog :visible.sync="showComment" title="批注列表">
      <avue-crud :data="taskData" :option="taskOption"/>
    </el-dialog>

    <el-dialog :visible.sync="showPicDialog" title="流程图">
      <img v-if="showPicDialog" :src="actPicUrl" width="100%">
    </el-dialog>
  </basic-container>
</template>

<script>
  import AvueUeditor from 'avue-plugin-ueditor'
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { formOption, taskOption, tableOption } from './option'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Task',
    comments: {
      AvueUeditor
    },
    data() {
      return {
        obj: {},
        actPicUrl: '',
        flagList: [],
        showTask: false,
        showComment: false,
        showPicDialog: false,
        page: pageOption,
        taskData: [],
        tableData: [],
        formOption: formOption,
        taskOption: taskOption,
        tableOption: tableOption,
        tableLoading: false
      }
    },
    computed: {
      ...mapGetters(['permissions'])
    },
    methods: {
      getList(page, params) {
        this.tableLoading = true
        GlobalRequest.server('/act/task/todo').fetch(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, params)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      audit: function (row) {
        GlobalRequest.server('/act/task/').fetchObj(row.taskId).then(response => {
          this.obj = response.data.data
          // 根据连线判断下次的流程
          this.flagList = this.obj.flagList
          this.showTask = true
        })
        this.obj = row
      },
      comment: function (row) {
        GlobalRequest.server('/act/task/comment/').fetchObj(row.taskId).then(response => {
          this.taskData = response.data.data
        })
        this.showComment = true
      },
      handleTask: function (row, result) {
        this.obj.taskFlag = result
        this.$refs.form.validate(valid => {
          if (valid) {
            GlobalRequest.server('/act/task').post(this.obj).then(() => {
              this.$message({
                showClose: true,
                message: '提交成功',
                type: 'success'
              })
              this.showTask = false
              this.getList(this.page)
            })
          }
        })
      },
      viewPic: function (row) {
        this.actPicUrl = `/act/task/view/` + row.taskId
        this.showPicDialog = true
      },
      sizeChange(pageSize) {
        this.page.pageSize = pageSize
      },
      currentChange(current) {
        this.page.currentPage = current
      },
      refreshChange() {
        this.getList(this.page)
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
