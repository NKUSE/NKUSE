<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="考场ID">
        <el-input v-model="form.name" />
      </el-form-item>
      
      
        <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">取消</el-button>
        <el-button type="success" @click="onButtonClick" 
      >新建</el-button
    >
    </el-form>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="roomid" label="考场ID"></el-table-column>
        <el-table-column prop="schoolname" label="考点名称"></el-table-column>
        <el-table-column prop="roomseq" label="考场房间号"></el-table-column>
        <el-table-column prop="capacity" label="考场容量"></el-table-column>
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
            roomid: "001",
            schoolname: "南开大学",
            roomseq: "101",
            capacity: "50",
          },{
            roomid: "002",
            schoolname: "南开大学",
            roomseq: "102",
            capacity: "50",
          },{
            roomid: "003",
            schoolname: "南开大学",
            roomseq: "103",
            capacity: "50",
          },{
            roomid: "004",
            schoolname: "天津大学",
            roomseq: "103",
            capacity: "50",
          },{
            roomid: "005",
            schoolname: "天津大学",
            roomseq: "101",
            capacity: "50",
          },{
            roomid: "006",
            schoolname: "天津大学",
            roomseq: "102",
            capacity: "50",
          },{
            roomid: "007",
            schoolname: "天才大学",
            roomseq: "103",
            capacity: "50",
          },
          // 添加更多考试信息...
        ],
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

