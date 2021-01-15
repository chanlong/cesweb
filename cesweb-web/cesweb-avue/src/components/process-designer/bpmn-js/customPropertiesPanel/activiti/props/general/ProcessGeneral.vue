<template>
  <el-form ref="form" :model="general" label-position="left" label-width="auto">
    <el-collapse v-model="activeNames">
      <el-collapse-item title="常规" name="1">
        <span class="group-toggle green"></span>
        <el-form-item label="编号" required>
          <el-input v-model="general.id" clearable></el-input>
        </el-form-item>
        <el-form-item label="名称" required>
          <el-input v-model="general.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="版本标签">
          <el-input v-model="general.versionTag" clearable></el-input>
        </el-form-item>
        <el-form-item label="执行监听">
          <el-switch v-model="general.isExecutable" active-text="开启" inactive-text="关闭"></el-switch>
        </el-form-item>
      </el-collapse-item>
    </el-collapse>
  </el-form>
</template>

<script>
  import bpmnUtil from '@/components/process-designer/utils/bpmnUtil'

  export default {
    name: 'ProcessGeneral',
    inject: ['modeler'],
    props: {
      element: {
        type: Object,
        default: () => {
          return {}
        }
      }
    },
    data() {
      return {
        general: {
          id: null,
          name: null,
          versionTag: null,
          isExecutable: false,
        },
        extension: {
          executionListeners: []
        }
      }
    },
    computed: {
      activeNames: {
        get() {
          return ['1', '2']
        },
        set() {}
      }
    },
    watch: {
      general: {
        deep: true,
        handler(newVal, oldVal) {
          if (newVal && newVal.id) {
            const that = this
            bpmnUtil.debounce(() => {
              that.$emit('update', bpmnUtil.filter(newVal))
            })
          }
        }
      },
      element: {
        deep: true,
        immediate: true,
        handler(newVal, oldVal) {
          if (newVal && newVal.businessObject) {
            // 为属性面板赋值
            bpmnUtil.setProperties(this.general, newVal.businessObject)
          }
        }
      }
    },
    created() {
      this.$emit('before')
    },
    mounted() {
      this.$emit('complete', this.extension)
    },
    methods: {
      handleUpdate(properties) {
        this.$emit('update', properties)
      }
    }
  }
</script>

<style>
</style>
