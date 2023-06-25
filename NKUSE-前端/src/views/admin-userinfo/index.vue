<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <div class="grid-content bg-purple">
            <el-input
              v-model="searchModel.userId"
              placeholder="输入用户ID"
            ></el-input>
            <el-button
              type="primary"
              icon="el-icon-search"
              @click="getOneUser()"
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
      <el-table :data="userList" stripe style="width: 100%">
        <el-table-column prop="userId" label="ID" width="120">
        </el-table-column>
        <el-table-column prop="userName" label="用户名" width="130">
        </el-table-column>
        <el-table-column prop="userPassword" label="用户密码" width="130">
        </el-table-column>
        <el-table-column prop="userEmail" label="用户邮箱" width="130">
        </el-table-column>
        <el-table-column prop="userPhonenumber" label="用户手机号" width="130">
        </el-table-column>
        <el-table-column prop="userIDnumber" label="用户证件号" width="130">
        </el-table-column>
        <el-table-column prop="userRole" label="用户身份" width="130">
          <template slot-scope="scope">
            <span>{{ getUserRole(scope.row.userRole) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="options" label="操作">
          <template slot-scope="scope">
            <el-button
              @click="openEditUI(scope.row.userId)"
              type="primary"
              icon="el-icon-edit"
              circle
              size="mini"
            ></el-button>
            <el-button
              @click="deleteById(scope.row.userId)"
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
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="userForm" ref="userFormRef" :rules="rules">
        <el-form-item
          label="用户id"
          :label-width="formLabelWidth"
          prop="userId"
        >
          <el-input
            v-model="userForm.userId"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth" prop="userPassword">
          <el-input v-model="userForm.userPassword" autocomplete="off"></el-input>
        </el-form-item>
               <el-form-item label="用户邮箱" :label-width="formLabelWidth" prop="userEmail">
          <el-input v-model="userForm.userEmail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名" :label-width="formLabelWidth" prop="userName">
          <el-input v-model="userForm.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户证件号" :label-width="formLabelWidth" prop="userIDnumber">
          <el-input v-model="userForm.userIDnumber" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户手机号" :label-width="formLabelWidth" prop="userPhonenumber">
          <el-input v-model="userForm.userPhonenumber" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户身份" :label-width="formLabelWidth" prop="userRole">
          <el-input v-model="userForm.userRole" autocomplete="off"></el-input>
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
import userApi from "@/api/admin_userinfo";
export default {
  name: "app",
  data() {
    return {
      formLabelWidth: "80px",
      title: "",
      dialogFormVisible: false,
      userForm: {},
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        user_id: 0,
      },
      userList: [],
      rules: {
        userId: [
          {
            required: true,
            message: "请输入用户id",
            trigger: "blur",
          },
        ],
        userPassword: [
          {
            required: true,
            message: "请输入用户密码",
            trigger: "blur",
          },
        ],
        userRole: [
          {
            required: true,
            message: "请输入用户身份",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    handleSizeChange() {},
    handleCurrentChange() {},
    getOneUser() {
      userApi.getOneUser(this.searchModel).then((response) => {
        this.userList = response.data.rows;
        this.total = response.data.total;
      });
    },
    getAllUser() {
      userApi.getAllUser(this.searchModel).then((response) => {
        this.userList = response.data.rows;
        this.total = response.data.total;
      });
    },
    getUserRole(state){
      if (state === 1) {
        return '学生'
      } else if (state === 2) {
        return '教师'
      } else if (state === 3) {
        return '管理员'
      } else {
        return '学生'
      }
    },
    newUser() {},
    openNewUI() {
      this.title = "新增用户";
      this.dialogFormVisible = true;
    },
    openEditUI(id) {
      this.title = "更新用户";
      this.dialogFormVisible = true;
      userApi.getOneUser_2(id).then((response) => {
        this.userForm = response.data.rows[0];
      });
    },
    clearForm() {
      this.dialogFormVisible = false;
      this.userForm = {};
      this.$refs.userFormRef.clearValidate();
    },
    submitForm() {
      if ((this.title == "新增用户")) {
        this.$refs.userFormRef.validate((valid) => {
          if (valid) {
            userApi.newUser(this.userForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.userForm = {};
              this.$refs.userFormRef.clearValidate();
              this.getAllUser();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      } else {
        this.$refs.userFormRef.validate((valid) => {
          if (valid) {
            userApi.updateUser(this.userForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.userForm = {};
              this.$refs.userFormRef.clearValidate();
              this.getAllUser();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      }
    },
    deleteById(id) {
      userApi.deleteUser(id).then((response) => {
        this.getAllUser();
      });
    }
  },
  created() {
    this.getAllUser(this.searchModel);
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

