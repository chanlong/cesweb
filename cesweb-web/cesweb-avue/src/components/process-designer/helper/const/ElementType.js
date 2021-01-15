'use strict';

const BPMN_PREFIX = 'bpmn:';
const CUSTOM_PROFIX = 'activiti:';

const ELEMETN_TYPE = {
  // 以下为 bpmn规范的元素
  collaboration: BPMN_PREFIX + 'Collaboration',
  textAnnotation: BPMN_PREFIX + 'TextAnnotation',
  group: BPMN_PREFIX + 'Group',
  process: BPMN_PREFIX + 'Process',
  documentation: BPMN_PREFIX + 'Documentation',

  // 事件元素
  startEvent: BPMN_PREFIX + 'StartEvent',
  endEvent: BPMN_PREFIX + 'EndEvent',
  throwEvent: BPMN_PREFIX + 'IntermediateThrowEvent',

  // 网关元素
  exclusiveGateway: BPMN_PREFIX + 'ExclusiveGateway',
  inclusiveGateway: BPMN_PREFIX + 'InclusiveGateway',
  parallelGateway: BPMN_PREFIX + 'ParallelGateway',
  sequenceFlow: BPMN_PREFIX + 'SequenceFlow',

  // 活动元素
  userTask: BPMN_PREFIX + 'UserTask',
  serviceTask: BPMN_PREFIX + 'ServiceTask',

  // 以下为activti的拓展元素
  listener: CUSTOM_PROFIX + 'Listener',

};
export default ELEMETN_TYPE;
