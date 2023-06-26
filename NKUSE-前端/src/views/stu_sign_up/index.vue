<template>
  <div>
    <div class="header">
      <h1 class="title">全国大学生六级考试报名系统</h1>
    </div>
    <div class="card">
      <div class="card-header">
        <h2 class="card-title">欢迎使用CET考试报名系统</h2>
      </div>
      <div class="card-body">
        <table class="info-table">
          <thead>
            <tr>
              <th colspan="2">基本信息</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>考次名称：</td>
              <td>2023年上半年CET考试</td>
            </tr>
            <tr>
              <td>考点省份：</td>
              <td>
                <strong class="highlight">天津市（当前考点省份需为报名考点所在省份）</strong>
              </td>
            </tr>
            <tr>
              <td>报名状态：</td>
              <td>
                <strong class="highlight">未报名（或当前通行证账号非报名时账号，或当前考点省份非报名时省份）</strong>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="card-footer">
        <el-button type="primary" @click="onClick">开始报名</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
    data() {
        return {}
    },
    methods: {
      request() {},
      onClick() {
        this.$router.push('/stu_sign_up/details')
      },
    }
}
</script>
<style scoped>
.header {
  background-color:  rgba(48, 65, 86, 0.8); /* 天青色 */
  padding: 20px;
  text-align: center;
}
.title {
  color: white;
}
.card {
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  margin: 20px auto;
  width: 50%;
}
.card-header {
  background-color:  rgba(48, 65, 86, 0.8); /* 天青色 */
  color: white;
  padding: 10px;
}
.card-title {
  margin: 0;
}
.card-body {
  padding: 20px;
}
.info-table {
  width: 100%;
  border-collapse: collapse;
}
.info-table th,
.info-table td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ccc;
}
.card-footer {
  padding: 10px;
  text-align: center;
}
.start-button {
  background-color:  rgba(48, 65, 86, 0.8); /* 红色 */
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}
.highlight {
  color: red; /* 红色 */
}
</style>