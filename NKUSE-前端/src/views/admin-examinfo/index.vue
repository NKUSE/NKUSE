<template>
  <div class="container" style="min-height: 100%; padding-bottom: 100px;">
    <el-card>
      <el-table :data="examList" style="width: 100%">
        <el-table-column prop="examId" label="考试ID" />
        <el-table-column prop="examName" label="考试名称" />
        <el-table-column prop="examTime" label="考试时间" width="160px" />
        <el-table-column prop="examFee" label="费用" />
        <el-table-column prop="examState" label="考试状态">
          <template slot-scope="scope">
            <span>{{ getExamState(scope.row.examState) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="editObjective(scope.row.examId)">编辑</el-button>
            <el-button type="danger" size="mini" @click="confirmDeleteObjective(scope.row.examId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible"
      :before-close="handleClose"
    >
      {{ this.examInfoForm }}
      <el-form ref="examInfoFormRef" :model="examInfoForm" :rules="rules">

        <!--<el-form-item
        label="考试ID"
        :label-width="formLabelWidth"
      >
        <span>{{ examInfoForm.examId }}</span>
      </el-form-item>-->

        <el-form-item
          label="考试名称"
          :label-width="formLabelWidth"
          prop="examName"
        >
          <el-input
            v-model="examInfoForm.examName"
            autocomplete="off"
          />
        </el-form-item>

        <el-form-item
          label="考试时间"
          :label-width="formLabelWidth"
          prop="examTime"
        >
          <el-date-picker
            v-model="examInfoForm.examTime"
            placeholder="选择日期"
            style="width: 190px"
          />
          <el-time-picker
            v-model="examInfoForm.examTime"
            placeholder="选择时间"
            style="width: 190px"
          />
        </el-form-item>

        <el-form-item
          label="考试费用"
          :label-width="formLabelWidth"
          prop="examFee"
        >
          <el-input
            v-model="examInfoForm.examFee"
            autocomplete="off"
          />
        </el-form-item>

        <el-form-item
          label="考试状态"
          :label-width="formLabelWidth"
          prop="examState"
        >
          <el-input
            v-model="examInfoForm.examState"
            autocomplete="off"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="clearForm()">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>

    <div class="grid-content bg-purple-light">
      <el-button
        type="primary"
        style=""
        icon="el-icon-plus"
        circle
        @click="openNewUI()"
      />
    </div>
  </div>
</template>

<script>
// import DatetimePicker from 'vue-datetime-picker'
import examInfoApi from '@/api/admin_examinfo'
export default {
  components: {},
  filters: {},
  props: [],
  data() {
    return {
      examIdCounter: 0,
      dialogFormVisible: false,
      title: '',
      examInfoForm: {},
      examList: [],
      formLabelWidth: '80px',
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        examInfoId: 0
      },
      rules: {
        examName: [
          {
            required: true,
            message: '请输入考试名称',
            trigger: 'blur'
          }
        ],
        examTime: [
          {
            required: true,
            message: '请输入考试时间',
            trigger: 'blur'
          }
        ],
        examFee: [
          {
            required: true,
            message: '请输入费用',
            trigger: 'blur'
          }
        ],
        examState: [
          {
            required: true,
            message: '请输入考试状态',
            trigger: 'blur'
          }
        ]
      },
      tableData: [
        {
          examid: '1',
          exam: '第4届全国大学生英语6级考试',
          time: '2023年9月8日',
          fee: '25',
          isfee: '开始报名'
        },
        {
          examid: '2',
          exam: '第3届全国大学生英语6级考试',
          time: '2023年6月6日',
          fee: '25',
          isfee: '结束报名'
        },
        {
          examid: '3',
          exam: '第2届全国大学生英语6级考试',
          time: '2023年3月6日',
          fee: '25',
          isfee: '等待阅卷'
        },
        {
          examid: '4',
          exam: '第1届全国大学生英语6级考试',
          time: '2020年1月1日',
          fee: '25',
          isfee: '已结束'
        }
        // 添加更多考试信息...
      ]
    }
  },
  computed: {},
  watch: {},
  beforeCreate() {},
  created() {
    this.getAllExamInfos(this.searchModel)
    this.getMaximumExamId()
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  destroyed() {},
  methods: {
    request() {},
    payFee(row) {
      // 在这里编写缴费的逻辑
      console.log('已缴费', row)
    },
    // new
    // 获取已存在考试的最大 examId 值
    getMaximumExamId() {
      // 调用接口或查询数据库，获取最大 examId 值
      examInfoApi.getMaximumExamId().then((response) => {
        // 将 examIdCounter 初始化为已存在的最大 examId 值加一
        this.examIdCounter = response.data.maximumExamId + 1
      })
    },
    getAllExamInfos() {
      examInfoApi.getAllExamInfos(this.searchModel).then((response) => {
        // this.tableData = response.data.rows
        this.examList = response.data.rows
        this.total = response.data.total
      })
    },
    editObjective(id) {
      this.title = '更新考试信息'
      // this.examInfoForm.examId = this.examIdCounter
      this.dialogFormVisible = true
      examInfoApi.getOneExamInfo(id).then((response) => {
        this.examInfoForm = response.data.rows[0]
        console.log(response.data.rows[0])
      })
    },
    clearForm() {
      this.dialogFormVisible = false
      this.examInfoForm = {}
      this.$refs.examInfoFormRef.clearValidate()
    },
    submitForm() {
      if ((this.title === '新增考试')) {
        this.$refs.examInfoFormRef.validate((valid) => {
          if (valid) {
            // 将 examInfoForm.examId 设置为 examIdCounter 值，并递增
            this.examInfoForm.examId = this.examIdCounter
            this.examIdCounter++
            examInfoApi.newExamInfo(this.examInfoForm).then((response) => {
              this.dialogFormVisible = false
              this.$message({
                message: response.message,
                type: 'success'
              })
              this.examInfoForm = {}
              this.$refs.examInfoFormRef.clearValidate()
              this.getAllExamInfos()
            })
          } else {
            console.log('error submit')
            return false
          }
        })
      } else if (this.title === '更新考试信息') {
        this.$refs.examInfoFormRef.validate((valid) => {
          if (valid) {
            examInfoApi.updateExamInfo(this.examInfoForm).then((response) => {
              this.dialogFormVisible = false
              this.$message({
                message: response.message,
                type: 'success'
              })
              this.examInfoForm = {}
              this.$refs.examInfoFormRef.clearValidate()
              this.getAllExamInfos()
            })
          } else {
            console.log('error submit')
            return false
          }
        })
      }
    },
    confirmDeleteObjective(id) {
      examInfoApi.deleteExamInfo(id)
      this.getAllExamInfos()
    },
    getExamState(state) {
      // 根据考试状态的数值返回相应的字符串
      if (state === 1) {
        return '开始报名'
      } else if (state === 2) {
        return '结束报名'
      } else if (state === 3) {
        return '等待阅卷'
      } else if (state === 4) {
        return '已结束'
      } else {
        return '未知状态'
      }
    },
    openNewUI() {
      this.title = '新增考试'
      // this.examInfoForm.examId = this.examIdCounter
      this.dialogFormVisible = true
      this.examInfoForm = {} // 清空表单数据
      this.examInfoForm.examId = this.examIdCounter
    },
    handleClose(done) {
      // 在这里编写你的逻辑
      // 如果需要关闭对话框，调用 done() 方法
      // 如果不需要关闭对话框，可以不调用 done() 方法

      // 示例：弹出确认提示框后再关闭对话框
      this.$confirm('确定要关闭对话框吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 用户点击了确认按钮
        this.examInfoForm = {}
        this.$refs.examInfoFormRef.clearValidate()
        done() // 关闭对话框
      }).catch(() => {
        // 用户点击了取消按钮
      })
    }
  }
}
</script>

  <style scoped>
  .container {}
  </style>

