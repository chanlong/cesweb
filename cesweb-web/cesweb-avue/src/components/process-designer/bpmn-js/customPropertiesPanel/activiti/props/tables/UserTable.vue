<template>
  <el-container>
    <el-header>
      <div style="display: flex; padding: 10px 0 0; align-items: center;">
        <el-form :model="queryform" inline>
          <template v-for="(item, index) in tableOptions.columns">
            <el-form-item :label="item.label" :key="index" v-if="item.search">
              <template v-if="item.type === 'treeselect'">
                <treeselect v-model="queryform[item.prop]"
                            :auto-load-root-options="false"
                            :options="item.options"
                            :normalizer="normalizer"
                            :load-options="item.onLoad"
                            placeholder="选择部门"
                            no-options-text="暂无数据" style="max-width: 200px;"/>
              </template>

              <template v-else>
                <el-input v-model="queryform[item.prop]" clearable />
              </template>
            </el-form-item>
          </template>

          <el-form-item>
            <el-button @click="searchChange" type="primary">查 询</el-button>
            <el-button @click="searchChange">重 置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-header>

    <el-main>
      <el-table v-loading="loading" highlight-current-row :data="tableOptions.rows" @row-click="handleRowClick">
        <template v-for="(item, index) in tableOptions.columns">
          <el-table-column v-if="!item.ignore" :key="index"
                                               :align="item.align ? item.align : 'center'"
                                               :header-align="item.headerAlign ? item.headerAlign : 'center'"
                                               :label="item.label"
                                               :prop="item.prop" />
        </template>
      </el-table>
      <div style="display: flex; justify-content: flex-end; padding: 10px 5px;">
        <el-pagination layout="total, sizes, prev, pager, next, jumper"
                       :total="tableOptions.page.total"
                       :page-size="tableOptions.page.pageSize"
                       :page-sizes="tableOptions.page.pageSizes"
                       :current-page="tableOptions.page.currentPage"
                       @size-change="sizeChange"
                       @current-change="currentChange">
        </el-pagination>
      </div>
      <div class="table-selected">
        <el-tag v-for="item in selected" closable :key="item.userId" @close="handleCloseTag(item)">{{item.name}}</el-tag>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import { pageOption } from '@/const/global/option'
  import arrayUtil from '@/components/process-designer/utils/arrayUtil'
  import Treeselect from "@riophae/vue-treeselect"
  import "@riophae/vue-treeselect/dist/vue-treeselect.css"

  export default {
    name: 'UserTable',
    components: {
      Treeselect
    },
    props: {
      multiple: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        loading: true,
        selected: [],
        queryform: {},
        deptOptions: null,
        tableOptions: {
          rows: null,
          page: pageOption,
          columns: [{
            type: 'treeselect',
            prop: 'deptId',
            label: '部门',
            search: true,
            ignore: true,
            onLoad: this.loadDepts,
            options: null
          }, {
            prop: 'name',
            label: '用户名',
            search: true
          }, {
            prop: 'username',
            label: '登录名'
          }]
        }
      }
    },
    computed: {

    },
    watch: {

    },
    created() {
      this.loadUsers()
    },
    methods: {
      handleRowClick(row, column, event) {
        if (!arrayUtil.includes(this.selected, 'userId', row)) {
          this.selected.push(row)
        }
      },
      handleCloseTag(tag) {
        this.selected.splice(this.selected.indexOf(tag), 1)
      },
      sizeChange(pageSize) {
        this.loading = true
        this.tableOptions.page.pageSize = pageSize
        this.loadUsers()
      },
      currentChange(current) {
        this.loading = true
        this.tableOptions.page.currentPage = current
        this.loadUsers()
      },
      searchChange(event) {
        this.loading = true
        switch(event.target.innerText) {
          case '查 询':
            this.loadUsers(this.queryform)
          return
          default:
            for(var key in this.queryform) {
              this.queryform[key] = null
              this.loadUsers()
            }
          return
        }
      },
      async loadUsers(params) {
        const that = this
        const page = this.tableOptions.page
        await GlobalRequest.server('/system/user').fetchPage(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, params)).then(response => response.data.data).then(data => {
          page.total = data.total
          page.pageSize = data.size
          page.currentPage = data.current
          that.tableOptions.rows = data.records
          that.loading = false
        })
      },
      async loadDepts({ action, parentNode, callback }) {
        const that = this
        await GlobalRequest.server('/system/dept').fetchTree().then(response => {
          switch(action) {
            case 'LOAD_ROOT_OPTIONS':
              that.tableOptions.columns[0].options = response.data.data
            return
          }
        })
      },
      /** 转换数据结构 */
      normalizer(node) {
        // 去除叶子节点的children属性
        if (node.children && !node.children.length) {
          delete node.children
        }
        return {
          id: node.id,
          label: node.name,
          children: node.children
        }
      }
    }
  }
</script>

<style lang="scss">
  .table-selected {
    padding: 10px;
    background: #f8fcff;
    .el-tag {
      margin-right: 10px;
    }
  }
</style>
