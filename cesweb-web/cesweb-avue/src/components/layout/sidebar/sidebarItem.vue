<template>
  <div class="menu-wrapper">
    <template v-for="item in menu">
      <el-menu-item v-if="validatenull(item[childrenKey]) && vaildRoles(item)" :index="item[pathKey]" @click="open(item)" :key="item[labelKey]" :class="{'is-active':vaildAvtive(item)}">
        <i :class="item[iconKey]"></i>
        <span slot="title" :alt="item[pathKey]">{{generateTitle(item)}}</span>
      </el-menu-item>
      <el-submenu v-else-if="!validatenull(item[childrenKey])&&vaildRoles(item)" :index="item[pathKey]" :key="item[labelKey]">
        <template slot="title">
          <i :class="item[iconKey]"></i>
          <span slot="title" :class="{'el-menu--display':collapse && first}">{{generateTitle(item)}}</span>
        </template>
        <template v-for="(child, cindex) in item[childrenKey]">
          <el-menu-item v-if="validatenull(child[childrenKey])" :index="child[pathKey], cindex" :class="{'is-active':vaildAvtive(child)}" :key="child[labelKey]" @click="open(child)">
            <i :class="child[iconKey]"></i>
            <span slot="title">{{generateTitle(child)}}</span>
          </el-menu-item>
          <sidebar-item v-else :menu="[child]" :key="cindex" :props="props" :screen="screen" :collapse="collapse"></sidebar-item>
        </template>
      </el-submenu>
    </template>
  </div>
</template>
<script>
import { mapGetters } from "vuex"
import { validateNull } from "@/utils/validate"
import config from "./config"

export default {
  name: "sidebarItem",
  data() {
    return {
      config: config
    }
  },
  props: {
    menu: {
      type: Array,
      default: () => {
        return []
      }
    },
    screen: {
      type: Number,
      default: null
    },
    first: {
      type: Boolean,
      default: false
    },
    props: {
      type: Object,
      default: () => {
        return {}
      }
    },
    collapse: {
      type: Boolean
    }
  },
  created() {},
  mounted() {},
  computed: {
    ...mapGetters(["roles"]),
    pathKey() {
      return this.props.path || this.config.propsDefault.path
    },
    iconKey() {
      return this.props.icon || this.config.propsDefault.icon
    },
    labelKey() {
      return this.props.label || this.config.propsDefault.label
    },
    childrenKey() {
      return this.props.children || this.config.propsDefault.children
    },
    nowTagValue() {
      return this.$router.$routerPlugin.getValue(this.$route)
    }
  },
  methods: {
    generateTitle(item) {
      return this.$router.$routerPlugin.generateTitle(item[this.labelKey], (item.meta || {}).i18n)
    },
    vaildAvtive(item) {
      const groupFlag = (item["group"] || []).some(ele => this.$route.path.includes(ele))
      return this.nowTagValue === item[this.pathKey] || groupFlag
    },
    vaildRoles(item) {
      item.meta = item.meta || {}
      return item.meta.roles ? item.meta.roles.includes(this.roles) : true
    },
    validatenull(val) {
      return validateNull(val)
    },
    open(item) {
      if (this.screen <= 1) this.$store.commit("SET_COLLAPSE")
      this.$router.$routerPlugin.meta = item.meta
      this.$router.$routerPlugin.group = item.group
      this.$router.push({path: this.$router.$routerPlugin.getPath({ name: item[this.labelKey], src: item[this.pathKey] }, item.meta), query: item.query})
    }
  }
}
</script>
