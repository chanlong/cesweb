import GlobalRequest from '@/api/crud'

const DIC = {
  vaild: [{
    label: '否',
    value: 'false'
  }, {
    label: '是',
    value: 'true'
  }]
}

var validateClient = (rule, value, callback) => {
  GlobalRequest.server('/system/client').fetchObj(value).then(response => {
    if (window.boxType === 'edit') {
      return callback()
    }
    const result = response.data.data
    if (result.length !== 0) {
      callback(new Error('客户端已存在'))
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
  searchMenuSpan: 6,
  column: [{
    width: 150,
    label: '编号',
    prop: 'clientId',
    align: 'center',
    sortable: true,
    editDisabled: true,
    rules: [{
      required: true,
      message: '请输入clientId',
      trigger: 'blur'
    }, {validator: validateClient, trigger: 'blur'}]
  }, {
    label: '密钥',
    prop: 'clientSecret',
    align: 'center',
    sortable: true,
    overHidden: true,
    width: 120,
    rules: [{
      required: true,
      message: '请输入clientSecret',
      trigger: 'blur'
    }]
  }, {
    label: '域',
    prop: 'scope',
    align: 'center',
    rules: [{
      required: true,
      message: '请输入scope',
      trigger: 'blur'
    }]
  }, {
    label: '自动放行',
    prop: 'autoapprove',
    align: 'center',
    type: 'radio',
    border: true,
    dicData: DIC.vaild,
    rules: [{
      required: true,
      message: '请选择是否放行',
      trigger: 'blur'
    }]
  }, {
    label: '授权模式',
    prop: 'authorizedGrantTypes',
    type: 'checkbox',
    dicUrl: '/system/dict/type/grant_types',
    align: 'center',
    overHidden: true,
    row: true,
    span: 24,
    rules: [{
      required: true,
      message: '请输入授权模式',
      trigger: 'blur'
    }]
  }, {
    label: '令牌时效',
    type: 'number',
    prop: 'accessTokenValidity',
    align: 'center'
  }, {
    label: '刷新时效',
    type: 'number',
    prop: 'refreshTokenValidity',
    align: 'center'
  }, {
    label: '回调地址',
    prop: 'webServerRedirectUri',
    align: 'center',
    hide: true
  }, {
    label: '权限',
    prop: 'authorities',
    align: 'center',
    hide: true
  }, {
    label: '扩展信息',
    prop: 'additionalInformation',
    align: 'center',
    type: 'textarea',
    placeholder: 'JSON格式数据',
    hide: true,
    minRows: 2,
    row: true,
    span: 24,
  }]
}
