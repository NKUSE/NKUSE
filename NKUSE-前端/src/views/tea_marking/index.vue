<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px;">
    <div style="display: inline-block;" />
    <el-container>
      <el-aside width="200px">
        <el-button
          type="info"
          plain
          size="small;"
          style="margin-left: 10px; margin-bottom: 10px;"
          class="button2"
          @click="turnback"
        >返回首页</el-button>
      </el-aside>
      <div style="display: inline-block; width: 800px; height: 700px;">
  <el-image
    style="width: 100%; height: 80%; margin-bottom: 20px;"
    :src="currentUrl"
    fit="cover"
  />
  <br>
  <el-button
    type="primary"
    style="margin-left: 120px;"
    @click="lastpage"
  >上一页</el-button>
</div>
      <el-container>
        <span
          style="font-size: small"
        >
          <b>当前题号: 20</b>
          <div label-lc-mark />
        </span>
        <span
          style="font-size: small"
        >
          <b>当前题目满分: 15</b>
          <div label-lc-mark />
        </span>
        <span
          style="font-size: small"
        >
          <b>已评阅数量: {{urlIndex}}</b>
          <div label-lc-mark />
        </span>
        <span
          style="font-size: small"
        >
          <b>未评阅数量: 27</b>
          <div label-lc-mark />
        </span>
        <el-header />
        <el-main />
        <el-footer
          style="font-size: small;"
        >
          得分：
          <el-input
            v-model="input"
            style="font-size: small;"
            size="small"
            class="input"
          />
        </el-footer>
        <el-button
          type="primary"
          style="width: 70px; text-align: center; margin-left: 50px; font-size: small;"
          @click="submit"
        >确认</el-button>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      input: '',
      urls: ['https://pic3.zhimg.com/v2-17a5bffae2d61c3c4718ffea20684974_r.jpg'
      ,'https://pic2.zhimg.com/v2-6103f284ae8f6133e6b862924224faf5_r.jpg'
      ,'https://picx.zhimg.com/v2-64598d45a8f76e3255897973a7ed3eb9_r.jpg'
      ,'https://pic2.zhimg.com/v2-6103f284ae8f6133e6b862924224faf5_r.jpg'
      ],
      url4: 'https://pic3.zhimg.com/v2-17a5bffae2d61c3c4718ffea20684974_r.jpg',
      urlIndex: 0
    }
  },
  computed: {
    currentUrl() {
    return this.urls[this.urlIndex];
  }
  },
  watch: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    request() {},
    turnback() {
      this.$router.push('/')
    },
    lastpage() {
      this.urlIndex = (this.urlIndex - 1 + this.urls.length) % this.urls.length;
    },
    submit() {
      this.urlIndex = (this.urlIndex + 1) % this.urls.length;
    }
  },
  fillter: {}
}
</script>

<style scoped>
  .container{}
  .el-icon--right{}
  .el-icon-arrow-right{}
  .input{}
</style>
