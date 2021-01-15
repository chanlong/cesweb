import inherits from 'inherits';
import { attr as svgAttr } from 'tiny-svg';
import { isLabel } from 'bpmn-js/lib/util/LabelUtil';
import { getBusinessObject } from 'bpmn-js/lib/util/ModelUtil';
import BpmnRenderer from 'bpmn-js/lib/draw/BpmnRenderer';

export default function ColorRenderer(injector, eventBus, styles) {

  var callPriority = 2000;

  // BaseRenderer.call(this, eventBus, callPriority)
  injector.invoke(BpmnRenderer, this);

  var self = this;

  var originalDrawShape = self.drawShape;

  eventBus.on(['render.shape'], callPriority, function(evt, context) {
    var element = context.element,
        visuals = context.gfx;

    // call default implementation
    var shape = originalDrawShape.call(self, visuals, element);

    // exclud label element
    if(!isLabel(element)) {
      // 2D shape with default white color
      svgAttr(shape, {
        fill: getBackgroundColor(element) || '#ffffff',
        stroke: getBorderColor(element) || '#000000'
      });
    }

    // make sure default renderer is not called anymore
    return shape;
  });

  eventBus.on(['render.connection'], callPriority, function(evt, context) {
    var element = context.element,
        visuals = context.gfx;

    // call default implementation
    var shape = self.drawConnection(visuals, element);

    // exclud label element
    if(!isLabel(element)) {
      // line shape with default black color
      svgAttr(shape, {
        stroke: getBorderColor(element) || '#000000'
      });
    }

    // make sure default renderer is not called anymore
    return shape;
  });
}

inherits(ColorRenderer, BpmnRenderer);

ColorRenderer.$inject = [ 'injector', 'eventBus', 'styles'];

function getBorderColor(element) {
  return getBusinessObject(element).di.get('color:border-color');
}

function getBackgroundColor(element) {
  return getBusinessObject(element).di.get('color:background-color');
}
