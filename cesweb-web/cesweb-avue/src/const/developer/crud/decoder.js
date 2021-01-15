import { option } from './index'
import { dicList } from './dict'
import { deepClone } from '@/utils/util'

export const crudDecoder = {
  getOptionKV: function() {
    let kvObj = new Object()
    let selectTypeList = new Array()
    option.group.forEach(ele => {
      if (Object.prototype.hasOwnProperty.call(ele, "column")) {
        ele.column.forEach(e => {
          kvObj[e.prop] = ""
          if (e.type === 'select') {
            selectTypeList.push(e.prop)
          }
        })
      }
    })
    selectTypeList.forEach(e => { kvObj['$' + e] = "" })
    return kvObj
  },

  // 根据输入框类型填充
  prefillDicList: (ele) => {
    if (Object.prototype.hasOwnProperty.call(ele, "type") && ele.type.length > 0) {
      for (let i = 0; i < dicList.length; i++) {
        let item = dicList[i]
        if (item.value === ele.type) {
          ele['$' + ele.type] = item.label
          break
        }
      }
    } else {
      ele['type'] = 'input'
      ele['$type'] = "输入框"
    }

    Object.keys(ele).forEach(e => {
      if (e.includes('$')) {
        let key = e.replace("$", "")
        if (ele[key] === 'true') {
          ele[e] = '是'
        } else if (ele[key] === 'true') {
          ele[e] = '否'
        }
      }
    })
  },

  // boolAsStr bool 是否按字符串处理, 默认不按字符处理
  decode: function(opt, boolAsStr = false) {
    if (Object.keys(opt).length == 0) return
    var column = opt.column
    let objList = new Array()
    let optKV = this.getOptionKV()
    for (var i = 0; i < column.length; i++) {
      var e = column[i]
      let obj = deepClone(optKV)
      Object.keys(e).forEach(key => {
        // boolean 转为字符串方式，不然form的select报错
        if (boolAsStr && (e[key] === true || e[key] === false)) {
          e[key] = "" + e[key]
        }
        obj[key] = e[key]
      })
      this.prefillDicList(obj)
      objList.push(obj)
    }
    return objList
  }
}
