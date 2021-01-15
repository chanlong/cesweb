export const tableOption = {
  align: 'center',
  index: true,
  border: true,
  stripe: true,
  delBtn: false,
  menuBtn: true,
  editBtn: false,
  menuType: 'menu',
  menuAlign: 'center',
  indexLabel: '序号',
  labelWidth: 120,
  searchMenuSpan: 4,
  column: [{
    fixed: true,
    label: '模型ID',
    prop: 'id',
    editDisabled: true,
    addDisplay: false
  }, {
    fixed: true,
    label: '模型标识',
    prop: 'key',
    editDisabled: true,
    rules: [{
      required: true,
      message: '请输入模型标识',
      trigger: 'blur'
    }]
  }, {
    label: '流程分类',
    prop: 'category',
    search: true,
    rules: [{
      required: true,
      message: '请输入流程分类',
      trigger: 'blur'
    }]
  }, {
    label: '模型名称',
    prop: 'name',
    rules: [{
      required: true,
      message: '请输入模型名称',
      trigger: 'blur'
    }]
  },
  {
    label: '描述',
    prop: 'desc',
    hide: true,
    editDisabled: false,
    addDisplay: true,
    rules: [{
      required: true,
      message: '请输入描述',
      trigger: 'blur'
    }]
  }, {
    label: '版本号',
    prop: 'version',
    editDisabled: true,
    addDisplay: false
  }, {
    label: '创建时间',
    prop: 'createTime',
    type: 'datetime',
    editDisabled: true,
    addDisplay: false
  }, {
    label: '最后更新时间',
    prop: 'lastUpdateTime',
    type: 'datetime',
    editDisabled: true,
    addDisplay: false
  }]
}
