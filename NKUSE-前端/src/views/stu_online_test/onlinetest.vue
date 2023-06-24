<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px">
    <el-container>
      <el-header>
        <div class="title-section">
          <h1 class="title">
            第{{ this.selected_onlineexam_id }}届英语六级考试-在线测试
          </h1>
        </div>
      </el-header>
      <el-container>
        <el-aside width="300px">
          <div class="sidebar-section">
            <h2 class="sidebar-title">答题卡</h2>
            <div class="answer-sheet">
              <div>客观题</div>
              <el-container>
                <!-- 其他内容... -->

                <el-row :gutter="10">
                  <el-col
                    v-for="(question, index) in questions"
                    :key="index"
                    :span="24 / 5"
                  >
                    <el-button
                      round
                      @click="setCurrentQuestion(index)"
                      size="small"
                      style="width: 40px; height: 40px"
                      :class="{ selected: selectedOptions[index] != null }"
                    >
                      {{ index + 1 }}
                    </el-button>
                    <div class="option"></div>
                  </el-col>
                </el-row>
              </el-container>

              <el-divider></el-divider>

              <div>主观题</div>
              <el-container>
                <!-- 其他内容... -->

                <el-row :gutter="10">
                  <el-col
                    v-for="(question, index) in questions_sub"
                    :key="index"
                    :span="24 / 5"
                  >
                    <el-button
                      round
                      @click="setCurrentQuestionSub(index)"
                      size="small"
                      style="width: 40px; height: 40px"
                      :class="{ selected: questions_sub[index].answer != null }"
                    >
                      {{ index + 1 }}
                    </el-button>
                    <div class="option"></div>
                  </el-col>
                </el-row>
              </el-container>

              <el-divider></el-divider>
              <el-button
                round
                size="big"
                style="width: 240px; height: 40px"
                @click="onSave"
                >保存答案</el-button
              >
              <div style="margin-bottom: 20px"></div>
              <el-button
                type="primary"
                round
                @click="onSubmit"
                size="big"
                style="width: 240px; height: 40px"
                >提交试卷</el-button
              >
            </div>
          </div>
        </el-aside>
        <el-main class="main-section">
          <div
            class="question-section"
            v-if="currentQuestionType === 'objective'"
          >
            <!-- 客观题内容 -->
            <div class="question-section" v-if="currentQuestion">
              <div class="question">
                [单项选择题 {{ currentQuestion.seq_num }}/{{
                  obj_questions_num
                }}] ({{ currentQuestion.score }}分)
              </div>
              <div>{{ currentQuestion.question }}</div>
              <el-divider></el-divider>
              <div class="options">
                <div
                  v-for="(option, index) in currentQuestion.options"
                  :key="index"
                  class="option"
                >
                  <label>
                    <input
                      type="radio"
                      v-model="selectedOptions[currentQuestionIndex]"
                      :value="index"
                    />
                    {{ option }}
                  </label>
                </div>
              </div>
            </div>
            <el-button-group>
              <el-button-group class="bottom-buttons">
                <el-button
                  type="primary"
                  @click="goToPreviousQuestion"
                  icon="el-icon-arrow-left"
                  >上一题</el-button
                >
                <el-button type="primary" @click="goToNextQuestion"
                  >下一题 <i class="el-icon-arrow-right el-icon--right"></i
                ></el-button>
              </el-button-group>
            </el-button-group>
          </div>
          <div
            class="question-section"
            v-else-if="currentQuestionType === 'subjective'"
          >
            <!-- 主观题内容 -->
            <div class="question">
              [主观题 {{ currentSubQuestion.seq_num }}/{{ sub_questions_num }}]
              ({{ currentSubQuestion.score }}分)
            </div>
            <div>{{ currentSubQuestion.question }}</div>
            <el-divider></el-divider>
            <div>请在此输入答案：</div>
            <el-input
              type="textarea"
              v-model="questions_sub[currentSubQuestionIndex].answer"
              @input="inputChange"
            ></el-input
            ><!--" -->
            <div class="bottom-buttons">
              <el-button
                plain
                @click="onResetClick(currentSubQuestion.seq_num - 1)"
                size="big"
                >重置</el-button
              >
              <el-button type="primary" plain @click="onSaveClick" size="big"
                >保存答案</el-button
              >
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
      <div class="countdown-section">考试剩余时间：{{ formatTime(countdown) }}</div>
  </div>
