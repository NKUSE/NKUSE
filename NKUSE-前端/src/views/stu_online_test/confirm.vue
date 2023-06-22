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
        >第1届全国大学英语六级考试</el-descriptions-item
      >
      <el-descriptions-item label="考试时间"
        >2023-05-22 20:03:26</el-descriptions-item
      >
      <el-descriptions-item label="考试地点" :span="2"
        >津南校区</el-descriptions-item
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
      @click="onButtonClick"
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
        phonenumber: ""
      },

      selected_onlineexam_id: 0,
    };
  },
  watch: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.getUserInfo();
    this.$forceUpdate();
    this.getExamInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    request() {},
    onButtonClick() {
      this.$router.push("/stu_online_test/Testing");
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
      //ExaminfoApi.getOne(this.selected_onlineexam_id).then((response) => {
      //  this.examinfo = response.dats.rows[0];
      //});
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