/**
 * 全站http配置
 *
 * axios参数说明
 * isSerialize是否开启form表单提交
 * isToken是否需要token
 */
 import qs from 'qs'
 import axios from 'axios'
 import store from '@/store' // progress bar style
 import router from '@/router/router'
 import errorCode from '@/const/errorCode'
 import { serialize } from '@/utils/util'
 import { getStore } from '@/utils/store'
 import { Message } from 'element-ui'
 import NProgress from 'nprogress' // progress bar
 import 'nprogress/nprogress.css'
 import { baseUrl } from '@/config/env'

 // 请求超时时间
 axios.defaults.timeout = 6000
 // 全局接口请求地址
 axios.defaults.baseURL = baseUrl
 // 返回其他状态吗
 axios.defaults.validateStatus = function (status) {
   return status >= 200 && status <= 500 // 默认的
 }
 // 跨域请求，允许保存cookie
 axios.defaults.withCredentials = true
 axios.defaults.crossDomain = true
 
 // NProgress Configuration
 NProgress.configure({ showSpinner: false })

 // HTTPrequest拦截
 axios.interceptors.request.use(config => {
   NProgress.start() // start progress bar
   const TENANT_ID = getStore({ name: 'tenantId' })
   const isToken = (config.headers || {}).isToken === false
   const token = store.getters.access_token

   // 租户ID
   if (TENANT_ID) {
     config.headers['TENANT-ID'] = TENANT_ID
   }
   // token
   if (token && !isToken) {
     config.headers['Authorization'] = 'Bearer ' + token
   }
   // headers中配置serialize为true开启序列化
   if (config.method === 'post' && config.headers.serialize) {
     config.data = serialize(config.data)
     delete config.data.serialize
   }
   if (config.method === 'get') {
     config.paramsSerializer = function (params) {
       return qs.stringify(params, { arrayFormat: 'repeat' })
     }
   }
   return config
 }, error => {
   return Promise.reject(error)
 })

 // HTTPresponse拦截
 axios.interceptors.response.use(res => {
   const status = Number(res.status) || 200
   const message = res.data.msg || errorCode[status] || errorCode['default']
   if (status === 401) {
     Message({ message: message, type: 'error' })
     store.dispatch('FedLogOut').then(() => { router.push({ path: '/login' }) })
     return
   }

   if (status !== 200 || res.data.code === 1) {
     Message({ message: message, type: 'error' })
     return Promise.reject(new Error(message))
   }
   NProgress.done()
   return res
 }, error => {
   NProgress.done()
   return Promise.reject(new Error(error))
 })

 // 修复avue-crud组件dicUrl不发送请求的问题
 window.axios = axios

 export default axios
