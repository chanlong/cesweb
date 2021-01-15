import 'babel-polyfill'
import 'classlist-polyfill'

import Vue from 'vue'
import axios from './router/router-axios'
import VueAxios from 'vue-axios'
import App from './App'
// 全局权限处理
import './permission'
// 全局日志处理
import './error'
// 引入路由组件
import router from './router/router'
// 引入存储组件
import store from './store'
// 引入国际化组件
import i18n from './lang'
// 引入element-ui
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 引入 vue-ele-form
import EleForm from 'vue-ele-form'
// 引入Avue
import Avue from '@smallwei/avue'
import '@smallwei/avue/lib/index.css'
// 引入Avue表单设计插件
import AvueFormDesign from '@sscfaith/avue-form-design'
// 引入全局样式
import './styles/common.scss'
// 加载相关url地址
import * as urls from './config/env'
// 全局filter
import * as filters from './filters'
// 动态加载阿里云字体库
import { loadStyle } from './utils/util'
import { iconfontUrl, iconfontVersion } from './config/env'
// 引入全局容器
import basicBlock from './components/layout/basic-block/main'
import basicContainer from './components/layout/basic-container/main'

// 注册路由
Vue.use(router)

// 注册axios
Vue.use(VueAxios, axios)

// 注册element-ui
Vue.use(Element, { size: 'small', menuType: 'text', i18n: (key, value) => i18n.t(key, value) })

// 注册 vue-ele-form
Vue.use(EleForm, {
  // 所有和上传相关(上传图片, 上传视频, 富文本中图片上传)
  upload: {
    action: 'https://www.xxx.com/posts', // 请求地址,
    data: { token: 'xxx' }, // 附带的参数,
    responseFn (response) { // 处理响应结果
      return 'https://www.xxx.com/upload/' + response.id
    }
  },
  // number类型
  number: {
    min: 0 // 所有 number 类型, 最小值为 0
  }
})

// 注册Avue
Vue.use(Avue, { size: 'small', menuType: 'text', i18n: (key, value) => i18n.t(key, value) })

// 注册Avue表单设计插件
Vue.use(AvueFormDesign, { i18n: (key, value) => i18n.t(key, value) })

// 注册全局容器
Vue.component('basicBlock', basicBlock)
Vue.component('basicContainer', basicContainer)

// 注册全局事件
Vue.prototype.$event = new Vue()

// 加载相关url地址
Object.keys(urls).forEach(key => { Vue.prototype[key] = urls[key] })
// 加载过滤器
Object.keys(filters).forEach(key => { Vue.filter(key, filters[key]) })
// 动态加载阿里云字体库
iconfontVersion.forEach(ele => { loadStyle(iconfontUrl.replace('$key', ele)) })

Vue.config.productionTip = false

new Vue({ router, store, i18n, render: h => h(App) }).$mount('#app')
