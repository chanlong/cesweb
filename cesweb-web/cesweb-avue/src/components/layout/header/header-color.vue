<template>
  <el-color-picker size="mini"
                   class="theme-picker top-bar-item"
                   popper-class="theme-picker-dropdown"
                   v-model="themeVal">
  </el-color-picker>
</template>

<script>
import color from '@/mixins/color'

export default {
  name: 'headerColor',
  mixins: [color()],
  data() {
    return {
      chalk: ''
    }
  }
}
</script>

<style lang="scss">
  .theme-picker .el-color-picker__trigger {
    vertical-align: middle;
    height: 30px!important;
    width: 30px!important;
  }
  .theme-picker-dropdown .el-color-dropdown__link-btn {
    display: none;
  }
</style>
