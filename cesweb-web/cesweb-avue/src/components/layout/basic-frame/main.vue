<template>
  <div class="basic-frame" :style="styleName" :class="{'basic-frame--block':block}">
    <el-card class="basic-frame--body">
      <slot></slot>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "basicFrame",
  props: {
    radius: {
      type: [String, Number],
      default: 10
    },
    background: {
      type: String,
      default: '#fff'
    },
    block: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    styleName() {
      return {
        borderRadius: this.setPx(this.radius),
        background: this.background
      }
    }
  }
};
</script>

<style lang="scss">
.basic-frame {
    padding: 10px 6px;
    box-sizing: border-box;
    &--block {
        height: 100%;
        .el-card {
            height: 100%;
        }
    }
    &--body {
      div {
        padding: 0;
      }
    }
    .el-card {
        width: 100%;
    }
    &:first-child {
        padding-top: 0;
    }
}
</style>
