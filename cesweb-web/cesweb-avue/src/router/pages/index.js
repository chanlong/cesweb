import Layout from '@/pages/index/'

export default [{
    path: '/login',
    name: '登录页',
    component: () => import('@/pages/login/index'),
    meta: {
      keepAlive: true,
      isTab: false,
      isAuth: false
    }
  },
  {
    path: '/home',
    component: Layout,
    redirect: '/home/index',
    children: [{
      path: 'index',
      name: '首页',
      meta: { i18n: 'dashboard' },
      component: () => import('@/views/home')
    }]
  },
  {
    path: '/lock',
    name: '锁屏页',
    component: () => import('@/pages/lock/index'),
    meta: {
      keepAlive: true,
      isTab: false,
      isAuth: false
    }
  },
  {
    path: '/404',
    component: () => import('@/components/error-pages/404'),
    name: '404',
    meta: {
      keepAlive: true,
      isTab: false,
      isAuth: false
    }
  },
  {
    path: '/403',
    component: () => import('@/components/error-pages/403'),
    name: '403',
    meta: {
      keepAlive: true,
      isTab: false,
      isAuth: false
    }
  },
  {
    path: '/500',
    component: () => import('@/components/error-pages/500'),
    name: '500',
    meta: {
      keepAlive: true,
      isTab: false,
      isAuth: false
    }
  },
  {
    path: '/',
    name: '主页',
    redirect: '/home'
  },
  {
    path: '/myiframe',
    component: Layout,
    redirect: '/myiframe',
    children: [{
      path: ":routerPath",
      name: 'iframe',
      props: true,
      component: () => import('@/components/iframe/main')
    }]
  },
  {
    path: '/redirect',
    component: () => import('@/pages/index/redirect'),
    name: '路由刷新',
    meta: {
      isTab: false,
      isAuth: false
    }
  },
  {
    path: '*',
    redirect: '/404'
  }
]
