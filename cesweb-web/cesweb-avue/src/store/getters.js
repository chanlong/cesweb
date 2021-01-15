/**
 * modify by chanlong on 2020-07-20
 * reference by: avue-admin & pigx-ui
 */
export default {
  screen: state => state.comm.screen,
  website: state => state.comm.website,
  language: state => state.comm.language,
  colorName: state => state.comm.colorName,
  theme: state => state.comm.theme,
  themeName: state => state.comm.themeName,
  lockPasswd: state => state.comm.lockPasswd,
  isLock: state => state.comm.isLock,
  isMenu: state => state.comm.isMenu,
  isShade: state => state.comm.isShade,
  isCollapse: state => state.comm.isCollapse,
  isFullScreen: state => state.comm.isFullScreen,
  tag: state => state.tags.tag,
  tagHome: state => state.tags.tagHome,
  tagList: state => state.tags.tagList,
  menu: state => state.user.menu,
  token: state => state.user.token,
  roles: state => state.user.roles,
  menuId: state => state.user.menuId,
  menuAll: state => state.user.menuAll,
  userInfo: state => state.user.userInfo,
  permissions: state => state.user.permissions,
  expires_in: state => state.user.expires_in,
  access_token: state => state.user.access_token,
  refresh_token: state => state.user.refresh_token,
  logsList: state => state.logs.logsList,
  logsLen: state => state.logs.logsList.length || 0,
  logsFlag: (state, getters) => getters.logsLen === 0,
  keyCollapse: (state, getters) => getters.screen > 1 ? getters.isCollapse : false
}
