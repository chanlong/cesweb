import Mock from 'mockjs'

const header = [{
  path: '/bix',
  meta: { i18n: 'biz' },
  icon: 'el-icon-menu',
  label: '业务平台',
  parentId: 0
},
{
  path: '/sys',
  meta: { i18n: 'sys' },
  icon: 'el-icon-s-tools',
  label: '系统管理',
  parentId: 1
},
{
  path: '/cms',
  meta: { i18n: 'cms' },
  icon: 'el-icon-info',
  label: '信息发布',
  parentId: 2
}]

const bizMenus = []

const sysMenus = [{
  path: '/sysadmin',
  meta: { i18n: 'sysadmin' },
  icon: 'el-icon-setting',
  label: '系统管理',
  children: [{
    path: 'orgn',
    icon: 'el-icon-s-help',
    label: '组织管理',
    component: 'views/sys/sysadmin/orgn/index',
    children: []
  }, {
    path: 'user',
    icon: 'el-icon-user',
    label: '用户管理',
    component: 'views/sys/sysadmin/user/index',
    children: []
  }, {
    path: 'role',
    icon: 'el-icon-s-check',
    label: '角色管理',
    component: 'views/sys/sysadmin/role/index',
    children: []
  }]
}, {
  path: '/developer',
  meta: { i18n: 'developer' },
  icon: 'el-icon-c-scale-to-original',
  label: '开发管理',
  children: [{
    path: 'sets',
    icon: 'icon-canshu',
    label: '参数配置',
    component: 'views/sys/developer/sets/index',
    children: []
  }, {
    path: 'gent',
    icon: 'icon-mima',
    label: '代码生成',
    component: 'views/sys/developer/gent/index',
    children: []
  }, {
    path: 'flow',
    icon: 'icon-iframe',
    label: '流程设计器',
    component: 'views/sys/developer/flow/index',
    children: []
  }, {
    path: 'form',
    icon: 'icon-biaodan',
    label: '表单设计器',
    component: 'views/sys/developer/form/index',
    children: []
  }, {
    path: 'crud',
    icon: 'icon-biaoge',
    label: '表格设计器',
    component: 'views/sys/developer/crud/index',
    children: []
  }]
}]

const cmsMenus = [{
  path: '/test',
  icon: 'icon-caidan',
  meta: { i18n: 'test' },
  label: '测试页面',
  component: 'views/test',
  children: []
}]

export default ({ mock }) => {
  if (!mock) return

  const menu = [bizMenus, sysMenus, cmsMenus]

  Mock.mock('/user/getMenu', 'get', (res) => {
    const body = JSON.parse(res.body)
    return { data: menu[body.type] || [] }
  })

  Mock.mock('/user/getTopMenu', 'get', () => {
    return { data: header }
  })
}
