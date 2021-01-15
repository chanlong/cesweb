<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :permission="permissionList"
                          :table-loading="tableLoading"
                          @on-load="getList"
                          @row-save="handleSave"
                          @row-update="handleUpdate"
                          @size-change="sizeChange"
                          @search-change="searchChange"
                          @current-change="currentChange"
                          @row-del="rowDel">

      <template slot="systemSearch" slot-scope="{row, size}">
        <el-radio-group v-model="row.system" :size="size">
          <el-radio-button label="1">系统类</el-radio-button>
          <el-radio-button label="0">业务类</el-radio-button>
        </el-radio-group>
      </template>

      <template slot="menu" slot-scope="scope">
        <el-button v-if="permissions.sys_dict_add" type="text" size="small" icon="el-icon-menu" @click="handleItem(scope.row,scope.index)">字典项</el-button>
      </template>
    </avue-crud>

    <el-dialog title="字典项管理" width="90%" :visible.sync="dialogFormVisible" :append-to-body="true" @close="dictItemVisible">
      <avue-crud ref="crudItem" v-model="form"
                                :page="itemPage"
                                :data="tableDictItemData"
                                :option="tableDictItemOption"
                                :permission="permissionList"
                                :before-open="handleBeforeOpen"
                                @size-change="itemSizeChange"
                                @current-change="itemCurrentChange"
                                @row-update="handleItemUpdate"
                                @row-save="handleItemSave"
                                @row-del="rowItemDel"/>
    </el-dialog>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import {tableDictItemOption, tableOption} from './option'
  import {mapGetters} from 'vuex'

  export default {
    name: 'Dict',
    data() {
      return {
        searchForm: {},
        form: {
          type: undefined,
          dictId: undefined
        },
        dictId: undefined,
        dictType: undefined,
        dialogFormVisible: false,
        tableData: [],
        tableDictItemData: [],
        page: pageOption,
        itemPage: {
          total: 0, // 总页数
          currentPage: 1, // 当前页数
          pageSize: 20 // 每页显示多少条
        },
        tableLoading: false,
        tableOption: tableOption,
        tableDictItemOption: tableDictItemOption
      }
    },
    computed: {
      ...mapGetters(['permissions']),
      permissionList() {
        return {
          addBtn: this.vaildData(this.permissions.sys_dict_add, false),
          delBtn: this.vaildData(this.permissions.sys_dict_del, false),
          editBtn: this.vaildData(this.permissions.sys_dict_edit, false)
        }
      }
    },
    methods: {
      //======字典表格相关=====
      getList(page, params) {
        this.tableLoading = true
        GlobalRequest.server('/system/dict').fetchPage(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, params, this.searchForm)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      rowDel: function (row) {
        this.$confirm('是否确认删除数据类型为"' + row.type + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/system/dict').delete(row)
        }).then(() => {
          this.getList(this.page)
          this.$message.success('删除成功')
        }).catch(function () {
        })
      },
      handleUpdate: function (row, index, done) {
        GlobalRequest.server('/system/dict').update(row).then(() => {
          this.$message.success('修改成功')
          this.getList(this.page)
          done()
        })
      },
      handleSave: function (row, done) {
        GlobalRequest.server('/system/dict').create(row).then(() => {
          this.$message.success('添加成功')
          this.getList(this.page)
          done()
        })
      },
      searchChange(form, done) {
        console.log(form);
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
      //======字典项表格相关=====
      dictItemVisible: function () {
        this.dialogFormVisible = false
        this.itemPage.currentPage = 1
      },
      handleItem: function (row) {
        this.getDictItemList(row.id, row.type)
      },
      getDictItemList(dictId, type) {
        this.dictType = type
        this.dictId = dictId
        this.dialogFormVisible = true
        GlobalRequest.server('/system/dict/item').fetchPage(Object.assign({
          current: this.itemPage.currentPage,
          size: this.itemPage.pageSize
        }, {dictId: dictId})).then(response => {
          this.tableDictItemData = response.data.data.records
          this.itemPage.total = response.data.data.total
        })
      },
      handleBeforeOpen(done) {
        this.form.type = this.dictType
        this.form.dictId = this.dictId
        done()
      },
      handleItemSave: function (row, done) {
        GlobalRequest.server('/system/dict/item').create(row).then(() => {
          this.$message.success('添加成功')
          this.getDictItemList(row.dictId, row.type)
          done()
        })
      },
      handleItemUpdate: function (row, index, done) {
        GlobalRequest.server('/system/dict/item').update(row).then(() => {
          this.$message.success('修改成功')
          this.getDictItemList(row.dictId, row.type)
          done()
        })
      },
      itemSizeChange(pageSize) {
        this.itemPage.pageSize = pageSize
        this.getDictItemList(this.dictId, this.type)
      },
      itemCurrentChange(current) {
        this.itemPage.currentPage = current
        this.getDictItemList(this.dictId, this.type)
      },
      rowItemDel: function (row) {
        this.$confirm('是否确认删除数据为"' + row.label + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/system/dict/item').delete(row.id)
        }).then(() => {
          this.getDictItemList(row.dictId, row.type)
          this.$message.success('删除成功')
        }).catch(function () {
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
