export default class CustomPalette {

  constructor(create, elementFactory, palette, translate) {
    this.create = create
    this.elementFactory = elementFactory
    this.translate = translate

    palette.registerProvider(this)
  }

  getPaletteEntries(element) {
    const { create, elementFactory, translate } = this

    function createAction(type) {
      return function(event) {
        const shape = elementFactory.createShape({ type: `bpmn:${type}` })
        create.start(event, shape)
      }
    }

    return {
      'create.user-task': {
        group: 'activity',
        className: 'bpmn-icon-user-task',
        title: translate('Create UserTask'),
        action: {
          dragstart: createAction('UserTask'),
          click: createAction('UserTask')
        }
      },
      'create.service-task': {
        group: 'activity',
        className: 'bpmn-icon-service-task',
        title: translate('Create ServiceTask'),
        action: {
          dragstart: createAction('ServiceTask'),
          click: createAction('ServiceTask')
        }
      }
    }
  }
}

CustomPalette.$inject = [
  'create',
  'elementFactory',
  'palette',
  'translate'
]
