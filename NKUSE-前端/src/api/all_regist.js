import request from '@/utils/request'

export default {
    getUserInfo(user_id) {
        return request({
            url: '/cet/userinfo/getInfoById',
            method: 'get',
            params: {
                user_id: user_id
            }
        })
    },
    regist(user) {
        return request({
          url: '/cet/userinfo/regist',
          method: 'post',
          data: user
        })
      },
}