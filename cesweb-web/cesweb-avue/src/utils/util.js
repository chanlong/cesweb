import { validateNull } from './validate'
import request from '@/router/router-axios'
import * as CryptoJS from 'crypto-js'

/** 替代eval */
export const evil = obj => {
  let Fn = Function
  return new Fn('return ' + obj)()
}

/** 表单序列化 */
export const serialize = data => {
  const list = []
  Object.keys(data).forEach(ele => { list.push(`${ele}=${data[ele]}`) })
  return list.join('&')
}

/** 获取对象类型 */
export const getObjType = obj => {
  var toString = Object.prototype.toString
  var map = {
    '[object Boolean]': 'boolean',
    '[object Number]': 'number',
    '[object String]': 'string',
    '[object Function]': 'function',
    '[object Array]': 'array',
    '[object Date]': 'date',
    '[object RegExp]': 'regExp',
    '[object Undefined]': 'undefined',
    '[object Null]': 'null',
    '[object Object]': 'object'
  }
  if (obj instanceof Element) {
    return 'element'
  }
  return map[toString.call(obj)]
}

/** 获取Avue主体视图的Dom元素 */
export const getViewDom = () => {
  return window.document.getElementById('avue-view').getElementsByClassName('el-scrollbar__wrap')[0]
}

/** 对象深拷贝 */
export const deepClone = data => {
  var type = getObjType(data)
  var obj
  if (type === 'array') {
    obj = []
  } else if (type === 'object') {
    obj = {}
  } else {
    return data
  }
  if (type === 'array') {
    for (var i = 0, len = data.length; i < len; i++) {
      obj.push(deepClone(data[i]))
    }
  } else if (type === 'object') {
    for (var key in data) {
      obj[key] = deepClone(data[key])
    }
  }
  return obj
}

/**
 * 设置灰度模式
 */
export const toggleGrayMode = (status) => {
  if (status) {
    document.body.className = document.body.className + ' grayMode'
  } else {
    document.body.className = document.body.className.replace(' grayMode', '')
  }
}

/**
 * 设置主题
 */
export const setTheme = (name) => {
  document.body.className = name
}

/**
 * 加密处理
 */
export const encryption = (params) => {
  let { data, type, param, key } = params
  const result = JSON.parse(JSON.stringify(data))
  if (type === 'Base64') {
    param.forEach(ele => { result[ele] = btoa(result[ele]) })
  } else {
    param.forEach(ele => {
      var data = result[ele]
      key = CryptoJS.enc.Latin1.parse(key)
      var iv = key
      // 加密
      var encrypted = CryptoJS.AES.encrypt(data, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.ZeroPadding })
      result[ele] = encrypted.toString()
    })
  }
  return result
}

/**
 * 浏览器判断是否全屏
 */
export const fullscreenToggel = () => {
  if (fullscreenEnable()) {
    exitFullScreen()
  } else {
    reqFullScreen()
  }
}

/**
 * esc监听全屏
 */
export const listenfullscreen = (callback) => {
  function listen () {
    callback()
  }
  document.addEventListener('fullscreenchange', function () {
    listen()
  })
  document.addEventListener('mozfullscreenchange', function () {
    listen()
  })
  document.addEventListener('webkitfullscreenchange', function () {
    listen()
  })
  document.addEventListener('msfullscreenchange', function () {
    listen()
  })
}

/**
 * 浏览器判断是否全屏
 */
export const fullscreenEnable = () => {
  var isFullscreen = document.isFullScreen || document.mozIsFullScreen || document.webkitIsFullScreen
  return isFullscreen
}

/**
 * 浏览器全屏
 */
export const reqFullScreen = () => {
  if (document.documentElement.requestFullScreen) {
    document.documentElement.requestFullScreen()
  } else if (document.documentElement.webkitRequestFullScreen) {
    document.documentElement.webkitRequestFullScreen()
  } else if (document.documentElement.mozRequestFullScreen) {
    document.documentElement.mozRequestFullScreen()
  }
}

/**
 * 浏览器退出全屏
 */
export const exitFullScreen = () => {
  if (document.documentElement.requestFullScreen) {
    document.exitFullScreen()
  } else if (document.documentElement.webkitRequestFullScreen) {
    document.webkitCancelFullScreen()
  } else if (document.documentElement.mozRequestFullScreen) {
    document.mozCancelFullScreen()
  }
}

