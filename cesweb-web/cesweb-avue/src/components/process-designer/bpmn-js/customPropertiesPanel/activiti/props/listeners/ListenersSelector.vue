<template>
  <el-form label-position="top">
    <el-collapse v-model="collapseActives">
      <el-collapse-item title="监听器" name="1">
        <span class="group-toggle green"></span>
        <el-form-item class="bpp-field-wrapper">
          <template slot="label">
            <div>执行监听器</div>
            <el-button-group>
              <el-button size="mini" icon="el-icon-plus" @click="handleAppend('execution')" />
              <el-button size="mini" icon="el-icon-delete" @click="handleRemove('execution')" />
            </el-button-group>
          </template>
          <select class="el-input__inner" size="2">
            <option v-for="(item, index) in mapper.execution.listeners" :key="index" @click="handleSelect('execution')">{{item.eventType}}: {{listenerTypes[item.listenerType]}}</option>
          </select>
        </el-form-item>

        <el-form-item class="bpp-field-wrapper" v-if="extension.taskListeners">
          <template slot="label">
            <div>任务监听器</div>
            <el-button-group>
              <el-button size="mini" icon="el-icon-plus" @click="handleAppend('task')" />
              <el-button size="mini" icon="el-icon-delete" @click="handleRemove('task')" />
            </el-button-group>
          </template>
          <select class="el-input__inner" size="2">
            <option v-for="(item, index) in mapper.task.listeners" :key="index" @click="handleSelect('task')">{{item.eventType}}: {{listenerTypes[item.listenerType]}}</option>
          </select>
        </el-form-item>
      </el-collapse-item>

      <el-collapse-item :title="model.title" name="2" v-show="isShow">
        <span class="group-toggle blue"></span>
        <component :is="model.component" v-model="mapper[model.type].listener" :listeners="mapper[model.type].listeners" :listener-index="mapper[model.type].index" @update="updateExtensionElements"></component>
      </el-collapse-item>
    </el-collapse>
  </el-form>
</template>

<script>
  import bpmnUtil from '@/components/process-designer/utils/bpmnUtil'
  import bpmnHelper from '@/components/process-designer/helper/BpmnHelper'
  import TaskListener from './TaskListener'
  import ExecutionListener from './ExecutionListener'

  export default {
    name: 'ListenersSelector',
    inject: ['modeler'],
    components: {
      TaskListener,
      ExecutionListener
    },
    props: {
      element: {
        type: Object,
        default: () => {
          return {}
        }
      },
      extension: {
        type: Object,
        default: () => {
          return {}
        }
      }
    },
    data() {
      return {
        model: {
          type: 'execution',
          title: '执行监听',
          component: 'ExecutionListener'
        },
        mapper: {
          task: {
            type: 'TaskListener',
            index: 0,
            title: '任务监听',
            listener: {},
            listeners: this.extension.taskListeners || [],
          },
          execution: {
            type: 'ExecutionListener',
            index: 0,
            title: '执行监听',
            listener: {},
            listeners: this.extension.executionListeners || [],
          }
        },
        isShow: false
      }
    },
    computed: {
      collapseActives: {
        get: function() {
          return ['1', '2']
        },
        set: function() {}
      },
      listenerTypes() {
        return bpmnUtil.getListenerTypes()
      }
    },
    watch: {
      extension: {
        deep: true,
        immediate: true,
        handler(newVal, oldVal) {
          if (newVal.taskListeners !== undefined) {
            this.mapper.task.listeners = newVal.taskListeners
          }
          if (newVal.executionListeners !== undefined) {
            this.mapper.execution.listeners = newVal.executionListeners
          }
        }
      }
    },
    methods: {
      // 添加监听器
      handleAppend(type) {
        this.appendListener(type)
      },
      // 删除执行监听
      handleRemove(type) {
        this.removeExtensionElements(type)
        this.removeListener(type)
      },
      // 点击执行监听
      handleSelect(type) {
        this.bindListener(type)
      },
      // 绑定监听数据
      bindListener(type){
        const currentIndex = event.target.index
        this.isShow = true
        this.model.type = type
        this.model.title = this.mapper[type].title
        this.model.component = this.mapper[type].type
        this.mapper[type].index = currentIndex
        this.mapper[type].listener = this.mapper[type].listeners[currentIndex]
      },
      // 添加监听数据
      appendListener(type) {
        const eventTypes = bpmnUtil.getEventType(type)
        if (this.mapper[type].listeners.length < eventTypes.length) {
          this.mapper[type].listeners.push({
            eventType: eventTypes[this.mapper[type].listeners.length],
            listenerType: 'class',
            listenerValue: null,
            scriptType: null,
            scriptFormat: null,
            scriptValue: null,
            scriptResourceValue: null
          })
        }
      },
      // 删除监听数据
      removeListener(type) {
        this.model.type = type
        this.mapper[type].listeners.splice(this.mapper[type].index, 1)
        this.isShow = this.mapper[type].listeners.length ? true : false
      },
      // 删除监听元素
      removeExtensionElements(type) {
        const listener = this.mapper[type].listeners[this.mapper[type].index]
        const bpmnModeler = this.modeler()
        const extensionElements = bpmnHelper.removeListener(this.mapper[this.model.type].type, bpmnModeler, this.element, listener)

        this.$emit('update', { extensionElements: extensionElements })
      },
      // 更新监听元素
      updateExtensionElements() {
        const listener = this.mapper[this.model.type].listeners[this.mapper[this.model.type].index]
        const bpmnModeler = this.modeler()
        const extensionElements = bpmnHelper.addListener(this.mapper[this.model.type].type, bpmnModeler, this.element, listener)

        this.$emit('update', { extensionElements: extensionElements })
      }
    }
  }
</script>

<style>

</style>
