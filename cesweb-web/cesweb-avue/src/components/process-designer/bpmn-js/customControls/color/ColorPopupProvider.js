import {
  getBusinessObject
} from 'bpmn-js/lib/util/ModelUtil'

export default function ColorPopupProvider(popupMenu, modeling) {
  this._popupMenu = popupMenu;
  this._modeling = modeling;

  this.register();
}

ColorPopupProvider.$inject = [
  'popupMenu',
  'modeling'
];

ColorPopupProvider.prototype.register = function() {
  this._popupMenu.registerProvider('color-picker', this);
};

ColorPopupProvider.prototype.getEntries = function(element) {
  var self = this;

  var entries = self._colors.map(function(color) {
    return {
      id: color.label.toLowerCase() + '-color',
      className: 'color-icon color-icon-' + color.label,
      action: self._createAction(self._modeling, element, color)
    };
  });

  return entries;
}

ColorPopupProvider.prototype._createAction = function(modeling, element, newColor) {
  // set hex value to an element
  return function() {
    var ns = (newColor ? 'http://www.omg.org/spec/BPMN/non-normative/color/1.0' : undefined);

    modeling.updateProperties(element, {
      di: {
        'xmlns:color': ns,
        'color:border-color': newColor.borderColor,
        'color:background-color': newColor.backgroundColor
      }
    });
  };
}

ColorPopupProvider.prototype._colors = [
  {
    label: 'clear',
    borderColor: '#000000',
    backgroundColor: 'rgba(255,255,255,0.8)'
  },
  {
    label: 'red',
    borderColor: '#CC0033',
    backgroundColor: 'rgba(204,0,51,0.45)'
  }, {
    label: 'orange',
    borderColor: '#CC6633',
    backgroundColor: 'rgba(204,102,51,0.45)'
  }, {
    label: 'yellow',
    borderColor: '#FFCC33',
    backgroundColor: 'rgba(255,204,51,0.45)'
  }, {
    label: 'green',
    borderColor: '#99CC99',
    backgroundColor: 'rgba(153,204,153,0.45)'
  }, {
    label: 'blue',
    borderColor: '#0066CC',
    backgroundColor: 'rgba(0,102,204,0.45)'
  }, {
    label: 'indigo',
    borderColor: '#666699',
    backgroundColor: 'rgba(102,102,153,0.45)'
  }, {
    label: 'violet',
    borderColor: '#663366',
    backgroundColor: 'rgba(102,51,102,0.45)'
  }
];
