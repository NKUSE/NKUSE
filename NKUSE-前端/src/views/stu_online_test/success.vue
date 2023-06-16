<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px;">
    <el-result icon="success" title="提交成功" subtitle="试卷提交成功">
      <template slot="extra">
        <el-button type="primary" size="medium" @click="onButtonClick">返回</el-button>
      </template>
    </el-result>
  </div>
</template>

<script>
export default {
methods: {
      onButtonClick() {
        this.$router.push('/')
      },
    },
}
</script>

<style>
    
  .container{}
</style>