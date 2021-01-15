import request from '@/router/router-axios'

export function fetchList(query) {
  return request({
    url: '/system/client/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/client/',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/system/client/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/system/client/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/client',
    method: 'put',
    data: obj
  })
}
