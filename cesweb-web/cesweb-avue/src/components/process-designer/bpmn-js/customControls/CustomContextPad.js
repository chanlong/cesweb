export default class CustomContextPad {

  constructor(config, popupMenu, contextPad, create, elementFactory, injector, translate) {
    this.config = config
    this.create = create
    this.elementFactory = elementFactory
    this.popupMenu = popupMenu
    this.translate = translate
    this.contextPad = contextPad
    //自动摆放位置
    if (config.autoPlace !== false) {
      this.autoPlace = injector.get('autoPlace', false)
    }
    //注册工具
    contextPad.registerProvider(this)
  }

  getContextPadEntries(element) {
    const {
      autoPlace,
      create,
      elementFactory,
      popupMenu,
      contextPad,
      translate
    } = this

    function appendUserTask(event, element) {
      if (autoPlace) {
        const shape = elementFactory.createShape({
          type: 'bpmn:UserTask'
        })
        autoPlace.append(element, shape)
      } else {
        appendUserTaskStart(event, element)
      }
    }

    function appendUserTaskStart(event) {
      const shape = elementFactory.createShape({
        type: 'bpmn:UserTask'
      })
      create.start(event, shape, element)
    }

    function chooseColorPopup(event, element) {
      // close any existing popup
      popupMenu.close();

      // get start popup draw start position
      var opts = getStartPosition(popupMenu._canvas, element);

      // or fallback to current cursor position
      opts.cursor = { x: event.x, y: event.y };

      // create new color-picker popup
      popupMenu.open(element, 'color-picker', opts);
    }

    function getStartPosition(canvas, element) {
      var Y_OFFSET = 15;

      var diagramContainer = canvas.getContainer(),
          pad = contextPad.getPad(element).html;

      var diagramRect = diagramContainer.getBoundingClientRect(),
          padRect = pad.getBoundingClientRect();

      var left = padRect.left - diagramRect.left;
      var pos = {
        x: left,
        y: padRect.top - Y_OFFSET
      };

      return pos;
    }

    return {
      'choose.color': {
        group: 'edit',
        className: 'bpmn-icon-color',
        title: translate('Choose Color'),
        action: {
          click: chooseColorPopup
        }
      },
      'append.user-task': {
        group: 'model',
        className: 'bpmn-icon-user-task',
        title: translate('Append UserTask'),
        action: {
          click: appendUserTask,
          dragstart: appendUserTaskStart
        }
      }
    }
  }
}

CustomContextPad.$inject = [
  'config',
  'popupMenu',
  'contextPad',
  'create',
  'elementFactory',
  'injector',
  'translate'
]
