<template>
  <el-form :model="general" label-position="left" label-width="auto">
    <el-collapse v-model="activeNames">
      <!-- 常规 -->
      <el-collapse-item title="常规" name="1">
        <span class="group-toggle green"></span>
        <el-form-item label="编号" required>
          <el-input v-model="general.id" clearable></el-input>
        </el-form-item>
        <el-form-item label="名称" required>
          <el-input v-model="general.name" clearable></el-input>
        </el-form-item>
      </el-collapse-item>

      <el-collapse-item title="详情" name="2">
        <span class="group-toggle green"></span>
        <el-form-item label="代理人">
          <el-input v-model="general.assignee" clearable>
            <el-button slot="append" icon="el-icon-user" @click="visible.user = true"></el-button>
            <el-button slot="append" icon="el-icon-s-help" @click="visible.role = true"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="候选人">
          <el-select-table v-model="general.candidateUsers" :rows="options.candidateUsers.rows"
                                                            :columns="options.candidateUsers.columns"
                                                            @onLoad="loadUsers"
                                                            @onSelect="handleUserSelect"
                                                            @onRemove="handleUserRemove"
                                                            @onSearch="handleUserSearch"/>
        </el-form-item>
        <el-form-item label="候选组">
          <el-select v-model="general.candidateGroups" clearable multiple default-first-option>
            <el-option v-for="item in options.candidateGroups" :key="item.value" :label="item.label" :value="item.label"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="到期时间">
          <el-date-picker v-model="general.dueDate" clearable></el-date-picker>
        </el-form-item>
        <el-form-item label="跟踪日期">
          <el-date-picker v-model="general.followUpDate" clearable></el-date-picker>
        </el-form-item>
        <el-form-item label="优先级">
          <el-input-number v-model="general.priority"></el-input-number>
        </el-form-item>
      </el-collapse-item>

      <!-- 持续异步 -->
      <AsyncProps :name="activeNames[2]" :modeler="modeler" :element="element" @update="handleUpdate"/>
    </el-collapse>

    <el-dialog title="选择用户" append-to-body :visible.sync="visible.user">
      <user-table style="margin: -28px -18px;" multiple/>
      <div slot="footer">
        <el-button @click="visible.user = false">取 消</el-button>
        <el-button type="primary" @click="visible.user = false">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="选择角色" append-to-body :visible.sync="visible.role">
      <role-table style="margin: -28px -18px;"/>
      <div slot="footer">
        <el-button @click="visible.role = false">取 消</el-button>
        <el-button type="primary" @click="visible.role = false">确 定</el-button>
      </div>
    </el-dialog>
  </el-form>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import bpmnUtil from '@/components/process-designer/utils/bpmnUtil'
  import arrayUtil from '@/components/elementui/select-table/util/arrayUtil'
  import elSelectTable from '@/components/elementui/select-table/elSelectTable'
  import AsyncProps from '../includes/AsyncProps'
  import UserTable from '../tables/UserTable'
  import RoleTable from '../tables/RoleTable'

  export default {
    name: 'StartEventGeneral',
    inject: ['modeler'],
    components: {
      elSelectTable,
      AsyncProps,
      UserTable,
      RoleTable
    },
    props: {
      element: {
        type: Object,
        default: () => {
          return {}
        }
      }
    },
    data() {
      return {
        general: {
          id: null,
          name: null,
          dueDate: null,
          assignee: null,
          priority: null,
          followUpDate: null,
          candidateUsers: null,
          candidateGroups: null
        },
        visible: {
          user: false,
          role: false
        },
        databind: {
          candidateUsers: [],
          candidateGroups: []
        },
        options: {
          candidateUsers: {
            rows: [],
            columns: [{
              type: 'treeselect',
              prop: 'deptId',
              label: '部门',
              search: true,
              hidden: true,
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
          },
          candidateGroups: []
        },
        extension: {
          taskListeners: [],
          executionListeners: []
        }
      }
    },
    computed: {
      activeNames: {
        get() {
          return ['1', '2', '3']
        },
        set() {}
      }
    },
    watch: {
      general: {
        deep: true,
        handler(newVal, oldVal) {
          if (newVal && newVal.id) {
            const that = this
            bpmnUtil.debounce(() => {
              that.$emit('update', bpmnUtil.filter(newVal))
            })
          }
        }
      },
      element: {
        deep: true,
        immediate: true,
        handler(newVal, oldVal) {
          if (newVal && newVal.businessObject) {
            // 为属性面板赋值
            bpmnUtil.setProperties(this.general, newVal.businessObject)
          }
        }
      },
      'databind.candidateUsers': {
        handler(newVal, oldVal) {
          this.general.candidateUsers = arrayUtil.join(newVal, 'userId')
        }
      }
    },
    created() {
      this.$emit('before')
    },
    mounted() {
      this.$emit('complete', this.extension)
    },
    methods: {
      handleUpdate(properties) {
        this.$emit('update', properties)
      },
      handleAssignee() {

      },
      handleUserSelect(user) {
        if (!this.databind.candidateUsers) {
          this.databind.candidateUsers = new Array()
        }
        if (!arrayUtil.includes(this.databind.candidateUsers, 'userId', user)) {
          this.databind.candidateUsers.push({
            userId: user.userId,
            name: user.name,
          })
        }
      },
      handleUserSearch() {

      },
      handleUserRemove(userName) {
        let user = arrayUtil.get(this.databind.candidateUsers , 'name', userName)
        let index = this.databind.candidateUsers.findIndex(item => item.userId === user.userId);
        this.databind.candidateUsers.splice(index, 1)
      },
      async loadDepts({ action, parentNode, callback }) {
        const that = this
        await GlobalRequest.server('/system/dept').fetchTree().then(response => {
          switch(action) {
            case 'LOAD_ROOT_OPTIONS':
              that.options.candidateUsers.columns[0].options = response.data.data
            return
          }
        })
      },
      loadUsers(page, params) {
        GlobalRequest.server('/system/user').fetchPage(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, params)).then(response => {
          const data = response.data.data
          page.total = data.total
          page.pageSize = data.size
          page.currentPage = data.current
          this.options.candidateUsers.rows = data.records
        })
      }
    }
  }
</script>

<style>
</style>
