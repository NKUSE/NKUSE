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
    updateUserInfo(user) {
        return request({
            url: '/cet/userinfo/updateUser',
            method: 'put',
            data: user
          })
    }
}