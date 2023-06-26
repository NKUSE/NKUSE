import request from '@/utils/request'

export default {
    getUserIdbyIdnum(idnum){
        return request({
            url:'/cet/userinfo/getUserId',
            method: 'get',
            params:{
                idnum:idnum
            }
        })
    },
    getAllExams(){
        return request({
            url: '/cet/examinfo/getAllExams',
            method: 'get',
        }
        );
    },
    signUp(data){
        return request({
            url: '/cet/registinfo/signup',
            method: 'post',
            data: data
        })
    }
}