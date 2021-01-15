import Layout from '@/pages/index/'

export default [{
  path: '/info',
  component: Layout,
  redirect: '/info/index',
  children: [{
    path: 'index',
    name: '个人信息',
    component: () => import('@/views/sys/authority/user/info')
  }]
}, {
  path: '/sys/workflow',
  component: Layout,
  redirect: '/workflow/detail',
  children: [{
    path: 'detail/:id',
    name: '流程设计器',
    component: () => import('@/views/sys/workflow/model/detail')
  }]
}]
