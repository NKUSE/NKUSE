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

    getInfoById(user_id) {
        return request({
            url: '/cet/userinfo/getInfoById',
            method: 'get',
            params: {
                user_id: user_id
            }
        })
    },

    getPaperId(exam_id) {
        return request({
            url: '/cet/examinfo/getPaperById',
            method: 'get',
            params: {
                exam_id: exam_id
            }
        })
    },

    getPaperInfo(paperId) {
        return request({
            url: '/cet/paperinfo/getPaperInfo',
            method: 'get',
            params: {
                paperId: paperId
            }
        })
    }
}