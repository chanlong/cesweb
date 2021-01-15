<template>
  <el-dropdown trigger="click" @command="handleSetLanguage" class="top-bar-item">
    <i class="icon-i18n"></i>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item :disabled="language==='zh'" command="zh">中文</el-dropdown-item>
      <el-dropdown-item :disabled="language==='en'" command="en">English</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'headerLang',
  computed: {
    ...mapGetters(['language', 'tag'])
  },
  methods: {
    handleSetLanguage(lang) {
      this.$i18n.locale = lang
      this.$store.commit('SET_LANGUAGE', lang)
      let tag = this.tag
      let title = this.$router.$routerPlugin.generateTitle(tag.label, (tag.meta || {}).i18n)
      // 根据当前的标签也获取label的值动态设置浏览器标题
      this.$router.$routerPlugin.setTitle(title)
    }
  }
}
</script>
