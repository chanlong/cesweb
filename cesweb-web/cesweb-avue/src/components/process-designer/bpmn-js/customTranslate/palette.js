/**
 * 存储流程设计相关参数
 */
export default class BpmData {
  constructor() {
    this.controls = [] // 设计器控件
    this.init()
  }

  init() {
    this.controls = [
      {
        action: 'hand-tool',
        title: '抓手工具'
      },
      {
        action: 'lasso-tool',
        title: '套索工具'
      },
      {
        action: 'space-tool',
        title: '空间工具'
      },
      {
        action: 'global-connect-tool',
        title: '连接工具'
      },
      {
        action: 'create.start-event',
        title: '开始事件'
      },
      {
        action: 'create.intermediate-event',
        title: '中间事件'
      },
      {
        action: 'create.end-event',
        title: '结束事件'
      },
      {
        action: 'create.exclusive-gateway',
        title: '排他网关'
      },
      {
        action: 'create.task',
        title: '任务'
      },
      {
        action: 'create.user-task',
        title: '用户任务'
      },
      {
        action: 'create.service-task',
        title: '自动任务'
      },
      {
        action: 'create.call-activity',
        title: '调用活动'
      },
      {
        action: 'create.user-sign-task',
        title: '会签任务'
      },
      {
        action: 'create.subprocess-expanded',
        title: '子流程'
      },
      {
        action: 'create.data-object',
        title: '数据对象'
      },
      {
        action: 'create.data-store',
        title: '数据存储'
      }, {
        action: 'create.participant-expanded',
        title: '扩展流程'
      },
      {
        action: 'create.group',
        title: '分组'
      }
    ]
  }

  //  获取控件配置信息
  getControl(action) {
    const result = this.controls.filter(item => item.action === action)
    return result[0] || {}
  }
}
