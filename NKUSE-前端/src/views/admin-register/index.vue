<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <div class="grid-content bg-purple">
            <el-input
              v-model="searchModel.registId"
              placeholder="输入报名ID"
            ></el-input>
            <el-button
              type="primary"
              icon="el-icon-search"
              @click="getOneInfo()"
              >查询</el-button
            >
          </div>
        </el-col>
        <el-col :span="4" align="right">
          <div class="grid-content bg-purple-light">
            <el-button
              type="primary"
              style=""
              icon="el-icon-plus"
              circle
              @click="openNewUI()"
            ></el-button>
          </div>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table :data="regList" stripe style="width: 100%">
        <el-table-column prop="registId" label="报名ID" width="100">
        </el-table-column>
        <el-table-column prop="userId" label="学生ID" width="100">
        </el-table-column>
        <el-table-column prop="examId" label="考试场次" width="100">
        </el-table-column>
        <el-table-column prop="roomId" label="考场ID" width="100">
        </el-table-column>
        <el-table-column prop="seatNumber" label="座位号" width="100">
        </el-table-column>
        <el-table-column prop="paid" label="缴费状态" width="100">
          <template slot-scope="scope">
            <span>{{ getPaidState(scope.row.paid) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="score" label="考试成绩" width="100">
        </el-table-column>
        <el-table-column prop="options" label="操作">
          <template slot-scope="scope">
            <el-button
              @click="openEditUI(scope.row.registId)"
              type="primary"
              icon="el-icon-edit"
              circle
              size="mini"
            ></el-button>
            <el-button
              @click="deleteById(scope.row.registId)"
              type="danger"
              icon="el-icon-delete"
              circle
              size="mini"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clearForm">
      <el-form :model="regForm" ref="regFormRef" :rules="rules" >
        <el-form-item label="用户ID" :label-width="formLabelWidth" prop="userId">
          <el-input v-model="regForm.userId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="考试场次" :label-width="formLabelWidth" prop="examId">
          <el-input v-model="regForm.examId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="考场" :label-width="formLabelWidth" prop="roomId">
          <el-input v-model="regForm.roomId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="座位号" :label-width="formLabelWidth" prop="seatNumber">
          <el-input v-model="regForm.seatNumber" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="clearForm()">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>    
  </div>
</template>

<script>
import regApi from "@/api/admin_register";
import { resetRouter } from '@/router';
export default {
  name: "app",
  data() {
    return {
      formLabelWidth: "80px",
      title: "",
      dialogFormVisible: false,
      regForm: {},
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        regist_Id: 0,
      },
      regList: [],
      rules: {
        userId:[
          {
            required: true,
            message: "请输入用户ID",
            trigger: "blur",
          }
        ],
        examId:[
          {
            required:true,
            message:"请输入考试场次",
            trigger:"blur",
          }
        ],
        roomId:[
          {
            required:true,
            message:"请输入考场",
            trigger:"blur",
          }
        ],
        seatNumber:[
          {
            required:true,
            message:"请输入座位号",
            trigger:"blur",
          }
        ],


      },
    };
  },
  methods: {
    handleSizeChange() {},
    handleCurrentChange() {},
    getOneInfo() {
      regApi.getOneInfo(this.searchModel).then((response) => {
        this.regList = response.data.rows;
        this.total = response.data.total;
      });
    },
    getAllRegInfo() {
      regApi.getAllRegInfo(this.searchModel).then((response) => {
        this.regList = response.data.rows;
        this.total = response.data.total;
      });
    },
    openNewUI() {
      this.title = "新增报名";
      this.dialogFormVisible = true;
    },
    openEditUI(id) {
      this.title = "编辑信息";
      //this.$refs.regFormRef.userId=0;
      this.dialogFormVisible = true;
      
      regApi._getOneInfo(id).then((response) => {
        this.regForm = response.data.rows[0];
      });
    },
    clearForm() {
      this.dialogFormVisible = false;
      this.regForm = {};
      this.$refs.regFormRef.clearValidate();
    },
    getPaidState(state) {
      // 根据考试状态的数值返回相应的字符串
      if (state === 1) {
        return '已缴费'
      } else if (state === 0) {
        return '未缴费'
      }
    },
    submitForm() {
      if ((this.title == "新增报名")) {
        this.$refs.regFormRef.validate((valid) => {
          if (valid) {
            regApi.newReg(this.regForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.regForm = {};
              this.$refs.regFormRef.clearValidate();
              this.getAllRegInfo();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      } else {
        this.$refs.regFormRef.validate((valid) => {
          if (valid) {
            regApi.updateReg(this.regForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.regForm = {};
              this.$refs.regFormRef.clearValidate();
              this.getAllRegInfo();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      }
    },
    deleteById(id) {
      regApi.deleteReg(id).then((response) => {
          
      this.getAllRegInfo();
      });
    }
  },
  created() {  
    this.getAllRegInfo(this.searchModel);
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
#search .el-input {
  width: 300px;
  margin-right: 40px;
}
.el-dialog .el-input {
  width: 70%;
}
</style>

