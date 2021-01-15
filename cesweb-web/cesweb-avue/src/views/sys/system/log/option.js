export const tableOption = {
  align: 'center',
  index: true,
  border: true,
  stripe: true,
  addBtn: false,
  editBtn: false,
  viewBtn: true,
  refreshBtn: true,
  menuAlign: 'center',
  menuWidth: 150,
  indexLabel: '序号',
  searchMenuSpan: 4,
  props: { label: 'label', value: 'value' },
  column: [{
    label: '类型',
    prop: 'type',
    type: 'radio',
    width: 80,
    dicUrl: '/system/dict/type/log_type',
    slot: true,
    border: true,
    search: true,
    searchSpan: 5,
    searchslot: true
  }, {
    label: '标题',
    prop: 'title'
  }, {
    label: 'IP地址',
    prop: 'remoteAddr'
  }, {
    label: '请求方式',
    prop: 'method'
  }, {
    label: '客户端',
    prop: 'serviceId'
  }, {
    width: 80,
    label: '请求时间',
    prop: 'time'
  }, {
    label: '创建时间',
    prop: 'createTime',
    type: 'datetime',
    width: 150,
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd HH:mm:ss'
  }]
}
