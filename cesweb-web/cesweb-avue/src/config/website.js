/** 全局配置文件 */
export default {
  key: 'chanlongcesgroup',
  logo: 'C',
  title: 'CES',
  indexTitle: '快速开发平台',
  lockPage: '/lock',
  tokenTime: 6000,
  authorization: 'Authorization',
  statusWhiteList: [400],
  isFirstPage: false, // 配置首页不可关闭
  fistPage: {
    meta: { i18n: 'dashboard' },
    label: '首页',
    value: '/home/index',
    close: false,
    query: {},
    group: [],
    params: {}
  },
  // 配置菜单的属性
  menu: {
    iconDefault: 'icon-caidan',
    props: {
      path: 'path',
      icon: 'icon',
      label: 'label',
      children: 'children'
    }
  }
}
