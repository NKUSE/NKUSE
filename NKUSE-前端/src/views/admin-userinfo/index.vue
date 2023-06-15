<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="用户ID">
        <el-input v-model="form.name" />
      </el-form-item>
              <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="onCancel">取消</el-button>
        <el-button type="success" @click="onButtonClick" 
      >新建</el-button
    >
    </el-form>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="userid" label="用户ID"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="ID" label="身份证号"></el-table-column>
        <el-table-column prop="phonenum" label="手机号"></el-table-column>
        <el-table-column prop="pswd" label="密码"></el-table-column>
        <el-table-column prop="type" label="用户类型"></el-table-column>
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
            userid: "000001",
            name: "南小开",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "学生"
          },{
            userid: "000002",
            name: "天小大",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "学生"
          },{
            userid: "000003",
            name: "南开开",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "学生"
          },{
            userid: "000004",
            name: "南南开",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "学生"
          },{
            userid: "000004",
            name: "教师1",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "教师"
          },{
            userid: "000005",
            name: "教师2",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "教师"
          },{
            userid: "999999",
            name: "admin",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "管理员"
          },{
            userid: "111114",
            name: "admin2",
            ID: "52xxxxxxxxxxxxxxxx",
            phonenum: "139xxxxxxxx",
            pswd: "123456",
            type: "管理员"
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

