<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px">
    <el-row :gutter="20">
      <el-col :span="6"></el-col>
      <el-col :span="2"></el-col>
    </el-row>
    <div v-for="(item, index) in examList" :key="index">
      <el-card
        class="box-card"
        style="width: 900px; margin: auto; width: 120%; padding-bottom: 10px"
      >
        <div slot="header" class="clearfix">
          <span>第{{ item.examId }}届全国大学英语六级考试</span>
        </div>
        <div class="text item">{{ "考试名称 : " + item.examName }}</div>
        <div class="text item">{{ "考试时间 : " + item.examDate }}</div>
        <div class="text item">{{ "考试地点 : 线上考场" }}</div>
        <div class="text item" v-if="item.examState === 1">
          {{ "考试状态 : " }}<el-tag>未开始</el-tag>
        </div>
        <div class="text item" v-if="item.examState === 2">
          {{ "考试状态 : " }}<el-tag type="success">考试中</el-tag>
        </div>
        <div class="text item" v-if="item.examState === 3">
          {{ "考试状态 : " }}<el-tag type="info">已结束</el-tag>
        </div>
        <div class="text item" v-if="item.examState === 4">
          {{ "考试状态 : " }}<el-tag type="info">已结束</el-tag>
        </div>
        <div class="text item">
          <el-button
            type="primary"
            @click="onButtonClick(item.examId)"
            size="small"
            style="float: right"
          >
            查看报名信息</el-button
          >
        </div>
      </el-card>
      <div style="padding-bottom: 20px"></div>
    </div>
    <div v-if="examList.length == 0">
      <h1>未报名任何考试，请先前往报名！</h1>
    </div>
  </div>
</template>

<script>
import store from "@/store";
import { mapGetters } from "vuex";
import SearchApi from "@/api/stu_search.js";
export default {
  data() {
    return {
      today: null,
      active: 1,
      num: 1,
      total: 0,
      examList: [],
      user_id: 0,
    };
  },
  name: "Online_test",
  props: [],
  components: {},
  computed: {
    ...mapGetters(["id"]),
  },
  watch: {},
  beforeCreate() {},
  created() {
    this.getExams();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    request() {},
    onButtonClick(examid) {
      this.$store.dispatch("app/updateExamid", examid);
      this.$router.push("/stu_search/application_enquiry");
    },
    getExams() {
      this.user_id = store.getters.userid;
      SearchApi.getExams(this.user_id).then((response) => {
        this.total = response.data.total;
        this.examList = response.data.exams;
        this.examList = this.examList.map((item) => {
          if (item.examState === 1) {
            item.state = "报名中";
          } else if (item.examState === 2) {
            item.state = "开始考试";
          } else if (item.examState === 3) {
            item.state = "已结束";
          } else if (item.examState === 4) {
            item.state = "已结束";
          }
          return item;
        });
        this.examList.forEach((item) => {
          const dateArray = item.examTime;
          const examDate = new Date(
            dateArray[0],
            dateArray[1] - 1,
            dateArray[2],
            dateArray[3],
            dateArray[4]
          );
          var year = examDate.getFullYear();
          var month = ("0" + (examDate.getMonth() + 1)).slice(-2);
          var day = ("0" + examDate.getDate()).slice(-2);
          var hour = ("0" + examDate.getHours()).slice(-2);
          var minute = ("0" + examDate.getMinutes()).slice(-2);
          var second = ("0" + examDate.getSeconds()).slice(-2);

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
          item.examDate = formattedDate;
        });
      });
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
</style>