<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px;">
    <div class="dashboard-text">ID: {{ id }}</div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>卡片名称</span>
        <el-button style="float: right; padding: 3px 0" type="text"
          >操作按钮</el-button
        >
      </div>
      <div v-for="o in 4" :key="o" class="text item">{{ "列表内容 " + o }}</div>
    </el-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

  export default {
    name: 'Online_test',
    props: [],
    components: {},
    computed: {
        ...mapGetters([
            'id'
        ])
    },
    watch: {},
    beforeCreate() {},
    created() {
      
    },
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    destoryed() {},
    methods: {
      request() {},
      onButtonClick() {},
    },
    fillter: {},
  }
</script>

<style scoped>
  .box-card{}
  .clearfix{}
  .container{}
  .text{}
  .item { margin-top: 10px; margin-right: 40px; }
</style>