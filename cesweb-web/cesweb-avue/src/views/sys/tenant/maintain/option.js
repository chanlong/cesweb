export const tableOption = {
  index: true,
  align: 'center',
  border: true,
  stripe: true,
  menuAlign: 'center',
  indexLabel: '序号',
  labelWidth: 100,
  searchMenuSpan: 4,
  column: [{
    label: '租户id',
    prop: 'id',
    hide: true,
    editDisabled: true,
    editDisplay: false,
    addDisplay: false
  }, {
    label: '租户名称',
    prop: 'name',
    search: true,
    rules: [{
      required: true,
      message: '请输入租户名称',
      trigger: 'blur'
    }, {
      min: 3,
      max: 32,
      message: '长度在 3 到 32 个字符',
      trigger: 'blur'
    }]
  }, {
    label: '租户编号',
    prop: 'code',
    rules: [{
      required: true,
      message: '请输入租户编号',
      trigger: 'blur'
    }]
  }, {
    label: '开始时间',
    prop: 'validityBegin',
    type: 'date',
    format: 'yyyy-MM-dd',
    valueFormat: 'yyyy-MM-dd',
    rules: [{
      required: true,
      message: '请输入结束时间',
      trigger: 'blur'
    }]
  }, {
    label: '结束时间',
    prop: 'validityFinish',
    type: 'date',
    format: 'yyyy-MM-dd',
    valueFormat: 'yyyy-MM-dd',
    rules: [{
      required: true,
      message: '请输入结束时间',
      trigger: 'blur'
    }]
  }, {
    label: '数据源名称',
    prop: 'dsname',
    rules: [{
      required: true,
      message: '请输入数据源名称',
      trigger: 'blur'
    }]
  }, {
    label: '状态',
    prop: 'status',
    type: 'radio',
    props: {
      label: 'label',
      value: 'value'
    },
    dicUrl: '/system/dict/type/status_type',
    border: true,
    search: true
  }]
}
