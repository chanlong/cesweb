<template>
  <div class="top-menu">
    <el-menu :default-active="current.id" mode="horizontal" text-color="#333" :active-text-color="themeVal">
      <template v-for="(item, index) in items">
        <el-menu-item :index="item.id" @click.native="openMenu(item)" :key="index">
          <template slot="title">
            <i :class="item.icon"></i>
            <span>{{generateTitle(item)}}</span>
          </template>
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import color from '@/mixins/color'

export default {
  name: 'headerMenu',
  mixins: [color()],
  data() {
    return {
      items: [],
      current: {},
      activeIndex: 0
    }
  },
  inject: ['index'],
  created() {
    this.getMenu()
  },
  computed: {
    ...mapGetters(['tagCurrent', 'menu'])
  },
  methods: {
    getMenu() {
      this.$store.dispatch('GetTopMenu').then(res => {
        this.items = res
        this.current = res[this.activeIndex]
        this.openMenu(this.current)
      })
    },
    openMenu(item) {
      this.index.openMenu(item)
    },
    generateTitle(item) {
      return this.$router.$routerPlugin.generateTitle(item.label, (item.meta || {}).i18n)
    }
  }
}
</script>

<style lang="scss" scoped>
  .top-menu {
    i {
      font-size: 30px!important;
    }
  }
  .el-menu-item {
    span {
      margin-left: 5px;
    }
  }
</style>
