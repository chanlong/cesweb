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
                          @row-save="handleSave"
                          @row-del="rowDel">
      <template slot-scope="scope" slot="menuBtn">
        <el-dropdown-item v-if="permissions.act_model_manage" divided @click.native="handleDesign(scope.row,scope.index)">设 计</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_model_manage" divided @click.native="handleDeploy(scope.row,scope.index)">部 署</el-dropdown-item>
        <el-dropdown-item v-if="permissions.act_model_manage" divided @click.native="handleDelete(scope.row,scope.index)">删 除</el-dropdown-item>
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
    name: 'Activiti',
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
        GlobalRequest.server('/act/model').fetch(Object.assign({
          descs: 'create_time',
          current: page.currentPage,
          size: page.pageSize
        }, params, this.searchForm)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      handleDesign(row) {
        this.$router.push({path: `/sys/workflow/detail/${row.id}`, query: {
          key: row.key,
          name: row.name,
          category: row.category
        }})
      },
      handleDelete(row, index) {
        this.$refs.crud.rowDel(row, index)
      },
      handleDeploy: function (row) {
        this.$confirm('是否确认部署ID为"' + row.id + '"的模型?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/act/model/deploy').post(row.id)
        }).then(() => {
          this.getList(this.page)
          this.$message.success('部署成功')
        })
      },
      rowDel: function (row) {
        this.$confirm('是否确认删除ID为"' + row.id + '"的模型?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return GlobalRequest.server('/act/model').delete(row.id)
        }).then(() => {
          this.getList(this.page)
          this.$message.success('删除成功')
        })
      },
      /**
       * @title 数据添加
       * @param row 为当前的数据
       * @param done 为表单关闭函数
       *
       **/
      handleSave: function (row, done) {
        GlobalRequest.server('/act/model').create(row).then(() => {
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
