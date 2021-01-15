/**
 * 模拟代码生成相关API
 */
import request from '@/router/router-axios'
import { baseUrl } from '@/config/env'

/**
 * 获取表格
 */
export const getTabes = (data) => {
  return request({
    url: baseUrl + '/gent/tables',
    method: 'get',
    meta: { isSerialize: true },
    params: data
  })
}
