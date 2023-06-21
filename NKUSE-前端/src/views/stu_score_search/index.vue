<template>
    <div class="container" style="min-height: 100%; padding-bottom: 100px;">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="id" label="身份证号"></el-table-column>
        <el-table-column prop="exam" label="考点信息"></el-table-column>
        <el-table-column prop="time" label="考试时间"></el-table-column>
        <el-table-column prop="score" label="考试成绩"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="printScore(scope.row)">打印成绩单</el-button>
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
            name: "王小虎",
            id: "123456",
            exam: "南开大学津南校区公教B102",
            time: "2023年5月8日",
            score: "99",
          },
          // 添加更多学生信息...
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
      printScore(row) {
        // 在这里编写打印成绩单的逻辑
        console.log("打印成绩单", row);
      },
    },
    filters: {},
  }
  </script>
  
  <style scoped>
  .container {}
  </style>
  