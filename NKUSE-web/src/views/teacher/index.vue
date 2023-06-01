<template>
  <div>
    <div class="header">
      <button @click="goBack">返回上页</button>
    </div>
    <div class="content">
      <div class="section">
        <label for="exam">选择考试场次:</label>
        <select id="exam" v-model="selectedExam">
          <option v-for="exam in exams" :key="exam.id" :value="exam.id">{{ exam.name }}</option>
        </select>
      </div>
      <div class="section">
        <label for="time">时间:</label>
        <select id="time" v-model="selectedTime">
          <option v-for="time in times" :key="time" :value="time">{{ time }}</option>
        </select>
      </div>
      <div class="section">
        <label for="location">地点:</label>
        <select id="location" v-model="selectedLocation">
          <option v-for="location in locations" :key="location" :value="location">{{ location }}</option>
        </select>
      </div>
      <div class="actions">
        <button @click="confirm">确认</button>
        <button @click="cancel">取消</button>
      </div>
    </div>
    <el-dialog
    title="提示"
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose">
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
    </el-dialog>
  </div>

</template>

<script>
export default {
  data() {
    return {
      exams: [
        { id: 1, name: '考试场次1' },
        { id: 2, name: '考试场次2' },
        { id: 3, name: '考试场次3' },
      ],
      selectedExam: null,
      times: ['选择时间', '时间1', '时间2', '时间3'],
      selectedTime: null,
      locations: ['选择地点', '地点1', '地点2', '地点3'],
      selectedLocation: null,
      dialogVisible:false
    };
  },
  methods: {
    goBack() {
      // 返回上页的逻辑
    },
    confirm() {
      // 点击确认按钮的逻辑,弹出表格
      this.dialogVisible=true;
      // 点击确认按钮的逻辑
      if (this.selectedExam && this.selectedTime && this.selectedLocation) {
        // 执行确认操作
        console.log('确认');

      } else {
        // 未选择完整信息
        console.log('请选择完整信息');
      }
    },
    cancel() {
      // 点击取消按钮的逻辑
      console.log('取消');
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

.section {
  margin-bottom: 20px;
}

.actions button {
  margin: 5px;
}
</style>