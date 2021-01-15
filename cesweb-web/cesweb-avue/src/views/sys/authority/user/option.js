import GlobalRequest from '@/api/crud'

var validateUsername = (rule, value, callback) => {
  console.log(value);
  GlobalRequest.server('/system/user/details').fetchPath(value).then(response => {
    if (window.boxType === 'edit') callback()
    const result = response.data.data
    console.log(result);
    if (result !== null) {
      callback(new Error('用户名已经存在'))
    } else {
      callback()
    }
  })
}

export const tableOption = {
  align: 'center',
  index: true,
  border: true,
  stripe: true,
  addBtn: false,
  delBtn: false,
  editBtn: false,
  menuAlign: 'center',
  indexLabel: '序号',
  searchMenuSpan: 4,
  column: [{
    fixed: true,
    label: 'id',
    prop: 'userId',
    span: 24,
    hide: true,
    editDisabled: true,
    addDisplay: false
  }, {
    fixed: true,
    label: '用户名',
    prop: 'name',
    span: 24,
    rules: [{
      required: true,
      message: '请输入用户名'
    }, {
      min: 3,
      max: 20,
      message: '长度在 3 到 20 个字符',
      trigger: 'blur'
    }]
  }, {
    fixed: true,
    label: '登录名',
    prop: 'username',
    editDisabled: true,
    slot: true,
    search: true,
    span: 24,
    rules: [{
      required: true,
      message: '请输入登录名'
    }, {
      min: 3,
      max: 20,
      message: '长度在 3 到 20 个字符',
      trigger: 'blur'
    }, {
      validator: validateUsername,
      trigger: 'blur'
    }]
  }, {
    label: '密码',
    prop: 'password',
    type: 'password',
    value: '',
    hide: true,
    span: 24,
    rules: [{
      min: 6,
      max: 20,
      message: '长度在 6 到 20 个字符',
      trigger: 'blur'
    }]
  }, {
    label: '所属部门',
    prop: 'deptId',
    formslot: true,
    slot: true,
    span: 24,
    hide: true,
    dataType:"number",
    rules: [{
      required: true,
      message: '请选择部门',
      trigger: 'change'
    }]
  }, {
    label: '手机号',
    prop: 'phone',
    value: '',
    span: 24,
    hide: true,
    rules: [{
      min: 11,
      max: 11,
      message: '长度在 11 个字符',
      trigger: 'blur'
    }]
  }, {
    label: '角色',
    prop: 'role',
    formslot: true,
    slot: true,
    overHidden: true,
    span: 24,
    rules: [{
      required: true,
      message: '请选择角色',
      trigger: 'blur'
    }]
  }, {
    label: '状态',
    prop: 'lockFlag',
    type: 'radio',
    slot: true,
    border:true,
    span: 24,
    rules: [{
      required: true,
      message: '请选择状态',
      trigger: 'blur'
    }],
    dicData: [{
      label: '有效',
      value: '0'
    }, {
      label: '锁定',
      value: '9'
    }]
  }, {
    width: 180,
    label: '创建时间',
    prop: 'createTime',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd HH:mm:ss',
    editDisabled: true,
    addDisplay: false,
    span: 24
  }]
}
