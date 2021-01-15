/**
 * modify by chanlong on 2020-07-20
 * reference by: avue-admin & pigx-ui
 */
import webiste from '@/config/website'
import GlobalRequest from '@/api/crud'
import { setStore, getStore } from '@/utils/store'
import { isURL, validateNull } from '@/utils/validate'
import { deepClone, encryption } from '@/utils/util'
import { loginByUsername, loginByMobile, loginBySocial, refreshToken, logout } from '@/api/login'

function addPath(ele, first) {
  const menu = webiste.menu
  const propsConfig = menu.props
  const propsDefault = {
    path: propsConfig.path || 'path',
    icon: propsConfig.icon || 'icon',
    label: propsConfig.label || 'name',
    children: propsConfig.children || 'children'
  }
  const icon = ele[propsDefault.icon]
  ele[propsDefault.icon] = validateNull(icon) ? menu.iconDefault : icon
  const isChild = ele[propsDefault.children] && ele[propsDefault.children].length !== 0
  if (!isChild) ele[propsDefault.children] = []
  if (!isChild && first && !isURL(ele[propsDefault.path])) {
    ele[propsDefault.path] = ele[propsDefault.path] + '/index'
  } else {
    ele[propsDefault.children].forEach(child => {addPath(child)})
  }
}

