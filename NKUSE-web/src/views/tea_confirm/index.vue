<template>
  <div>
    <div class="header">
      <el-button round @click="goBack">返回首页</el-button>
    </div>
    <div class="content">
      <div class="info">
        <p>时间: {{ time }}</p>
        <p>地点: {{ location }}</p>
      </div>
      <div class="actions">
        <el-button type="primary" plain @click="confirm">确认</el-button>
        <el-button type="primary" plain @click="cancel">取消</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      time: 'xxxx', // 用于展示时间的数据
      location: 'xxxxx', // 用于展示地点的数据
    };
  },
  methods: {
    goBack() {
      // 返回上页的逻辑
    },
    confirm() {
      // 点击确认按钮的逻辑
    },
    cancel() {
      // 点击取消按钮的逻辑
    },
  },
};
</script>

<style>
/* 样式可以根据需要进行自定义 */
.header {
  text-align: left;
  padding: 10px;
}

.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

.info {
  margin-bottom: 20px;
}

.actions button {
  margin: 5px;
}
</style>