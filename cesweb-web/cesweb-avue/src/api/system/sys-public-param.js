import request from '@/router/router-axios'

export function fetchList(query) {
  return request({
    url: '/system/param/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/param',
    method: 'post',
    data: obj
  })
}

export function getObj(key) {
  return request({
    url: '/system/param/publicValue/' + key,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/system/param/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/param',
    method: 'put',
    data: obj
  })
}
