import request from '@/utils/request'

export default {
    getExams(userid) {
        return request({
            url: '/cet/examinfo/getByUserId',
            method: 'get',
            params: {
                user_id: userid
            }
        }
        );
    },
    getRegist(userid,examid) {
        return request({
            url: '/cet/registinfo/getRegist',
            method: 'get',
            params: {
                userid: userid,
                examid: examid
            }
        }
        );
    },
    updateReg(reg){
        return request({
            url: '/cet/registinfo/updateReg',
            method: 'put',
            data: reg
        });
    },
}