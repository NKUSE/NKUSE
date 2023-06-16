<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px;">
    <el-row :gutter="20">
      <el-col :span="6"></el-col>
      <el-col :span="2"></el-col>
    </el-row>
    <el-steps :active="active" finish-status="success" style="padding-bottom: 100px; margin: auto; width: 120%;">
      <el-step title="选择考试"></el-step>
      <el-step title="信息确认"></el-step>
      <el-step title="在线作答"></el-step>
    </el-steps>
    <div v-for="(item, index) in examList" :key="index">
      <el-card class="box-card" style="width: 900px; margin: auto; width: 120%;" >
        <div slot="header" class="clearfix">
          <span>第{{item.examination_id}}届全国大学英语六级考试</span>
        </div>
        <div class="text item">{{ "考试时间 : " + item.time }}</div>
        <div class="text item">{{ "考试地点 : " + item.place }}</div>
        <div class="text item">{{ "考场 : " + item.room }}</div>
        <div class="text item">
          {{ "座位号 : " + item.seat_num }}
          <el-button type="primary" v-bind:disabled="checkDate(item.time)" @click="onButtonClick" size="small" style="float: right;">开始考试</el-button>
        </div>
      </el-card>
      <div style="padding-bottom: 20px;"> </div>
      
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
//import onlineExamApi from '@/api/online_exam'
  export default {
    data(){
      return {
        today: null,
        active: 1,
        num: 1,
        total: 0,
        examList: [
          {
          examination_id : "2",
          time : "2023-05-22T20:00:00",
          place : "南开大学",
          room : "101",
          seat_num : "01"
          },{
          examination_id : "1",
          time : "2023-07-22T21:00:00",
          place : "南开大学",
          room : "102",
          seat_num : "1"
          }
        ],
        user_id: 0
      }
    },
    name: 'Online_test',
    props: [],
    components: {},
    computed: {
        ...mapGetters([
            'id'
        ]),
    },
    watch: {},
    beforeCreate() {},
    created() {
      //mapGetters
      //this.getExams();
    },
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    destoryed() {},
    methods: {
      request() {},
      onButtonClick() {
        this.$router.push('/stu_online_test/confirm')
      },
      getExams() {
        onlineExamApi.getExams("000001").then(response => {
          this.total = response.data.total
          this.examList = response.data.exams
        }
        );
      },
      checkDate(date) {
        const dateObj = new Date(date); // 将 date 变量转换为 Date 对象
        const today = new Date();
        return dateObj > today; // 判断 date 变量是否晚于今天的日期
      },
    },
    fillter: {},
  }
</script>

<style scoped>
  .box-card{}
  .clearfix{}
  .text{}
  .item { margin-top: 10px; margin-right: 40px; }
  .container {
    margin: auto;
    width: 50%;
  }
</style>
