export const tableOption = {
  align: 'center',
  index: true,
  border: true,
  stripe: true,
  addBtn: false,
  viewBtn: true,
  editBtn: false,
  menuAlign: 'center',
  indexLabel: '序号',
  searchMenuSpan: 6,
  column: [{
    label: '用户ID',
    prop: 'id',
    align: 'center',
    slot: true,
  }, {
    label: '用户名',
    prop: 'username',
    align: 'center',
    slot: true,
  }, {
    label: '令牌',
    prop: 'access_token',
    align: 'center',
    'overHidden': true
  }, {
    label: '类型',
    prop: 'token_type',
    align: 'center'
  }, {
    label: '过期时间',
    prop: 'expires_in',
    align: 'center'
  }]
}
