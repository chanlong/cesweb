/**
 * 模拟数据 mock
 *
 * mock:true 前端本地开发时，开启模拟数据拦截
 * mock:false 生产系统及服务端联调时，关闭模拟数据拦截
 */
import modules from './modules'

for(var key in modules){
  modules[key]({ mock: true })
}
