<template>
  <div style="display: flex; padding: 10px 0px; justify-content: space-between;">
    <div>
      <el-button-group>
        <el-button type="primary" size="mini" icon="el-icon-adapt iconfont icon-save" @click="handleSave">保存</el-button>
        <el-button type="primary" size="mini" icon="el-icon-upload" @click="handleDeploy">发布</el-button>
      </el-button-group>

      <el-divider direction="vertical"></el-divider>

      <el-button-group>
        <!-- <el-tooltip effect="dark" content="新建" placement="bottom">
          <el-button size="mini" icon="el-icon-circle-plus" @click="handleCreate" />
        </el-tooltip> -->
        <el-tooltip effect="dark" content="源码" placement="bottom">
          <el-button size="mini" icon="el-icon-adapt iconfont icon-codegen" @click="handleCode" />
        </el-tooltip>
        <el-tooltip effect="dark" content="自适应屏幕" placement="bottom">
          <el-button size="mini" icon="el-icon-rank" @click="fitViewport" />
        </el-tooltip>
        <el-tooltip effect="dark" content="放大" placement="bottom">
          <el-button size="mini" icon="el-icon-zoom-in" @click="zoomViewport(true)" />
        </el-tooltip>
        <el-tooltip effect="dark" content="缩小" placement="bottom">
          <el-button size="mini" icon="el-icon-zoom-out" @click="zoomViewport(false)" />
        </el-tooltip>
        <el-tooltip effect="dark" content="撤销" placement="bottom">
          <el-button size="mini" icon="el-icon-back" @click="bpmnModeler.commandStack.undo()" />
        </el-tooltip>
        <el-tooltip effect="dark" content="恢复" placement="bottom">
          <el-button size="mini" icon="el-icon-right" @click="bpmnModeler.commandStack.redo()" />
        </el-tooltip>
      </el-button-group>
    </div>
    <div>
      <el-button size="mini" icon="el-icon-download" @click="bpmnModeler.saveXML(true)">下载xml</el-button>
      <el-button size="mini" icon="el-icon-picture" @click="bpmnModeler.saveSVG(true)">下载svg</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'designerHeader',
    inject: ['modeler'],
    data() {
      return {
        zoom: 1,
        bpmnModeler: null,
      }
    },
    mounted() {
      this.bpmnModeler = this.modeler()
    },
    methods: {
      handleCode() {
        this.$emit("code", this.bpmnModeler.processForm)
      },
      handleSave() {
        this.$emit("save", this.bpmnModeler.processForm)
      },
      handleDeploy() {
        this.$emit("deploy", this.bpmnModeler.processForm)
      },
      // 画布缩放
      fitViewport() {
        this.zoom = this.bpmnModeler.canvas.zoom('fit-viewport')
      },
      zoomViewport(zoomIn = true) {
        this.zoom += (zoomIn ? 0.1 : -0.1)
        this.bpmnModeler.canvas.zoom(this.zoom)
      }
    }
  }
</script>

<style>
</style>
