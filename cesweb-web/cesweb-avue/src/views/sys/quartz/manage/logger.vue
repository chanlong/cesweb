<template>
  <div class="jobLog">
    <basic-container>
      <el-dialog title="执行日志" width="90%" :visible.sync="dialogFormVisible" :append-to-body="true" @close="closeJobLogDialog">
        <avue-crud ref="crudLog" :page="pageLog"
                                 :data="tableLogData"
                                 :option="tableLogOption"
                                 :table-loading="tableLogLoading"
                                 @on-load="getJobLogList"
                                 @size-change="sizeChange"
                                 @current-change="currentChange"
                                 @refresh-change="getJobLogList">
        </avue-crud>
      </el-dialog>
    </basic-container>
  </div>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { tableLogOption } from './option'
  import { mapGetters } from 'vuex'

  export default {
    name: 'jobLog',
    data() {
      return {
        pageLog: pageOption,
        jobId: '',
        tableLogData: [],
        tableLogOption: tableLogOption,
        tableLogLoading: false,
        dialogFormVisible: false
      }
    },
    computed: {
      ...mapGetters(['permissions'])
    },
    mounted: function () {
    },
    methods: {
      init() {
        this.dialogFormVisible = true
        this.getJobLogList(this.pageLog)
      },
      getJobLog(row) {
        this.dialogFormVisible = true
        this.tableLogLoading = true
        this.jobId = row.jobId
        this.getJobLogList(this.pageLog)
      },
      getJobLogList(page) {
        GlobalRequest.server('/job/sys-job/job-log').fetch(Object.assign({
          descs: 'create_time',
          current: page.currentPage,
          size: page.pageSize
        }, {jobId: this.jobId})).then(response => {
          this.tableLogData = response.data.data.records
          this.pageLog.total = response.data.data.total
          this.pageLog.pageSize = response.data.data.pageSize
          this.tableLogLoading = false
        })
      },
      sizeChange(pageSize) {
        this.page.pageSize = pageSize
      },
      currentChange(current) {
        this.page.currentPage = current
      },
      closeJobLogDialog() {
        this.jobId = ''
        this.pageLog.total = 0
        this.pageLog.currentPage = 1
        this.pageLog.pageSize = 10
      },
    }
  }
</script>
