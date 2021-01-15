<template>
  <el-container class="codeditor-container">
    <el-header v-if="showTools" height="50px" class="codeditor-container__head" :class="skin">
      <el-button-group>
        <el-tooltip effect="dark" content="新建" placement="bottom">
          <el-button size="mini" icon="el-icon-adapt iconfont icon-new" @click="handleNew" />
        </el-tooltip>
        <el-tooltip effect="dark" content="保存" placement="bottom">
          <el-button size="mini" icon="el-icon-adapt iconfont icon-save-cu" @click="handleSave" />
        </el-tooltip>
        <el-tooltip effect="dark" content="另存为" placement="bottom">
          <el-button size="mini" icon="el-icon-adapt iconfont icon-save-as" @click="handleSaveAs" />
        </el-tooltip>
        <el-tooltip effect="dark" content="撤销" placement="bottom">
          <el-button size="mini" icon="el-icon-adapt iconfont icon-revoke" @click="handleRevoke" :disabled="!editor.hasUndo" />
        </el-tooltip>
        <el-tooltip effect="dark" content="恢复" placement="bottom">
          <el-button size="mini" icon="el-icon-adapt iconfont icon-resume" @click="handleResume" :disabled="!editor.hasRedo" />
        </el-tooltip>
      </el-button-group>

      <el-form ref="form" :model="editor" label-position="left" label-width="auto">
        <el-form-item label="语言">
          <el-select v-model="editor.mode" placeholder="请选择">
            <el-option-group v-for="group in modes" :label="group.label" :key="group.label">
              <el-option v-for="(item, index) in group.options" :label="item" :value="item" :key="index"></el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
      </el-form>
    </el-header>

    <el-container class="codeditor-container__body">
      <codemirror ref="cmEditor" v-model="editor.code"
                                 :options="cmOptions"
                                 @ready="onCmReady"
                                 @input="onCmCodeChange"/>
    </el-container>
  </el-container>
</template>

<script>
  /* 编辑器 */
  import { codemirror } from 'vue-codemirror'

  /* 代码 */
  import 'codemirror/mode/groovy/groovy.js'
  import 'codemirror/mode/htmlembedded/htmlembedded.js'

  /* 编辑器样式 */
  import 'codemirror/lib/codemirror.css'
  import 'codemirror/theme/base16-dark.css'
  import 'codemirror/theme/base16-light.css'

  /* 代码折叠 */
  import 'codemirror/addon/fold/foldgutter.css'
  import 'codemirror/addon/fold/foldcode'
  import 'codemirror/addon/fold/foldgutter'
  import 'codemirror/addon/fold/xml-fold'
  import 'codemirror/addon/fold/brace-fold'
  import 'codemirror/addon/fold/indent-fold'
  import 'codemirror/addon/fold/comment-fold'
  import 'codemirror/addon/fold/markdown-fold'
  import 'codemirror/addon/selection/active-line'
  import 'codemirror/addon/selection/mark-selection'
  import 'codemirror/addon/selection/selection-pointer'

  /* 括号自动补全 */
  import 'codemirror/addon/edit/closebrackets'
  import 'codemirror/addon/edit/matchbrackets'

  export default {
    name: 'codeEditor',
    components: {
      codemirror
    },
    props: {
      value: {
        type: String,
        default: ''
      },
      lang: {
        type: String,
        default: 'text/javascript'
      },
      skin: {
        type: String,
        default: 'base16-dark'
      },
      showTools: {
        type: Boolean,
        default: false
      },
      cantEditCodes: {
        type: Array,
        default: () => {
          return []
        }
      }
    },
    data() {
      return {
        editor: {
          mode: this.lang,
          code: this.value,
          history: null,
          hasUndo: false,
          hasRedo: false,
          cmDoc: null,
          cmEditor: null
        },
        cantEdit: []
      }
    },
    computed: {
      modes() {
        return [{
          label: '数据',
          options: ['xml', 'json']
        }, {
          label: '前端',
          options: ['css', 'html', 'javascript']
        }, {
          label: '脚本',
          options: ['goovy']
        }, {
          label: '后端',
          options: ['java']
        }]
      },
      cmOptions() {
        return {
          // 语言
          mode: this.lang,
          // 主题样式
          theme: this.skin,
          // tab键空格数
          tabSize: 2,
          // 自动获取焦点
          autofocus: true,
          // 代码折叠
          foldGutter: true,
          // 显示行号
          lineNumbers: true,
          // 自动换行
          lineWrapping: true,
          matchBrackets: true,
          styleActiveLine: true,
          // 自动关闭
          autoCloseBrackets: true,
          continueComments: 'Enter',
          gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter']
        }
      }
    },
    watch: {
      value(newValue, oldValue) {
        if (newValue !== undefined & newValue !== null) this.editor.code = newValue
      },
      'editor.history': {
        handler(newValue, oldValue) {
          this.editor.hasUndo = newValue.undo > 1
          this.editor.hasRedo = newValue.redo > 0
        }
      }
    },
    methods: {
      onCmReady(cm) {
        this.editor.cmEditor = cm
        this.editor.cmDoc = cm.getDoc()
      },
      onCmCodeChange(newCode) {
        this.editor.history = this.historySize()
        this.$emit('input', newCode)
      },
      handleNew() {

      },
      handleSave() {
        this.$emit('save', this.getValue())
      },
      handleSaveAs() {

      },
      handleRevoke() {
        this.editor.cmDoc.undo()
        this.editor.history = this.historySize()
      },
      handleResume() {
        this.editor.cmDoc.redo()
        this.editor.history = this.historySize()
      },
      refresh() {
        this.editor.cmEditor.refresh()
      },
      getValue() {
        return this.editor.cmDoc.getValue()
      },
      historySize() {
        return this.editor.cmDoc.historySize()
      }
    }
  }
</script>

<style lang="scss">
  .editor-readonly {
    color: '#f0e';
  }

  .codeditor-container {
    &__head {
      display: flex !important;
      align-items: center;
      justify-content: space-between;

      .el-form {
        .el-form-item {
          margin-bottom: 0;
        }
      }

      .el-button {
        &--mini {
          padding: 6px;

          .el-icon-adapt {
            font-size: 16px !important;
          }
        }
      }
    }

    &__head.base16-dark {
      background: #181818;
    }

    &__head.base16-light {
      background: #f1f1f1;
    }
  }

  .vue-codemirror {
    flex: 1;
  }
</style>
