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
    },

    getSheetId(userId, examId) {
        return request({
            url: '/cet/registinfo/getSheetId',
            method: 'get',
            params: {
                userId: userId,
                examId: examId
            }
        })
    },

    newAnswerSheet(info) {
        return request({
            url: '/cet/answersheet/newAnswerSheet',
            method: 'post',
            data: info
        })
    },

    setSheetId(info) {
        return request({
            url: '/cet/registinfo/setSheetId',
            method: 'put',
            data: info
        })
    },

    checkScore(sheetid) {
        return request({
            url: '/cet/answersheet/checkScore',
            method: 'get',
            params: {
                sheetid: sheetid
            }
        })
    },

    getSheet(sheetid) {
        return request({
            url: '/cet/answersheet/getSheet',
            method: 'get',
            params: {
                sheetid: sheetid
            }
        })
    },

    updateSheet(sheet) {
        return request({
            url: '/cet/answersheet/updateSheet',
            method: 'put',
            data: sheet
        })
    },

    submitSheet(sheet) {
        return request({
            url: '/cet/answersheet/submitSheet',
            method: 'put',
            data: sheet
        })
    }
}