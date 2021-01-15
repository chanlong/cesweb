<template>
  <el-collapse-item title="异步" :name="name">
    <el-form :model="model" label-position="top" class="async-form">
      <span class="group-toggle blue"></span>
      <el-form-item style="text-align: center;">
        <el-checkbox label="异步前" v-model="model.asyncBefore"></el-checkbox>
        <el-checkbox label="异步后" v-model="model.asyncAfter"></el-checkbox>
      </el-form-item>
      <el-form-item label="任务优先级">
        <el-input v-model="model.jobPriority" clearable></el-input>
      </el-form-item>
      <el-form-item label="重试时间周期">
        <el-input v-model="model.retryTimeCycle" clearable></el-input>
      </el-form-item>
    </el-form>
  </el-collapse-item>
</template>

<script>
  import bpmnUtil from '@/components/process-designer/utils/bpmnUtil'
  import bpmnHelper from '@/components/process-designer/helper/BpmnHelper'
  import elementHelper from '@/components/process-designer/helper/ElementHelper'

  export default {
    name: 'AsyncProps',
    props: {
      name: {
        type: [Number, String],
        default: 'async'
      },
      modeler: {
        type: Function,
        default: () => {}
      },
      element: {
        type: Object,
        default: () => {}
      }
    },
    data() {
      return {
        model: {
          asyncAfter: null,
          asyncBefore: null,
          jobPriority: null,
          retryTimeCycle: null
        }
      }
    },
    watch: {
      model: {
        deep: true,
        handler(newVal, oldVal) {
          const that = this
          bpmnUtil.debounce(() => {
            that.createFailedJobRetryTimeCycle()
          })
        }
      },
      // 监听当前元素变化，为属性面板赋值
      element: {
        deep: true,
        immediate: true,
        handler(newVal, oldVal) {
          if (newVal && newVal.businessObject) {
            this.setProperties(newVal.businessObject)
          }
        }
      }
    },
    methods: {
      // 创建失败重试时间周期元素
      createFailedJobRetryTimeCycle() {
        const bpmnModeler = this.modeler()
        const extensionElements = bpmnHelper.getBo(this.element).get('extensionElements')
        const extensionElementsValues = extensionElements.get("values")


        if (this.model.retryTimeCycle) {
          const extensionRetryTimeCycle = elementHelper.createElement('activiti:FailedJobRetryTimeCycle', { body: this.model.retryTimeCycle }, extensionElements, bpmnModeler.bpmnFactory)
          const thisListenerIndex = extensionElementsValues.findIndex(item => { return bpmnHelper.is(item, 'activiti:FailedJobRetryTimeCycle') })

          console.log(thisListenerIndex);
          if (thisListenerIndex > -1) {
            extensionElementsValues.splice(thisListenerIndex, 1, extensionRetryTimeCycle)
          } else {
            extensionElementsValues.push(extensionRetryTimeCycle)
          }
        } else {
          const thisListenerIndex = extensionElementsValues.findIndex(item => { return bpmnHelper.is(item, 'activiti:FailedJobRetryTimeCycle') })
          extensionElementsValues.splice(thisListenerIndex, 1)
        }

        const properties = this.getProperties()
        this.$emit('update', {
          ...properties,
          extensionElements: extensionElements ,
        })
      },
      getProperties() {
        return {
          'activiti:asyncAfter': this.transform(this.model.asyncAfter),
          'activiti:asyncBefore': this.transform(this.model.asyncBefore),
          'activiti:jobPriority': this.transform(this.model.jobPriority),
        }
      },
      setProperties(bo) {
        this.model.asyncAfter = bo.$attrs['activiti:asyncAfter']
        this.model.asyncBefore = bo.$attrs['activiti:asyncBefore']
        this.model.jobPriority = bo.jobPriority
      },
      transform(value) {
        return value ? value : undefined
      }
    }
  }
</script>

<style lang="scss">
  .async-form.el-form--label-top {
    .el-form-item__label {
      padding-bottom: 0;
    }
  }
</style>
