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
                          @refresh-change="refreshChange"
                          @current-change="currentChange"
                          @row-update="handleUpdate"
                          @row-save="handleSave"
                          @row-del="handleDelete"/>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { tableOption } from './option'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Client',
    data() {
      return {
        page: pageOption,
        tableData: [],
        tableOption: tableOption,
        tableLoading: false
      }
    },
    computed: {
      ...mapGetters(['permissions']),
      permissionList() {
        return {
          addBtn: this.vaildData(this.permissions.sys_client_add, false),
          delBtn: this.vaildData(this.permissions.sys_client_del, false),
          editBtn: this.vaildData(this.permissions.sys_client_edit, false)
        }
      }
    },
    methods: {
      getList(page, params) {
        this.tableLoading = true
        GlobalRequest.server('/system/client').fetchPage(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, params)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      handleDelete: function (row, index) {
        this.$confirm('是否确认删除ID为' + row.clientId, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/system/client').delete(row.clientId)
        }).then(() => {
          this.$message.success('删除成功')
          this.refreshChange()
        })
      },
      handleUpdate: function (row, index, done) {
        if (row.accessTokenValidity === undefined) {
          row.accessTokenValidity = 60 * 60 * 12
        }
        if (row.refreshTokenValidity === undefined) {
          row.refreshTokenValidity = 60 * 60 * 24 * 30
        }

        GlobalRequest.server('/system/client').update(row).then(() => {
          this.$message.success('修改成功');
          this.refreshChange()
          done()
        })
      },
      handleSave: function (row, done) {
        if (row.accessTokenValidity === undefined) {
          row.accessTokenValidity = 60 * 60 * 12
        }
        if (row.refreshTokenValidity === undefined) {
          row.refreshTokenValidity = 60 * 60 * 24 * 30
        }
        GlobalRequest.server('/system/client').create(row).then(() => {
          this.$message.success('添加成功');
          this.refreshChange()
          done()
        })
      },
      sizeChange(pageSize) {
        this.page.pageSize = pageSize
      },
      currentChange(current) {
        this.page.currentPage = current
      },
      refreshChange() {
        this.getList(this.page)
      },
      beforeOpen(show, type) {
        window.boxType = type
        show()
      }
    }
  }
</script>
