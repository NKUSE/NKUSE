<template>
  <div
    v-if="suc"
    class="container"
    style="min-height: 100%;
    padding-bottom: 100px;"
  >
    <i
      class="el-icon-success"
      style="font-size: 40px;
      margin-left: 340px;
      margin-bottom: 20px;"
    />
    <br>
    <p
      style="margin-left: 325px;"
      v-text="successmessage"
    />
    <br>
    <el-button
      type="primary"
      size="medium"
      style="margin-left: 320px;"
      @click="closeShow"
    >返回</el-button>
  </div>
</template>

<script>
export default {
  components: {},
  props: ['suc'],
  data() {
    return {
      successmessage: '缴费成功！'
    }
  },
  computed: {},
  watch: {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destoryed() {},
  methods: {
    closeShow() {
      this.$emit('update:suc', false)
    }
  },
  fillter: {}
}
</script>

<style scoped>
  .container{}
</style>
