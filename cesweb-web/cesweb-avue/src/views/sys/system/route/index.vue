<template>
  <basic-container>
    <el-alert title="路由配置是非常专业的事情，不建议非工程师操作" type="warning" />
    <div class="editor-toolbar">
      <el-button @click="edit()">更新</el-button>
    </div>
    <div class="editor-content">
      <vue-json-editor v-model="json" lang="zh" :show-btns="false" />
    </div>
  </basic-container>
</template>

<script>
import vueJsonEditor from "vue-json-editor"
import GlobalRequest from '@/api/crud'

export default {
  // 注入vueJsonEditor组件
  components: {
    vueJsonEditor
  },
  data() {
    return {
      json: null
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      GlobalRequest.server('/system/route').fetch().then(response => {
        const result = response.data.data
        for (var i = 0; i < result.length; i++) {
          const route = result[i]
          if (route.predicates) {
            const predicates = route.predicates
            route.predicates = JSON.parse(predicates)
          }
          if (route.filters) {
            const filters = route.filters
            route.filters = JSON.parse(filters)
          }
        }
        this.json = result
      })
    },
    edit() {
      GlobalRequest.server('/system/route').update(this.json).then(() => {
        GlobalRequest.server('/actuator/refresh').post().then(() => {
          this.$notify({
            title: "成功",
            message: "更新成功",
            type: "success",
            duration: 2000
          })
        })
      })
    }
  }
}
</script>

<style lang="scss">
  div.jsoneditor-contextmenu div.jsoneditor-icon {
    position: relative;
  }
  .editor-content {
    height: calc(100% - 70px);
    & > div {
      height:100%;
      .jsoneditor-vue {
         height:100%;
      }
    }
  }
</style>
