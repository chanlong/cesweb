'use strict';

/**
 * bpmn-js的帮助类
 */
import ElementCompantMap from './const/ElementCompantMap';
import ElementHelper from './ElementHelper';

import bpmnUtil from '@/components/process-designer/utils/bpmnUtil';

const { is, getBusinessObject } = require('bpmn-js/lib/util/ModelUtil');

export default {
  /**
   * 获取bpmn初始化模版
   */
  getBpmnTempate() {
    return `<?xml version="1.0" encoding="UTF-8"?>
    <bpmn2:definitions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xsi:schemaLocation="http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd" id="sample-diagram" targetNamespace="http://activiti.org/bpmn">
      <bpmn2:process id="Process_1" isExecutable="false">
        <bpmn2:startEvent id="StartEvent_1"/>
      </bpmn2:process>
      <bpmndi:BPMNDiagram id="BPMNDiagram_1">
        <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_1">
          <bpmndi:BPMNShape id="_BPMNShape_StartEvent_2" bpmnElement="StartEvent_1">
            <dc:Bounds height="36.0" width="36.0" x="412.0" y="240.0"/>
          </bpmndi:BPMNShape>
        </bpmndi:BPMNPlane>
      </bpmndi:BPMNDiagram>
    </bpmn2:definitions>`
  },

  /**
   * 判断元素类型
   * @param {Object} type
   * @param {Object} element
   */
  is(element, type) {
    return is(element, type)
  },
  /**
   * 设置当前元素对象
   * @param {Object} vm       vue实例
   * @param {Object} element  当前选中的元素
   */
  setElement(vm, element) {
    vm.tabName = 'general';
    vm.element = element === undefined ? vm.bpmnModeler.canvas.getRootElement() : element;
    vm.general = this.getComponentByElementType(element.type);
  },

  setExtensionElements(element, extension) {
    const extensionElements = this.getBo(element).get('extensionElements')
    extensionElements.get('values').forEach(item => {
      if (this.is(item, 'activiti:ExecutionListener')) {
        let type = bpmnUtil.getListenerType(item)
        if (type.val instanceof Object) {
          if (this.is(type.val, 'activiti:Script')) {
            extension.executionListeners.push({
              eventType: item.event,
              listenerType: type.key,
              scriptType: 'script',
              scriptValue: type.val.value,
              scriptFormat: type.val.scriptFormat,
            })
          } else {
            extension.executionListeners.push({
              eventType: item.event,
              listenerType: type.key,
              scriptType: 'scriptResource',
              scriptValue: type.val.value,
              scriptFormat: type.val.scriptFormat,
            })
          }
        } else {
          extension.executionListeners.push({
            eventType: item.event,
            listenerType: type.key,
            listenerValue: type.val
          })
        }
      }
    })
  },

  isImplicitRoot(element) {
    return element.id === '__implicitroot';
  },

  /**
   * 更新元素属性
   * @param {Object} element    当前选中的元素
   * @param {Object} properties 属性对象
   */
  updateProperties(element, properties) {
    let bo = getBusinessObject(element);
    Object.keys(properties).forEach(key => {
      bo.set(key, properties[key]);
    });
  },

  /**
   * 通过命令更新属性
   * @param {Object} element      当前选中的元素
   * @param {Object} commandStack bpmn命令对象
   * @param {Object} properties   属性对象
   */
  updatePropertiesByCmd(element, commandStack, properties) {
    commandStack.execute('element.updateProperties', {
      element: element,
      properties: properties
    });
  },

  /**
   * 获取元素的属性
   * @param {Object} element 当前选中的元素
   * @param {Array} propNames 属性名数组
   * @return {type} 元素属性对象
   */
  getProperties(element, propNames) {
    let bo = this.getBo(element);
    let properties = {};
    for (let propName of propNames) {
      properties[propName] = bo.get(propName);
    }
    return properties;
  },

  /**
   * 获取单元素属性
   * @param {Object} element 当前选中的元素
   * @param {Object} propName 属性名
   * @return {type} 属性值
   */
  getProperty(element, propName) {
    let bo = this.getBo(element);
    return bo.get(propName);
  },

  /**
   * 设置单个元素属性
   * @param {Object} element 当前选中的元素
   * @param {Object} propName 属性名
   * @param {Object} propValue 属性值
   */
  setProperty(element, propName, propValue) {
    let bo = this.getBo(element);
    bo.set(propName, propValue);
  },

  /**
   * 获取元素的业务对象
   * @param {Object} element 当前选中的元素
   * @return {type} 业务对象
   */
  getBo(element) {
    return getBusinessObject(element);
  },

  /**
   * 获取组件
   * @param {Object} type  元素类型
   * @return {type} 组件名称
   */
  getComponentByElementType(type) {
    return ElementCompantMap[type];
  },

  getExtensionElements(bpmnModeler, element) {
    const bpmnFactory = bpmnModeler.bpmnFactory;
    const extensionElements = this.getBo(element).get('extensionElements');
    return extensionElements ? extensionElements : ElementHelper.createElement('bpmn:ExtensionElements', null, element, bpmnFactory);
  },

  /**
   * 创建一个元素
   * @param {Object} bpmnModeler  bpmnModler实例
   * @param {String} elementName  元素名称
   * @param {Object} properties   元素属性对象
   * @return {type} bpmn元素
   */
  createElement(bpmnModeler, elementName, properties) {
    const bpmnFactory = bpmnModeler.bpmnFactory;
    return bpmnFactory.create(elementName, properties);
  },

  /**
   * 创建ExtensionElements
   * @param {Object} bpmnModeler  bpmnModler实例
   * @param {Object} element      当前选中的元素
   */
  createExtensionElements(bpmnModeler, element) {
    const bpmnFactory = bpmnModeler.bpmnFactory;

    let extensionElements = this.getBo(element).get('extensionElements');

    // 若“extensionElements”未定义，则先创建
    if (!extensionElements) {
      extensionElements = ElementHelper.createElement('bpmn:ExtensionElements', null, element, bpmnFactory);
    }

    // 清除“extensionElements”的旧值
    for (let i = 0, length = extensionElements.get('values').length; i < length; i++) {
      extensionElements.get('values').pop();
    }

    return extensionElements;
  },

  /**
   * 判断当前元素是否包含ExtensionElements
   * @param {Object} element 当前元素
   */
  hasExtensionElements(element) {
    let extensionElements = this.getBo(element).get('extensionElements');
    return extensionElements !== undefined;
  },

  /**
   * 创建监听元素
   * @param {Object} type         监听类型
   * @param {Object} bpmnModeler  bpmnModler实例
   * @param {Object} listener     监听对象
   */
  createListener(type, bpmnModeler, listener) {
    var scriptFormat = listener.scriptFormat,
        parameterValue,
        parameterDefinition;

    // 监听类型为脚本时
    if (listener.listenerType === 'script') {
      // 脚本类型为内联脚本
      if(listener.scriptType === 'script') {
        parameterDefinition = this.createElement(bpmnModeler, 'activiti:Script', {
          scriptFormat: scriptFormat,
          value: listener.scriptValue
        })
      }
      // 脚本类型为外部资源
      else {
        parameterDefinition = this.createElement(bpmnModeler, 'activiti:Script', {
          scriptFormat: scriptFormat,
          resource: listener.scriptResourceValue
        })
      }
    }
    // 监听类型非脚本时
    else {
      parameterValue = listener.listenerValue
    }

    // 创建“taskListener”
    const extensionListener = this.createElement(bpmnModeler, 'activiti:' + type, {
      event: listener.eventType,
      script: parameterDefinition
    })

    // 排除监听类型为脚本
    if (listener.listenerType !== 'script') {
      this.setProperty(extensionListener, listener.listenerType, parameterValue)
    }

    return extensionListener;
  },

  /**
   * 添加监听元素
   * @param {Object} type         监听类型
   * @param {Object} bpmnModeler  bpmnModler实例
   * @param {Object} element      当前元素
   * @param {Object} listener     监听对象
   */
  addListener(type, bpmnModeler, element, listener) {
    const extensionElements = this.getBo(element).get('extensionElements');
    const extensionElementsValues = extensionElements.get("values");
    const extensionListener = this.createListener(type, bpmnModeler, listener);
    const thisListenerIndex = extensionElementsValues.findIndex(item => { return item.event === extensionListener.event });

    if (thisListenerIndex > -1) {
      extensionElementsValues.splice(thisListenerIndex, 1, extensionListener);
    } else {
      extensionElementsValues.push(extensionListener);
    }

    return extensionElements;
  },

  /**
   * 删除监听元素
   * @param {Object} type         监听类型
   * @param {Object} bpmnModeler  bpmnModler实例
   * @param {Object} element      当前元素
   * @param {Object} listener     监听对象
   */
  removeListener(type, bpmnModeler, element, listener) {
    const extensionElements = this.getBo(element).get('extensionElements');
    const extensionElementsValues = extensionElements.get("values");

    const thisListenerIndex = extensionElementsValues.findIndex(item => { return item.event === listener.eventType })

    if (thisListenerIndex > -1) {
      extensionElementsValues.splice(thisListenerIndex, 1);
    }

    return extensionElements;
  }
};
