<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <div class="grid-content bg-purple">
            <el-input
              v-model="searchModel.roomId"
              placeholder="输入考场ID"
            ></el-input>
            <el-button
              type="primary"
              icon="el-icon-search"
              @click="getOneRoom()"
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
      <el-table :data="roomList" stripe style="width: 100%">
        <el-table-column prop="roomId" label="ID" width="200">
        </el-table-column>
        <el-table-column prop="place" label="考点名称" width="300">
        </el-table-column>
        <el-table-column prop="roomName" label="考场房间号" width="300">
        </el-table-column>
        <el-table-column prop="capacity" label="考场容量" width="200">
        </el-table-column>
        <el-table-column prop="options" label="操作">
          <template slot-scope="scope">
            <el-button
              @click="openEditUI(scope.row.roomId)"
              type="primary"
              icon="el-icon-edit"
              circle
              size="mini"
            ></el-button>
            <el-button
              @click="deleteById(scope.row.roomId)"
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
      <el-form :model="roomForm" ref="roomFormRef" :rules="rules">
        <el-form-item
          label="考点名称"
          :label-width="formLabelWidth"
          prop="place"
        >
          <el-input
            v-model="roomForm.place"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="考场" :label-width="formLabelWidth" prop="roomName">
          <el-input v-model="roomForm.roomName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="考场容量" :label-width="formLabelWidth" prop="capacity">
          <el-input v-model="roomForm.capacity" autocomplete="off"></el-input>
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
import roomApi from "@/api/admin_roominfo";
export default {
  name: "app",
  data() {
    return {
      formLabelWidth: "80px",
      title: "",
      dialogFormVisible: false,
      roomForm: {},
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        room_id: 0,
      },
      roomList: [],
      rules: {
        place: [
          {
            required: true,
            message: "请输入考点名称",
            trigger: "blur",
          },
        ],
        roomName: [
          {
            required: true,
            message: "请输入考场房间号",
            trigger: "blur",
          },
        ],
        capacity: [
          {
            required: true,
            message: "请输入考场容量",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    handleSizeChange() {},
    handleCurrentChange() {},
    getOneRoom() {
      roomApi.getOneRoom(this.searchModel).then((response) => {
        this.roomList = response.data.rows;
        this.total = response.data.total;
      });
    },
    getAllRoom() {
      roomApi.getAllRoom(this.searchModel).then((response) => {
        this.roomList = response.data.rows;
        this.total = response.data.total;
      });
    },
    newRoom() {},
    openNewUI() {
      this.title = "新增考场";
      this.dialogFormVisible = true;
    },
    openEditUI(id) {
      this.title = "更新考场";
      this.dialogFormVisible = true;
      roomApi.getOneRoom_2(id).then((response) => {
        this.roomForm = response.data.rows[0];
      });
    },
    clearForm() {
      this.dialogFormVisible = false;
      this.roomForm = {};
      this.$refs.roomFormRef.clearValidate();
    },
    submitForm() {
      if ((this.title == "新增考场")) {
        this.$refs.roomFormRef.validate((valid) => {
          if (valid) {
            roomApi.newRoom(this.roomForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.roomForm = {};
              this.$refs.roomFormRef.clearValidate();
              this.getAllRoom();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      } else {
        this.$refs.roomFormRef.validate((valid) => {
          if (valid) {
            roomApi.updateRoom(this.roomForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.roomForm = {};
              this.$refs.roomFormRef.clearValidate();
              this.getAllRoom();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      }
    },
    deleteById(id) {
      roomApi.deleteRoom(id).then((response) => {
        this.getAllRoom();
      });
    }
  },
  created() {
    this.getAllRoom(this.searchModel);
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

