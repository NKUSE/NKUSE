<template>
  <div>
    <header class="header">
      <h1>全国大学生六级考试报名系统</h1>
    </header>
    <div class="container">
      <div class="card">
        <h2 class="card-title">资格信息确认</h2>
        <div class="sub-card sub-card1">
            <h3 class="sub-card-title">提示</h3>
            <p class="red-text">
            1、如果发现学籍信息有误，请勿继续报考，尽快联系学校相关负责老师进行处理。<br />
            2、资格科目是综合当前考次的设置信息而生成的，如有异议，请联系学校相关负责老师。<br />
            3、资格科目为无的考生无法继续报名。<br />
            4、如暂时无法报考口试，可能是由于机位已满或考点暂未开放，完成笔试报考及缴费后，可随时登录系统查看可否进行口试报考。
            </p>
        </div>
        <el-card class="sub-card sub-card2" :body-style="{ padding: '20px' }">
          <h3 slot="header" class="sub-card-title">个人信息</h3>
          <el-table :data="queryResult" border>
            <el-table-column prop="name" label="用户名"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="idType" label="证件类型"></el-table-column>
            <el-table-column prop="idNumber" label="证件号码"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
          </el-table>
        </el-card>
        <div class="checkbox">
            <label>
            <input type="checkbox" v-model="confirmed">
            *我已检查并确认学籍与资格信息正确
            </label>
        </div>
        <div class="buttons">
            <el-button type="primary" @click="goToHome">返回首页</el-button>
            <el-button class="btn" @click="query" :disabled="!confirmed">请勾选确认</el-button>
        </div>
        </div>
    </div>
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" @close="clearForm">
      <el-form :model="regForm" ref="regFormRef" :rules="rules">
        <el-form-item>
          <span class="blue-text">{{ dialogContent }}</span>
        </el-form-item>
          <el-form-item v-for="(exam, index) in exams" :key="index">
          <el-radio
            :label="exam.examId"
            v-model="selectedExamId"
            :class="{ 'selected-exam': selectedExamId === exam.examId }"
          >
            {{ `考试场次: ${exam.examName} 考试时间: ${formatExamTime(exam.examTime)}` }}
          </el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="clearForm">取消</el-button>
        <el-button type="primary" @click="submitForm" class="center">提交</el-button>
      </div>
    </el-dialog> 
  </div>
</template>

<script>
import userApi from "@/api/all_userinfo";
import signApi from "@/api/stu_sign_up";
export default {
  data() {
    return {
      queryResult: [], // Update the query result here
      userid:'0',
      confirmed: false,
      dialogFormVisible: false,
      dialogTitle: '可报考场次',
      dialogContent: '可报考场次:',
      exams: [
      // { name: "英语六级笔试1", time: "考试时间1", checked: false },
      // { name: "英语六级笔试2", time: "考试时间2", checked: false },
      // { name: "英语六级口试", time: "考试时间3", checked: false }
      ],
      regForm: {},
      selectedExamId: null,
      rules: {}
    };
  },
  methods: {
    formatExamTime(timeArray) {
      const [year, month, day, hour, minute] = timeArray;
      return `${year}-${this.padZero(month)}-${this.padZero(day)} ${this.padZero(hour)}:${this.padZero(minute)}`;
    },
    padZero(value) {
      return String(value).padStart(2, '0');
    },
    goToHome() {
      // 处理返回首页的逻辑
      this.$router.push('/dashboard');
    },
    query(){
        console.log('查询考试');
        
        this.getExamInfo();
        this.dialogFormVisible = true;
    },
    getUserInfo(){
      
      this.userid=this.$store.state.app.Userid;
      console.log(this.userid);
      userApi.getUserInfo(this.userid).then(response => {
        //display info
        this.queryResult=[
          {
            name:response.data.name,
            email:response.data.email,
            idType:'中华人民共和国居民身份证',
            idNumber:response.data.idnumber,
            phone:response.data.phonenumber
          }
        ];
      });
      // Process query logic and update queryResult
      // Example code:
      // this.queryResult = [
      // {
      //      name: '于文明',
      //      gender: '男',
      //      idType: '中华人民共和国居民身份证',
      //      idNumber: '410221200412284237',
      //      qualificationSubjects: '英语六级笔试,英语六级口试'
      // }
      // ];
    },
    getExamInfo(){
      signApi.getAllExams().then(response => {
        this.exams=response.data.exams;
      })
    },
    submitForm() {
      // Process the submitted form data here
      console.log('Selected Exam:', this.selectedExam);
      //userid+this.selectedExam.examId
      var temp  ={};
      temp.examId=this.selectedExamId;
      temp.userId=this.userid;
      temp.paid=0;
      console.log(temp);
      signApi.signUp(temp).then(response => {
        this.$message({
                  message: "报名成功",
                  type: "success",
                });
      })
      this.dialogFormVisible=false;
    },
    clearForm() {
      // Clear the form data here
      this.selectedExam = '';
      this.dialogFormVisible=false;
    }
  },
  created() {  
      this.getUserInfo();
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
}
.header {
  background-color:  rgba(48, 65, 86, 0.8);
  padding: 20px;
  color: white;
}
.card {
  background-color: #f8f8f8;
  border-radius: 5px;
  padding: 20px;
}
.card-title {
  color:  rgba(48, 65, 86, 0.8);
  margin-bottom: 20px;
}
.sub-card {
  background-color: #dceeff;
  margin-bottom: 20px;
  padding: 10px;
}
.sub-card-title {
  color: red;
  margin-bottom: 10px;
}
.red-text {
  color: red;
}
.checkbox {
  margin-top: 20px;
}
  .buttons {
    text-align: center;
  }
.btn {
  background-color: skyblue;
  color: white;
  border: none;
  cursor: pointer;
}
.btn:disabled {
  background-color: lightgray;
  cursor: not-allowed;
}
.blue-text {
  color:  rgba(48, 65, 86, 0.8);
}
.center {
  margin-left: 10px;
}
</style>