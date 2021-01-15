<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :table-loading="tableLoading"
                          :permission="permissionList"
                          @on-load="getList"
                          @size-change="sizeChange"
                          @refresh-change="refreshChange"
                          @current-change="currentChange"
                          @row-del="handleDel">
      <template slot="id" slot-scope="scope">
        <span>{{ scope.row.user_info.id }}</span>
      </template>
      <template slot="username" slot-scope="scope">
        <span>{{ scope.row.user_info.username }}</span>
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
  name: 'Token',
  data() {
    return {
      tableData:[],
      page: pageOption,
      tableOption: tableOption,
      tableLoading: false
    }
  },
  created() {
  },
  mounted: function() {
  },
  computed: {
    ...mapGetters(['permissions']),
    permissionList() {
      return {
        delBtn: this.vaildData(this.permissions.sys_token_del, false)
      }
    }
  },
  methods: {
    getList(page, params) {
      this.tableLoading = true
      GlobalRequest.server('/system/token').fetchPage(Object.assign({
        current: page.currentPage,
        size: page.pageSize
      }, params)).then(response => {
        this.tableData = response.data.data.records
        this.page.total = response.data.data.total
        this.tableLoading = false
      })
    },
    handleDel: function(row, index) {
      var _this = this
      this.$confirm('是否强制' + row.user_info.username + '下线?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return GlobalRequest.server('/system/token').delete(row.access_token)
      }).then(() => {
        _this.$message.success('强制下线成功')
      })
    },
    refreshChange() {
      this.getList(this.page)
    },
    sizeChange(pageSize){
      this.page.pageSize = pageSize
    },
    currentChange(current){
      this.page.currentPage = current
    },
  }
}
</script>
