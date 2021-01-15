<template>
  <el-form ref="form" :model="form" label-position="top">
    <!--
    <el-form-item label="事件类型" class="el-form-item-inline">
      <el-select v-model="listener.eventType" placeholder="请选择">
        <el-option label="开始" value="start"></el-option>
        <el-option label="结束" value="end"></el-option>
      </el-select>
    </el-form-item>
    -->
    <el-form-item label="监听类型" class="el-form-item-inline">
      <el-select v-model="form.listenerType" placeholder="请选择" @change="handleListenerType">
        <el-option v-for="(label, type) in listenerTypes" :label="label" :value="type" :key="type"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item :label="listenerTypes[form.listenerType]" required v-show="form.listenerType !== 'script'">
      <el-input v-model="form.listenerValue" clearable></el-input>
    </el-form-item>
    <el-form-item label="脚本格式" class="el-form-item-inline" v-show="form.listenerType === 'script'">
      <el-select v-model="form.scriptFormat" placeholder="请选择">
        <el-option label="groovy" value="groovy"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="脚本类型" class="el-form-item-inline" v-show="form.listenerType === 'script'">
      <el-select v-model="form.scriptType" placeholder="请选择">
        <el-option v-for="(label, type) in scriptTypes" :label="label" :value="type" :key="type"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item :label="scriptTypes[form.scriptType]" v-show="form.listenerType === 'script'" required>
      <code-editor ref="codeEditor" v-show="form.scriptType === 'script'" v-model="form.scriptValue" lang="groovy" skin="base16-light" @input="handleScriptEditor" />
      <el-input type="text" v-show="form.scriptType === 'scriptResource'" v-model="form.scriptResourceValue" clearable></el-input>
    </el-form-item>
  </el-form>
</template>

<script>
  import bpmnUtil from '@/components/process-designer/utils/bpmnUtil'
  import codeEditor from '@/components/codemirror/editor'

  export default {
    name: 'ExecutionListener',
    components: {
      codeEditor,
    },
    props: {
      value: {
        type: Object,
        default: () => { return {} }
      },
      listeners: {
        type: Array,
        default: () => { return [] }
      },
      listenerIndex: {
        type: Number,
        default: 0
      }
    },
    data() {
      return {
        form: this.value
      }
    },
    computed: {
      scriptTypes() {
        return {
          script: '内联脚本',
          scriptResource: '外部资源'
        }
      },
      listenerTypes() {
        return bpmnUtil.getListenerTypes()
      }
    },
    watch: {
      value: {
        deep: true,
        handler(value) {
          this.form = value
        }
      },
      'form.eventType': {
        handler(value) {
          bpmnUtil.setProperty(this.listeners[this.listenerIndex], 'eventType', value)
        }
      },
      'form.listenerType': {
        handler(value) {
          bpmnUtil.setProperty(this.listeners[this.listenerIndex], 'listenerType', value)
        }
      },
      'form.listenerValue': {
        handler(value) {
          const that = this
          bpmnUtil.debounce(() => { that.appendExtensionListener() })
        }
      },
      'form.scriptValue': {
        handler(value) {
          const that = this
          bpmnUtil.debounce(() => { that.appendExtensionListener() })
        }
      },
      'form.scriptResourceValue': {
        handler(value) {
          const that = this
          bpmnUtil.debounce(() => { that.appendExtensionListener() })
        }
      }
    },
    methods:{
      // 切换监听类型
      handleListenerType(value) {
        if (value === 'script') {
          this.form.scriptFormat = 'groovy'
          this.form.scriptType = 'script'
        } else {
          this.form.scriptFormat = null
          this.form.scriptType = null
        }
      },
      // 脚本编辑监听
      handleScriptEditor(value) {
        const that = this
        bpmnUtil.debounce(() => { that.form.scriptValue = value })
      },
      appendExtensionListener() {
        this.$emit('update')
      }
    }
  }
</script>

<style>
</style>
