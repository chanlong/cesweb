<template>
  <el-form class="login-form" status-icon :rules="loginRules" ref="loginForm" :model="loginForm" label-width="0">
    <!-- 租户下拉框 -->
    <el-form-item prop="tenantid">
      <el-select size="small" class="login-select animated fadeIn" v-model="active" placeholder="点击请选择租户" @change="handleCommand">
        <i slot="prefix" class="icon-huanyingye"></i>
        <el-option v-for="tenant in tenantList" :key="tenant.id" :label="tenant.name" :value="tenant.id"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item prop="username">
      <el-input size="small" @keyup.enter.native="handleLogin" v-model="loginForm.username" auto-complete="off" :placeholder="$t('login.username')">
        <i slot="prefix" class="icon-yonghu"></i>
      </el-input>
    </el-form-item>

    <el-form-item prop="password">
      <el-input size="small" @keyup.enter.native="handleLogin" :type="passwordType" v-model="loginForm.password" auto-complete="off" :placeholder="$t('login.password')">
        <i class="el-icon-view el-input__icon" slot="suffix" @click="showPassword"></i>
        <i slot="prefix" class="icon-mima"></i>
      </el-input>
    </el-form-item>

    <!-- 图片验证 -->
    <el-form-item prop="code">
      <Verify @success="verifySuccess" :mode="'pop'" :captcha-type="'blockPuzzle'" :img-size="{ width: '330px', height: '155px' }" ref="verify" />
    </el-form-item>

    <el-form-item>
      <el-button class="login-submit" type="primary" size="small" :loading="loading" @click.native.prevent="handleLogin">{{loading ? loadingText : $t('login.submit')}}</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { mapGetters } from "vuex"
import { setStore } from '@/utils/store'
import GlobalRequest from '@/api/crud'
import Verify from "@/components/verifition/Verify"

export default {
  name: "userlogin",
  components: {
    Verify
  },
  data() {
    return {
      active: '',
      loading: false,
      loadingText: '正在验证身份，请稍后...',
      tenantList: [],
      socialForm: {
        code: '',
        state: ''
      },
      loginForm: {
        code: '',
        username: 'admin',
        password: '123456',
        randomStr: 'blockPuzzle'
      },
      code: {
        src: undefined,
        len: 4
      },
      loginRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, message: "密码长度最少为6位", trigger: "blur" }
        ]
      },
      passwordType: "password"
    }
  },
  created() {
    this.getTenantList()
  },
  computed: {
    ...mapGetters(["tagHome"])
  },
  methods: {
    showPassword() {
      this.passwordType == "" ? (this.passwordType = "password") : (this.passwordType = "")
    },
    handleCommand (command) {
      setStore({ name: 'tenantId', content: command })
    },
    getTenantList () {
      GlobalRequest.server('/system/tenant').fetchList().then(response => {
        this.tenantList = response.data.data
      })
    },
    handleLogin() {
      this.$refs.verify.show()
    },
    verifySuccess(params) {
      this.loading = true
      this.loginForm.code = params.captchaVerification
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$store.dispatch('LoginByUsername', this.loginForm).then(() => {
            this.$router.push({path: this.tagHome.value})
          })
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .login-select {
    display: block;
  }
</style>
