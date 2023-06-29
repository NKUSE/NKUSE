<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px">
    <div style="display: inline-block" />
    <el-container>
      <el-aside width="200px">
        <el-button
          type="info"
          plain
          size="small;"
          style="margin-left: 10px; margin-bottom: 10px"
          class="button2"
          @click="turnback"
          >返回首页</el-button
        >
      </el-aside>
      <div style="display: inline-block; width: 800px; height: 700px">
        <div class="title-section">
          <h1 class="title">
            第{{ this.selected_exam_id }}届英语六级考试-在线阅卷
          </h1>
        </div>
        <el-card v-if="this.sheetList.length == this.urlIndex"
          ><el-result
            style="margin-top: 100px; margin-bottom: 100px"
            icon="success"
            title="试卷已全部批阅完毕"
            subtitle="点击按钮统计成绩并发布"
          >
            <template slot="extra">
              <el-button type="primary" size="medium" @click="publish()"
                >统计成绩并发布</el-button
              >
            </template>
          </el-result>
        </el-card>
        <el-card
          v-if="
            this.sheetList.length != 0 && this.sheetList.length != this.urlIndex
          "
        >
          <h3>翻译：</h3>
          <el-input
            class="textarea1"
            type="textarea"
            :rows="6"
            v-model="sheetList[urlIndex].answerTrans"
            readOnly="true"
          >
          </el-input>
          <el-divider></el-divider>
          <h3>写作：</h3>
          <br />
          <el-input
            class="textarea1"
            type="textarea"
            :rows="10"
            readOnly="true"
            v-model="sheetList[urlIndex].answerWriting"
          >
          </el-input>
          <br />
        </el-card>
      </div>

      <el-container>
        <el-card style="margin-top: 120px">
          <span style="font-size: small">
            <h3>翻译总分: 20</h3>
            <div label-lc-mark />
          </span>
          <span style="font-size: small">
            <h3>写作总分: 30</h3>
            <div label-lc-mark />
          </span>
          <span style="font-size: small">
            <h3>已评阅数量: {{ urlIndex }}</h3>
            <div label-lc-mark />
          </span>
          <span style="font-size: small">
            <h3>未评阅数量: {{ total - urlIndex }}</h3>
            <div label-lc-mark />
          </span>
        </el-card>
        <el-card style="">
          <el-form
            :model="score"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="翻译得分" prop="score_trans">
              <el-input
                v-model="score.score_trans"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="写作得分" prop="score_write">
              <el-input
                v-model="score.score_write"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <div style="display: flex">
            <el-button
              style="font-size: small; margin-right: 10px; margin-top: 10px"
              @click="lastpage"
              >上一份</el-button
            >
            <el-button
              type="primary"
              style="font-size: small; width: 70px; margin-top: 10px"
              @click="submit"
              >确认</el-button
            >
          </div>
        </el-card>
        <el-header />
        <el-main />
      </el-container>
    </el-container>
  </div>
</template>

<script>
import store from "@/store";
import markingAPI from "@/api/tea_marking";
export default {
  components: {},
  props: [],
  data() {
    return {
      input: "",
      urlIndex: 0,
      total: 0,
      sheetList: [],
      selected_exam_id: 0,
      score: { score_trans: "", score_write: "" },
      rules: {
        score_trans: [
          { required: true, message: "请输入翻译得分", trigger: "blur" },
          {
            pattern: /^(0|[1-9]|[1]\d|20)$/,
            message: "请输入0到20之间的数字",
            trigger: "blur",
          },
        ],
        score_write: [
          { required: true, message: "请输入写作得分", trigger: "blur" },
          {
            pattern: /^(0|[1-9]|[1-2]\d|30)$/,
            message: "请输入0到30之间的数字",
            trigger: "blur",
          },
        ],
      },
    };
  },
  computed: {
    currentUrl() {
      return this.urls[this.urlIndex];
    },
    currentSheet() {
      return this.sheetList[this.urlIndex];
    },
  },
  watch: {},
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
    request() {},
    turnback() {
      this.$router.push("/");
    },
    lastpage() {
      this.score.score_trans = "";
      this.score.score_write = "";
      if (this.urlIndex == 0) return;
      //this.urlIndex = (this.urlIndex - 1 + this.sheetList.length) % this.sheetList.length
      this.urlIndex -= 1;
    },
    submit() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          //更新键值对
          this.sheetList[this.urlIndex].scoreTrans = this.score.score_trans;
          this.score.score_trans = "";
          this.sheetList[this.urlIndex].scoreWriting = this.score.score_write;
          this.score.score_write = "";
          //调用api更新sheet
          markingAPI
            .updateSheet(this.sheetList[this.urlIndex])
            .then((response) => {
              if (this.urlIndex == this.sheetList.length) return;
              this.urlIndex += 1;
            });
        } else {
          console.log("error submit");
        }
      });
      //this.urlIndex = (this.urlIndex + 1) % this.sheetList.length
    },
    init() {
      this.selected_exam_id = this.$store.state.app.Examid;
      markingAPI.getSheets(this.selected_exam_id).then((response) => {
        this.total = response.data.total;
        this.sheetList = response.data.sheets;
      });
    },
    publish() {
      markingAPI.updateScore(this.selected_exam_id).then((response) => {
        this.$message({
          message: response.message,
          type: "success",
        });
      });
    },
  },
  fillter: {},
};
</script>

<style scoped>
.container {
}
.el-icon--right {
}
.el-icon-arrow-right {
}
.input {
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
.textarea1 {
  padding: 20px;
}
</style>
