//元素和组件的映射表
import ElementType from './ElementType'
var ElementCompantMap = {}

// 属性组件
// 流程
ElementCompantMap[ElementType.process] = 'processProps'
ElementCompantMap[ElementType.sequenceFlow] = 'sequenceFlowProps'

// 事件
ElementCompantMap[ElementType.endEvent] = 'endEventProps'
ElementCompantMap[ElementType.startEvent] = 'startEventProps'
ElementCompantMap[ElementType.throwEvent] = 'intermediateThrowEventProps'

// 网关
ElementCompantMap[ElementType.exclusiveGateway] = 'exclusiveGatewayProps'
ElementCompantMap[ElementType.inclusiveGateway] = 'inclusiveGatewayProps'
ElementCompantMap[ElementType.parallelGateway] = 'parallelGatewayProps'

// 活动
ElementCompantMap[ElementType.userTask] = 'userTaskProps'
ElementCompantMap[ElementType.serviceTask] = 'serviceTaskProps'

// 只读设计器
ElementCompantMap[ElementType.startEventReadonly] = 'StartEventPropsReadonly'
ElementCompantMap[ElementType.endEventReadonly] = 'EndEventPropsReadonly'
ElementCompantMap[ElementType.throwEventReadonly] = 'IntermediateThrowEventPropsReadonly'
ElementCompantMap[ElementType.exclusiveGatewayReadonly] = 'ExclusiveGatewayPropsReadonly'
ElementCompantMap[ElementType.inclusiveGatewayReadonly] = 'InclusiveGatewayPropsReadonly'
ElementCompantMap[ElementType.parallelGatewayReadonly] = 'ParallelGatewayPropsReadonly'
ElementCompantMap[ElementType.sequenceFlowReadonly] = 'SequenceFlowPropsReadonly'
ElementCompantMap[ElementType.userTaskReadonly] = 'UserTaskPropsReadonly'
ElementCompantMap[ElementType.callActivityReadonly] = 'CallActivityPropsReadonly'

export default ElementCompantMap
