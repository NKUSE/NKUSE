<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px">
    <el-row :gutter="20">
      <el-col :span="6"></el-col>
      <el-col :span="2"></el-col>
    </el-row>
    <el-steps
      :active="2"
      finish-status="success"
      style="padding-bottom: 100px; margin: auto; width: 120%"
    >
      <el-step title="选择考试"></el-step>
      <el-step title="信息确认"></el-step>
      <el-step title="在线作答"></el-step>
    </el-steps>
    <el-descriptions
      title="考生信息确认"
      direction="vertical"
      :column="4"
      border
    >
      >
      <el-descriptions-item label="考生姓名">{{
        this.userinfo.name
      }}</el-descriptions-item>
      <el-descriptions-item label="考生身份证号">{{
        this.userinfo.idnumber
      }}</el-descriptions-item>
      <el-descriptions-item label="考生ID" :span="2">{{
        this.user_id
      }}</el-descriptions-item>
      <el-descriptions-item label="报考科目">
        <el-tag size="small">英语6级</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="电子邮箱">{{
        this.userinfo.email
      }}</el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <el-descriptions
      title="考试信息确认"
      direction="vertical"
      :column="4"
      border
    >
      >
      <el-descriptions-item label="考试名称"
        >第{{
          this.selected_onlineexam_id
        }}届全国大学英语六级考试</el-descriptions-item
      >
      <el-descriptions-item label="考试时间">{{
        this.examinfo.dateprint
      }}</el-descriptions-item>
      <el-descriptions-item label="考试地点" :span="2"
        >线上考场</el-descriptions-item
      >
    </el-descriptions>
    <el-divider></el-divider>
    <div style="min-height: 100%; padding-bottom: 30px; font-weight: bold">
      考试注意事项：
    </div>
    <div style="min-height: 100%; padding-bottom: 10px">
      1. 请自觉遵守线上考试纪律
    </div>
    <div style="min-height: 100%; padding-bottom: 10px">
      2. 考试总时长: 60 分钟。
    </div>
    <div style="min-height: 100%; padding-bottom: 10px">
      3. 若答题过程中异常退出，使用相同考号重新登录继续考试。
    </div>
    <div style="min-height: 100%; padding-bottom: 10px">
      4. 答题过程中禁止切屏。
    </div>
    <el-divider></el-divider>
    <el-button
      type="primary"
      @click="onButtonClick(selected_onlineexam_id)"
      size="big"
      style="float: right"
      :disabled="!checkList.includes('我已认真阅读考试注意事项，可以开始考试')"
      >开始答题</el-button
    >
    <el-checkbox-group v-model="checkList">
      <el-checkbox label="我已认真阅读考试注意事项，可以开始考试"></el-checkbox>
    </el-checkbox-group>
  </div>
</template>

<script>
import store from "@/store";
import onlineExamApi from "@/api/stu_online_exam";
import ExaminfoApi from "@/api/admin_examinfo";
export default {
  props: [],
  components: {},
  data() {
    return {
      checkList: ["选中且禁用", "我已认真阅读考试注意事项，可以开始考试"],
      userinfo: {
        name: "",
        idnumber: "",
        email: "",
        phonenumber: "",
      },
      examinfo: {},
      selected_onlineexam_id: 0,
    };
  },
  watch: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.getUserInfo();
    this.getExamInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    request() {
      
    },
    onButtonClick() {
      onlineExamApi
        .getSheetId(this.user_id, this.selected_onlineexam_id)
        .then((response) => {
          console.log(response.data.HasAnswersheet);
          if (response.data.HasAnswersheet == 0) {
            //创建新答题卡
            var info = {};
            info.userId = this.user_id;
            info.examId = this.selected_onlineexam_id;
            onlineExamApi.newAnswerSheet(info).then((response) => {
              info.answersheetId = response.data.sheetId;
              //更新registinfo
              onlineExamApi.setSheetId(info).then((response) => {
                this.$store.dispatch(
                  "app/updateExamid",
                  this.selected_onlineexam_id
                );
                this.$router.push("/stu_online_test/Testing");
              });
            });
          } else {
            var answersheetid = response.data.answersheetId;
            //检查是否有score
            onlineExamApi.checkScore(answersheetid).then((response) => {
              if (response.data.hasScore != 0) {
                this.$message({
                  message: "已提交试卷，无法再次进入考试",
                  type: "error",
                });
              } else {
                this.$store.dispatch(
                  "app/updateExamid",
                  this.selected_onlineexam_id
                );
                this.$router.push("/stu_online_test/Testing");
              }
            });
          }
        });
    },
    getUserInfo() {
      this.user_id = store.getters.userid;
      onlineExamApi.getInfoById(this.user_id).then((response) => {
        this.userinfo.name = response.data.name;
        this.userinfo.idnumber = response.data.idnumber;
        this.userinfo.email = response.data.email;
        this.userinfo.phonenumber = response.data.phonenumber;
      });
      console.log(this.userinfo);
    },
    getExamInfo() {
      this.selected_onlineexam_id = this.$store.state.app.Examid;
      console.log("exam_id : " + this.$store.state.app.Examid);
      console.log("exam_id : " + this.selected_onlineexam_id);
      ExaminfoApi.getOneExamInfo(this.selected_onlineexam_id).then(
        (response) => {
          this.examinfo = response.data.rows[0];
          console.log("this.examinfo:");
          console.log(this.examinfo);
          this.examinfo.date2 = new Date(
            this.examinfo.examTime[0],
            this.examinfo.examTime[1] - 1,
            this.examinfo.examTime[2],
            this.examinfo.examTime[3],
            this.examinfo.examTime[4]
          );
          var year = this.examinfo.date2.getFullYear();
          var month = ("0" + (this.examinfo.date2.getMonth() + 1)).slice(-2);
          var day = ("0" + this.examinfo.date2.getDate()).slice(-2);
          var hour = ("0" + this.examinfo.date2.getHours()).slice(-2);
          var minute = ("0" + this.examinfo.date2.getMinutes()).slice(-2);
          var second = ("0" + this.examinfo.date2.getSeconds()).slice(-2);

          var formattedDate =
            year +
            "-" +
            month +
            "-" +
            day +
            " " +
            hour +
            ":" +
            minute +
            ":" +
            second;
          this.examinfo.dateprint = formattedDate;
        }
      );
    },
  },

  fillter: {},
};
</script>

<style scoped>
.box-card {
}
.clearfix {
}
.text {
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
.container {
  margin: auto;
  width: 50%;
}
.demo-border {
  border: 1px grey dashed;
  min-height: 1rem;
  border-radius: 5px;
}
.shadow-container {
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  background-color: rgba(10, 10, 10, 0.1);
  width: 120%; /* 设置宽度为300像素 */
  height: 120%; /* 设置高度为200像素 */
}
body {
  background-color: rgba(10, 10, 10, 0.3);
}
</style>