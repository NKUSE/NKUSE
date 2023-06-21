<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px;">
     
     <el-header>
        <div class="title-section">
          <h1 class="title">全国英语六级考试-在线报名</h1>
        </div>
      </el-header>
    <el-divider></el-divider>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-border custom-form"
    >
      <el-form-item label="报考场次" prop="region">
        <el-select v-model="ruleForm.region" placeholder="请选择报考场次">
          <el-option label="第1届英语六级考试" value="1"></el-option>
          <el-option label="第2届英语六级考试" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学校名称">
        <el-select v-model="formInline.region" placeholder="请选择学校名称">
          <el-option label="南开大学" value="nk"></el-option>
          <el-option label="天津大学" value="td"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="formInline.user" placeholder="请输入身份证号"></el-input>
      </el-form-item>
      <el-form-item label="电子邮箱">
        <el-input v-model="formInline.phone" placeholder="请输入电子邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机号码">
        <el-input v-model="formInline.email" placeholder="请输入手机号码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">立即报名</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
      
    </el-form>
  </div>
</template>

<script>
  export default {
    props: [],
    components: {},
    data() {
      return {
        formInline: {
          id: "",
          phone: "",
          email: "",
          region: "",
        },
        ruleForm: {
          name: "",
          region: "",
          date1: "",
          date2: "",
          delivery: false,
          type: [],
          resource: "",
          desc: "",
        },
        rules: {
        },
      }
    },
    watch: {},
    computed: {},
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    destoryed() {},
    methods: {
      request() {},
      resetForm() {
        this.$message({
                message:response.message,
                type:'success'
              });
      },
      submitForm() {
        console.log("submit!")
      },
    },
    fillter: {},
  }
</script>

<style scoped>
  .container{}
  .title-section {
  background-color: rgba(48, 65, 86, 0.8);
  padding: 1px;
  text-align: center;
}
.title {
  color: white;
  font-weight: bold;
}
  .demo-border { border: 1px grey dashed; min-height: 1rem; border-radius: 5px; }

  .custom-form {
  margin-top: 20px; /* 调整上边距 */
}
</style>