<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">欢迎来到六级报考系统</h3>
      </div>
      <el-form-item label="考试省份" prop="region">
        <el-select v-model="loginForm.region" placeholder="请选择考试省份">
          <el-option label="北京" value="beijing"></el-option>
          <el-option label="天津" value="tianjin"></el-option>
          <el-option label="河北" value="hebei"></el-option>
          <el-option label="山西" value="shanxi"></el-option>
          <el-option label="内蒙古" value="neimenggu"></el-option>
          <el-option label="辽宁" value="liaoning"></el-option>
          <el-option label="吉林" value="jilin"></el-option>
          <el-option label="黑龙江" value="heilongjiang"></el-option>
          <el-option label="上海" value="shanghai"></el-option>
          <el-option label="江苏" value="jiangsu"></el-option>
          <el-option label="浙江" value="zhejiang"></el-option>
          <el-option label="安徽" value="anhui"></el-option>
          <el-option label="福建" value="fujian"></el-option>
          <el-option label="江西" value="jiangxi"></el-option>
          <el-option label="山东" value="shandong"></el-option>
          <el-option label="河南" value="henan"></el-option>
          <el-option label="湖北" value="hubei"></el-option>
          <el-option label="湖南" value="hunan"></el-option>
          <el-option label="广东" value="guangdong"></el-option>
          <el-option label="广西" value="guangxi"></el-option>
          <el-option label="海南" value="hainan"></el-option>
          <el-option label="重庆" value="chongqing"></el-option>
          <el-option label="四川" value="sichuan"></el-option>
          <el-option label="贵州" value="guizhou"></el-option>
          <el-option label="云南" value="yunnan"></el-option>
          <el-option label="西藏" value="xizang"></el-option>
          <el-option label="陕西" value="shanxi1"></el-option>
          <el-option label="甘肃" value="gansu"></el-option>
          <el-option label="青海" value="qinghai"></el-option>
          <el-option label="宁夏" value="ningxia"></el-option>
          <el-option label="新疆" value="xinjiang"></el-option>
          <el-option label="台湾" value="taiwan"></el-option>
          <el-option label="香港" value="xianggang"></el-option>
          <el-option label="澳门" value="aomen"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="Password"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>

      

    </el-form>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码不能少于6位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: 'admin',
        password: '111111'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  background-image: url('../../assets/bg5.png');
  background-size: 100%;

  display:flex;
  align-items:center;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 0;
    margin: 0 auto;
    overflow: hidden;
    background-color: #283443;
    border-radius:8px;
    opacity:0.9;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
