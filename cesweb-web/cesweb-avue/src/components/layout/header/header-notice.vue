<template>
  <el-popover placement="bottom" width="350" trigger="click" class="top-bar-item">
    <div slot="reference">
      <el-badge is-dot>
        <i class="icon-remind"></i>
      </el-badge>
    </div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="邮件(1)" name="first"></el-tab-pane>
      <el-tab-pane label="消息(2)" name="second"></el-tab-pane>
      <el-tab-pane label="通知" name="third"></el-tab-pane>
    </el-tabs>
    <el-scrollbar style="height:300px">
      <avue-notice :data="data" :option="option" @page-change="pageChange"></avue-notice>
    </el-scrollbar>
  </el-popover>
</template>

<script>
let list = []

export default {
  name: 'headerNotice',
  data() {
    return {
      activeName: 'first',
      option: {
        props: {
          img: 'img',
          tag: 'tag',
          title: 'title',
          status: 'status',
          subtitle: 'subtitle'
        }
      },
      data: list
    }
  },
  methods: {
    pageChange(page, done) {
      setTimeout(() => {
        this.$message.success('页码' + page)
        this.data = this.data.concat(list)
        done()
      }, 1000)
    }
  }
}
</script>
