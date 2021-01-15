<template>
  <basic-container :block="true" :sidebar="true">
    <template slot="left">
      <avue-tree class="left__tree" v-loading="treeLoading" element-loading-text="部门数据加载中..." :option="treeOption" :data="treeData" @node-click="nodeClick">
        <span class="el-tree-node__label" slot-scope="{ node, data }">
          <el-tooltip class="item" effect="dark" content="无数据权限" placement="right-start" v-if="data.isLock">
            <span>{{node.label}} <i class="el-icon-lock"></i></span>
          </el-tooltip>
          <span v-if="!data.isLock">{{node.label}}</span>
        </span>
      </avue-tree>
    </template>

    <template slot="body">
      <avue-crud class="body__crud" ref="crud" v-model="form"
                                               :data="list"
                                               :page="page"
                                               :option="option"
                                               :table-loading="listLoading"
                                               :before-open="handleOpenBefore"
                                               @on-load="getList"
                                               @size-change="sizeChange"
                                               @search-change="searchChange"
                                               @refresh-change="refreshChange"
                                               @current-change="currentChange"
                                               @row-save="create"
                                               @row-update="update">
        <template slot="menuLeft">
          <el-button v-if="sys_user_add" class="filter-item" type="primary" size="small" icon="el-icon-edit" @click="$refs.crud.rowAdd()">添加</el-button>
        </template>
        <template slot="username" slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
        <template slot="role" slot-scope="scope">
          <span v-for="(item, index) in scope.row.roleList" :key="index">
            <el-tag>{{ item.roleName }} </el-tag>&nbsp;&nbsp;
          </span>
        </template>
        <template slot="deptId" slot-scope="scope">
          {{ scope.row.deptName }}
        </template>
        <template slot="lockFlag" slot-scope="scope">
          <el-tag>{{ scope.label }}</el-tag>
        </template>
        <template slot="menu" slot-scope="scope">
          <el-button v-if="sys_user_edit" type="text" size="small" icon="el-icon-edit" @click="handleUpdate(scope.row,scope.index)">编辑</el-button>
          <el-button v-if="sys_user_del" type="text" size="small" icon="el-icon-delete" @click="deletes(scope.row,scope.index)">删除</el-button>
        </template>
        <template slot="roleForm" slot-scope="scope">
          <avue-select v-model="role" :dic="rolesOptions" :props="roleProps" multiple placeholder="请选择角色" />
        </template>
        <template slot="deptIdForm" slot-scope="scope">
          <avue-input-tree v-model="form.deptId" :node-click="getNodeData" :dic="treeDeptData" :props="defaultProps" placeholder="请选择所属部门" />
        </template>
      </avue-crud>
    </template>
  </basic-container>
</template>

<script>
import { mapGetters } from 'vuex'
import GlobalRequest from '@/api/crud'
import { pageOption } from '@/const/global/option'
import { tableOption } from './option'

const GR = GlobalRequest.server('/system/user')
export default {
  name: 'SysUser',
  data() {
    return {
      treeLoading: true,
      treeOption: {
        nodeKey: 'id',
        menu: false,
        addBtn: false,
        props: { label: 'name', value: 'id' }
      },
      treeData: [],
      searchForm: {},
      option: tableOption,
      treeDeptData: [],
      checkedKeys: [],
      roleProps: { label: 'roleName', value: 'roleId' },
      defaultProps: { label: 'name', value: 'id' },
      page: pageOption,
      list: [],
      listLoading: true,
      role: [],
      form: {},
      rolesOptions: []
    }
  },
  computed: {
    ...mapGetters(['permissions'])
  },
  watch: {
    role() {
      this.form.role = this.role
    }
  },
  created() {
    this.init()
    this.sys_user_add = this.permissions['sys_user_add']
    this.sys_user_del = this.permissions['sys_user_del']
    this.sys_user_edit = this.permissions['sys_user_edit']
  },
  methods: {
    init() {
      GlobalRequest.server('/system/dept').fetchTree().then(response => {
        this.treeData = response.data.data
        this.treeLoading = false
      })
    },
    nodeClick(data) {
      this.page.page = 1
      this.getList(this.page, { deptId: data.id })
    },
    getList(page, params) {
      this.listLoading = true
      GR.fetchPage(Object.assign({
        current: page.currentPage,
        size: page.pageSize
      }, params, this.searchForm)).then(response => {
        this.list = response.data.data.records
        this.page.total = response.data.data.total
        this.listLoading = false
      })
    },
    getNodeData() {
      GlobalRequest.server('/system/role/list').fetchPath().then(response => {
        this.rolesOptions = response.data.data
      })
    },
    searchChange(param, done) {
      this.searchForm = param
      this.page.currentPage = 1
      this.getList(this.page, param)
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
    },
    handleOpenBefore(show, type) {
      window.boxType = type
      // 查询部门树
      GlobalRequest.server('/system/dept').fetchTree().then(response => {
        this.treeDeptData = response.data.data
      })
      // 查询角色列表
      GlobalRequest.server('/system/role/list').fetchPath().then(response => {
        this.rolesOptions = response.data.data
      })

      if (['edit', 'views'].includes(type)) {
        this.role = []
        for (let i = 0; i < this.form.roleList.length; i++) {
          this.role[i] = this.form.roleList[i].roleId
        }
      } else if (type === 'add') {
        this.role = []
      }
      show()
    },
    handleUpdate(row, index) {
      this.$refs.crud.rowEdit(row, index)
      this.form.password = undefined
    },
    create(row, done, loading) {
      if (this.form.phone.indexOf('*') > 0) {
        this.form.phone = undefined
      }
      GR.create(this.form).then(() => {
        this.getList(this.page)
        done()
        this.$notify.success('创建成功')
      }).catch(() => {
        loading()
      })
    },
    update(row, index, done, loading) {
      if (this.form.phone && this.form.phone.indexOf('*') > 0) {
        this.form.phone = undefined
      }
      GR.update(this.form).then(() => {
        this.getList(this.page)
        done()
        this.$notify.success('修改成功')
      }).catch(() => {
        loading()
      })
    },
    deletes(row, index) {
      this.$confirm('此操作将永久删除该用户(用户名:' + row.username + '), 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        GR.delete(row.userId).then(() => {
          this.list.splice(index, 1)
          this.$notify.success('删除成功')
        }).catch(() => {
          this.$notify.error('删除失败')
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
  .left__tree,
  .body__crud {
    padding: 20px;
  }
  .body__crud {
    width: auto;
  }
</style>
