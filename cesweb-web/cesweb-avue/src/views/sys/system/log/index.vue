<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :table-loading="tableLoading"
                          :option="tableOption"
                          :permission="permissionList"
                          @on-load="getList"
                          @search-change="searchChange"
                          @refresh-change="refreshChange"
                          @size-change="sizeChange"
                          @current-change="currentChange"
                          @row-del="handleDel">

      <template slot="typeSearch" slot-scope="{row, size}">
        <el-radio-group v-model="row.type" :size="size" @change="searchChange(row)">
          <el-radio-button>全部</el-radio-button>
          <el-radio-button label="0">正常</el-radio-button>
          <el-radio-button label="9">异常</el-radio-button>
        </el-radio-group>
      </template>

      <template slot="type" slot-scope="scope">
        <el-tag :type="scope.row.type == 0 ? '' : 'danger'">{{ scope.label }}</el-tag>
      </template>
    </avue-crud>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { tableOption } from './option'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Log',
    data() {
      return {
        page: pageOption,
        tableData: [],
        searchForm: {},
        tableOption: tableOption,
        tableLoading: false
      }
    },
    computed: {
      ...mapGetters(['permissions']),
      permissionList() {
        return {
          delBtn: this.vaildData(this.permissions.sys_log_del, false)
        }
      }
    },
    methods: {
      getList(page, params) {
        this.tableLoading = true
        GlobalRequest.server('/system/log').fetchPage(Object.assign({
          descs: 'create_time',
          current: page.currentPage,
          size: page.pageSize
        }, params, this.searchForm)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      handleDel: function (row) {
        this.$confirm('是否确认删除ID为"' + row.id + '"的日志?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/system/log').delete(row.id)
        }).then(() => {
          this.getList(this.page)
          this.$message.success('删除成功')
        })
      },
      searchChange(form, done) {
        this.searchForm = form
        this.page.currentPage = 1
        this.getList(this.page, form)
        if (done) done()
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