export default {
  state: {
    roles: [],
    userInfo: getStore({ name: 'userInfo' }) || {},
    permissions: getStore({ name: 'permissions' }) || [],
    menu: getStore({ name: 'menu' }) || [],
    menuId: getStore({ name: 'menuId' }) || [],
    menuAll: getStore({ name: 'menuAll' }) || [],
    expires_in: getStore({ name: 'expires_in' }) || '',
    access_token: getStore({ name: 'access_token' }) || '',
    refresh_token: getStore({ name: 'refresh_token' }) || ''
  },
  actions: {
    // 用户名登录
    LoginByUsername ({ commit }, userInfo) {
      const user = encryption({
        key: 'chanlongcesgroup',
        type: 'AES',
        data: userInfo,
        param: ['password']
      })
      return new Promise((resolve, reject) => {
        loginByUsername(user.username, user.password, userInfo.code, userInfo.redomStr).then(response => {
          const data = response.data
          commit('SET_USER_INFO', data.user_info)
          commit('SET_PERMISSIONS', data.user_info.authorities || [])
          commit('SET_EXPIRES_IN', data.expires_in)
          commit('SET_ACCESS_TOKEN', data.access_token)
          commit('SET_REFRESH_TOKEN', data.refresh_token)
          commit('DEL_ALL_TAG')
          commit('CLEAR_LOCK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 手机号登录
    LoginByPhone ({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        loginByMobile(userInfo.mobile, userInfo.code).then(response => {
          const data = response.data
          commit('SET_USER_INFO', data.user_info)
          commit('SET_PERMISSIONS', data.user_info.authorities || [])
          commit('SET_EXPIRES_IN', data.expires_in)
          commit('SET_ACCESS_TOKEN', data.access_token)
          commit('SET_REFRESH_TOKEN', data.refresh_token)
          commit('DEL_ALL_TAG')
          commit('CLEAR_LOCK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // OpenId登录
    LoginBySocial({ commit }, param) {
      return new Promise((resolve, reject) => {
        loginBySocial(param.state, param.code).then(response => {
          const data = response.data
          commit('SET_USER_INFO', data.user_info)
          commit('SET_PERMISSIONS', data.user_info.authorities || [])
          commit('SET_EXPIRES_IN', data.expires_in)
          commit('SET_ACCESS_TOKEN', data.access_token)
          commit('SET_REFRESH_TOKEN', data.refresh_token)
          commit('DEL_ALL_TAG')
          commit('CLEAR_LOCK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 刷新token
    RefreshToken({ commit, state }) {
      return new Promise((resolve, reject) => {
        refreshToken(state.refresh_token).then(response => {
          const data = response.data
          commit('SET_EXPIRES_IN', data.expires_in)
          commit('SET_ACCESS_TOKEN', data.access_token)
          commit('SET_REFRESH_TOKEN', data.refresh_token)
          commit('CLEAR_LOCK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 登出
    LogOut({ commit }) {
      return new Promise((resolve, reject) => {
        logout().then(() => {
          //resetRouter()
          commit('SET_MENU', [])
          commit('SET_MENU_ID', {})
          commit('SET_MENU_ALL', [])
          commit('SET_ROLES', [])
          commit('SET_TAG_LIST', [])
          commit('SET_USER_INFO', {})
          commit('SET_PERMISSIONS', [])
          commit('SET_EXPIRES_IN', '')
          commit('SET_ACCESS_TOKEN', '')
          commit('SET_REFRESH_TOKEN', '')
          commit('DEL_ALL_TAG')
          commit('CLEAR_LOCK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 注销 session
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        //resetRouter()
        commit('SET_MENU', [])
        commit('SET_MENU_ID', {})
        commit('SET_MENU_ALL', [])
        commit('SET_ROLES', [])
        commit('SET_TAG_LIST', [])
        commit('SET_USER_INFO', {})
        commit('SET_PERMISSIONS', [])
        commit('SET_ACCESS_TOKEN', '')
        commit('SET_REFRESH_TOKEN', '')
        commit('DEL_ALL_TAG')
        commit('CLEAR_LOCK')
        resolve()
      })
    },
    // 获取系统菜单
    GetMenu({ commit }, options) {
      return new Promise(resolve => {
        GlobalRequest.server('/system/menu').fetch({parentId: options.id}).then((res) => {
          const type = options.type
          const data = res.data.data
          const menu = deepClone(data)
          menu.forEach(ele => { addPath(ele, true) })
          commit('SET_MENU', { type, menu })
          resolve(menu)
        })
      })
    },
    // 获取顶部菜单
    GetTopMenu () {
      return new Promise(resolve => {
        GlobalRequest.server('/system/menu').fetch({type: 'top'}).then((res) => {
          resolve(res.data.data || [])
        })
      })
    }
  },
  mutations: {
    SET_EXPIRES_IN: (state, expires_in) => {
      state.expires_in = expires_in
      setStore({ type: 'session', name: 'expires_in', content: state.expires_in })
    },
    SET_ACCESS_TOKEN: (state, access_token) => {
      state.access_token = access_token
      setStore({ type: 'session', name: 'access_token', content: state.access_token })
    },
    SET_REFRESH_TOKEN: (state, refresh_token) => {
      state.refresh_token = refresh_token
      setStore({ type: 'session', name: 'refresh_token', content: state.refresh_token })
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo
      setStore({ type: 'session', name: 'userInfo', content: state.userInfo })
    },
    SET_MENU: (state, params = {}) => {
      let { type, menu } = params
      if (type !== false) state.menu = menu
      setStore({ name: 'menu', content: menu })
      if (validateNull(menu)) return

      // 合并动态路由去重::开始
      let menuAll = state.menuAll
      menuAll = menuAll.concat(menu).reverse()
      const newMenu = []
      for (const orig of menuAll) {
        let flag = true
        for (const dest of newMenu) {
          if (orig.label === dest.label || orig.path === dest.path) {
            flag = false
          }
        }
        if (flag) newMenu.push(orig)
      }
      state.menuAll = newMenu
      // 合并动态路由去重::结束

      // 存储所有菜单
      setStore({ type: 'session', name: 'menuAll', content: state.menuAll })
    },
    SET_MENU_ID: (state, menuId) => {
      state.menuId = menuId
      setStore({ type: 'session', name: 'menuId', content: state.menuId })
    },
    SET_MENU_ALL: (state, menuAll) => {
      state.menuAll = menuAll
      setStore({ name: 'menuAll', content: state.menuAll })
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      const list = {}
      for (let i = 0; i < permissions.length; i++) {
        list[permissions[i].authority] = true
      }
      state.permissions = list
      setStore({ type: 'session',  name: 'permissions', content: list })
    }
  }
}
