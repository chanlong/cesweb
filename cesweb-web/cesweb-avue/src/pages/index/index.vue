<template>
  <div class="avue-contail" :class="{'avue--collapse':isCollapse}">
    <!-- 顶部导航栏 -->
    <ces-header></ces-header>

    <div class="avue-layout">
      <!-- 左侧导航栏 -->
      <ces-sidebar></ces-sidebar>

      <!-- 右侧主体区域 -->
      <div class="avue-main" :class="{'avue-main--fullscreen':!isMenu}">
        <!-- 顶部搜索框 -->
        <transition name="fade-scale"></transition>

        <!-- 顶部标签卡 -->
        <ces-navi-tag></ces-navi-tag>

        <!-- 主体视图层 -->
        <div style="position:relative;display:flex;flex:1;overflow-y:auto;overflow-x:hidden;" id="avue-view" v-show="!isSearch">
          <keep-alive>
            <router-view class="avue-view" v-if="$route.meta.keepAlive" />
          </keep-alive>
          <router-view class="avue-view" v-if="!$route.meta.keepAlive" />
        </div>
      </div>
    </div>
    <!--
    <el-footer class="avue-footer">
      <img src="/svg/logo.svg" alt="" class="logo">
      <p class="copyright">© 2018 Avue designed by smallwei</p>
    </el-footer>
    -->
    <div class="avue-shade" @click="showCollapse"></div>
  </div>
</template>

<script>
import Stomp from 'stomp-websocket'
import * as SockJS from 'sockjs-client'
import { mapGetters } from "vuex"
import cesHeader from '@/components/layout/header'
import cesSidebar from '@/components/layout/sidebar'
import cesNaviTag from "@/components/layout/navitag"
import { validateNull } from "@/utils/validate"
import { getStore } from "@/utils/store"
import admin from "@/utils/admin"
import store from '@/store'

export default {
  components: {
    cesHeader,
    cesSidebar,
    cesNaviTag
  },
  name: 'index',
  provide () {
    return {
      index: this
    }
  },
  data () {
    return {
      // 计时器
      timer: '',
      // 搜索控制
      isSearch: false,
      // 刷新token锁
      refreshLock: false,
      // 刷新token的时间
      refreshTime: ''
    }
  },
  created () {
    // 实时检测刷新token
    this.refreshToken()
  },
  mounted () {
    this.init()
    // 默认开启websocket，启用工作流通知
    // this.initWebSocket()
  },
  destroyed() {
    clearInterval(this.timer)
    clearInterval(this.refreshTime)
    // 默认开启websocket，启用工作流通知
    // this.disconnect()
  },
  computed: mapGetters(['userInfo', 'isMenu', 'isLock', 'isCollapse', 'website', 'expires_in']),
  methods: {
    // 屏幕检测
    init () {
      this.$store.commit("SET_SCREEN", admin.getScreen())
      window.onresize = () => {setTimeout(() => { this.$store.commit("SET_SCREEN", admin.getScreen()) }, 0)}
    },
    // 打开菜单
    openMenu (item = {}) {
      this.$store.dispatch("GetMenu", { type: true, id: item.id }).then(data => {
        if (data.length !== 0) {
          this.$router.$routerPlugin.formatRoutes(data, true)
        }
        // 当点击顶部菜单做的事件
        if (!validateNull(item)) {
          let itemActive = {}, childItemActive = 0
          //vue-router路由
          if (item.path) {
            itemActive = item
          } else {
            if (this.menu[childItemActive].length == 0) {
              itemActive = this.menu[childItemActive]
            } else {
              itemActive = this.menu[childItemActive].children[childItemActive]
            }
          }
          this.$store.commit('SET_MENU_ID', itemActive)
          //this.$router.push({ path: this.$router.$routerPlugin.getPath({ name: itemActive.label, src: itemActive.path }, itemActive.meta) })
        }
      })
    },
    showCollapse () {
      this.$store.commit("SET_COLLAPSE")
    },
    // 10分钟检测一次token
    refreshToken () {
      this.refreshTime = setInterval(() => {
        const token = getStore({ name: 'access_token', debug: true })
        if (validateNull(token)) return
        if (this.expires_in <= 1000 && !this.refreshLock) {
          this.refreshLock = true
          this.$store.dispatch('RefreshToken').catch(() => {
            clearInterval(this.refreshTime)
          })
          this.refreshLock = false
        }
        this.$store.commit('SET_EXPIRES_IN', this.expires_in - 10)
      }, 10000)
    },
    initWebSocket() {
      this.connection()
      const self = this
      // 断开重连机制,尝试发送消息,捕获异常发生时重连
      this.timer = setInterval(() => {
        try {
          self.stompClient.send('test')
        } catch (err) {
          console.log('断线了: ' + err)
          self.connection()
        }
      }, 5000)
    },
    connection() {
      const token = store.getters.access_token
      const headers = { 'Authorization': 'Bearer ' + token }
      const TENANT_ID = getStore({name: 'tenantId'}) ? getStore({name: 'tenantId'}) : '1'

      // 建立连接对象
      this.socket = new SockJS('/act/ws')// 连接服务端提供的通信接口，连接以后才可以订阅广播消息和个人消息
      // 获取STOMP子协议的客户端对象
      this.stompClient = Stomp.over(this.socket)
      this.stompClient.debug = null
      // 向服务器发起websocket连接
      this.stompClient.connect(headers, () => {
        // 订阅服务端提供的某个topic;
        this.stompClient.subscribe('/task/' + this.userInfo.username + '-' + TENANT_ID + '/remind', (msg) => {
          this.$notify({
            title: '协同提醒',
            type: 'warning',
            dangerouslyUseHTMLString: true,
            message: msg.body + '任务，请及时处理',
            offset: 60
          })
        })
      }, (err) => {
        console.log(err)
      })
    },
    disconnect() {
      if (this.stompClient != null) {
        this.stompClient.disconnect()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .avue-main {
    display: flex;
    padding-bottom: 10px;
    flex-direction: column;
  }
</style>
