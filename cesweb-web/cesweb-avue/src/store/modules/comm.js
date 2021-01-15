import { setStore, getStore, removeStore } from '@/utils/store'
import website from '@/config/website'

const comm = {
  state: {
    screen: -1,
    website: website,
    language: getStore({ name: 'language' }) || 'zh',
    isLock: getStore({ name: 'isLock' }) || false,
    isMenu: true,
    isShade: false,
    isCollapse: false,
    isFullScreen: false,
    showTag: getStore({ name: 'showTag' }) || true,
    showLock: getStore({ name: 'showLock' }) || true,
    showMenu: getStore({ name: 'showMenu' }) || true,
    showTheme: getStore({ name: 'showTheme' }) || true,
    showColor: getStore({ name: 'showColor' }) || true,
    showDebug: getStore({ name: 'showDebug' }) || true,
    showSearch: getStore({ name: 'showSearch' }) || true,
    showRefresh: getStore({ name: 'showRefresh' }) || true,
    showCollapse: getStore({ name: 'showCollapse' }) || true,
    showFullScreen: getStore({ name: 'showFullScreen' }) || true,
    theme: getStore({ name: 'theme' }) || '#409EFF',
    themeName: getStore({ name: 'themeName' }) || 'default',
    colorName: getStore({ name: 'colorName' }) || '#409EFF',
    lockPasswd: getStore({ name: 'lockPasswd' }) || ''
  },

  mutations: {
    SET_LANGUAGE: (state, language) => {
      state.language = language
      setStore({ name: 'language', content: state.language })
    },
    SET_LOCK: (state) => {
      state.isLock = true
      setStore({ name: 'isLock', type: 'session', content: state.isLock })
    },
    SET_MENU: (state, menu) => {
      state.isMenu = menu.type
    },
    SET_SHADE: (state, active) => {
      state.isShade = active
    },
    SET_SCREEN: (state, screen) => {
      state.screen = screen
    },
    SET_COLLAPSE: (state) => {
      state.isCollapse = !state.isCollapse
    },
    SET_FULLSCREEN: (state) => {
      state.isFullScreen = !state.isFullScreen
    },
    SET_SHOW_TAG: (state, active) => {
      state.showTag = active
      setStore({ name: 'showTag', content: state.showTag })
    },
    SET_SHOW_LOCK: (state, active) => {
      state.showLock = active
      setStore({ name: 'showLock', content: state.showLock })
    },
    SET_SHOW_MENU: (state, active) => {
      state.showMenu = active
      setStore({ name: 'showMenu', content: state.showMenu })
    },
    SET_SHOW_THEME: (state, active) => {
      state.showTheme = active
      setStore({ name: 'showTheme', content: state.showTheme })
    },
    SET_SHOW_COLOR: (state, active) => {
      state.showColor = active
      setStore({ name: 'showColor', content: state.showColor })
    },
    SET_SHOW_DEBUG: (state, active) => {
      state.showDebug = active
      setStore({ name: 'showDebug', content: state.showDebug })
    },
    SET_SHOW_SEARCH: (state, active) => {
      state.showSearch = active
      setStore({ name: 'showSearch', content: state.showSearch })
    },
    SET_SHOW_REFRESH: (state, active) => {
      state.showRefresh = active
      setStore({ name: 'showRefresh', content: state.showRefresh })
    },
    SET_SHOW_COLLAPSE: (state, active) => {
      state.showCollapse = active
      setStore({ name: 'showCollapse', content: state.showCollapse })
    },
    SET_SHOW_FULL_SCREEN: (state, active) => {
      state.showFullScreen = active
      setStore({ name: 'showFullScreen', content: state.showFullScreen })
    },
    SET_THEME: (state, theme) => {
      state.theme = theme
      setStore({ name: 'theme', content: state.theme })
    },
    SET_THEME_NAME: (state, themeName) => {
      state.themeName = themeName
      setStore({ name: 'themeName', content: state.themeName })
    },
    SET_COLOR_NAME: (state, colorName) => {
      state.colorName = colorName
      setStore({ name: 'colorName', content: state.colorName })
    },
    SET_LOCK_PASSWD: (state, lockPasswd) => {
      state.lockPasswd = lockPasswd
      setStore({ name: 'lockPasswd', type: 'session', content: state.lockPasswd })
    },
    CLEAR_LOCK: (state) => {
      state.isLock = false
      state.lockPasswd = ''
      removeStore({ name: 'lockPasswd', type: 'session' })
      removeStore({ name: 'isLock', type: 'session' })
    }
  }
}

export default comm
