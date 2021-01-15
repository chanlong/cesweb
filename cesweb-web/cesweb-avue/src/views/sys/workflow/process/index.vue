<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :table-loading="tableLoading"
                          @on-load="getList"
                          @search-change="searchChange"
                          @refresh-change="refreshChange"
                          @row-del="rowDel">

      <template slot-scope="scope" slot="menuBtn">
        <el-dropdown-item v-if="permissions.act_process_manage" divided @click.native="handlePic(scope.row,scope.index)">流程图</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_process_manage && scope.row.suspend" divided @click.native="handleStatus(scope.row,'active')">激活</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_process_manage && !scope.row.suspend" divided @click.native="handleStatus(scope.row,'suspend')">挂起</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_process_manage" divided @click.native="handleDel(scope.row,'suspend')">删除</el-dropdown-item>
      </template>
    </avue-crud>

    <el-dialog :visible.sync="showPicDialog" title="流程图">
      <img :src="actPicUrl" width="100%">
    </el-dialog>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { tableOption } from './option'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Process',
    data() {
      return {
        searchForm: {},
        page: pageOption,
        actPicUrl: '',
        tableData: [],
        tableOption: tableOption,
        tableLoading: false,
        showPicDialog: false
      }
    },
    computed: {
      ...mapGetters(['permissions'])
    },
    methods: {
      getList(page, params) {
        this.tableLoading = true
        GlobalRequest.server('/act/process').fetch(Object.assign({
          descs: 'create_time',
          current: page.currentPage,
          size: page.pageSize
        }, params, this.searchForm)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      handlePic(row) {
        this.actPicUrl = `/act/process/resource/${row.deploymentId}/${row.processonDefinitionId}/image`
        this.showPicDialog = true
      },
      handleStatus(row, type) {
        this.$confirm(
          '是否确认操作ID为"' + row.processonDefinitionId + '"的流程?', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(function () {
          return GlobalRequest.server('/act/process/status/').put([row.processonDefinitionId, type])
        }).then(() => {
          this.$message.success('操作成功')
          this.getList(this.page)
        })
      },
      handleDel(row, index) {
        this.$refs.crud.rowDel(row, index)
      },
      rowDel: function (row) {
        this.$confirm(
          '是否确认删除ID为"' + row.deploymentId + '"的模型?', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(function () {
          return GlobalRequest.server('/act/process').delete(row.deploymentId)
        }).then(() => {
          this.$message.success('删除成功')
          this.getList(this.page)
        })
      },
      searchChange(form, done) {
        this.searchForm = form
        this.page.currentPage = 1
        this.getList(this.page, form)
        done()
      },
      refreshChange() {
        this.getList(this.page)
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
