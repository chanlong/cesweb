import { rule } from "@/utils/validateRules"
import GlobalRequest from '@/api/crud'

var validateParam = (rule, value, callback) => {
  GlobalRequest.server('/system/param/publicValue').fetchPath(value).then(response => {
    if (window.boxType === 'edit') callback()
    const result = response.data.data
    if (result !== null) {
      callback(new Error('参数键已经存在'))
    } else {
      callback()
    }
  })
}

export const tableOption = {
  index: true,
  border: true,
  stripe: true,
  menuAlign: 'center',
  indexLabel: '序号',
  searchMenuSpan: 6,
  column: [
    {
      label: '名称',
      search: true,
      prop: 'publicName',
      rules: [
        { required: true, message: '请输名称', trigger: 'blur' },
        { max: 30, message: '长度在 30 个字符', trigger: 'blur' },
        { validator: rule.validatorNameCn, trigger: 'blur'}
      ]
    },
    {
      label: '键',
      prop: 'publicKey',
      rules: [
        { required: true, message: '请输入键', trigger: 'blur' },
        { validator: rule.validatorKey, trigger: 'blur'},
        { validator: validateParam, trigger: 'blur'},
      ]

    },
    {
      label: '值',
      overHidden: true,
      prop: 'publicValue',
      rules: [
        { required: true, message: '请输入值', trigger: 'blur' }
      ]
    },
    {
      label: '编码',
      prop: 'validateCode'
    },
    {
      label: '类型',
      prop: 'system',
      type: 'select',
      dicUrl: '/system/dict/type/dict_type',
      rules: [{
        required: true,
        message: '请输入类型',
        trigger: 'blur'
      }],
      search: true
    },
    {
      label: '状态',
      prop: 'status',
      width: 80,
      type: 'select',
      dicUrl: '/system/dict/type/status_type',
      rules: [
        { required: true, message: '请输入值', trigger: 'blur' }
      ]
    },
    {
      label: '类型',
      prop: 'publicType',
      width: 80,
      type: 'select',
      dicUrl: '/system/dict/type/param_type',
      rules: [{
        required: true,
        message: '请选择类型',
        trigger: 'blur'
      }]
    }
  ]
}
