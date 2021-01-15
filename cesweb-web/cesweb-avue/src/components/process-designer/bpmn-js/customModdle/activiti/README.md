# activiti-bpmn-moddle

```js
var BpmnModeler = require('bpmn-js/lib/Modeler'),
    activitiExtensionModule = require('./lib'),
    activitiModdle = require('./resources/activiti');

var modeler = new BpmnModeler({
    additionalModules: [
      activitiExtensionModule
    ],
    moddleExtensions: {
      activiti: activitiModdle
    }
  });
```
