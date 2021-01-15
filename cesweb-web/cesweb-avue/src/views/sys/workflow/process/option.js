export const tableOption = {
  align: 'center',
  index: true,
  border: true,
  stripe: true,
  addBtn: false,
  delBtn: false,
  editBtn: false,
  menuBtn: true,
  menuType: 'menu',
  menuWidth: 150,
  menuAlign: 'center',
  indexLabel: '序号',
  searchMenuSpan: 3,
  column: [{
    fixed: true,
    label: '流程ID',
    prop: 'processonDefinitionId'
  }, {
    fixed: true,
    label: '模型标识',
    prop: 'key',
    editDisabled: true
  }, {
    label: '流程分类',
    prop: 'category',
    search: true
  }, {
    label: '模型名称',
    prop: 'name'
  }, {
    label: '版本号',
    prop: 'revision',
    editDisabled: true,
    addDisplay: false
  }, {
    label: '部署时间',
    prop: 'deploymentTime',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'timestamp',
    editDisabled: true,
    addDisplay: false
  }]
}
