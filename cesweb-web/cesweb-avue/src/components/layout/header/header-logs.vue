<template>
  <span @click="logsFlag?'':handleOpen()" class="top-bar-item">
    <el-badge :value="logsFlag?'':logsLen" :max="99">
      <i class="icon-errorlog"></i>
    </el-badge>
    <el-dialog title="日志" width="100%" fullscreen append-to-body :visible.sync="box">
      <logs></logs>
    </el-dialog>
  </span>
</template>

<script>
import { mapGetters } from 'vuex'
import logs from '@/pages/logs/index'

export default {
  name: 'headerLogs',
  components: { logs },
  data() {
    return {
      box: false
    }
  },
  computed: {
    ...mapGetters(['logsFlag', 'logsLen'])
  },
  methods: {
    handleOpen() {
      this.box = true
    }
  }
}
</script>
