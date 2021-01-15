// 配置编译环境和线上环境之间的切换
let baseUrl = ''
const env = process.env
const apiUrl = 'http://gateway.chanlong.online:9999'
const actUrl = `${apiUrl}/act/designer/index.html#/editor/`
const codeUrl = `${baseUrl}/code`
const iconfontVersion = ['1542178_c5fmaqa7s5m']
const iconfontUrl = '/cdn/font_$key/iconfont.css'
//const iconfontUrl = '//at.alicdn.com/t/font_$key.css'
if (env.NODE_ENV === 'development') {
  baseUrl = '/api'  // 开发环境地址
} else if (env.NODE_ENV === 'production') {
  baseUrl = '/prod'      // 生产环境地址
} else if (env.NODE_ENV === 'test') {
  baseUrl = '/test'      // 测试环境地址
}
export {
  env,
  apiUrl,
  actUrl,
  baseUrl,
  codeUrl,
  iconfontUrl,
  iconfontVersion
}
