import request from '@/utils/request'

export default {
  getExams() {
    return request({
      url: '/cet/examinfo/getByState',
      method: 'get'
    }
    )
  },
  getSheets(examId) {
    return request({
      url: '/cet/answersheet/getSheetByExamId',
      method: 'get',
      params: {
        examId: examId
      }
    })
  },
  updateSheet(sheet){
    return request({
        url: '/cet/answersheet/updateSheet',
        method: 'put',
        data: sheet
    })
  },
  updateScore(examId){
    return request({
      url: '/cet/answersheet/updateScore',
      method: 'get',
      params: {
        examId: examId
      }
  })
  }
}
