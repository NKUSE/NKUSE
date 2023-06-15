<template>
    <div class="container" style="min-height: 100%; padding-bottom: 100px;">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="exam" label="考试名称"></el-table-column>
        <el-table-column prop="time" label="考试时间"></el-table-column>
        <el-table-column prop="fee" label="费用"></el-table-column>
        <el-table-column prop="isfee" label="考试状态"></el-table-column>
          <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="editObjective(scope.$index)">编辑</el-button>
            <el-button type="danger" size="mini" @click="confirmDeleteObjective(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
  
  <script>
  export default {
    props: [],
    components: {},
    data() {
      return {
        tableData: [
          {
            exam: "第4届全国大学生英语6级考试",
            time: "2023年9月8日",
            fee: "25",
            isfee: "开始报名",
          },
          {
            exam: "第3届全国大学生英语6级考试",
            time: "2023年6月6日",
            fee: "25",
            isfee: "结束报名",
          },{
            exam: "第2届全国大学生英语6级考试",
            time: "2023年3月6日",
            fee: "25",
            isfee: "等待阅卷",
          },{
            exam: "第1届全国大学生英语6级考试",
            time: "2020年1月1日",
            fee: "25",
            isfee: "已结束",
          },
          // 添加更多考试信息...
        ],
      }
    },
    watch: {},
    computed: {},
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    destroyed() {},
    methods: {
      request() {},
      payFee(row) {
        // 在这里编写缴费的逻辑
        console.log("已缴费", row);
      },
    },
    filters: {},
  }
  </script>
  
  <style scoped>
  .container {}
  </style>
  