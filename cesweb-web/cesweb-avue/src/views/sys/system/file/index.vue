<template>
  <basic-container>
    <avue-crud ref="crud" :page="page"
                          :data="tableData"
                          :option="tableOption"
                          :upload-after="uploadAfter"
                          :permission="permissionList"
                          :table-loading="tableLoading"
                          @on-load="getList"
                          @size-change="sizeChange"
                          @search-change="searchChange"
                          @refresh-change="refreshChange"
                          @current-change="currentChange"
                          @upload-preview="uploadPreview"
                          @row-del="rowDel">
      <template slot="menu" slot-scope="scope">
        <el-button type="text" size="small" icon="el-icon-download" @click="download(scope.row,scope.index)">下载 </el-button>
      </template>
    </avue-crud>
  </basic-container>
</template>

<script>
import GlobalRequest from '@/api/crud'
import { pageOption } from '@/const/global/option'
import { tableOption } from './option'
import { mapGetters } from 'vuex'
import { handleDown } from '@/utils/util'


export default {
  name: 'sysfile',
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
        addBtn: this.vaildData(this.permissions.sys_file_add, true),
        delBtn: this.vaildData(this.permissions.sys_file_del, true),
        editBtn: this.vaildData(this.permissions.sys_file_edit, false)
      }
    }
  },
  methods: {
    getList(page, params) {
      this.tableLoading = true
      GlobalRequest.server('/system/sysfile').fetchPage(Object.assign({
        descs: 'create_time',
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
        return GlobalRequest.server('/system/sysfile').delete(row.id)
      }).then(data => {
        _this.$message.success('删除成功')
        this.getList(this.page)
      })
    },
    searchChange(form, done) {
      this.page.currentPage = 1
      this.getList(this.page, form)
      done()
    },
    refreshChange() {
      this.searchForm = this.form
      this.getList(this.page)
    },
    sizeChange(pageSize){
      this.page.pageSize = pageSize
    },
    currentChange(current){
      this.page.currentPage = current
    },
    download: function(row, index) {
      handleDown(row.fileName, row.bucketName)
    },
    uploadAfter(res, done, loading) {
        this.$message.success('上传成功')
        done()
        this.getList(this.page)
    },
    uploadPreview(file, column) {
      console.log(file);
    }
  }
}
</script>
