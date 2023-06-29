<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px">
    <el-row :gutter="20">
      <el-col :span="6"></el-col>
      <el-col :span="2"></el-col>
    </el-row>
    <el-descriptions title="考生信息" direction="vertical" :column="4" border>
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
    <el-descriptions title="考试信息" direction="vertical" :column="4" border>
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
      <el-descriptions-item label="考场号">{{
        this.registinfo.roomId
      }}</el-descriptions-item>
      <el-descriptions-item label="座位号">{{
        this.registinfo.seatNumber
      }}</el-descriptions-item>
      <el-descriptions-item label="缴费状态" v-if="this.registinfo.paid == 1">
        <el-tag size="small">已缴费</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="缴费状态" v-if="this.registinfo.paid == 0">
        <el-tag size="small" type="warning">未缴费</el-tag>
      </el-descriptions-item>
    </el-descriptions>
    <el-divider></el-divider>
    <div style="min-height: 100%; padding-bottom: 30px; font-weight: bold">
      缴费
    </div>
    <el-button
    v-if="this.registinfo.paid == 0"
      type="primary"
      @click="onButtonClick()"
      size="big"
      style="float: right"
      >点击缴费</el-button
    >
    <el-button
    v-if="this.registinfo.paid == 1"
      type="primary"
      @click="onButtonClick()"
      size="big"
      style="float: right"
      disabled=true
      >已完成缴费</el-button
    >
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-radio-group v-model="radio">
        <el-radio :label="3">支付宝</el-radio>
        <el-radio :label="6">微信支付</el-radio>
        <el-radio :label="9">银联支付</el-radio>
      </el-radio-group>
      <div slot="footer" class="dialog-footer">
        <el-button @click="onCancel()">取 消</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from "@/store";
import onlineExamApi from "@/api/stu_online_exam";
import ExaminfoApi from "@/api/admin_examinfo";
import SearchApi from "@/api/stu_search.js";
export default {
  props: [],
  components: {},
  data() {
    return {
      title: "支付页面",
      dialogFormVisible: false,
      userinfo: {
        name: "",
        idnumber: "",
        email: "",
        phonenumber: "",
        user_id: "",
      },
      examinfo: {},
      selected_onlineexam_id: 0,
      registinfo: {},
      radio: 0,
    };
  },
  watch: {},
  computed: {},
  beforeCreate() {},
  created() {
    this.getUserInfo();
    this.getExamInfo();
    this.getRoomInfo();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    request() {},
    onButtonClick() {
      this.dialogFormVisible = true;
    },
    onSubmit() {
      this.registinfo.paid = 1;
      SearchApi.updateReg(this.registinfo).then((response) => {
        this.$message({
          message: "缴费成功",
          type: "success",
        });
      });
      this.dialogFormVisible = false;
    },
    onCancel() {
      this.dialogFormVisible = false;
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
    getRoomInfo() {
      SearchApi.getRegist(this.user_id, this.selected_onlineexam_id).then(
        (response) => {
          this.registinfo = response.data;
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