/**
 * 递归寻找子类的父类
 */
export const findParent = (menu, id) => {
  for (let i = 0; i < menu.length; i++) {
    if (menu[i].children.length !== 0) {
      for (let j = 0; j < menu[i].children.length; j++) {
        if (menu[i].children[j].id === id) {
          return menu[i]
        } else {
          if (menu[i].children[j].children.length !== 0) {
            return findParent(menu[i].children[j].children, id)
          }
        }
      }
    }
  }
}

/**
 * 动态插入css
 */
export const loadStyle = url => {
  const link = document.createElement('link')
  link.type = 'text/css'
  link.rel = 'stylesheet'
  link.href = url
  const head = document.getElementsByTagName('head')[0]
  head.appendChild(link)
}

/**
 * 判断路由是否相等
 */
export const diff = (orig, dest) => {
  return orig.label === dest.label && orig.value === dest.value
}

/**
 * 根据字典的value显示label
 */
export const findByValue = (dic, value) => {
  let result = ''
  if (validateNull(dic)) return value
  if (typeof (value) === 'string' || typeof (value) === 'number' || typeof (value) === 'boolean') {
    let index = 0
    index = findArray(dic, value)
    if (index !== -1) {
      result = dic[index].label
    } else {
      result = value
    }
  } else if (value instanceof Array) {
    result = []
    let index = 0
    value.forEach(ele => {
      index = findArray(dic, ele)
      if (index !== -1) {
        result.push(dic[index].label)
      } else {
        result.push(value)
      }
    })
    result = result.toString()
  }
  return result
}

/**
 * 根据字典的value查找对应的index
 */
export const findArray = (dic, value) => {
  for (let i = 0; i < dic.length; i++) {
    if (dic[i].value === value) {
      return i
    }
  }
  return -1
}

/**
 * 生成随机len位数字
 */
export const randomLenNum = (len, date) => {
  let random = ''
  random = Math.ceil(Math.random() * 100000000000000).toString().substr(0, len || 4)
  if (date) random = random + Date.now()
  return random
}

/**
 * 打开小窗口
 */
export const openWindow = (url, title, w, h) => {
  // Fixes dual-screen position                            Most browsers       Firefox
  const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : screen.left
  const dualScreenTop = window.screenTop !== undefined ? window.screenTop : screen.top

  const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width
  const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height

  const left = ((width / 2) - (w / 2)) + dualScreenLeft
  const top = ((height / 2) - (h / 2)) + dualScreenTop
  const newWindow = window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left)

  // Puts focus on the newWindow
  if (window.focus) {
    newWindow.focus()
  }
}

/**
 *  <img> <a> src 处理
 * @returns {PromiseLike<T | never> | Promise<T | never>}
 */
export function handleImg(url, id) {
  return validateNull(url) ? null : request({
    url:  url,
    method: 'get',
    responseType: 'blob'
  }).then((response) => { // 处理返回的文件流
    const blob = response.data
    const img = document.getElementById(id)
    img.src = URL.createObjectURL(blob)
    window.setTimeout(function() {window.URL.revokeObjectURL(blob)}, 0)
  })
}

export function handleDown(filename, bucket) {
  return request({
    url: '/system/sysfile/' + bucket + '/' + filename,
    method: 'get',
    responseType: 'blob'
  }).then((response) => { // 处理返回的文件流
    const blob = response.data
    const link = document.createElement('a')
    link.href = URL.createObjectURL(blob)
    link.download = filename
    document.body.appendChild(link)
    link.click()
    window.setTimeout(function() {
      URL.revokeObjectURL(blob)
      document.body.removeChild(link)
    }, 0)
  })
}

/**
 *  根据元素类名查找同级所有同类名元素
 * @param elem
 */
export function getNodeListByLastClassName(elem, excluds) {
  // 获取元素类名
  let clazz = elem.getAttribute('class').split(' ')
  // 去除要排除的类名
  if (excluds) clazz = clazz.filter(cls => !excluds.includes(cls))
  // 返回同类名的所有同级节点
  return elem.parentNode.querySelectorAll(`.${clazz[clazz.length - 1]}`)
}

/**
 *  根据元素类名查找同级所有同类名元素
 * @param elem
 * @param keys
 */
export function getDataset(elem, keys) {
  return keys.reduce((map, key) => (map[key] = elem.dataset[key], map), {})
}
