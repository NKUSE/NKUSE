<template>
  <div class="app-container">
    <el-form ref="signupForm" :model="stu_sign_up" :rules="rules" label-width="120px">
      <el-form-item label="报考省份">
        <select v-model="selectedProvince" class="u246_input" @change="updateCityOptions">
          <option value="">--请选择省份--</option>
          <option class="u246_input_option" value="河北省">河北省</option>
          <option class="u246_input_option" value="山西省">山西省</option>
          <option class="u246_input_option" value="辽宁省">辽宁省</option>
          <option class="u246_input_option" value="吉林省">吉林省</option>
          <option class="u246_input_option" value="黑龙江省">黑龙江省</option>
          <option class="u246_input_option" value="江苏省">江苏省</option>
          <option class="u246_input_option" value="浙江省">浙江省</option>
          <option class="u246_input_option" value="安徽省">安徽省</option>
          <option class="u246_input_option" value="福建省">福建省</option>
          <option class="u246_input_option" value="江西省">江西省</option>
          <option class="u246_input_option" value="山东省">山东省</option>
          <option class="u246_input_option" value="河南省">河南省</option>
          <option class="u246_input_option" value="湖北省">湖北省</option>
          <option class="u246_input_option" value="湖南省">湖南省</option>
          <option class="u246_input_option" value="广东省">广东省</option>
          <option class="u246_input_option" value="海南省">海南省</option>
          <option class="u246_input_option" value="四川省">四川省</option>
          <option class="u246_input_option" value="贵州省">贵州省</option>
          <option class="u246_input_option" value="云南省">云南省</option>
          <option class="u246_input_option" value="陕西省">陕西省</option>
          <option class="u246_input_option" value="甘肃省">甘肃省</option>
          <option class="u246_input_option" value="青海省">青海省</option>
          <option class="u246_input_option" value="台湾省">台湾省</option>
          <option class="u246_input_option" value="内蒙古自治区">内蒙古自治区</option>
          <option class="u246_input_option" value="广西壮族自治区">广西壮族自治区</option>
          <option class="u246_input_option" value="西藏自治区">西藏自治区</option>
          <option class="u246_input_option" value="宁夏回族自治区">宁夏回族自治区</option>
          <option class="u246_input_option" value="新疆维吾尔自治区">新疆维吾尔自治区</option>
          <option class="u246_input_option" value="北京市">北京市</option>
          <option class="u246_input_option" value="天津市">天津市</option>
          <option class="u246_input_option" value="上海市">上海市</option>
          <option class="u246_input_option" value="重庆市">重庆市</option>
          <option class="u246_input_option" value="香港特别行政区">香港特别行政区</option>
          <option class="u246_input_option" value="澳门特别行政区">澳门特别行政区</option>
        </select>
      </el-form-item>

      <el-form-item label="报考城市" prop="city">
        <select v-model="selectedCity" class="u247_input" style="width:120px">
          <option v-for="city in cities" :value="city">{{ city }}</option>
        </select>
      </el-form-item>

      <el-form-item label="姓名" prop="stu_name">
        <input v-model="stu_sign_up.stu_name" clearable value=""/>
      </el-form-item>

      <el-form-item label="身份证号" prop="stu_id">
        <input v-model="stu_sign_up.stu_id" value=""/>
      </el-form-item> 

      <el-form-item label="联系电话" prop="stu_phone">
        <input v-model="stu_sign_up.stu_phone" value=""/>
      </el-form-item> 

      <el-form-item label="邮箱" prop="stu_email">
        <input v-model="stu_sign_up.stu_email" value=""/>
      </el-form-item>   
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
        <el-button @click="onReset()">重置</el-button>
      </el-form-item>   
    </el-form>

  </div>

</template>

<script>
import userAPI from '@/api/stu_sign_up'
export default {
  data() {
    return {
      selectedProvince: '',
      selectedCity: '',
      cities: [],
      stu_sign_up:{
        stu_name: '',
        stu_id: '',
        stu_phone: '',
        stu_email: '',
      },
    rules: {
      stu_name: [
        { required: true, message: '请输入姓名', trigger: 'blur' },
        { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
      ],
      stu_id: [
        { required: true, message: '请输入身份证号', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (!value) {
              callback(new Error('请输入身份证号'));
            } else if (!/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/.test(value)) {
              callback(new Error('身份证号格式不正确'));
            } else {
              callback();
            }
          },
          trigger: 'blur'
        }
      ],
      stu_phone: [
        { required: true, message: '请输入手机号', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (!value) {
              callback(new Error('请输入手机号'));
            } else if (!/^[1][3-9]\d{9}$/.test(value)) {
              callback(new Error('手机号格式不正确'));
            } else {
              callback();
            }
          },
          trigger: 'blur'
        }
      ],
      stu_email: [
        { required: true, message: '请输入电子邮件', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (!value) {
              callback(new Error('请输入电子邮件'));
            } else if (!/^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*\.[A-Za-z]{2,4}$/.test(value)) {
              callback(new Error('电子邮件格式不正确'));
            } else {
              callback();
            }
          },
          trigger: 'blur'
        }
      ],
    },
    };
  },
  methods: {
    updateCityOptions() {
      if (this.selectedProvince === '河北省') {
        this.cities = ['张家口市', '石家庄市'];
        // Add more cities for 河北省
      } else if (this.selectedProvince === '山西省') {
        this.cities = ['太原市', '大同市'];
        // Add more cities for 山西省
      }
      // Add more conditions for other provinces

      this.$nextTick(() => {
        this.$refs.signupForm.resetFields();
      });
    },
    submitForm() {
      //触发表单验证
        this.$refs.signupForm.validate((valid) => {
          if (valid) {
            //提交请求给后台
            userAPI.stuSignUp(this.stu_sign_up).then(response =>{
              //成功提示
              this.$message({
                message:response.message,
                type:'success'
              });
              this.$router.push('/dashboard');
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      
    },
    onReset() {
      //this.$message('onReset');
      this.selectedProvince=null;
      this.selectedCity=null;
      this.$refs.signupForm.resetFields();
    },
    
  }
};

</script>

<style scoped>
</style>

