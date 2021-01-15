import GlobalRequest from '@/api/crud'

var validateSocial = (rule, value, callback) => {
  GlobalRequest.server('/system/social').fetchPath(value).then(response => {
    if (window.boxType === 'edit') {
      return callback()
    }
    const result = response.data.data
    if (result.length !== 0) {
      callback(new Error('此类型密钥已存在'))
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
  viewBtn: true,
  menuAlign: 'center',
  indexLabel: '序号',
  labelWidth: 120,
  searchMenuSpan: 6,
  column: [{
    label: 'ID',
    prop: 'id',
    hide: true,
    addDisplay: false,
    editDisabled: true
  }, {
    label: '类型',
    prop: 'type',
    type: 'select',
    dicUrl: '/system/dict/type/social_type',
    search: true,
    rules: [{
      required: true,
      message: '请选择类型',
      trigger: 'blur'
    }, {validator: validateSocial, trigger: 'blur'}]
  }, {
    label: '描述',
    prop: 'remark'
  }, {
    label: 'appId',
    prop: 'appId',
    overHidden: true,
    rules: [{
      required: true,
      message: '请输入appId',
      trigger: 'blur'
    }]
  }, {
    label: 'appSecret',
    prop: 'appSecret',
    overHidden: true,
    rules: [{
      required: true,
      message: '请输入appSecret',
      trigger: 'blur'
    }]
  }, {
    label: '回调地址',
    prop: 'redirectUrl',
    hide: true,
    rules: [{
      required: true,
      message: '请输入回调地址',
      trigger: 'blur'
    }]
  }, {
    valueFormat: 'timestamp',
    format: 'yyyy-MM-dd hh:mm:ss',
    label: '创建时间',
    prop: 'createTime',
    align: 'center',
    addDisplay: false,
    editDisabled: true
  }]
}
