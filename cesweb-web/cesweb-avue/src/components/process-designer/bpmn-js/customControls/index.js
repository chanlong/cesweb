import CustomPalette from './CustomPalette'
import CustomContextPad from './CustomContextPad'
import ColorRenderer from './color/ColorRenderer'
import ColorPopupProvider from './color/ColorPopupProvider'

export default {
  __init__: [
    'customContextPad',
    'customPalette' ,
    'colorRenderer' ,
    'colorPopupProvider'
  ],
  customPalette: [ 'type', CustomPalette ],
  customContextPad: [ 'type', CustomContextPad ],
  colorRenderer: ['type', ColorRenderer ],
  colorPopupProvider: ['type', ColorPopupProvider ]
}
