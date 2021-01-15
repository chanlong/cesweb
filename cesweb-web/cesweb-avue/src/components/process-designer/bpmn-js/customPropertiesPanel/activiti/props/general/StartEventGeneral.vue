<template>
  <el-form :model="general" label-position="left" label-width="auto">
    <el-collapse v-model="activeNames">
      <!-- 常规 -->
      <el-collapse-item title="常规" name="1">
        <span class="group-toggle green"></span>
        <el-form-item label="编号" required>
          <el-input v-model="general.id" clearable></el-input>
        </el-form-item>
        <el-form-item label="名称" required>
          <el-input v-model="general.name" clearable></el-input>
        </el-form-item>
      </el-collapse-item>

      <!-- 持续异步 -->
      <AsyncProps :name="activeNames[1]" :modeler="modeler" :element="element" @update="handleUpdate"/>
    </el-collapse>
  </el-form>
</template>

<script>
  import bpmnUtil from '@/components/process-designer/utils/bpmnUtil'
  import AsyncProps from '../includes/AsyncProps'

  export default {
    name: 'StartEventGeneral',
    inject: ['modeler'],
    components: {
      AsyncProps
    },
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
          name: null
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
