/**
 * 全站权限配置
 * modify by chanlong on 2020-07-20
 * reference by: avue-admin & pigx-ui
 */
import store from './store'
import router from './router/router'
import { validateNull } from './utils/validate'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

NProgress.configure({ showSpinner: false })
const lockPage = store.getters.website.lockPage

router.beforeEach((to, from, next) => {
  NProgress.start()
  const meta = to.meta || {}
  if (store.getters.access_token) {
    // 如果系统激活锁屏，全部跳转到锁屏页
    if (store.getters.isLock && to.path != lockPage) {
      next({ path: lockPage })
    }
    // 如果登录成功访问登录页跳转到主页
    else if (to.path === '/login') {
      next({ path: '/' })
    }
    // 否则执行登录逻辑判断
    else {
      const value = to.query.src || to.fullPath
      const label = to.query.name || to.name
      const meta = to.meta || router.$routerPlugin.meta || {}
      const i18n = to.query.i18n

      if (meta.isTab !== false && !validateNull(value) && !validateNull(label)) {
        store.commit('ADD_TAG', {
          meta: (() => {
            if (!i18n) {
              return meta
            }
            return {
              i18n: i18n
            }
          })(),
          group: router.$routerPlugin.group || [],
          value: value,
          label: label,
          query: to.query,
          params: to.params
        })
      }
      next()
    }
  } else {
    // 判断是否需要认证，没有登录访问去登录页
    meta.isAuth === false ? next() : next('/login')
  }
})

router.afterEach(() => {
  let i18n = store.getters.tag.meta.i18n
  let label = store.getters.tag.label
  let title = router.$routerPlugin.generateTitle(label, i18n)
  // 根据当前的标签值动态设置浏览器标题
  router.$routerPlugin.setTitle(title)
  NProgress.done()
})
