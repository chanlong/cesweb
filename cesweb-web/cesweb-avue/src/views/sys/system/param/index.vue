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
                          @row-del="rowDel"/>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import { tableOption } from './option'
  import {mapGetters} from 'vuex'

  export default {
    name: 'Syspublicparam',
    data() {
      return {
        page: pageOption,
        searchForm: {},
        tableData: [],
        tableLoading: false,
        tableOption: tableOption
      }
    },
    computed: {
      ...mapGetters(['permissions']),
      permissionList() {
        return {
          addBtn: this.vaildData(this.permissions.admin_syspublicparam_add, false),
          delBtn: this.vaildData(this.permissions.admin_syspublicparam_del, false),
          editBtn: this.vaildData(this.permissions.admin_syspublicparam_edit, false)
        }
      }
    },
    methods: {
      getList(page, params) {
        this.tableLoading = true
        GlobalRequest.server('/system/param').fetchPage(Object.assign({
          descs: 'create_time',
          current: page.currentPage,
          size: page.pageSize
        }, params, this.searchForm)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      rowDel: function (row, index) {
        this.$confirm('是否确认删除ID为' + row.publicId, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/system/param').delete(row.publicId)
        }).then(data => {
          this.getList(this.page)
          this.$message.success('删除成功')
          this.getList(this.page)
        })
      },
      /**
       * @title 数据更新
       * @param row 为当前的数据
       * @param index 为当前更新数据的行数
       * @param done 为表单关闭函数
       *
       **/
      handleUpdate: function (row, index, done, loading) {
        GlobalRequest.server('/system/param').update(row).then(data => {
          this.$message.success('修改成功')
          done()
          this.getList(this.page)
        }).catch(() => {
          loading()
        })
      },
      /**
       * @title 数据添加
       * @param row 为当前的数据
       * @param done 为表单关闭函数
       *
       **/
      handleSave: function (row, done, loading) {
        GlobalRequest.server('/system/param').create(row).then(data => {
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
