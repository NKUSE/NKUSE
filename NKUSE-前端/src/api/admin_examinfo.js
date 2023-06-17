import request from '@/utils/request'

export default {
  getAllExamInfos(searchModel) {
    return request({
      url: '/cet/examinfo/getAll',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize
      }
    }
    )
  },
  getOneExamInfo(id) {
    return request({
      url: '/cet/examinfo/getOne',
      method: 'get',
      params: {
        examId: id
      }
    }
    )
  },
  updateExamInfo(exam) {
    return request({
      url: '/cet/examinfo/updateExamInfo',
      method: 'put',
      data: exam
    })
  },
  newExamInfo(exam) {
    return request({
      url: '/cet/examinfo/newExamInfo',
      method: 'post',
      data: exam
    })
  },
  deleteExamInfo(id) {
    return request({
      url: '/cet/examinfo/deleteExamInfo',
      method: 'delete',
      params: {
        examId: id
      }
    })
  },
  getMaximumExamId() {
    return request({
      url: '/cet/examinfo/maxId',
      method: 'get'
    })
  },
  removeExamInfoPaper(exam) {
    return request({
      url: '/cet/examinfo/removeExamInfoPaper',
      method: 'put',
      data: exam
    })
  },
}
