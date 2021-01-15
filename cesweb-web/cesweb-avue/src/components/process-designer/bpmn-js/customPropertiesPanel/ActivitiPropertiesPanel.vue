<template>
  <el-scrollbar class="custom-properties-panel">
    <el-tabs type="border-card" v-model="tabName" @tab-click="handleSwitchTab">
      <el-tab-pane label="常规" name="general">
        <component v-if="element" ref="general" :is="general" :element="element" @before="handleBefore" @complete="handleComplete" @update="updateProperties"></component>
      </el-tab-pane>
      <el-tab-pane label="监听" name="listeners">
        <listeners-selector v-if="tabShow" ref="listeners" :element="element" :extension="$refs.general.extension" @update="updateProperties"></listeners-selector>
      </el-tab-pane>
    </el-tabs>
  </el-scrollbar>
</template>

<script>
  import bpmnHelper from '@/components/process-designer/helper/BpmnHelper'
  import ProcessGeneral from './activiti/props/general/ProcessGeneral'
  import StartEventGeneral from './activiti/props/general/StartEventGeneral'
  import UserTaskGeneral from './activiti/props/general/UserTaskGeneral'
  import ListenersSelector from './activiti/props/listeners/ListenersSelector'

  export default {
    name: 'ActivitiPropertiesPanel',
    components: {
      ProcessGeneral,
      StartEventGeneral,
      UserTaskGeneral,
      ListenersSelector
    },
    inject: ['modeler'],
    props: {
      processKey: {
        type: String,
        default: 'Process_1'
      }
    },
    data() {
      return {
        general: 'ProcessGeneral',
        tabName: 'general',
        tabShow: false,
        element: null,
        bpmnModeler: null
      }
    },
    mounted() {
      this.bpmnModeler = this.modeler()
      this.registerEvents()
    },
    methods: {
      handleBefore() {
        if (!bpmnHelper.hasExtensionElements(this.element)) {
          const bpmnModeler = this.modeler()
          const extensionElements = bpmnHelper.createExtensionElements(bpmnModeler, this.element)
          this.updateProperties({ extensionElements: extensionElements })
        }
      },
      handleComplete(extension) {
        bpmnHelper.setExtensionElements(this.element, extension)
      },
      // 切换tab标签监听
      handleSwitchTab(tabObj) {
        this.tabShow = tabObj.name === 'listeners'
      },
      // 注册bpmn事件
      registerEvents() {
        const { bpmnModeler, processKey } = this

        // listener event by import done
        bpmnModeler.on('eventBus', 'import.done', () => {
          try {
            // xml导入成功后，获取process元素
            this.element = bpmnModeler.getElement(processKey)
          } catch (e) {
            console.log('The bpmnModeler get element by [%s] is error: ', processKey, e.message)
          }
        })

        // listener event by element click
        bpmnModeler.on('bpmnModeler', 'element.click', e => {
          const { element } = e
          bpmnHelper.setElement(this, element)
          if (this.$refs.listeners) this.$refs.listeners.isShow = false
        })
      },
      /**
       * 更新元素属性
       * @param { Object } 要更新的属性, 例如 { name: '', id: '' }
       */
      updateProperties(properties) {
        const { bpmnModeler, element } = this
        //console.log(properties);
        bpmnModeler.modeling.updateProperties(element, properties)
      }
    }
  }
</script>

<style lang="scss">
  .custom-properties-panel {
    position: relative;
    width: 300px;
    height: 100%;
    background-color: #fefefe;
    border-color: rgba(0, 0, 0, 0.09);
    padding: 0;

    .el-tabs--border-card {
      border: 0;
      box-shadow: none;
    }
    .el-form-item.el-form-item-inline {
      display: flex;
      justify-content: space-between;
      .el-form-item__label {
        width: 60px;
        text-align: right;
        margin-right: 20px;
      }
      .el-form-item__content {
        flex: 1;
      }
    }
  }

  .el-collapse-item {
    position: relative;
    .group-toggle {
      position: absolute;
      top: 0;
      left: -15px;
      bottom: 0;
      width: 4px;
      cursor: pointer;
      transition: background-color 0.218s linear;
    }
    .group-toggle.green {
      background-color: #8fc071;
    }
    .group-toggle.blue {
      background-color: #409EFF;
    }
  }

  .bpp-field-wrapper {
    .el-form-item__label {
      display: flex;
      justify-content: space-between;
      padding-bottom: 0;
      align-items: center;
    }

    select.el-input__inner {
      height: unset;
      line-height: unset;
      padding-top: 5px;
      padding-bottom: 5px;
      option {
        padding: 5px;
        border-radius: 4px;
      }
    }
  }
</style>
