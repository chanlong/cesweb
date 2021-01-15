const BPMN_PREFIX = 'bpmn:'
const CUSTOM_PROFIX = 'activiti:'

const ELEMETN_TYPE = {
  // 以下为 bpmn规范的元素
  group: BPMN_PREFIX + 'Group',
  process: BPMN_PREFIX + 'Process',
  sequenceFlow: BPMN_PREFIX + 'SequenceFlow',
  documentation: BPMN_PREFIX + 'Documentation',
  collaboration: BPMN_PREFIX + 'Collaboration',
  textAnnotation: BPMN_PREFIX + 'TextAnnotation',

  // 事件
  throwEvent: BPMN_PREFIX + 'IntermediateThrowEvent',
  startEvent: BPMN_PREFIX + 'StartEvent',
  endEvent: BPMN_PREFIX + 'EndEvent',

  // 网关
  exclusiveGateway: BPMN_PREFIX + 'ExclusiveGateway',
  inclusiveGateway: BPMN_PREFIX + 'InclusiveGateway',
  parallelGateway: BPMN_PREFIX + 'ParallelGateway',

  // 活动
  userTask: BPMN_PREFIX + 'UserTask',
  serviceTask: BPMN_PREFIX + 'ServiceTask',

  // 以下为activti的拓展元素
  listener: CUSTOM_PROFIX + 'Listener',

  // 只读设计器
  startEventReadonly: BPMN_PREFIX + 'StartEventReadonly',
  endEventReadonly: BPMN_PREFIX + 'EndEventReadonly',
  throwEventReadonly: BPMN_PREFIX + 'IntermediateThrowEventReadonly',
  exclusiveGatewayReadonly: BPMN_PREFIX + 'ExclusiveGatewayReadonly',
  inclusiveGatewayReadonly: BPMN_PREFIX + 'InclusiveGatewayReadonly',
  parallelGatewayReadonly: BPMN_PREFIX + 'ParallelGatewayReadonly',
  sequenceFlowReadonly: BPMN_PREFIX + 'SequenceFlowReadonly',
  userTaskReadonly: BPMN_PREFIX + 'UserTaskReadonly',
  callActivityReadonly: BPMN_PREFIX + 'CallActivityReadonly',

}

export default ELEMETN_TYPE
