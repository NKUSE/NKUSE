import request from '@/utils/request'

export default{
    stuSignUp(user){
        return request({
          url:'/user/signup',
          method:'post',
          data:user
        });
    },
}