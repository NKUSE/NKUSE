<template>
  <div class="app-container">
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <div class="grid-content bg-purple">
            <el-input
              v-model="searchModel.quesId"
              placeholder="输入题目ID"
            ></el-input>
            <el-button
              type="primary"
              icon="el-icon-search"
              @click="getOneQuestion()"
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
      <el-table :data="quesList" stripe style="width: 100%">
        <el-table-column prop="questionId" label="ID" width="100">
        </el-table-column>
        <el-table-column prop="description" label="题目描述" width="300">
        </el-table-column>
        <el-table-column prop="optionA" label="A" width="100">
        </el-table-column>
        <el-table-column prop="optionB" label="B" width="100">
        </el-table-column>
        <el-table-column prop="optionC" label="C" width="100">
        </el-table-column>
        <el-table-column prop="optionD" label="D" width="100">
        </el-table-column>
        <el-table-column prop="answer" label="答案" width="100">
        </el-table-column>
        <el-table-column prop="options" label="操作">
          <template slot-scope="scope">
            <el-button
              @click="openEditUI(scope.row.questionId)"
              type="primary"
              icon="el-icon-edit"
              circle
              size="mini"
            ></el-button>
            <el-button
              @click="deleteById(scope.row.questionId)"
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
      {{this.quesForm}}
      <el-form :model="quesForm" ref="quesFormRef" :rules="rules">
        <el-form-item
          label="题目描述"
          :label-width="formLabelWidth"
          prop="description"
        >
          <el-input
            v-model="quesForm.description"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="A" :label-width="formLabelWidth" prop="optionA">
          <el-input v-model="quesForm.optionA" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="B" :label-width="formLabelWidth" prop="optionB">
          <el-input v-model="quesForm.optionB" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="C" :label-width="formLabelWidth" prop="optionC">
          <el-input v-model="quesForm.optionC" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="D" :label-width="formLabelWidth" prop="optionD">
          <el-input v-model="quesForm.optionD" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="答案" :label-width="formLabelWidth" prop="answer">
          <el-select v-model="quesForm.answer" placeholder="请选择正确答案">
            <el-option label="A" value="1"></el-option>
            <el-option label="B" value="2"></el-option>
            <el-option label="C" value="3"></el-option>
            <el-option label="D" value="4"></el-option>
          </el-select>
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
import quesApi from "@/api/admin_question"
export default {
  name: "app",
  data() {
    return {
      formLabelWidth: "80px",
      title: "",
      dialogFormVisible: false,
      quesForm: {},
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        ques_id: 0,
      },
      quesList: [],
      rules: {
        description: [
          {
            required: true,
            message: "请输入题目描述",
            trigger: "blur",
          },
        ],
        optionA: [
          {
            required: true,
            message: "请输入选项内容",
            trigger: "blur",
          },
        ],
        optionB: [
          {
            required: true,
            message: "请输入选项内容",
            trigger: "blur",
          },
        ],
        optionC: [
          {
            required: true,
            message: "请输入选项内容",
            trigger: "blur",
          },
        ],
        optionD: [
          {
            required: true,
            message: "请输入选项内容",
            trigger: "blur",
          },
        ],
        answer: [
          {
            required: true,
            message: "请选择答案",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    handleSizeChange() {},
    handleCurrentChange() {},
    getOneQuestion() {
      quesApi.getOneQuestion(this.searchModel).then((response) => {
        this.quesList = response.data.rows;
        this.total = response.data.total;
      });
    },
    getAllQuestions() {
      quesApi.getAllQuestions(this.searchModel).then((response) => {
        this.quesList = response.data.rows;
        this.total = response.data.total;
      });
    },
    newQuestion() {},
    openNewUI() {
      this.title = "新增题目";
      this.dialogFormVisible = true;
    },
    openEditUI(id) {
      this.title = "更新题目";
      this.dialogFormVisible = true;
      quesApi.getOneQuestion_2(id).then((response) => {
        this.quesForm = response.data.rows[0];
      });
    },
    clearForm() {
      this.dialogFormVisible = false;
      this.quesForm = {};
      this.$refs.quesFormRef.clearValidate();
    },
    submitForm() {
      if ((this.title == "新增题目")) {
        this.$refs.quesFormRef.validate((valid) => {
          if (valid) {
            quesApi.newQuestion(this.quesForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.quesForm = {};
              this.$refs.quesFormRef.clearValidate();
              this.getAllQuestions();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      } else {
        this.$refs.quesFormRef.validate((valid) => {
          if (valid) {
            quesApi.updateQuestion(this.quesForm).then((response) => {
              this.dialogFormVisible = false;
              this.$message({
                message: response.message,
                type: "success",
              });
              this.quesForm = {};
              this.$refs.quesFormRef.clearValidate();
              this.getAllQuestions();
            });
          } else {
            console.log("error submit");
            return false;
          }
        });
      }
    },
    deleteById(id) {
      //quesApi.deleteQuestion(id);
      this.getAllQuestions();
    }
  },
  created() {
    this.getAllQuestions(this.searchModel);
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

