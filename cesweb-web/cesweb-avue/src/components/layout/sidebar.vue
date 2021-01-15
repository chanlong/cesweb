<template>
  <div class="avue-left">
    <div class="avue-sidebar">
      <logo></logo>

      <el-scrollbar style="height:100%">
        <div v-if="validatenull(menu)" class="avue-sidebar--tip">{{$t('menuTip')}}</div>
        <el-menu unique-opened mode="vertical" :default-active="nowTagValue" :show-timeout="200" :collapse="keyCollapse">
          <sidebar-item first :menu="menu" :screen="screen" :props="website.menu.props" :collapse="keyCollapse"></sidebar-item>
        </el-menu>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import logo from "@/components/logo"
import sidebarItem from "./sidebar/sidebarItem"

export default {
  name: "cesSidebar",
  components: {
    sidebarItem,
    logo
  },
  inject: ["index"],
  data() {
    return {}
  },
  created() {
    // 已经通过顶部菜单加载左侧菜单，此处无需重复加载
    // this.index.openMenu(this.menuId)
  },
  computed: {
    ...mapGetters(["website", "menu", "tag", "keyCollapse", "screen"]),
    nowTagValue: function() {
      return this.$router.$routerPlugin.getValue(this.$route)
    }
  }
};
</script>

<style lang="scss" scoped>

</style>
