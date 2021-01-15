import { debounce } from 'min-dash'

export default {
  /**
   * 函数防抖动
   */
  debounce: debounce((callback) => {
    callback()
  }, 500),
  /**
   * 处对象中的空值，将空值改为null
   *
   * @param {Object} properties 属性对象
   */
  filter(properties) {
    let obj = {}
    if (properties) {
      for (var key in properties) {
        // properties[key] instanceof Boolean ? properties[key]
        //console.log(properties[key]);
        obj[key] = properties[key]
                 ? properties[key] instanceof Array
                 ? properties[key].join(',')
                 : properties[key] instanceof Boolean
                 ? properties[key]
                 : properties[key]
                 : null
      }
    }
    return obj
  },
  setProperty(object, propName, propValue) {
    object[propName] = propValue ? propValue : null
  },
  /**
   * 设置面板属性
   * @param {Object} object 对象
   * @param {Object} properties 属性对象
   */
  setProperties(object, properties, reverse) {
    if (object && properties) {
      if (reverse) {
        for (var key in properties) {
          this.setProperty(object, key, properties[key])
        }
      } else {
        for (var key in object) {
          this.setProperty(object, key, properties[key])
        }
      }
    }
  },
  /**
   * 获取脚本类型
   * @param {Object} type
   */
  getScriptTypes(type) {
    return {
      script: '内联脚本',
      scriptResource: '外部资源'
    }
  },
  /**
   * 获取监听器类型键值对象
   * @param {Object} object
   * @param {Object} type
   */
  getListenerType(object) {
    const types = Object.keys(listenerType)
    for (var key in object) {
      if (types.includes(key)) {
        return {
          key: key,
          val: object[key]
        }
      }
    }
  },

  getListenerTypes() {
    return listenerType
  },

  getEventType(type, index) {
    return index !== undefined ? EventType[type][index] : EventType[type]
  }
}

const EventType = {
  task: ['create', 'assignment', 'complete', 'delete'],
  execution: ['start', 'end']
}

const listenerType = {
  'class': 'Java类',
  'script': '脚本',
  'expression': '表达式',
  'delegateExpression': '代理表达式'
}
