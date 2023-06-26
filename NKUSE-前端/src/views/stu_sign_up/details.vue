<template>
  <div>
    <div class="header">
      <h1 class="title">全国大学生六级考试报名系统</h1>
    </div>
    <div class="container">
      <div class="card">
        <h2 class="card-title">报名协议 & 诚信承诺书</h2>
        <hr class="divider">
        <div class="sub-card">
          <h3 class="sub-card-title">报名协议</h3>
          <ul>
            <li>1. 本报名网站的所有权归教育部教育考试院所有，考生须完全同意本协议条款，方可进行网上报名。</li>
            <li>2. 本人已认真阅读并同意遵守本网站提供的报名流程及考生须知。</li>
            <li>3. 考生本人理解并同意，报考资格须符合考点相关规定，经审查通过后方可报考。</li>
            <li>4. 本人保证报名时的个人信息资料真实、正确，并将对个人信息不真实或不正确而导致无法参加考试以及其它直接或间接的后果自行负责。</li>
            <li>5. 本人理解并同意，在报名过程中出现不可抗力所引起的无法正常报名的情况，本网站不承担任何直接或间接的责任。</li>
            <li>6. 本人理解并同意，网上支付系统由支付平台所有并运营，在付费过程中由于支付平台网上系统问题导致的任何异常情况，本网站不承担直接或间接责任。</li>
            <li>7. 本人理解并同意，在报名成功后，因本网站系统出现问题或其他不可抗力因素影响考生参加考试，本网站所负责任范围仅限于帮助考生安排重新报名或全额按支付原路退还报名费，不承担任何其它责任。</li>
            <li>8. 本人理解并同意，考生最终参加考试的考点和时间以考生领取的准考证上注明的考点和时间为准。</li>
            <li>9. 本报名网站所收集的考生个人信息数据仅用于全国大学英语四、六级考试报名，考生同意教育部教育考试院根据考试需要获知、处理、存储、使用本人信息数据。</li>
            <li>10. 本人已阅读并且理解全国大学英语四、六级考试的有关规定和纪律要求，应在考试中自觉遵守相关规定，保证按规定的程序和要求参加考试，不违规，不作弊。如有违反，同意按《国家教育考试违规处理办法》及其他相关规定接受处理。</li>
            <li>11. 本人已知晓，成绩发布后的规定时间，可在中国教育考试网（www.neea.edu.cn）查看并下载电子成绩报告单（小语种科目为电子证书），电子成绩报告单与纸质成绩报告单同等效力。</li>
            <li>12. 本人已知晓，报名期间或成绩发布后规定时间内登录本网站，自助选择是否需要纸质成绩报告单（小语种科目为证书）。选择纸质成绩报告单（小语种科目为证书），应按考点规定时间及地点免费领取，超过半年未领取的，视为自动放弃，不再补发。</li>
            <li>13．本人理解并同意，在报名成功后，由于考生个人原因无法参加考试，由考生个人承担相关责任，本网站不进行退费。</li>
          </ul>
        </div>
        <hr class="divider">
        <div class="sub-card">
          <h3 class="sub-card-title">诚信承诺书</h3>
          <p>本人自愿参加全国大学英语四、六级考试，现郑重承诺：</p>
          <ol>
            <li>本人已阅读并且透彻地理解了全国大学英语四、六级考试的有关规定和纪律要求，愿意在考试中自觉遵守这些规定，保证按规定的程序和要求参加考试，如有违反，自愿按《国家教育考试违规处理办法》有关条款接受处理。</li>
            <li>本人坚决遵守全国大学英语四、六级考试有关报名规定，不弄虚作假，不伪造、使用假证明、假证书。如有违反，自愿按规定接受处理。</li>
            <li>本人坚决服从考场工作人员和监考教师管理，自觉遵守考试纪律，考试诚实守信，不违规，不作弊。</li>
            <li>本人报名时所提供的个人信息是真实、准确、完整的，如因个人信息错误、失真、缺失造成不良后果，责任由本人承担。</li>
          </ol>
        </div>
      </div>
    <div class="checkbox">
    <label>
        <input type="checkbox" v-model="accepted">
        我已阅读并接受遵守本网站
        <a href="./res/user-agreement.pdf" target="_blank" class="blue-link">《用户服务协议》</a>
        和
        <a href="/path/to/privacy-policy.pdf" target="_blank" class="blue-link">《用户隐私政策》</a>
        报名协议及诚信承诺书
    </label>
    </div>
      <div class="buttons">
        <el-button type="primary" @click="decline">不同意</el-button>
        <el-button class="btn" @click="accept" :disabled="!accepted">请勾选确认</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
    data() {
        return {
        accepted: false
        };
    },
    methods:{
        decline() {
            this.$router.push('/dashboard')
        },
        accept() {
            this.$router.push('/stu_sign_up/detailsqq')
        }
    },
    created(){
      
      this.$forceUpdate();
    }
}
</script>
<style scoped>
  .header {
    background-color:  rgba(48, 65, 86, 0.8);
    padding: 20px;
    text-align: center;
  }
  .title {
    color: white;
  }
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-top: 50px;
  }
  .card {
    background-color: white;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    border-radius: 5px;
    width: 800px;
    padding: 20px;
  }
  .card-title {
    color:  rgba(48, 65, 86, 0.8);
    font-size: 24px;
    margin-bottom: 10px;
  }
  .divider {
    border: none;
    border-top: 1px solid lightgray;
    margin: 10px 0;
  }
  .sub-card {
    background-color: #f8f8f8;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    border-radius: 5px;
    padding: 10px;
  }
  .sub-card-title {
    color:  rgba(48, 65, 86, 0.8);
    font-size: 18px;
    margin-bottom: 10px;
  }
  .checkbox {
    margin: 20px 0;
    text-align: center;
  }
  .buttons {
    text-align: center;
  }
  .decline-button {
    margin-right: 10px;
  }
.blue-link {
  color:  rgba(48, 65, 86, 0.8);
  text-decoration: none;
}
.blue-link:hover {
  text-decoration: underline;
}
</style>