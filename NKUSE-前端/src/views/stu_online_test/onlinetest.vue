<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px">
    <el-container>
      <el-header>
        <div class="title-section">
          <h1 class="title">第{{this.selected_onlineexam_id}}届英语六级考试-在线测试</h1>
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
              <el-button round size="big" style="width: 240px; height: 40px"
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
              v-model="currentSubQuestion.answer"
            ></el-input>
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
  </div>
</template>

<script>
import onlineExamApi from "@/api/stu_online_exam";
import { get } from 'js-cookie';
export default {
  props: [],
  components: {},
  data() {
    return {
      selected_onlineexam_id: 1,
      paperid: 1,
      currentQuestionType: "objective",
      currentQuestionIndex: 0,
      currentSubQuestionIndex: 0,
      selectedOption: null,
      selectedOptions: [], // 新增选中选项数组
      selected: [], // 新增选中选项数组
      obj_questions_num: "20",
      sub_questions_num: "2",
      questions: [
        {
          seq_num: "1",
          question:
            "Question 1: --- I can't find my keys. Have you seen them anywhere?\n--- Yes, I __________ them on the kitchen counter.",
          options: ["A. saw", "B. have seen", "C. had seen", "D. see"],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "2",
          question:
            "Question 2: We've been waiting for the bus _______ half an hour, but it still hasn't arrived.",
          options: ["A. since", "B. for", "C. in", "D. during"],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "3",
          question: "Question 3: Could you please tell me ________?",
          options: [
            "A. where is the nearest bank",
            "B. where the nearest bank is",
            "C. the nearest bank where is",
            "D. where is nearest the bank",
          ],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "4",
          question:
            "Question 4: --- How long _______ studying in the library?\n--- Since 9 a.m.",
          options: [
            "A. have you been",
            "B. do you start",
            "C. did you start",
            "D. will you start",
          ],
          answer: 0,
          score: 5,
        },
        {
          seq_num: "5",
          question:
            "Question 5: I've never been to _______ university as beautiful as this one.",
          options: ["A. a", "B. an", "C. the", "D. no article needed"],
          answer: 2,
          score: 5,
        },
        {
          seq_num: "6",
          question:
            "Question 6: This is the first time I _______ such a delicious pizza.",
          options: [
            "A. have tasted",
            "B. tasted",
            "C. had tasted",
            "D. will taste",
          ],
          answer: 0,
          score: 5,
        },
        {
          seq_num: "7",
          question:
            "Question 7: _______ you work hard, you won't pass the exam.",
          options: ["A. If", "B. Unless", "C. Although", "D. Because"],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "8",
          question:
            "Question 8: My brother, along with his friends, _______ going to the concert tonight.",
          options: ["A. is", "B. are", "C. were", "D. have been"],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "9",
          question: "Question 9: The book on the desk belongs to _______.",
          options: ["A. he", "B. him", "C. his", "D. himself"],
          answer: 2,
          score: 5,
        },
        {
          seq_num: "10",
          question:
            "Question 10: We _______ go to the supermarket. We can order groceries online now.",
          options: [
            "A. don't have to",
            "B. mustn't",
            "C. needn't",
            "D. shouldn't",
          ],
          answer: 0,
          score: 5,
        },
        {
          seq_num: "11",
          question:
            "Question 11: _______ you finish your homework, you can play video games.",
          options: ["A. After", "B. Until", "C. While", "D. Since"],
          answer: 0,
          score: 5,
        },
        {
          seq_num: "12",
          question: "Question 12: Tom _______ the TV when the phone rang.",
          options: [
            "A. was watching",
            "B. watched",
            "C. had watched",
            "D. is watching",
          ],
          answer: 0,
          score: 5,
        },
        {
          seq_num: "13",
          question:
            "Question 13: --- Could you lend me your pen?\n--- I'm sorry, but I _______ mine either.",
          options: [
            "A. don't have",
            "B. didn't have",
            "C. haven't",
            "D. hadn't",
          ],
          answer: 2,
          score: 5,
        },
        {
          seq_num: "14",
          question:
            "Question 14: Not only _______ fluent English, but she also speaks French and German.",
          options: [
            "A. she speaks",
            "B. does she speak",
            "C. she does speak",
            "D. she did speak",
          ],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "15",
          question:
            "Question 15: --- Excuse me, is there _______ to park my car around here?\n--- Yes, there's a parking lot just down the street.",
          options: [
            "A. anywhere",
            "B. somewhere",
            "C. nowhere",
            "D. everywhere",
          ],
          answer: 0,
          score: 5,
        },
        {
          seq_num: "16",
          question:
            "Question 16: _______ hard he tried, he couldn't solve the math problem.",
          options: ["A. However", "B. Although", "C. Because", "D. Therefore"],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "17",
          question:
            "Question 17: You should apologize _______ her for your rude behavior.",
          options: ["A. with", "B. to", "C. for", "D. about"],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "18",
          question:
            "Question 18: My sister _______ a cake for my birthday party tomorrow.",
          options: ["A. will make", "B. makes", "C. is making", "D. has made"],
          answer: 2,
          score: 5,
        },
        {
          seq_num: "19",
          question:
            "Question 19: By the time we got to the cinema, the movie _______.",
          options: [
            "A. has already started",
            "B. had already started",
            "C. will already start",
            "D. already starts",
          ],
          answer: 1,
          score: 5,
        },
        {
          seq_num: "20",
          question:
            "Question 20: If you _______ more vegetables, you _______ healthier.",
          options: ["A. eat", "B. will eat", "C. have eaten", "D. are eating"],
          answer: 0,
          score: 5,
        },
        // Add more questions here...
      ],
      questions_sub: [
        {
          seq_num: "1",
          question:
            "",
          score: 20,
        },
        {
          seq_num: "2",
          question:
            "",
          score: 50,
        },
      ],
      currentQuestionIndex: 0,
      selectedOption: null,
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
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    init() {
      this.selected_onlineexam_id = this.$store.state.app.Examid;
      onlineExamApi.getPaperId(this.selected_onlineexam_id).then((response) => {this.paperid = response.data.paperid});
      onlineExamApi.getPaperInfo(this.paperid).then((response) => {
        this.questions = response.data.questions;
        this.questions_sub = response.data.questionsSub;
      });
    },
    onSubmit() {
      window.close();
      this.$router.push("/stu_online_test/success");
    },
    onResetClick(index) {
      this.questions_sub[index].answer = null;
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
</style>
