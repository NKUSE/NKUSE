<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px">
    <el-card>
      <el-table :data="examList" style="width: 100%">
        <el-table-column prop="examId" label="考试ID" />
        <el-table-column prop="examName" label="考试名称" />
        <el-table-column prop="examState" label="考试状态">
          <template slot-scope="scope">
            <span>{{ getExamState(scope.row.examState) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="success"
              size="mini"
              v-if="scope.row.examPaperid == null"
              @click="OnNewClick()"
              >添加试卷</el-button
            >
            <el-button
              type="primary"
              size="mini"
              v-else
              @click="OnViewClick(scope.row.examPaperid)"
              >查看试卷</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog
      :title="title"
      :visible.sync="ViewDialogFormVisible"
      :before-close="handleClose"
    >
      <h1 class="title" style="">第1届英语六级考试-在线测试</h1>
      <el-card v-if="quesList[0] != null">
        <el-row> 1.{{ quesList[0].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[0].optionA }} </el-row>
        <el-row> B.{{ quesList[0].optionB }} </el-row>
        <el-row> C.{{ quesList[0].optionC }} </el-row>
        <el-row> D.{{ quesList[0].optionD }} </el-row>
      </el-card>
      <el-card v-if="quesList[1] != null">
        <el-row> 2.{{ quesList[1].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[1].optionA }} </el-row>
        <el-row> B.{{ quesList[1].optionB }} </el-row>
        <el-row> C.{{ quesList[1].optionC }} </el-row>
        <el-row> D.{{ quesList[1].optionD }} </el-row>
      </el-card>
      <el-card v-if="quesList[2] != null">
        <el-row> 3.{{ quesList[2].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[2].optionA }} </el-row>
        <el-row> B.{{ quesList[2].optionB }} </el-row>
        <el-row> C.{{ quesList[2].optionC }} </el-row>
        <el-row> D.{{ quesList[2].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[3] != null">
        <el-row> 4.{{ quesList[3].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[3].optionA }} </el-row>
        <el-row> B.{{ quesList[3].optionB }} </el-row>
        <el-row> C.{{ quesList[3].optionC }} </el-row>
        <el-row> D.{{ quesList[3].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[4] != null">
        <el-row> 5.{{ quesList[4].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[4].optionA }} </el-row>
        <el-row> B.{{ quesList[4].optionB }} </el-row>
        <el-row> C.{{ quesList[4].optionC }} </el-row>
        <el-row> D.{{ quesList[4].optionD }} </el-row>
      </el-card>
      <el-card v-if="quesList[5] != null">
        <el-row> 6.{{ quesList[5].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[5].optionA }} </el-row>
        <el-row> B.{{ quesList[5].optionB }} </el-row>
        <el-row> C.{{ quesList[5].optionC }} </el-row>
        <el-row> D.{{ quesList[5].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[6] != null">
        <el-row> 7.{{ quesList[6].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[6].optionA }} </el-row>
        <el-row> B.{{ quesList[6].optionB }} </el-row>
        <el-row> C.{{ quesList[6].optionC }} </el-row>
        <el-row> D.{{ quesList[6].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[7] != null">
        <el-row> 8.{{ quesList[7].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[7].optionA }} </el-row>
        <el-row> B.{{ quesList[7].optionB }} </el-row>
        <el-row> C.{{ quesList[7].optionC }} </el-row>
        <el-row> D.{{ quesList[7].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[8] != null">
        <el-row> 9.{{ quesList[8].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[8].optionA }} </el-row>
        <el-row> B.{{ quesList[8].optionB }} </el-row>
        <el-row> C.{{ quesList[8].optionC }} </el-row>
        <el-row> D.{{ quesList[8].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[9] != null">
        <el-row> 10.{{ quesList[9].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[9].optionA }} </el-row>
        <el-row> B.{{ quesList[9].optionB }} </el-row>
        <el-row> C.{{ quesList[9].optionC }} </el-row>
        <el-row> D.{{ quesList[9].optionD }} </el-row> </el-card
      ><el-card v-if="quesList[10] != null">
        <el-row> 11.{{ quesList[10].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[10].optionA }} </el-row>
        <el-row> B.{{ quesList[10].optionB }} </el-row>
        <el-row> C.{{ quesList[10].optionC }} </el-row>
        <el-row> D.{{ quesList[10].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[11] != null">
        <el-row> 12.{{ quesList[11].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[11].optionA }} </el-row>
        <el-row> B.{{ quesList[11].optionB }} </el-row>
        <el-row> C.{{ quesList[11].optionC }} </el-row>
        <el-row> D.{{ quesList[11].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[12] != null">
        <el-row> 13.{{ quesList[12].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[12].optionA }} </el-row>
        <el-row> B.{{ quesList[12].optionB }} </el-row>
        <el-row> C.{{ quesList[12].optionC }} </el-row>
        <el-row> D.{{ quesList[12].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[13] != null">
        <el-row> 14.{{ quesList[13].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[13].optionA }} </el-row>
        <el-row> B.{{ quesList[13].optionB }} </el-row>
        <el-row> C.{{ quesList[13].optionC }} </el-row>
        <el-row> D.{{ quesList[13].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[14] != null">
        <el-row> 15.{{ quesList[14].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[14].optionA }} </el-row>
        <el-row> B.{{ quesList[14].optionB }} </el-row>
        <el-row> C.{{ quesList[14].optionC }} </el-row>
        <el-row> D.{{ quesList[14].optionD }} </el-row> </el-card
      ><el-card v-if="quesList[15] != null">
        <el-row> 16.{{ quesList[15].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[15].optionA }} </el-row>
        <el-row> B.{{ quesList[15].optionB }} </el-row>
        <el-row> C.{{ quesList[15].optionC }} </el-row>
        <el-row> D.{{ quesList[15].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[16] != null">
        <el-row> 17.{{ quesList[16].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[16].optionA }} </el-row>
        <el-row> B.{{ quesList[16].optionB }} </el-row>
        <el-row> C.{{ quesList[16].optionC }} </el-row>
        <el-row> D.{{ quesList[16].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[17] != null">
        <el-row> 18.{{ quesList[17].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[17].optionA }} </el-row>
        <el-row> B.{{ quesList[17].optionB }} </el-row>
        <el-row> C.{{ quesList[17].optionC }} </el-row>
        <el-row> D.{{ quesList[17].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[18] != null">
        <el-row> 19.{{ quesList[18].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[18].optionA }} </el-row>
        <el-row> B.{{ quesList[18].optionB }} </el-row>
        <el-row> C.{{ quesList[18].optionC }} </el-row>
        <el-row> D.{{ quesList[18].optionD }} </el-row>
      </el-card>

      <el-card v-if="quesList[19] != null">
        <el-row> 20.{{ quesList[19].description }} </el-row>
        <el-divider></el-divider>
        <el-row> A.{{ quesList[19].optionA }} </el-row>
        <el-row> B.{{ quesList[19].optionB }} </el-row>
        <el-row> C.{{ quesList[19].optionC }} </el-row>
        <el-row> D.{{ quesList[19].optionD }} </el-row>
      </el-card>
      <el-card v-if="paperInfoForm != null">
        <el-row> 21.翻译部分 </el-row>
        <el-divider></el-divider>
        <el-row> {{paperInfoForm.questionTranslating}} </el-row>
      </el-card><el-card v-if="paperInfoForm != null">
        <el-row> 22.写作部分 </el-row>
        <el-divider></el-divider>
        <el-row> {{paperInfoForm.questionWriting}} </el-row>
      </el-card>
      <div slot="footer" class="dialog-footer">
        <el-button @click="ViewFinished()">编 辑</el-button>
        <el-button type="primary" @click="ViewFinished()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import DatetimePicker from 'vue-datetime-picker'
import examInfoApi from "@/api/admin_examinfo";
import paperInfoApi from "@/api/admin_paperinfo";
import quesApi from "@/api/admin_question";
export default {
  components: {},
  filters: {},
  props: [],
  data() {
    return {
      examIdCounter: 0,
      ViewDialogFormVisible: false,
      NewDialogFormVisible: false,
      title: "",
      examInfoForm: {},
      examList: [],
      formLabelWidth: "80px",
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        examInfoId: 0,
        quesId: 0,
      },
      paperInfoForm: {},
      quesList: [],
    };
  },
  computed: {},
  watch: {},
  beforeCreate() {},
  created() {
    this.getAllExamInfos(this.searchModel);
    this.getMaximumExamId();
  },
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
    // new
    // 获取已存在考试的最大 examId 值
    getMaximumExamId() {
      // 调用接口或查询数据库，获取最大 examId 值
      examInfoApi.getMaximumExamId().then((response) => {
        // 将 examIdCounter 初始化为已存在的最大 examId 值加一
        this.examIdCounter = response.data.maximumExamId + 1;
      });
    },
    getAllExamInfos() {
      examInfoApi.getAllExamInfos(this.searchModel).then((response) => {
        // this.tableData = response.data.rows
        this.examList = response.data.rows;
        this.total = response.data.total;
      });
    },
    confirmDeleteObjective(id) {
      examInfoApi.deleteExamInfo(id);
      this.getAllExamInfos();
    },
    getExamState(state) {
      // 根据考试状态的数值返回相应的字符串
      if (state === 1) {
        return "开始报名";
      } else if (state === 2) {
        return "结束报名";
      } else if (state === 3) {
        return "等待阅卷";
      } else if (state === 4) {
        return "已结束";
      } else {
        return "未知状态";
      }
    },
    getPaperInfos() {},
    OnNewClick() {
      this.title = "添加试卷";
      this.NewDialogFormVisible = true;
    },
    getOneQuestion(id, num) {
      quesApi.getOneQuestion_2(id).then((response) => {
        if (response.data.rows == null) {
          this.quesList.push(null);
        } else {
          this.quesList.push(response.data.rows[0]);
        }
      });
    },
    OnViewClick(paperId) {
      this.title = "查看试卷";
      this.ViewDialogFormVisible = true;
      paperInfoApi.getOnePaperInfo(paperId).then((response) => {
        this.paperInfoForm = response.data.rows;
        this.getOneQuestion(this.paperInfoForm.questionObj1, 0);
        this.getOneQuestion(this.paperInfoForm.questionObj2, 1);
        this.getOneQuestion(this.paperInfoForm.questionObj3, 2);
        this.getOneQuestion(this.paperInfoForm.questionObj4, 3);
        this.getOneQuestion(this.paperInfoForm.questionObj5, 4);
        this.getOneQuestion(this.paperInfoForm.questionObj6, 5);
        this.getOneQuestion(this.paperInfoForm.questionObj7, 6);
        this.getOneQuestion(this.paperInfoForm.questionObj8, 7);
        this.getOneQuestion(this.paperInfoForm.questionObj9, 8);
        this.getOneQuestion(this.paperInfoForm.questionObj10, 9);
        this.getOneQuestion(this.paperInfoForm.questionObj11, 10);
        this.getOneQuestion(this.paperInfoForm.questionObj12, 11);
        this.getOneQuestion(this.paperInfoForm.questionObj13, 12);
        this.getOneQuestion(this.paperInfoForm.questionObj14, 13);
        this.getOneQuestion(this.paperInfoForm.questionObj15, 14);
        this.getOneQuestion(this.paperInfoForm.questionObj16, 15);
        this.getOneQuestion(this.paperInfoForm.questionObj17, 16);
        this.getOneQuestion(this.paperInfoForm.questionObj18, 17);
        this.getOneQuestion(this.paperInfoForm.questionObj19, 18);
        this.getOneQuestion(this.paperInfoForm.questionObj20, 19);
      });
    },
    handleClose(done) {
      this.$confirm('确定要关闭对话框吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 用户点击了确认按钮
        this.paperInfoForm = {};
        this.quesList = [];
        done() // 关闭对话框
      }).catch(() => {
        // 用户点击了取消按钮
      })
    },
    ViewFinished() {
      this.paperInfoForm = {};
      this.quesList = [];
      this.ViewDialogFormVisible = false;
    }
  },
};
</script>

  <style scoped>
.container {
}
</style>

