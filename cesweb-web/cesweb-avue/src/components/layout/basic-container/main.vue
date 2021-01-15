<template>
  <div class="basic-container" :style="styleName" :class="{'basic-container--block':block}">
    <el-row :gutter="10" v-if="sidebar">
      <el-col :xs="24" :sm="24" :md="6">
        <div class="basic-container__left">
          <slot name="left"></slot>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="18">
        <div class="basic-container__body">
          <slot name="body"></slot>
        </div>
      </el-col>
    </el-row>
    <el-row v-else>
      <el-col :xs="24" :sm="24" :md="24">
        <div class="basic-container__body">
          <slot></slot>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "basicContainer",
  props: {
    radius: {
      type: [String, Number],
      default: 4
    },
    background: {
      type: String,
      default: 'rgba(255,255,255,.2)'
    },
    block: {
      type: Boolean,
      default: true
    },
    sidebar: {
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

<style lang="scss" scoped>
  .basic-container {
    box-sizing: border-box;
    display: flex;
    margin: 0 10px;
    &--block {
      flex: 1;
    }
    &:first-child {
      padding-top: 0;
    }
    .el-row {
      flex: 1;
      .el-col {
        display: flex;
        height: 100%;
        margin-bottom: 0;
      }
    }
    &__left,
    &__body {
      flex: 1;
      background: #fff;
      box-shadow: 1px 0 5px 0 rgba(0, 0, 0, 0.05);
      border-radius: 4px;
    }
  }
</style>
