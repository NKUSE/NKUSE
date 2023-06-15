<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="考生ID">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="考试科目">
        <el-input v-model="form.name" />
      </el-form-item>
              <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">取消</el-button>
        <el-button type="success" @click="onButtonClick" 
      >新建</el-button
    >
    </el-form>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="examid" label="考试场次"></el-table-column>
        <el-table-column prop="studentid" label="学生ID"></el-table-column>
        <el-table-column prop="roomid" label="考场ID"></el-table-column>
        <el-table-column prop="seatid" label="座位号"></el-table-column>
        <el-table-column prop="fee" label="缴费状态"></el-table-column>
        <el-table-column prop="score" label="考试成绩"></el-table-column>
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
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      tableData: [
          {
            examid: "1",
            roomid: "001",
            seatid: "01",
            studentid: "000001",
            fee: "已缴费",
            score: "0",
          },{
            examid: "1",
            roomid: "001",
            seatid: "02",
            studentid: "000002",
            fee: "已缴费",
            score: "100",
          },{
            examid: "1",
            roomid: "001",
            seatid: "03",
            studentid: "000003",
            fee: "已缴费",
            score: "100",
          },{
            examid: "1",
            roomid: "001",
            seatid: "04",
            studentid: "000004",
            fee: "已缴费",
            score: "100",
          },{
            examid: "2",
            roomid: "001",
            seatid: "01",
            studentid: "000005",
            fee: "已缴费",
            score: "0",
          },{
            examid: "2",
            roomid: "001",
            seatid: "02",
            studentid: "000001",
            fee: "已缴费",
            score: "0",
          },{
            examid: "2",
            roomid: "001",
            seatid: "03",
            studentid: "000002",
            fee: "已缴费",
            score: "0",
          },
      ]
    }
  },
  methods: {
    onSubmit() {
      this.$message('已确认!')
    },
    onCancel() {
      this.$message({
        message: '已取消!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

