import request from '@/router/router-axios'

export function fetchList(query) {
  return request({
    url: '/system/social/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/social/',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/system/social/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/system/social/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/social/',
    method: 'put',
    data: obj
  })
}
