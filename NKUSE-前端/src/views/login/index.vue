<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">欢迎来到六级报考系统</h3>
      </div>
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
          <svg-icon
            :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
          />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width: 100%; margin-bottom: 10px"
        @click.native.prevent="handleLogin"
        >登录</el-button
      >
      <el-button
        style="width: 100%; margin-bottom: 20px; margin-left: 0px"
        @click.native.prevent="handleRegist"
        >注册</el-button
      >
    </el-form>
    <el-dialog
      class="Regdialog"
      title="用户注册"
      :visible.sync="dialogFormVisible"
    >
      <el-form :model="userForm" ref="userFormRef" :rules="rules">
        <el-form-item
          label="用户id"
          :label-width="formLabelWidth"
          prop="userId"
        >
          <el-input v-model="userForm.userId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          :label-width="formLabelWidth"
          prop="userPassword"
        >
          <el-input
            v-model="userForm.userPassword"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="邮箱"
          :label-width="formLabelWidth"
          prop="userEmail"
        >
          <el-input v-model="userForm.userEmail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="姓名"
          :label-width="formLabelWidth"
          prop="userName"
        >
          <el-input v-model="userForm.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="证件号"
          :label-width="formLabelWidth"
          prop="userIdnumber"
        >
          <el-input
            v-model="userForm.userIdnumber"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="手机号"
          :label-width="formLabelWidth"
          prop="userPhonenumber"
        >
          <el-input
            v-model="userForm.userPhonenumber"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="clearForm()">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { validUsername } from "@/utils/validate";
import UserApi from "@/api/all_regist";
export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("请输入正确的用户名"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 0) {
        callback(new Error("密码不能少于6位"));
      } else {
        callback();
      }
    };
    return {
      formLabelWidth: "80px",
      userForm: {},
      dialogFormVisible: false,
      loginForm: {
        username: "admin",
        password: "111111",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", validator: validateUsername },
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword },
        ],
      },
      loading: false,
      passwordType: "password",
      redirect: undefined,
      rules: {
        userId: [
          { required: true, message: "请输入用户ID", trigger: "blur" },
          {
            pattern: /^\d{6}$/,
            message: "用户ID必须为6位数字",
            trigger: "blur",
          },
        ],
        userPassword: [
          { required: true, message: "请输入密码", trigger: "blur" },
        ],
        userEmail: [
          { required: false, message: "请输入邮箱", trigger: "blur" },
          {
            type: "email",
            message: "请输入有效的邮箱",
            trigger: ["blur", "change"],
          },
        ],
        userName: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        userIDnumber: [
          { required: false, message: "请输入证件号", trigger: "blur" },
        ],
        userPhonenumber: [
          { required: false, message: "请输入手机号", trigger: "blur" },
          {
            pattern: /^\d{11}$/,
            message: "手机号格式不正确",
            trigger: ["blur", "change"],
          },
        ],
      },
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  methods: {
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("user/login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" });
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    submitForm() {
      this.$refs.userFormRef.validate((valid) => {
        if (valid) {
          UserApi.regist(this.userForm).then((response) => {
            this.dialogFormVisible = true;
            this.userForm.userRole = 1;
            if (response.code == 20001) {
              this.$message({
                message: response.message,
                type: "error",
              });
              return false;
            } else {
              this.$message({
                message: "注册成功",
                type: "success",
              });
              this.dialogFormVisible = false;
              return true;
            }
          });
        } else {
          return false;
        }
      });
    },
    clearForm() {
      this.dialogFormVisible = false;
    },
    handleRegist() {
      this.dialogFormVisible = true;
      this.userForm.userRole = 1;
      return true;
    },
  },
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
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

  .el-dialog__body {
    background-color: $bg;
    border: 0;
  }
  .el-dialog__header {
    background-color: $bg;
    border: 0;
  }
  .el-dialog__footer {
    background-color: $bg;
    border: 0;
  }
  .el-dialog__title {
    color: #fff;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  background-image: url("../../assets/bg5.png");
  background-size: 100%;

  display: flex;
  align-items: center;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 0;
    margin: 0 auto;
    overflow: hidden;
    background-color: #283443;
    border-radius: 8px;
    opacity: 0.9;
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
