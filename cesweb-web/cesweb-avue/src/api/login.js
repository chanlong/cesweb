/**
 * modify by chanlong on 2020-07-20
 * reference by: avue-admin & pigx-ui
 */
import qs from 'qs'
import request from '@/router/router-axios'

const scope = 'server'
const base64 = 'Y2Vzd2ViOmNlc3dlYg=='

/** 用户名登录 */
export const loginByUsername = (username, password, code, randomStr) => {
  let grant_type = 'password'
  return request({
    url: '/auth/oauth/token',
    headers: {
      isToken: false,
      'TENANT-ID': '1',
      'Authorization': `Basic ${base64}`
    },
    method: 'post',
    params: {randomStr, code, grant_type},
    data: qs.stringify({'username': username, 'password': password})
  })
}

/** 手机登录 */
export const loginByMobile = (mobile, code) => {
  const grant_type = 'mobile'
  return request({
    url: '/auth/mobile/token/sms',
    headers: {
      isToken: false,
      'TENANT-ID': '1',
      'Authorization': `Basic ${base64}`
    },
    method: 'post',
    params: {mobile: 'SMS@' + mobile, code: code, grant_type}
  })
}

/** 社交账号登录 */
export const loginBySocial = (state, code) => {
  const grant_type = 'mobile'
  return request({
    url: '/auth/mobile/token/social',
    headers: {
      isToken: false,
      'TENANT-ID': '1',
      'Authorization': `Basic ${base64}`
    },
    method: 'post',
    params: {mobile: state + '@' + code, grant_type}
  })
}

/** 刷新TOKEN */
export const refreshToken = (refresh_token) => {
  const grant_type = 'refresh_token'
  return request({
    url: '/auth/oauth/token',
    headers: {
      'isToken': false,
      'TENANT-ID': '1',
      'Authorization': `Basic ${base64}`
    },
    method: 'post',
    params: {refresh_token, grant_type, scope}
  })
}

/** 登出 */
export const logout = () => {
  return request({
    url: '/auth/token/logout',
    method: 'delete'
  })
}
