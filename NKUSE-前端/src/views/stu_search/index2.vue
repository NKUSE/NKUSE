


<template>
  <div
    class="container"
    style="min-height: 100%;
    padding-bottom: 100,    margin-left: 50px;"
  >
    <el-button
      type="info"
      plain
      size="small;"
      style="min-height: 100%;
      margin-top: 10px;
      margin-bottom: 10px;
      margin-left: 10px;"
      class="button2"
      @click="turnback"
    >返回首页</el-button>
    <el-table
      :show-header="false"
      :data="tableData"
      border
      :cell-style="columnStyle"
      style="width: 100%; margin-top: 10px"
    > 
      <el-table-column prop="name" align="center" />
      <el-table-column prop="value" align="center" />
    </el-table>
    <message :show.sync="showFlag" />
    <el-button
      type="primary"
      size="small;"
      style="min-height: 100%;
      margin-top: 20px;
      margin-left: 10px;"
      class="button1"
      @click="onButtonClick"
    >点击缴费</el-button>
  </div>
</template>

<script>
import message from './message'
export default {
  components: {
    message
  },
  props: ['show'],
  data() {
    return {
      showFlag: false,
      tableData: [{
        name: '姓名',
        value: '南小开'
      },
      {
        name: '身份证',
        value: 'xxxxxx xxxx xxxx xxxx'
      },
      {
        name: '考点信息',
        value: '一个考点'
      },
      {
        name: '考场ID',
        value: 'xx'
      },
      {
        name: '座位号',
        value: '01'
      },
      {
        name: '缴费状态',
        value: '未缴费'
      }]
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
    // 自定义列背景色
    columnStyle({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        // 修改每行第一个单元格的背景色
        return 'background:#f3f6fc;'
      } else {
        return 'background:#ffffff;'
      }
    },
    onButtonClick() {
      this.showFlag = !this.showFlag
    },
    turnback() {
      this.$router.push('/')
    }
  },
  fillter: {}
}
</script>

<style scoped>
  .button1{}
  .button2{}
  .container{}
</style>