</template>

<script>
import store from "@/store";
import onlineExamApi from "@/api/stu_online_exam";
import { get } from "js-cookie";
export default {
  props: [],
  components: {},
  data() {
    return {
      countdown: 7200, // 初始值为2小时（2小时 = 2 * 60 * 60秒）
      user_id: 0,
      selected_onlineexam_id: 1,
      paperid: 1,
      sheetid: 0,
      currentQuestionType: "objective",
      currentQuestionIndex: 0,
      currentSubQuestionIndex: 0,
      selectedOption: null,
      selectedOptions: [], // 新增选中选项数组
      selected: [], // 新增选中选项数组
      obj_questions_num: "20",
      sub_questions_num: "2",
      questions: [],
      questions_sub: [],
      currentQuestionIndex: 0,
      subanswer: [],
    };
  },
  watch: {},
  computed: {
    currentQuestion() {
      return this.questions[this.currentQuestionIndex];
    },
    currentSubQuestion() {
      return this.questions_sub[this.currentSubQuestionIndex];
    },
  },
  beforeCreate() {},
  created() {
    this.init();
  },
  beforeMount() {},
  mounted() {
    setInterval(() => {
      this.countdown -= 1;
      if (this.countdown <= 0) {
        clearInterval(); // 倒计时结束后停止计时器
        this.onSubmit();
      }
    }, 1000);
  },
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    
    inputChange(e) {
      this.$forceUpdate();
    },
    init() {
      console.log("init执行");
      this.user_id = store.getters.userid;
      this.selected_onlineexam_id = this.$store.state.app.Examid;
      onlineExamApi.getPaperId(this.selected_onlineexam_id).then((response) => {
        this.paperid = response.data.paperid;
      });
      onlineExamApi.getPaperInfo(this.paperid).then((response) => {
        this.questions = response.data.questions;
        this.questions_sub = response.data.questionsSub;
        //初始化答题卡
        onlineExamApi
          .getSheetId(this.user_id, this.selected_onlineexam_id)
          .then((response) => {
            this.sheetid = response.data.answersheetId;
            onlineExamApi.getSheet(this.sheetid).then((response) => {
              this.selectedOptions = response.data.objlist;
              this.questions_sub[0].answer = response.data.sublist[0];
              this.questions_sub[1].answer = response.data.sublist[1];
            });
          });
      });
    },
    onSubmit() {
      var sheetinfo = {};
      sheetinfo.answersheetId = this.sheetid;
      sheetinfo.userId = this.user_id;
      sheetinfo.examId = this.selected_onlineexam_id;
      sheetinfo.answerTrans = this.questions_sub[0].answer;
      sheetinfo.answerWriting = this.questions_sub[1].answer;
      sheetinfo.answerObj1 = this.selectedOptions[0];
      sheetinfo.answerObj2 = this.selectedOptions[1];
      sheetinfo.answerObj3 = this.selectedOptions[2];
      sheetinfo.answerObj4 = this.selectedOptions[3];
      sheetinfo.answerObj5 = this.selectedOptions[4];
      sheetinfo.answerObj6 = this.selectedOptions[5];
      sheetinfo.answerObj7 = this.selectedOptions[6];
      sheetinfo.answerObj8 = this.selectedOptions[7];
      sheetinfo.answerObj9 = this.selectedOptions[8];
      sheetinfo.answerObj10 = this.selectedOptions[9];
      sheetinfo.answerObj11 = this.selectedOptions[10];
      sheetinfo.answerObj12 = this.selectedOptions[11];
      sheetinfo.answerObj13 = this.selectedOptions[12];
      sheetinfo.answerObj14 = this.selectedOptions[13];
      sheetinfo.answerObj15 = this.selectedOptions[14];
      sheetinfo.answerObj16 = this.selectedOptions[15];
      sheetinfo.answerObj17 = this.selectedOptions[16];
      sheetinfo.answerObj18 = this.selectedOptions[17];
      sheetinfo.answerObj19 = this.selectedOptions[18];
      sheetinfo.answerObj20 = this.selectedOptions[19];
      onlineExamApi.submitSheet(sheetinfo).then((response) => {
        this.$message({
          message: response.message,
          type: "success",
        });
        window.close();
        this.$router.push("/stu_online_test/success");
      });
    },
    onSave() {
      //获取信息
      var sheetinfo = {};
      sheetinfo.answersheetId = this.sheetid;
      sheetinfo.userId = this.user_id;
      sheetinfo.examId = this.selected_onlineexam_id;
      sheetinfo.answerTrans = this.questions_sub[0].answer;
      sheetinfo.answerWriting = this.questions_sub[1].answer;
      sheetinfo.answerObj1 = this.selectedOptions[0];
      sheetinfo.answerObj2 = this.selectedOptions[1];
      sheetinfo.answerObj3 = this.selectedOptions[2];
      sheetinfo.answerObj4 = this.selectedOptions[3];
      sheetinfo.answerObj5 = this.selectedOptions[4];
      sheetinfo.answerObj6 = this.selectedOptions[5];
      sheetinfo.answerObj7 = this.selectedOptions[6];
      sheetinfo.answerObj8 = this.selectedOptions[7];
      sheetinfo.answerObj9 = this.selectedOptions[8];
      sheetinfo.answerObj10 = this.selectedOptions[9];
      sheetinfo.answerObj11 = this.selectedOptions[10];
      sheetinfo.answerObj12 = this.selectedOptions[11];
      sheetinfo.answerObj13 = this.selectedOptions[12];
      sheetinfo.answerObj14 = this.selectedOptions[13];
      sheetinfo.answerObj15 = this.selectedOptions[14];
      sheetinfo.answerObj16 = this.selectedOptions[15];
      sheetinfo.answerObj17 = this.selectedOptions[16];
      sheetinfo.answerObj18 = this.selectedOptions[17];
      sheetinfo.answerObj19 = this.selectedOptions[18];
      sheetinfo.answerObj20 = this.selectedOptions[19];
      onlineExamApi.updateSheet(sheetinfo).then((response) => {
        this.$message({
          message: response.message,
          type: "success",
        });
      });
    },
    onResetClick(index) {
      this.questions_sub[index].answer = null;
      this.$forceUpdate();
    },
    onSaveClick(index) {
      //this.questions_sub[index].answer = null
    },
    goToPreviousQuestion() {
      if (this.currentQuestionIndex > 0) {
        this.currentQuestionIndex--;
      }
    },
    goToNextQuestion() {
      if (this.currentQuestionIndex < this.questions.length - 1) {
        this.currentQuestionIndex++;
      }
    },
    setCurrentQuestion(index) {
      this.currentQuestionIndex = index;
      this.selectedOption = this.selectedOptions[index];
      this.selected[index] = true;
      this.currentQuestionType = "objective";
    },
    setCurrentQuestionSub(index) {
      this.currentSubQuestionIndex = index;
      this.selectedOption = this.selectedOptions[index];
      this.selected[index] = true;
      this.currentQuestionType = "subjective";
    },formatTime(seconds) {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const formattedTime = `${hours}:${minutes.toString().padStart(2, '0')}:${(seconds % 60).toString().padStart(2, '0')}`;
    return formattedTime;
  },
  },
  fillter: {},
};
</script>

