<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :permission="permissionList"
                          :before-open="beforeOpen"
                          :table-loading="tableLoading"
                          @on-load="getList"
                          @size-change="sizeChange"
                          @search-change="searchChange"
                          @refresh-change="refreshChange"
                          @current-change="currentChange"
                          @row-update="handleUpdate"
                          @row-save="handleSave"
                          @row-del="rowDel"/>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { tableOption } from './option'
  import { mapGetters } from 'vuex'

  export default {
    name: 'SysSocialDetails',
    data() {
      return {
        page: pageOption,
        searchForm: {},
        tableData: [],
        tableOption: tableOption,
        tableLoading: false
      }
    },
    created() {
    },
    mounted: function () {
    },
    computed: {
      ...mapGetters(['permissions']),
      permissionList() {
        return {
          addBtn: this.vaildData(this.permissions.sys_social_details_add, false),
          delBtn: this.vaildData(this.permissions.sys_social_details_del, false),
          editBtn: this.vaildData(this.permissions.sys_social_details_edit, false)
        }
      }
    },
    methods: {
      getList(page, params) {
        this.tableLoading = true
        GlobalRequest.server('/system/social').fetchPage(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, params, this.searchForm)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      rowDel: function (row, index) {
        var _this = this
        this.$confirm('是否确认删除ID为' + row.id, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/system/social').delete(row.id)
        }).then(() => {
          _this.$message.success('删除成功')
          this.refreshChange()
        }).catch(function () {
        })
      },
      handleUpdate: function (row, index, done) {
        GlobalRequest.server('/system/social').update(row).then(() => {
          this.$message.success('修改成功')
          this.refreshChange()
          done()
        })
      },
      handleSave: function (row, done) {
        GlobalRequest.server('/system/social').create(row).then(() => {
          this.tableData.push(Object.assign({}, row))
          this.$message.success('添加成功')
          this.refreshChange()
          done()
        })
      },
      refreshChange() {
        this.getList(this.page)
      },
      searchChange(form, done) {
        this.searchForm = form
        this.page.currentPage = 1
        this.getList(this.page, form)
        done()
      },
      sizeChange(pageSize) {
        this.page.pageSize = pageSize
      },
      currentChange(current) {
        this.page.currentPage = current
      },
      beforeOpen(show, type) {
        window.boxType = type
        show()
      }
    }
  }
</script>
