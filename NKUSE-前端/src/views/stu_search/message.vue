<template>
  <div
    v-if="show"
    class="container"
    style="min-height: 100%; padding-bottom: 50px; padding-top: 50px;"
  >
    <el-container
      style="box-shadow: 0px 15px 25px rgba(0,0,0,.5);
      padding-top: 20px;"
    >
      <el-header
        style="font-size: 20px;
        margin-left: 335px;"
      >缴费</el-header>
      <el-main>
        <success :suc.sync="sucFlag" />
        <el-button
          type="primary"
          size="small"
          style="margin-left: 270px;
            text-align: center;
            margin-right: 20px;"
          @click="onButtonClick"
        >确认</el-button>
        <el-button
          size="small"
          style="margin-right: 250px;
            text-align: center;"
          @click="closeShow"
        >取消</el-button>
      </el-main>
    </el-container>
  </div>
</template>
<script>

import success from './success'
export default {
  components: {
    success
  },
  props: ['show'],
  data() {
    return {
      sucFlag: false
    }
  },
  methods: {
    closeShow() {
      this.$emit('update:show', false)
    },
    onButtonClick() {
      this.sucFlag = !this.sucFlag
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
