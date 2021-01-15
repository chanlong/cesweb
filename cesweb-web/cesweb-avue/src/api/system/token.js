import request from '@/router/router-axios'

export function fetchList(query) {
  return request({
    url: '/system/token/page',
    method: 'get',
    params: query
  })
}

export function delObj(token) {
  return request({
    url: '/system/token/' + token,
    method: 'delete'
  })
}
