import { rule } from "@/const/global/rules"

export const tableOption = {
  index: true,
  align: 'center',
  border: true,
  stripe: true,
  addBtn: false,
  delBtn: false,
  editBtn: false,
  viewBtn: true,
  menuAlign: 'center',
  indexLabel: '序号',
  searchMenuSpan: 6,
  column: [{
    label: '角色名称',
    prop: 'roleName',
    span: 24,
    rules: [{
      required: true,
      message: '角色名称不能为空',
      trigger: 'blur'
    }, {
      min: 3,
      max: 20,
      message: '长度在 3 到 20 个字符',
      trigger: 'blur'
    }]
  }, {
    width: 120,
    label: '角色标识',
    prop: 'roleCode',
    span: 24,
    editDisabled: true,
    rules: [{
      required: true,
      message: '角色标识不能为空',
      trigger: 'blur'
    },{
      min: 3,
      max: 20,
      message: '长度在 3 到 20 个字符',
      trigger: 'blur'
    },{
      validator: rule.validatorKey,
      trigger: 'blur'
    }]
  }, {
    width: 150,
    label: '角色描述',
    prop: 'roleDesc',
    overHidden: true,
    type: 'textarea',
    span: 24,
    row: true,
    minRows: 2,
    rules: [{
      max: 128,
      message: '长度在 128 个字符内',
      trigger: 'blur'
    }]
  }, {
    width: 180,
    label: '数据权限',
    prop: 'dsType',
    type: 'select',
    span: 24,
    rules: [{
      required: true,
      message: '请选择数据权限类型',
      trigger: 'blur'
    }],
    dicData: [{
      label: '全部',
      value: 0
    }, {
      label: '自定义',
      value: 1
    }, {
      label: '本级及子级',
      value: 2
    }, {
      label: '本级',
      value: 3
    }]
  }, {
    label: '',
    prop: 'dsScope',
    formslot: true,
    showClomnu: false,
    hide: true
  }, {
    label: '创建时间',
    prop: 'createTime',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd HH:mm:ss',
    editDisplay: false,
    addDisplay: false,
    span: 24
  }]
}
