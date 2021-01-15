<template>
  <div class="avue-header">
    <div class="avue-top">
      <div class="top-bar__left">
        <div v-if="showCollapse" class="avue-breadcrumb cursor-point" :class="[{ 'avue-breadcrumb--active': isCollapse }]">
          <i class="icon-navicon" @click="setCollapse"></i>
        </div>

        <el-tooltip v-if="showRefresh" effect="dark" placement="bottom" :content="$t('navbar.refresh')">
          <div class="avue-refresh cursor-point">
            <i class="el-icon-refresh" @click="setRefresh"></i>
          </div>
        </el-tooltip>
      </div>
      <div class="top-bar__title">
        <div v-if="showMenu" class="top-bar__item top-bar__item--show">
          <header-menu ref="headerMenu"></header-menu>
        </div>
        <span v-if="showSearch" class="top-bar__item">
          <header-search></header-search>
        </span>
      </div>
      <div class="top-bar__right">
        <el-tooltip v-if="showColor" effect="dark" placement="bottom" :content="$t('navbar.color')">
          <div class="top-bar__item">
            <header-color></header-color>
          </div>
        </el-tooltip>

        <el-tooltip v-if="showDebug" effect="dark" placement="bottom" :content="logsFlag?$t('navbar.bug'):logsLen+$t('navbar.bugs')">
          <div class="top-bar__item cursor-point">
            <header-logs></header-logs>
          </div>
        </el-tooltip>

        <el-tooltip v-if="showLock" effect="dark" placement="bottom" :content="$t('navbar.lock')">
          <div class="top-bar__item cursor-point">
            <header-lock></header-lock>
          </div>
        </el-tooltip>

        <el-tooltip v-if="showTheme" effect="dark" placement="bottom" :content="$t('navbar.theme')">
          <div class="top-bar__item cursor-point">
            <header-theme></header-theme>
          </div>
        </el-tooltip>

        <el-tooltip effect="dark" placement="bottom" :content="$t('navbar.notice')">
          <div class="top-bar__item cursor-point">
            <header-notice></header-notice>
          </div>
        </el-tooltip>

        <el-tooltip effect="dark" placement="bottom" :content="$t('navbar.language')">
          <div class="top-bar__item cursor-point">
            <header-lang></header-lang>
          </div>
        </el-tooltip>

        <el-tooltip v-if="showFullScreen" effect="dark" placement="bottom" :content="isFullScreen?$t('navbar.screenfullF'):$t('navbar.screenfull')">
          <div class="top-bar__item cursor-point">
            <i class="icon-header" :class="isFullScreen?'icon-fullscreen-exit':'icon-fullscreen'" @click="handleScreen"></i>
          </div>
        </el-tooltip>

        <img class="top-bar__img" :src="getAvatar()">
        <el-dropdown>
          <span class="top-bar-dropdown el-dropdown-link cursor-point"> {{userInfo.username}} <i class="el-icon-arrow-down" :style="'line-height:64px!important;'"></i> </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><router-link to="/">{{$t('navbar.dashboard')}}</router-link></el-dropdown-item>
            <el-dropdown-item><router-link to="/info/index">{{$t('navbar.userinfo')}}</router-link></el-dropdown-item>
            <el-dropdown-item><router-link to="/info/setting">{{$t('navbar.setting')}}</router-link></el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">{{$t('navbar.logOut')}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import { resetRouter } from '@/router/router'
import { fullscreenToggel, listenfullscreen } from '@/utils/util'
import headerLock from './header/header-lock'
import headerLang from './header/header-lang'
import headerMenu from './header/header-menu'
import headerLogs from './header/header-logs'
import headerTheme from './header/header-theme'
import headerColor from './header/header-color'
import headerSearch from './header/header-search'
import headerNotice from './header/header-notice'

export default {
  name: 'cesHeader',
  components: {
    headerLock,
    headerLang,
    headerMenu,
    headerLogs,
    headerTheme,
    headerColor,
    headerSearch,
    headerNotice
  },
  computed: {
    ...mapState({
      showLock: state => state.comm.showLock,
      showMenu: state => state.comm.showMenu,
      showDebug: state => state.comm.showDebug,
      showTheme: state => state.comm.showTheme,
      showColor: state => state.comm.showColor,
      showSearch: state => state.comm.showSearch,
      showRefresh: state => state.comm.showRefresh,
      showCollapse: state => state.comm.showCollapse,
      showFullScreen: state => state.comm.showFullScreen
    }),
    ...mapGetters([
      'tag',
      'tagHome',
      'tagList',
      'logsLen',
      'logsFlag',
      'userInfo',
      'isCollapse',
      'isFullScreen'
    ])
  },
  data () {
    return { }
  },
  mounted () {
    listenfullscreen(this.setScreen)
  },
  methods: {
    handleScreen () {
      fullscreenToggel()
    },
    setCollapse () {
      this.$store.commit('SET_COLLAPSE')
    },
    setRefresh () {
      this.$router.replace({
        path: "/redirect",
        query: { nextPath: this.$route.path }
      })
    },
    setScreen () {
      this.$store.commit('SET_FULLSCREEN')
    },
    getAvatar() {
      return `${this.apiUrl}${this.userInfo.avatar}`
    },
    logout () {
      this.$confirm(this.$t('logoutTip'), this.$t('tip'), {
        confirmButtonText: this.$t('submitText'),
        cancelButtonText: this.$t('cancelText'),
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          resetRouter()
          this.$router.push({ path: '/login' })
        })
      })
    }
  }
}
</script>

<style lang="scss" >
  .cursor-point {
    cursor: pointer;
  }
  .icon-header {
    font-size: 24px!important;
    line-height: 24px!important;
  }
  .top-bar-dropdown {
    display: flex;
    i {
      line-height: 24px!important;
    }
  }
  .top-bar {
    &__left {
      .avue-breadcrumb  {
        display: inline-block;
      }
      .avue-refresh  {
        display: inline-block;
        margin: 0 10px;
        line-height: 24px;
        position: relative;
        top: -2px;
        i {
          font-size: 24px;
          line-height: inherit;
        }
      }
    }
    &__title {
      padding-left: 80px!important;
    }
    &__right {
      .top-bar {
        &__item {
          display: flex;
          height: 32px;
          align-items: center;
          justify-content: center;
          .top-bar-item {
            display: flex;
            i {
              font-size: 24px!important;
              line-height: 24px!important;
            }
            span {
              display: flex;
              justify-content: center;
            }
            .el-badge {
              display: flex;
              sup {
                top: 0;
              }
            }
          }
          div {
            display: flex;
          }
        }
      }
    }
    &__tools {
      position: relative;
      display: inline-block;
      margin: 0 10px;
      i {
        font-size: 24px!important;
        line-height: normal;
      }
    }
  }
</style>
