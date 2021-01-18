<template>
  <el-tabs v-model="activeName" type="card">
    <el-tab-pane v-for="item in data" :key="item.k" :lazy="true" :label="item.k" :name="item.k">
      <Java :value="item.v" height="600px"/>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
  import Java from '@/components/editor/index'
  import GlobalRequest from '@/api/crud'

  export default {
    name: 'Preview',
    components: {Java},
    props: {
      /* eslint-disable-next-line vue/require-default-prop, vue/require-prop-types */
      queryData: {}
    },
    data() {
      return {
        data: [], height: '', activeName: 'Entity.java'
      }
    },
    created() {
      GlobalRequest.server('/develope/generator/preview').fetch(this.queryData).then(res => {
        let map = res.data.data

        for (let key in map) {
          let k = key.replace(/^template\/|.vm$/g, '')
          let v = map[key]
          let obj = {k,v}
          this.data.push(obj)
        }
      })
    }
  }
</script>
