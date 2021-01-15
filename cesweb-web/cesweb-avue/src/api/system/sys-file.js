import request from '@/router/router-axios'

export function fetchList(query) {
  return request({
    url: '/system/sys-file/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/sys-file',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/system/sys-file/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/system/sys-file/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/sys-file',
    method: 'put',
    data: obj
  })
}