<style scoped>
.question {
  margin-bottom: 20px;
  font-weight: bold;
  color: rgb(14, 14, 38);
}

.container {
  min-height: 100%;
  padding-bottom: 100px;
}

.title-section {
  background-color: rgba(48, 65, 86, 0.8);
  padding: 20px;
  text-align: center;
}

.title {
  color: white;
  font-weight: bold;
}

.sidebar-section {
  padding: 20px;
  margin-top: 40px;
}

.sidebar-title {
  color: #333;
}

.answer-sheet {
  margin-top: 20px;
}

.question-number {
  display: inline-block;
  margin-right: 10px;
  cursor: pointer;
}

.main-section {
  height: 60vh;
  padding: 20px;
  margin-top: 80px;
  margin-right: 20px;
  border: 2px solid #333;
  border-radius: 4px;
  position: relative;
}

.main-title {
  color: #011b1d21;
}
.options {
  display: flex;
  flex-direction: column;
}

.option {
  margin-bottom: 10px; /* 调整选项之间的下方间距 */
}
.selected {
  /* 添加选中样式的样式属性 */
  background-color: rgba(210, 226, 244, 0.5);
  color: black;
}
.bottom-buttons {
  position: absolute;
  bottom: 10px;
  right: 10px;
}
.countdown-section {
  
  text-align: right;
  font-weight: bold;
  margin-right: 20px;font-size: 20px; /* 根据需要调整字体大小 */

}
</style>
