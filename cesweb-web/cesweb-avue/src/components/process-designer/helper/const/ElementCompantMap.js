'use strict';

// 元素和组件的映射表
import ElementType from './ElementType';

var ElementCompantMap = {};

// 流程属性组件
ElementCompantMap[ElementType.process] = 'ProcessGeneral';

// 事件属性组件
ElementCompantMap[ElementType.startEvent] = 'StartEventGeneral';
ElementCompantMap[ElementType.endEvent] = 'EndEventGeneral';
ElementCompantMap[ElementType.throwEvent] = 'IntermediateThrowEventGeneral';

// 网关属性组件
ElementCompantMap[ElementType.exclusiveGateway] = 'ExclusiveGatewayGeneral';
ElementCompantMap[ElementType.inclusiveGateway] = 'InclusiveGatewayGeneral';
ElementCompantMap[ElementType.parallelGateway] = 'ParallelGatewayGeneral';
ElementCompantMap[ElementType.sequenceFlow] = 'SequenceFlowGeneral';

// 活动属性组件
ElementCompantMap[ElementType.userTask] = 'UserTaskGeneral';
ElementCompantMap[ElementType.serviceTask] = 'ServiceTaskGeneral';

export default ElementCompantMap;
