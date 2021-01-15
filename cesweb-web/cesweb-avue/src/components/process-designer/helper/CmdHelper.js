'use strict';

var CmdHelper = {};
module.exports = CmdHelper;

/**
 * 更新属性
 * 
 * @param {Object} element
 * @param {Object} properties
 */
CmdHelper.updateProperties = function(element, properties) {
  return {
    cmd: 'element.updateProperties',
    context: {
      element: element,
      properties: properties
    }
  };
};

/**
 * 更新bo
 * 
 * @param {Object} element
 * @param {Object} businessObject
 * @param {Object} newProperties
 */
CmdHelper.updateBusinessObject = function(element, businessObject, newProperties) {
  return {
    cmd: 'properties-panel.update-businessobject',
    context: {
      element: element,
      businessObject: businessObject,
      properties: newProperties
    }
  };
};

/**
 * 添加元素
 * 
 * @param {Object} element
 * @param {Object} businessObject
 * @param {Object} objectsToAdd
 * @param {Object} listPropertyName
 */
CmdHelper.addElementsTolist = function(element, businessObject, listPropertyName, objectsToAdd) {
  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      objectsToAdd: objectsToAdd
    }
  };
};

/**
 * 删除元素
 * 
 * @param {Object} element
 * @param {Object} businessObject
 * @param {Object} listPropertyName
 * @param {Object} referencePropertyName
 * @param {Object} objectsToRemove
 */
CmdHelper.removeElementsFromList = function(element, businessObject, listPropertyName, referencePropertyName, objectsToRemove) {
  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      referencePropertyName: referencePropertyName,
      objectsToRemove: objectsToRemove
    }
  };
};

/**
 * 添加新元素并删除旧的指定元素
 * 
 * @param {Object} element
 * @param {Object} businessObject
 * @param {Object} listPropertyName
 * @param {Object} referencePropertyName
 * @param {Object} objectsToAdd
 * @param {Object} objectsToRemove
 */
CmdHelper.addAndRemoveElementsFromList = function(element, businessObject, listPropertyName, referencePropertyName,  objectsToAdd, objectsToRemove) {
  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      referencePropertyName: referencePropertyName,
      objectsToAdd: objectsToAdd,
      objectsToRemove: objectsToRemove
    }
  };
};

/**
 * 设置列表
 * 
 * @param {Object} element
 * @param {Object} businessObject
 * @param {Object} updatedObjectList
 * @param {Object} listPropertyName
 */
CmdHelper.setList = function(element, businessObject, listPropertyName, updatedObjectList) {
  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      updatedObjectList: updatedObjectList
    }
  };
};
