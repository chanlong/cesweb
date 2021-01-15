export const tableOption = {
  align: 'center',
  index: true,
  border: true,
  stripe: true,
  delBtn: false,
  addBtn: false,
  editBtn: false,
  menuBtn: true,
  menuType: 'menu',
  menuAlign: 'center',
  searchMenuSpan: 3,
  column: [{
    label: 'ID',
    prop: 'leaveId',
    editDisabled: true,
    addDisplay: false
  },
  {
    label: '申请人',
    prop: 'username',
    editDisabled: true,
    addDisplay: false
  },
  {
    label: '天数',
    prop: 'days',
    type: 'number'
  },
  {
    label: '请假时间',
    prop: 'leaveTime',
    type: 'datetime',
    overHidden: true,
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd hh:mm:ss'
  },
  {
    label: '提交时间',
    prop: 'createTime',
    type: 'datetime',
    overHidden: true,
    format: 'yyyy-MM-dd HH:mm',
    editDisabled: true,
    addDisplay: false,
    hide: true
  },
  {
    label: '状态',
    prop: 'state',
    type: 'select',
    dicUrl: '/system/dict/type/leave_status',
    search: true,
    addDisplay: false
  },
  {
    hide: true,
    label: '备注',
    prop: 'content',
    type: 'ueditor',
    component: 'avueUeditor',
    span: 24,
    options: {
      action: "/system/sys-file/upload",
      props: {
        res: "data",
        url: "url"
      }
    }
  }]
}
