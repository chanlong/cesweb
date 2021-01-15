import BpmnModeler from 'bpmn-js/lib/Modeler'
import cliModule from 'bpmn-js-cli'
import propertiesPanelModule from 'bpmn-js-properties-panel'
import propertiesProviderModule from 'bpmn-js-properties-panel/lib/provider/camunda'
// import camundaModdleDescriptor from 'camunda-bpmn-moddle/resources/camunda'
// import propertiesProviderModule from './designer/properties-panel/provider/activiti'
import activitiModdleDescriptor from 'activiti-bpmn-moddle/resources/activiti'

import customTranslate from './customTranslate'
import customTranslatePalette from './customTranslate/palette'
import customTranslatePaletteGroup from './customTranslate/paletteGroup'
import customControlsModule from './customControls'

const customTranslateModule = {
  translate: ['value', customTranslate]
}

export default class Modeler {

  constructor(container, processForm) {
    this.container = container
    this.processForm = processForm

    this.bpmnModeler = new BpmnModeler({
      container: container,
      cli: {
        bindTo: 'cli',
      },
      // 添加控制板
      /*propertiesPanel: {
        parent: '#js-properties-panel'
      },*/
      additionalModules: [
        cliModule,
        customControlsModule, // 自定义控制面板
        customTranslateModule, // 翻译模块
        propertiesPanelModule, // 右侧属性面板
        propertiesProviderModule // 扩展右侧属性面板
      ],
      // 模块拓展，拓展activiti的描述
      moddleExtensions: {
        // camunda: camundaModdleDescriptor
        activiti: activitiModdleDescriptor
      }
    })

    // 自定义左侧面板
    this._adjustPalette()

    this.cli = window.cli
    this.done = false
    this.canvas = this.bpmnModeler.get('canvas')
    this.modeling = this.bpmnModeler.get('modeling')
    this.overlays = this.bpmnModeler.get('overlays')
    this.eventBus = this.bpmnModeler.get('eventBus')
    this.selection = this.bpmnModeler.get('selection')
    this.zoomScroll = this.bpmnModeler.get('zoomScroll')
    this.bpmnFactory = this.bpmnModeler.get('bpmnFactory')
    this.commandStack = this.bpmnModeler.get('commandStack')
    this.elementRegistry = this.bpmnModeler.get('elementRegistry')
    this.interactionEvents = this.bpmnModeler.get('interactionEvents')
  }

  async importXML(xml) {
    try {
      if (xml) {
        const { warnings } = await this.bpmnModeler.importXML(xml)
        if (!(warnings && warnings.length > 0)) {
          this._setEncoded('XML', xml)
          this._addBpmnListener()
        }
      }
    } catch (e) {
      return console.error('could not import BPMN 2.0 diagram', e)
    }
  }

  // 保存xml
  async saveXML(download = false) {
    try {
      let { xml } = await this.bpmnModeler.saveXML({ format: true })
      xml = xml.replace(/&lt;/g, '<').replace(/&gt;/g, '>')
      let { filename } = this._setEncoded('XML', xml)
      if (download) {
        this._downloadFile(filename, xml, 'application/xml')
      }
      return xml
    } catch (e) {
      console.log(e)
    }
  }

  // 保存svg
  async saveSVG(download = false) {
    try {
      let { svg } = await this.bpmnModeler.saveSVG({ format: true })
      let { filename } = this._setEncoded('SVG', svg)
      if (download) {
        this._downloadFile(filename, svg, 'image/svg+xml')
      }
      return svg
    } catch (e) {
      console.log(e)
    }
  }

  // 获取流程元素
  getElement(elementId) {
    return this.cli.element(elementId)
  }

  // 事件监听
  on(listener, type, callback, that) {
    this[listener].on(type, callback, that)
  }

  // 添加设计器监听
  _addBpmnListener() {
    const that = this
    this.bpmnModeler.on('commandStack.changed', function() {
      that.saveXML(false)
      that.saveSVG(false)
    })
  }

  /** 设计图改变时调用 */
  _setEncoded(type, data) {
    const encodedData = encodeURIComponent(data)
    if (data) {
      if (type === 'XML') {
        this.processForm.modelXml = data
        return {
          filename: this.processForm.name + '.bpmn20.xml',
          href: "data:application/bpmn20-xml;charset=UTF-8," + encodedData,
          data: data
        }
      }
      if (type === 'SVG') {
        this.processForm.modelImage = data
        return {
          filename: this.processForm.name + '.temp.svg',
          href: "data:application/text/xml;charset=UTF-8," + encodedData,
          data: data
        }
      }
    }
  }

  // 下载文件
  _downloadFile(filename, data, type) {
    var a = document.createElement('a')
    var url = window.URL.createObjectURL(new Blob([data], { type: type }))
    a.href = url
    a.download = filename
    a.click()
    window.URL.revokeObjectURL(url)
  }

  // 调整左侧工具栏排版
  _adjustPalette() {
    try {
      // 获取 bpmn 设计器实例
      const canvas = this.container
      const djsPalette = canvas.children[0].children[1].children[4]
      const djsPalStyle = {
        top: '10px',
        left: '10px',
        bottom: '10px',
        width: '130px',
        background: 'white',
        border: '1px solid #ebeef5',
        boxShadow: '1px 2px 5px 1px rgba(0, 0, 0, 0.08)',
        borderRadius: '4px',
        overflowY: 'auto'
      }
      for (var key in djsPalStyle) {
        djsPalette.style[key] = djsPalStyle[key]
      }

      // 重新构造左侧菜单
      const palette = djsPalette.children[0]
      const cloneNode = djsPalette.children[0].cloneNode(true)
      const allGroups = cloneNode.children
      const accordion = document.createElement('ul')

      palette.innerHTML = ''
      allGroups[1].className = allGroups[1].className + " open"
      allGroups.forEach((group, i) => {
        // 为左侧菜单图标增加文字标签
        for (var cKey in group.children) {
          const control = group.children[cKey]
          const controlStyle = {
            display: 'flex',
            justifyContent: 'flex-start',
            alignItems: 'center',
            width: '100%',
            padding: '5px'
          }
          if (control.dataset && control.className && control.className.indexOf('entry') !== -1) {
            const controlProps = new customTranslatePalette().getControl(control.dataset.action)
            control.innerHTML = `<div class="palette-item-label">${controlProps['title']}</div>`
            for (var csKey in controlStyle) {
              control.style[csKey] = controlStyle[csKey]
            }
          }
        }
        // 将左侧菜单项包装到列表元素中
        let node = group.cloneNode(true)
        let li = document.createElement('li')
        let a = document.createElement('a')
        let text = document.createTextNode(customTranslatePaletteGroup[node.dataset.group])
        let icon = document.createElement('i')
        icon.className = 'el-icon-arrow-right'
        a.appendChild(text).parentNode.appendChild(icon).parentNode.className = 'palette-item-title'
        a.addEventListener('click', function(e) {
          let groups = document.getElementsByClassName('group')
          groups.forEach((item, i) => {
            item.className = item.className.replace('open', '')
          })
          node.className = node.className + " open"
        })
        li.appendChild(a).parentNode.appendChild(node)
        accordion.appendChild(li)
      })

      palette.appendChild(accordion);
    } catch (e) {
      console.log(e)
    }
  }
}

function getAttrs(businessObject) {
  return businessObject.$attrs
}

function getProperty(node) {
  let attrs = getAttrs(node)
  if(attrs){
    return JSON.parse(attrs.PROPERTY)
  }
}
