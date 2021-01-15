<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :permission="permissionList"
                          :table-loading="tableLoading"
                          @on-load="getList"
                          @size-change="sizeChange"
                          @search-change="searchChange"
                          @refresh-change="refreshChange"
                          @current-change="currentChange"
                          @row-update="handleUpdate"
                          @row-save="handleSave"
                          @row-del="rowDel" />
  </basic-container>
</template>

<script>
import { mapGetters } from 'vuex'
import GlobalRequest from '@/api/crud'
import { pageOption } from '@/const/global/option'
import { tableOption } from './option'

const GR = GlobalRequest.server('/tenant/tenant')

export default {
  name: 'Tenant',
  data() {
    return {
      page: pageOption,
      tableData: [],
      searchForm: {},
      tableLoading: false,
      tableOption: tableOption
    }
  },
  computed: {
    ...mapGetters(['permissions']),
    permissionList() {
      return {
        addBtn: this.vaildData(this.permissions.admin_tenant_add, false),
        delBtn: this.vaildData(this.permissions.admin_tenant_del, false),
        editBtn: this.vaildData(this.permissions.admin_tenant_edit, false)
      }
    }
  },
  methods: {
    getList(page, params) {
      this.tableLoading = true
      GR.fetchPage(Object.assign({
        current: page.currentPage,
        size: page.pageSize
      }, params, this.searchForm)).then(response => {
        this.tableData = response.data.data.records
        this.page.total = response.data.data.total
        this.tableLoading = false
      }).catch(() => {
        this.tableLoading = false
      })
    },
    rowDel: function(row, index) {
      var _this = this
      this.$confirm('是否确认删除ID为' + row.id, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return GR.delete(row.id)
      }).then(data => {
        _this.$message.success('删除成功')
        this.getList(this.page)
      })
    },
    handleUpdate: function(row, index, done, loading) {
      GR.update(row).then(data => {
        this.$message.success('修改成功')
        done()
        this.getList(this.page)
      }).catch(() => {
        loading()
      })
    },
    handleSave: function(row, done, loading) {
      GR.create(row).then(data => {
        this.$message.success('添加成功')
        done()
        this.getList(this.page)
      }).catch(() => {
        loading()
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
    },
    sizeChange(pageSize) {
      this.page.pageSize = pageSize
    },
    currentChange(current) {
      this.page.currentPage = current
    }
  }
}
</script>
