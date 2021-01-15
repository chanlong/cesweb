let RouterPlugin = function() {
  this.$router = null
  this.$store = null
}

RouterPlugin.install = function(vue, option = {}) {
  this.$router = option.router
  this.$store = option.store
  this.$vue = new vue({ i18n: option.i18n })

  function isURL(s) {
    if (s.includes('html')) return true
    return /^http[s]?:\/\/.*/.test(s)
  }

  function objToform(obj) {
    let result = []
    Object.keys(obj).forEach(ele => {result.push(`${ele}=${obj[ele]}`)})
    return result.join('&')
  }

  this.$router.$routerPlugin = {
    safe: this,
    meta: {},
    group: '',
    // 全局配置
    $website: this.$store.getters.website,
    // 路由表
    routerList: [],
    // 设置标题
    setTitle: (title) => {
      const defaultTitle = this.$vue.$t('title')
      title = title ? `${title}——${defaultTitle}` : defaultTitle
      document.title = title
    },
    // 关闭标签
    closeTag: (value) => {
      let tag = value || this.$store.getters.tag
      if (typeof value === 'string') {
        tag = this.$store.getters.tagList.filter(ele => ele.value === value)[0]
      }
      this.$store.commit('DEL_TAG', tag)
    },
    // 生成标题
    generateTitle: (title, key) => {
      if (!key) return title
      const hasKey = this.$vue.$te('route.' + key)
      if (hasKey) {
        // $t :this method from vue-i18n, inject in @/lang/index.js
        return this.$vue.$t('route.' + key)
      }
      return title
    },
    // 处理路由
    getPath: function(params = {}, meta = {}) {
      let { src } = params
      let result = src || '/'
      if (isURL(src)) {
        result = `/myiframe/urlPath?${objToform(Object.assign(meta, params))}`
      }
      return result
    },
    // 正则处理路由
    vaildPath: function(list, path) {
      let result = false
      list.forEach(ele => {
        if (new RegExp("^" + ele + ".*", "g").test(path)) {
          result = true
        }
      })
      return result
    },
    // 设置路由值
    getValue: function(route) {
      return route.query.src ? route.query.src : route.path
    },
    // 动态路由
    formatRoutes: function(aMenu = [], first) {
      const aRouter = []
      const propsConfig = this.$website.menu.props
      const propsDefault = {
        path: propsConfig.path || 'path',
        icon: propsConfig.icon || 'icon',
        meta: propsConfig.meta || 'meta',
        label: propsConfig.label || 'label',
        children: propsConfig.children || 'children'
      }
      if (aMenu.length === 0) return
      // 添加动态路由::开始
      for (let i = 0; i < aMenu.length; i++) {
        const oMenu = aMenu[i]
        // 判断如果路由表中是否已存在
        if (this.routerList.includes(oMenu[propsDefault.path])) return
        // 定义并获取路由属性
        const path = (() => first ? oMenu[propsDefault.path].replace('/index', '') : oMenu[propsDefault.path])()
        const meta = Object.assign(oMenu[propsDefault.meta] || {}, { keepAlive: Number(oMenu['keepAlive']) === 1 })
        const name = oMenu[propsDefault.label]
        const icon = oMenu[propsDefault.icon]
        const children = oMenu[propsDefault.children]
        const component = 'views' + oMenu.path //特殊处理组件
        const isChild = children && children.length !== 0
        // 创建路由对象
        const oRouter = {
          component (resolve) {
            // 判断是否为首路由
            if (first) {
              require(['../pages/index'], resolve)
              return
            }
            // 判断是否为多层路由
            else if (isChild && !first) {
              require(['../pages/index/layout'], resolve)
              return
            }
            // 判断是否为最终的页面视图
            else {
              require([`../${component}.vue`], resolve)
            }
          },
          meta: meta,
          name: name,
          icon: icon,
          path: path,
          redirect: (() => {return !isChild && first && !isURL(path) ? `${path}/index` : ''})(),
          // 处理是否为一级路由::开始
          children: !isChild ? (() => {
            if (first) {
              if (!isURL(path)) oMenu[propsDefault.path] = `${path}/index`
              return [{
                meta: meta,
                name: name,
                icon: icon,
                path: 'index',
                component (resolve) { require([`../${component}.vue`], resolve) }
              }]
            }
            return []
          })() : (() => {return this.formatRoutes(children, false)})()
          // 处理是否为一级路由::结束
        }
        aRouter.push(oRouter)
      }
      // 添加动态路由::结束

      if (first) {
        if (!this.routerList.includes(aRouter[0][propsDefault.path])) {
          // 添加到动态路由
          this.safe.$router.addRoutes(aRouter)
          // 添加到路由表
          this.routerList.push(aRouter[0][propsDefault.path])
        }
      } else {
        return aRouter
      }
    }
  }
}
export default RouterPlugin
