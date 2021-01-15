/**
 * 全站路由配置
 *
 * meta参数说明
 * keepAlive是否缓冲页面
 * isTab是否加入到tag导航
 * isAuth是否需要授权
 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import PagesRouter from './pages/'
import ViewsRouter from './views/'
import RouterPlugin from './router-plugin'
import Store from '@/store'
import i18n from '@/lang'

// 加载路由组件
Vue.use(VueRouter)

// 解决重复点击路由报错的问题（Error: Avoided redundant navigation to current location）
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}

// 创建路由函数
const createRouter = () => {
  return new VueRouter({
    // 路由模式
    mode: 'history',
    // 添加路由
    routes: [...PagesRouter, ...ViewsRouter],
    // 当切换到新路由时，页面滚到顶部或保持原始位置
    scrollBehavior (to, from, savedPosition) {
      if (savedPosition) {
        return savedPosition
      } else {
        if (from.meta.keepAlive) {
          from.meta.savedPosition = document.body.scrollTop
        }
        return { x: 0, y: to.meta.savedPosition || 0 }
      }
    }
  })
}

// 创建路由
const Router = createRouter()
RouterPlugin.install(Vue, { router: Router, store: Store, i18n: i18n, keepAlive: false })
Router.$routerPlugin.formatRoutes(Store.state.user.menuAll, true)
// Router.addRoutes([...PagesRouter, ...ViewsRouter])

// 重置路由
export function resetRouter () {
  const newRouter = createRouter()
  Router.matcher = newRouter.matcher // reset router
  RouterPlugin.install(Vue, { router: Router, store: Store, i18n: i18n, keepAlive: false })
}

export default Router
