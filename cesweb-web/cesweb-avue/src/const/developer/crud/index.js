import { dicList, ynList, alignList } from './dict'

export const menuOption = {
  menuBtn: false,
  column: [{
    label: '显示',
    prop: 'menu',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '宽度',
    prop: 'menuWidth',
    type: 'input',
    span: 24
  }, {
    label: '对齐方式',
    prop: 'menuAlign',
    type: 'select',
    dicData: alignList,
    span: 24
  }, {
    label: '按钮类型',
    prop: 'menuType',
    type: 'select',
    dicData: [{
      label: 'button',
      value: 'button'
    }, {
      label: 'icon',
      value: 'icon'
    }, {
      label: 'text',
      value: 'text'
    }, {
      label: 'menu',
      value: 'menu'
    }],
    span: 24
  }]
}

export const dialogOption = {
  menuBtn: false,
  column: [{
    label: '宽度',
    prop: 'dialogWidth',
    type: 'input',
    span: 24
  }, {
    label: '高度',
    prop: 'dialogHeight',
    type: 'input',
    span: 24
  }, {
    label: '类型',
    prop: 'dialogType',
    type: 'select',
    dicData: [{
      label: '弹窗',
      value: 'dialog'
    }, {
      label: '抽屉',
      value: 'drawer'
    }],
    span: 24
  }, {
    label: '顶部距离',
    prop: 'dialogTop',
    type: 'input',
    span: 24
  }, {
    label: '全屏',
    prop: 'dialogFullscreen',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: 'esc关闭',
    prop: 'dialogEscape',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '遮罩',
    prop: 'dialogModal',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '遮罩关闭',
    prop: 'dialogClickModal',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '关闭按钮',
    prop: 'dialogCloseBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }]
}

export const btnOption = {
  menuBtn: false,
  column: [{
    label: '新增按钮',
    prop: 'addBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '编辑按钮',
    prop: 'editBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '查看按钮',
    prop: 'viewBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '删除按钮',
    prop: 'delBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '保存按钮',
    prop: 'saveBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '保存文案',
    prop: 'saveBtnTitle',
    span: 24,
  }, {
    label: '更新按钮',
    prop: 'updateBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '更新文案',
    prop: 'updateBtnTitle',
    span: 24,
  }, {
    label: '取消按钮',
    prop: 'cancelBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '取消文案',
    prop: 'cancelBtnTitle',
    span: 24,
  }, {
    label: '搜索按钮',
    prop: 'searchBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '刷新按钮',
    prop: 'refreshBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '过滤按钮',
    prop: 'filterBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '打印按钮',
    prop: 'printBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '导出按钮',
    prop: 'excelBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '显隐按钮',
    prop: 'columnBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '日期按钮',
    prop: 'dateBtn',
    type: 'select',
    span: 24,
    dicData: ynList
  }]
}

export const tableOption = {
  menuBtn: false,
  column: [{
    label: '主键',
    prop: 'rowKey',
    span: 24,
  }, {
    label: '提示',
    prop: 'tip',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '序号',
    prop: 'index',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '多选',
    prop: 'selection',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '边框',
    prop: 'border',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '折叠',
    prop: 'expand',
    type: 'select',
    span: 24,
    dicData: ynList
  }, {
    label: '表头对其',
    prop: 'headerAlign',
    type: 'select',
    span: 24,
    dicData: alignList
  }, {
    label: '对其方式',
    prop: 'align',
    type: 'select',
    span: 24,
    dicData: alignList
  }]
}

export const option = {
  dialogType: 'drawer',
  labelWidth: 100,
  selection: false,
  sortable: true,
  rowKey: 'id',
  index: true,
  border: true,
  stripe: true,
  expand: true,
  addBtn: true,
  delBtn: true,
  saveBtn: true,
  editBtn: false,
  cancelBtn: true,
  refreshBtn: false,
  dragHandler: true,
  searchMenuSpan: 6,
  group: [{
    label: '基本参数',
    prop: 'jbcs',
    column: [{
      label: '名称',
      prop: 'label',
      rules: [{
        required: true,
        message: '请输入名称',
        trigger: 'change'
      }]
    }, {
      label: 'prop值',
      prop: 'prop',
      rules: [{
        required: true,
        message: '请输入名称',
        trigger: 'change'
      }]
    }, {
      label: '类型',
      prop: 'type',
      type: 'select',
      dicData: dicList,
      rules: [{
        required: true,
        message: '请选择类型',
        trigger: 'change'
      }]
    }]
  }, {
    label: '位置设置',
    prop: 'wzsz',
    column: [{
      label: 'span',
      prop: 'span'
    }, {
      label: 'gutter',
      prop: 'gutter'
    }, {
      label: 'size',
      prop: 'size'
    }]
  }, {
    label: '表格属性',
    prop: 'bgsx',
    column: [{
        label: '宽度',
        prop: 'width',
        type: 'input'
      }, {
        label: '最小宽度',
        prop: 'minwidth',
        type: 'input'
      }, {
        label: '对其方式',
        prop: 'align',
        type: 'select',
        dicData: alignList
      }, {
        label: '冻结',
        prop: 'fixed',
        type: 'select',
        dicData: ynList
      }, {
        label: '隐藏',
        prop: 'hide',
        type: 'select',
        dicData: ynList
      },
      {
        label: '超出省略',
        prop: 'overHidden',
        type: 'select',
        dicData: ynList
      }, {
        label: '筛选',
        prop: 'filter',
        type: 'select',
        dicData: ynList
      }, {
        label: '搜索',
        prop: 'search',
        type: 'select',
        dicData: ynList
      }
    ]
  }, {
    label: '字典属性',
    prop: 'zdsx',
    column: [{
      label: '字典类型',
      prop: 'dataType',
      type: 'select',
      dicData: [{
        label: '数字',
        value: 'number'
      }, {
        label: '字符串',
        value: 'string'
      }]
    }]
  }, {
    label: '表单属性',
    prop: 'bdsx',
    column: [{
      label: '辅助语',
      prop: 'placeholder',
    }, {
      label: '提示语',
      prop: 'tip',
    }, {
      label: '新增显示',
      prop: 'addDisplay',
      type: 'select',
      dicData: ynList
    }, {
      label: '编辑显示',
      prop: 'editDisplay',
      type: 'select',
      dicData: ynList
    }, {
      label: '新增禁止',
      prop: 'addDisabled',
      type: 'select',
      dicData: ynList
    }, {
      label: '编辑禁止',
      prop: 'editDisabled',
      type: 'select',
      dicData: ynList
    }, {
      label: '只读',
      prop: 'readonly',
      type: 'select',
      dicData: ynList
    }, {
      label: '最大行',
      prop: 'maxRows',
      type: 'input',
    }, {
      label: '最小行',
      prop: 'minRows',
      type: 'input',
    }, {
      label: '多选',
      prop: 'multiple',
      type: 'select',
      dicData: ynList,
    }, {
      label: '精度',
      prop: 'precision',
      type: 'input',
    }, {
      label: '日期格式化',
      prop: 'format'
    }, {
      label: '日期格式化值',
      prop: 'valueFormat'
    }]
  }],
  column: [{
    label: '名称',
    prop: 'label',
    display: false
  }, {
    label: 'prop值',
    prop: 'prop',
    display: false
  }, {
    label: '类型',
    prop: 'type',
    type: 'select',
    dicData: dicList,
    display: false
  }]
}
