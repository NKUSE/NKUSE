<template>
  <div>
    <el-header class="header">
      <div class="title"></div>
    </el-header>
    <el-carousel :interval="5000" style="height: auto">
      <el-carousel-item>
        <img src="../../assets/bg.png" alt="Carousel Image 1" />
      </el-carousel-item>
      <el-carousel-item>
        <img src="../../assets/bg6.png" alt="Carousel Image 2" />
      </el-carousel-item>
    </el-carousel>
    <!-- 其他组件内容... -->
    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-image
                style="width: 250px; height: 50px"
                :src="require('@/assets/dashboard_items/6.png')"
                fit="cover"
              />
            </div>
            <div style="display: inline-block">
              <div style="margin-bottom: 40px">报名信息管理页面</div>
              <div class="mores">
                <a
                  class="link-style"
                  href="http://localhost:6613/#/admin-register/index"
                  >点击跳转&gt;&gt;</a
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-image
                style="width: 250px; height: 50px"
                :src="require('@/assets/dashboard_items/7.png')"
                fit="cover"
              />
            </div>
            <div style="display: inline-block">
              <div style="margin-bottom: 40px">用户信息管理页面</div>
              <div class="mores">
                <a
                  class="link-style"
                  href="http://localhost:6613/#/admin-userinfo/index"
                  >点击跳转&gt;&gt;</a
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-image
                style="width: 250px; height: 50px"
                :src="require('@/assets/dashboard_items/8.png')"
                fit="cover"
              />
            </div>
            <div style="display: inline-block">
              <div style="margin-bottom: 40px">考场信息管理页面</div>
              <div class="mores">
                <a
                  class="link-style"
                  href="http://localhost:6613/#/admin-examroominfo/index"
                  >点击跳转&gt;&gt;</a
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-image
                style="width: 250px; height: 50px"
                :src="require('@/assets/dashboard_items/9.png')"
                fit="cover"
              />
            </div>
            <div style="display: inline-block">
              <div style="margin-bottom: 40px">考试信息管理页面</div>
              <div class="mores">
                <a
                  class="link-style"
                  href="http://localhost:6613/#/admin-examinfo/index"
                  >点击跳转&gt;&gt;</a
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-image
                style="width: 250px; height: 50px"
                :src="require('@/assets/dashboard_items/10.png')"
                fit="cover"
              />
            </div>
            <div style="display: inline-block">
              <div style="margin-bottom: 40px">考题信息管理页面</div>
              <div class="mores">
                <a
                  class="link-style"
                  href="http://localhost:6613/#/admin-questioninfo/index"
                  >点击跳转&gt;&gt;</a
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-image
                style="width: 250px; height: 50px"
                :src="require('@/assets/dashboard_items/11.png')"
                fit="cover"
              />
            </div>
            <div style="display: inline-block">
              <div style="margin-bottom: 40px">试卷管理页面</div>
              <div class="mores">
                <a
                  class="link-style"
                  href="http://localhost:6613/#/admin-paperinfo/index"
                  >点击跳转&gt;&gt;</a
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      links: [
        { title: "考试信息", image: "exam-info.png", url: "/exam" },
        { title: "报名指南", image: "registration-guide.png", url: "/guide" },
        // 添加更多链接对象
      ],
      url4: "../../assets/1.png",
      url5: "../../assets/dashboard_items/2.png",
      url6: "../../assets/dashboard_items/3.png",
      url7: "../../assets/dashboard_items/4.png",
      selectedLink: null,
    };
  },
  methods: {
    goToPage(url) {
      // 根据传入的URL跳转到对应页面的逻辑
    },
  },
  mounted() {
    // 默认选择第一个链接
    this.selectedLink = this.links[0];
  },
  watch: {
    selectedLink() {
      // 监听选中链接的变化，进行页面跳转
      if (this.selectedLink) {
        this.goToPage(this.selectedLink.url);
      }
    },
  },
};
</script>

<style scoped>
.header {
  background-image: url("../../assets/titleline.jpg");
  background-repeat: no-repeat;
  color: white;
  padding: 20px;
  text-align: center;
}

.title {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
}

.logo {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}
.custom-card {
  height: 200px; /* 自定义高度 */
  /* 其他样式设置 */
}
.card-item {
  width: 250px;
  height: 200px;
  margin: 10px;
}

.card-content {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  height: 100%;
}

.card-content i {
  font-size: 24px;
  margin-bottom: 10px;
  width: 24px;
  height: 24px;
  background-size: cover;
}

.bg-purple {
  background: #d3dce6 !important;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}

.header-text {
  font-weight: bold;
  font-size: 16px;
  text-align: right;
}
.mores {
  text-align: right;
  margin: 0px -50px 0 0;
}
.link-style {
  color: blue;
  font-style: italic;
}
</style>
