<template>
  <div class="top-bar-item">
    <el-dialog title="请选择主题" :visible.sync="box" width="50%" custom-class="ces-theme-dialog">
      <el-radio-group v-model="text" class="list">
        <el-row :span="24">
          <el-col v-for="(item, index) in list" :key="index" :md="4" :xs="12" :sm="4">
            <el-radio :label="item.value">{{item.name}}</el-radio>
          </el-col>
        </el-row>
      </el-radio-group>
    </el-dialog>
    <span>
      <i class="icon-theme" @click="open"></i>
    </span>
  </div>
</template>

<script>
import { setTheme } from '@/utils/util'
import { mapGetters } from 'vuex'

export default {
  name: 'headerTheme',
  data() {
    return {
      box: false,
      text: '',
      list: [{
        name: '默认主题',
        value: 'default'
      },
      {
        name: '白色主题',
        value: 'theme-white'
      },
      {
        name: '黑色主题',
        value: 'theme-dark'
      },
      {
        name: 'hey主题',
        value: 'theme-hey'
      },
      {
        name: '炫彩主题',
        value: 'theme-star'
      },
      {
        name: 'vip尊贵主题',
        value: 'theme-vip'
      },
      {
        name: '智能工厂主题',
        value: 'theme-bule'
      },
      {
        name: 'iview主题',
        value: 'theme-iview'
      },
      {
        name: 'cool主题',
        value: 'theme-cool'
      },
      {
        name: 'd2主题',
        value: 'theme-d2'
      },
      {
        name: 'hey主题',
        value: 'theme-hey'
      },
      {
        name: 'renren主题',
        value: 'theme-renren'
      }]
    }
  },
  watch: {
    text: function(val) {
      this.$store.commit('SET_THEME_NAME', val)
      setTheme(val)
    }
  },
  computed: {
    ...mapGetters(['themeName'])
  },
  mounted() {
    this.text = this.themeName
    if (!this.text) {
      this.text = ''
    }
  },
  methods: {
    open() {
      this.box = true
    }
  }
}
</script>

<style lang="scss">
  .ces-theme-dialog {
    .el-dialog__header {
      padding: 20px;
      line-height: 24px;
      background-color: #fcfcfc;
      box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
      .el-dialog__headerbtn {
        top: auto;
        .el-dialog__close {
          line-height: 24px;
        }
      }
    }

    .list {
      width: 100%;
    }
  }
</style>
