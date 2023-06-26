<template>
    <div class="container" style="min-height: 100%;width: 700px; padding-bottom: 100px">
      <h1 style="text-align: center; margin-bottom: 20px;">全国大学生英语六级成绩单</h1>
      <el-row :gutter="20">
        <el-col :span="6"></el-col>
        <el-col :span="2"></el-col>
      </el-row>
      <el-table
      :show-header="false"
      :data="tableData"
      border
      :cell-style="columnStyle"
      style="width: 100%; margin-top: 10px"
    >
      <el-table-column prop="name" align="center" />
      <el-table-column prop="value" align="center" />
    </el-table>
      <el-divider></el-divider>

      <el-button
        type="primary"
        @click="onButtonClick(selected_onlineexam_id)"
        size="big"
        style="float: right"
        >打印成绩单</el-button
      >
    </div>
  </template>
  
  <script>
  import store from "@/store";
  import scoreApi from "@/api/stu_score_search";
  import html2pdf from "html2pdf.js";
  export default {
    props: [],
    components: {},
    data() {
        return {
      userinfo :{},
      showFlag: false,
      tableData: [{
        name: '考生ID',
        value: ''
      },
      {
        name: '考生姓名',
        value: ''
      },
      {
        name: '考生身份证号',
        value: ''
      },
      {
        name: '考试科目',
        value: '英语六级'
      },
      {
        name: '考试成绩',
        value: ''
      }]
    }
    },
    watch: {},
    computed: {},
    beforeCreate() {},
    created() {
      this.getUserInfo();
      this.getScore();
    },
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    destoryed() {},
    methods: {
      request() {
        
      },
      columnStyle({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        // 修改每行第一个单元格的背景色
        return 'background:#f3f6fc;'
      } else {
        return 'background:#ffffff;'
      }
    },
      onButtonClick() {
        // 获取要生成 PDF 的容器元素
        const container = document.querySelector(".container");

        // 使用 html2pdf 将容器内容转换为 PDF
        html2pdf().from(container).save("score_sheet.pdf");
      },
      getUserInfo() {
        this.user_id = store.getters.userid;
        scoreApi.getInfoById(this.user_id).then((response) => {
          this.userinfo.name = response.data.name;
          this.userinfo.idnumber = response.data.idnumber;
          this.userinfo.email = response.data.email;
          this.userinfo.phonenumber = response.data.phonenumber;
          this.tableData[0].value=this.user_id;
          this.tableData[1].value=this.userinfo.name;
          this.tableData[2].value=this.userinfo.idnumber;
        });
      },
      getScore(){
        this.examid = this.$store.state.app.Examid;
        this.user_id = store.getters.userid;
        scoreApi.getScore(this.user_id,this.examid).then((response)=>{
            this.tableData[4].value=response.data.score;
        })
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