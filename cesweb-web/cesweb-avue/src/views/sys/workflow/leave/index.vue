<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :table-loading="tableLoading"
                          @on-load="getList"
                          @size-change="sizeChange"
                          @search-change="searchChange"
                          @refresh-change="refreshChange"
                          @current-change="currentChange"
                          @row-update="handleUpdate"
                          @row-save="handleSave"
                          @row-del="rowDel">
      <template slot="menuLeft">
        <el-button v-if="permissions.act_leavebill_add" type="primary" @click="handleAdd">新 增</el-button>
      </template>
      <template slot-scope="scope" slot="menuBtn">
        <el-dropdown-item v-if="permissions.act_leavebill_edit && scope.row.state == 0" divided @click.native="handleSubmit(scope.row,scope.index)">提交</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_leavebill_edit" divided @click.native="handleEdit(scope.row,scope.index)">编辑</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_leavebill_del" divided @click.native="handleDel(scope.row,'suspend')">删除</el-dropdown-item>
      </template>
    </avue-crud>
  </basic-container>
</template>

<script>
import AvueUeditor from 'avue-plugin-ueditor'
import GlobalRequest from '@/api/crud'
import { pageOption } from '@/const/global/option'
import { tableOption } from './option'
import { mapGetters } from 'vuex'

export default {
  comments: {
    AvueUeditor
  },
  name: 'LeaveBill',
  data() {
    return {
      searchForm: {},
      page: pageOption,
      tableData: [],
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
      GlobalRequest.server('/act/leave-bill').fetchPage(Object.assign({
        descs: 'create_time',
        current: page.currentPage,
        size: page.pageSize
      }, params, this.searchForm)).then(response => {
        this.tableData = response.data.data.records
        this.page.total = response.data.data.total
        this.tableLoading = false
      })
    },
    handleAdd: function() {
      this.$refs.crud.rowAdd()
    },
    handleEdit(row, index) {
      this.$refs.crud.rowEdit(row, index)
    },
    handleDel(row, index) {
      this.$refs.crud.rowDel(row, index)
    },
    rowDel: function(row, index) {
      this.$confirm('是否确认删除ID为' + row.leaveId, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return GlobalRequest.server('/act/leave-bill').delete(row.leaveId)
      }).then(() => {
        this.$message.success('删除成功')
      })
    },
    handleSubmit: function(row, index) {
      this.$confirm('是否确认提交ID为' + row.leaveId, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return GlobalRequest.server('/act/leave-bill/submit').post(row.leaveId)
      }).then(() => {
        this.$message.success('提交成功')
        this.getList(this.page)
      })
    },
    handleUpdate: function(row, index, done) {
      GlobalRequest.server('/act/leave-bill').update(row).then(() => {
        this.$message.success('修改成功')
        done()
        this.getList(this.page)
      })
    },
    handleSave: function(row, done) {
      GlobalRequest.server('/act/leave-bill').create(row).then(() => {
        this.$message.success('添加成功')
        done()
        this.getList(this.page)
      })
    },
    searchChange(form, done) {
      this.searchForm = form
      this.page.currentPage = 1
      this.getList(this.page, form)
      done()
    },
    sizeChange(pageSize){
      this.page.pageSize = pageSize
    },
    currentChange(current){
      this.page.currentPage = current
    },
    refreshChange() {
      this.getList(this.page)
    }
  }
}
</script>
