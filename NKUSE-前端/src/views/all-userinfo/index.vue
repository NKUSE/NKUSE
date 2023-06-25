<template>
  <div style="min-height: 100%; padding-bottom: 100px">
    <el-header>
      <div class="title-section">
        <h1 class="title">个人信息管理</h1>
      </div>
    </el-header>
    <el-divider></el-divider>
    <div class="card-container">
      <el-card>
        <el-row>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <el-form :model="userinfo" ref="userinfoRef">
                <el-form-item
                  label="用户ID"
                  :label-width="formLabelWidth"
                  prop="userid"
                >
                  <el-input
                    v-model="userid"
                    autocomplete="off"
                    disabled
                  ></el-input>
                </el-form-item>
                <el-form-item
                  label="姓名"
                  :label-width="formLabelWidth"
                  prop="name"
                >
                  <el-input
                    v-model="userinfo.name"
                    autocomplete="off"
                    :disabled="edit"
                  ></el-input>
                </el-form-item>
                <el-form-item
                  label="电子邮箱"
                  :label-width="formLabelWidth"
                  prop="email"
                >
                  <el-input
                    v-model="userinfo.email"
                    autocomplete="off"
                    :disabled="edit"
                  ></el-input>
                </el-form-item>
                <el-form-item
                  label="身份证号"
                  :label-width="formLabelWidth"
                  prop="idnumber"
                >
                  <el-input
                    v-model="userinfo.idnumber"
                    autocomplete="off"
                    :disabled="edit"
                  ></el-input>
                </el-form-item>
                <el-form-item
                  label="电话号码"
                  :label-width="formLabelWidth"
                  prop="phonenumber"
                >
                  <el-input
                    v-model="userinfo.phonenumber"
                    autocomplete="off"
                    :disabled="edit"
                  ></el-input>
                </el-form-item>
                <el-form-item
                  label="密码"
                  :label-width="formLabelWidth"
                  prop="password"
                >
                  <el-input
                    v-model="userinfo.password"
                    autocomplete="off"
                    :disabled="edit"
                  ></el-input>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
          <el-col :span="12" class="center-container">
            <el-container>
              <el-header class="custom-header">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="url4"
                  fit="fill"
                ></el-image>
              </el-header>
              <el-main>
                <div>
                  <el-button v-if="showButtonUpdate" @click="updateInfo()"
                    >修改信息</el-button
                  >
                </div>
                <div>
                  <el-button
                    type="primary"
                    v-if="showButtonSave"
                    @click="saveInfo()"
                    >保存</el-button
                  >
                </div>
              </el-main>
            </el-container>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<script>
import store from "@/store";
import Api from "@/api/all_userinfo";
export default {
  data() {
    return {
      edit: true,
      showButtonUpdate: true,
      showButtonSave: false,
      formLabelWidth: "80px",
      userinfo: {},
      userid: 0,
      url4: "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
    };
  },
  methods: {
    getUserInfo(userid) {
      Api.getUserInfo(userid).then((response) => {
        this.userinfo = response.data;
        this.userinfo.userId = userid;
      });
    },
    updateInfo() {
      this.$refs.userinfoRef.validate((valid) => {
        if (valid) {
          this.edit = false;
          this.showButtonUpdate = false;
          this.showButtonSave = true;
          // 表单验证通过，执行更新用户信息的操作
          // 可以在这里启用输入框
          this.$message.success("用户信息已更新");
        } else {
          this.$message.error("表单验证失败");
        }
      });
    },
    saveInfo() {
      this.$refs.userinfoRef.validate((valid) => {
        if (valid) {
          this.edit = true;
          this.showButtonUpdate = true;
          this.showButtonSave = false;
          // 表单验证通过，执行更新用户信息的操作
          // 可以在这里启用输入框
          var info = {};
          info.userId = this.userinfo.userId;
          info.userName = this.userinfo.name;
          info.userPassword = this.userinfo.password;
          info.userEmail = this.userinfo.email;
          info.userPhonenumber = this.userinfo.phonenumber;
          info.userIdnumber = this.userinfo.idnumber;
          Api.updateUserInfo(info).then((response) => {
            this.$message.success("保存成功");
          });
        } else {
          this.$message.error("表单验证失败");
        }
      });
    },
  },
  created() {
    this.userid = store.getters.userid;

    this.getUserInfo(this.userid);
  },
};
</script>

<style scoped>
.title-section {
  background-color: rgba(48, 65, 86, 0.8);
  padding: 1px;
  text-align: center;
}
.title {
  color: white;
  padding: 1px;
  font-weight: bold;
}
.el-card .el-input {
  width: 60%;
}
.custom-header {
  text-align: center;
  margin-bottom: 80px;
  margin-top: 80px;
}
.el-main {
  text-align: center;
  line-height: 160px;
}
</style>