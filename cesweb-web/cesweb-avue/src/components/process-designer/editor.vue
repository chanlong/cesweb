<template>
  <el-container class="container">
    <el-header height="50px" class="header">
      <designer-header v-if="modeler" @code="handleCode" @save="handleSave" @deplay="handleDeplay" />
    </el-header>

    <el-container style="overflow: hidden;">
      <el-main>
        <div ref="canvas" class="canvas" />
      </el-main>

      <el-aside class="properties-panel">
        <activiti-properties-panel v-if="modeler" :process-key="form.key"/>
      </el-aside>
    </el-container>

    <el-drawer ref="drawer" size="50%" direction="ltr" custom-class="editor-drawer" :visible.sync="drawerVisible" :append-to-body="true">
      <template slot="title">
        <el-page-header @back="handleClose" :content="form.name"></el-page-header>
      </template>
      <div class="editor-drawer__content">
        <code-editor ref="codeditor" lang="text/xml"
                                     skin="base16-dark"
                                     v-model="bpmnXml"
                                     :show-tools="true"
                                     :cant-edit-codes="cantEditCodes"
                                     @save="handleConfirm"/>
      </div>
    </el-drawer>
  </el-container>
</template>

<script>
  import codeEditor from '@/components/codemirror/editor'

  import GlobalRequest from '@/api/crud'
  import designerHeader from './header'
  import activitiPropertiesPanel from './bpmn-js/customPropertiesPanel/ActivitiPropertiesPanel'
  import getDiagramXml from './resources/template'
  import Modeler from './bpmn-js/Modeler'

  export default {
    name: 'activitiBpmn',
    components: {
      codeEditor,
      designerHeader,
      activitiPropertiesPanel
    },
    provide () {
      return {
        modeler: this.getModeler
      }
    },
    data() {
      return {
        form: {},
        done: false,
        modeler: null,
        bpmnXml: '',
        drawerVisible: false,
      }
    },
    props: {
      id: {
        type: String,
        default: ''
      },
      processKey: {
        type: String,
        default: ''
      },
      processName: {
        type: String,
        default: ''
      },
      processCategory: {
        type: String,
        default: ''
      }
    },
    computed: {
      cantEditCodes() {
        return [{
          regex: `${this.processKey}`,
          message: '请不要在代码编辑模式中直接修改流程ID'
        }]
      }
    },
    created() {
      this.initForm()
      this.$emit('before')
    },
    mounted() {
      const that = this
      this.$event.$on('getBpmnXml', function(xml) {
        that.modeler = new Modeler(that.$refs.canvas, that.form)
        that.modeler.importXML(xml || getDiagramXml())
        that.form['key'] = xml ? that.processKey : 'Process_1'
      })
    },
    methods: {
      getModeler() {
        return this.modeler
      },
      // 初始化表单数据
      initForm() {
        this.form['id'] = this.id
        this.form['key'] = this.processKey
        this.form['name'] = this.processName
        this.form['desc'] = ''
        this.form['category'] = this.processCategory
      },
      /**
       * 保存设计图
       * @param {Object} modelForm
       */
      handleSave(modelForm) {
        if (modelForm.modelXml && modelForm.modelImage) {
          GlobalRequest.server(`/act/model/${modelForm.id}`).update(modelForm).then(response => {
            console.log(response)
          }).catch(e => {
            console.log(e)
          })
        }
      },
      /**
       * 发布设计图
       * @param {Object} modelForm
       */
      handleDeplay(modelForm) {
        console.log(modelForm)
      },
      /**
       * 显示源代码视图
       * @param {Object} modelForm
       */
      handleCode(modelForm) {
        if (modelForm.modelXml) {
          this.bpmnXml = modelForm.modelXml
          this.drawerVisible = true
        }
      },
      /**
       * 源代码视图-关闭视图
       */
      handleClose() {
        this.$refs.drawer.closeDrawer()
      },
      /**
       * 源代码视图-保存
       * @param {Object} xml
       */
      handleConfirm(xml) {
        this.modeler.importXML(xml)
        this.handleClose()
      }
    }
  }
</script>

<style lang="scss">
  /* 左边工具栏以及编辑节点的样式 */
  @import "./style/diagram-js.css";
  @import "~bpmn-js/dist/assets/bpmn-font/css/bpmn.css";
  @import "~bpmn-js/dist/assets/bpmn-font/css/bpmn-codes.css";
  @import "~bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css";
  @import '~bpmn-js-properties-panel/dist/assets/bpmn-js-properties-panel.css';
  @import "@/components/process-designer/bpmn-js/customControls/color/color.scss";
  @import './style/app.css';

  .editor-drawer {
    display: flex;
    .el-drawer__header {
      padding: 10px 10px 10px 20px;
      margin-bottom: 0;
      background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(250,250,250,1) 50%,rgba(245,245,245,1) 100%);
    }
    .el-drawer__body {
      display: flex;
      flex-direction: column;
    }
    &__content {
      display: flex;
      flex-direction: column;
      flex: 1;
      .vue-codemirror {
        position: relative;
        flex: 1;
        .CodeMirror {
          height: auto;
          position: absolute;
          bottom: 0;
          right: 0;
          left: 0;
          top: 0;
        }
      }
    }
    &__footer {
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 10px 0;
      border-top: 1px solid #ccc;
    }
  }

  .container {
    width: 100%;
    height: 100%;
    position: relative;
    .header {
      background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(250,250,250,1) 50%,rgba(245,245,245,1) 100%);
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.08);
      border-radius: 4px 4px 0 0;
    }
    .canvas {
      height: 100%;
    }
    .properties-panel {
      overflow: hidden;
      margin-top: 2px;
      background: #fafafa;
      border-left: 1px solid rgba(0, 0, 0, 0.08);
      border-radius: 0 0 4px 0;
    }
  }

  // 设计器网格背景
  .djs-container {
    background-image: linear-gradient(90deg, rgba(200, 200, 200, 0.15) 10%, rgba(0, 0, 0, 0) 10%),
                      linear-gradient(rgba(200, 200, 200, 0.15) 10%, rgba(0, 0, 0, 0) 10%);
    background-size: 10px 10px;
  }

  // 上下文面板
  .djs-context-pad {
    border: 1px solid #ccc;
    padding: 0 10px;
    border-radius: 4px;
    background-color: #f9f9f8;
    .group {
      border-bottom: 1px dotted #ccc;
      padding: 5px 0;
    }
  }

  // 隐藏bpmn-js logo
  .bjs-powered-by {
    display: none;
  }

  // 左侧折叠面板
  .djs-palette-entries {
    ul {
      list-style-type: none;
      margin: 0;
      padding: 0;

      li {
        display: flex;
        flex-direction: column;

        .palette-item-title {
          display: block;
          padding: 10px 5px;
          border-bottom: 1px solid #eee;
          position: relative;
          cursor: pointer;

          i {
            position: absolute;
            right: 5px;
            top: 12px;
          }
        }

        .group {
          display: none;
          background: #f5f7fa;

          .palette-item-label {
            font-size: 14px;
            font-weight: 500;
            margin-left: 15px;
          }
        }

        .group.open {
          display: block;
        }
      }
    }
  }
</style>
