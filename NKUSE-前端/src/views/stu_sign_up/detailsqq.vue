<template>
  <div>
    <header class="header">
      <h1>全国大学生六级考试报名系统</h1>
    </header>

    <div class="container">
      <el-card class="card" :body-style="{ padding: '20px' }" :style="{ width: '600px' }">
        <h2 slot="header" class="card-title">资格信息查询</h2>
        <div class="card-body">
          <el-form :model="formData" :rules="rules" ref="form" label-width="80px">
            <el-form-item label="报考省份">
              <el-input disabled v-model="province"></el-input>
            </el-form-item>
            <el-form-item label="证件号码" prop="idNumber">
              <el-input v-model="formData.idNumber"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="btn" @click="query">查询</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import userApi from "@/api/all_userinfo";
import signApi from "@/api/stu_sign_up";

export default {
  data() {
    return {
      province: '天津市',
      formData: {
        userid: '',
        idNumber: '',
        name: ''
      },
      rules: {
        idNumber: [
          { required: true, message: '请输入证件号码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    query() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          // Form is valid, execute query logic
          console.log('执行查询操作');
          signApi.getUserIdbyIdnum(this.formData.idNumber).then(response => {
            //Handle the response
            this.userid=response.data.userid;
            this.$store.dispatch('app/updateUserid',this.userid);
            console.log('查询成功');
            this.$router.push("/stu_sign_up/detailsqc");
            
          })
          // Call the corresponding API or perform other operations
          // userApi.query(this.formData.idNumber, this.formData.name).then(response => {
          //   // Handle the response
          // }).catch(error => {
          //   // Handle the error
          // });
        } else {
          // Form is not valid, show error message
          this.$message.error('请填写完整信息');
        }
      });
    }
  }
}
</script>

<style scoped>
.header {
  background-color:  rgba(48, 65, 86, 0.8);
  padding: 20px;
  color: white;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.el-card__header {
  background-color:  rgba(48, 65, 86, 0.8);
  color: white;
  padding: 10px 20px;
  font-size: 20px;
}

.card-title {
  margin-bottom: 10px;
}

.card-body {
  margin-bottom: 20px;
}

.btn {
  margin-top: 10px;
  text-align: center;
}

.el-form-item__error {
  color: red;
}
</style>