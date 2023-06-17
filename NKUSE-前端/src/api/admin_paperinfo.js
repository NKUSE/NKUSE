import request from '@/utils/request'

export default {
  getOnePaperInfo(id) {
    return request({
      url: '/cet/paperinfo/getById',
      method: 'get',
      params: {
        paperId: id
      }
    }
    )
  },
  getMaximumPaperId() {
    return request({
      url: '/cet/paperinfo/getMaxId',
      method: 'get'
    })
  },
  newPaperInfo(paper) {
    return request({
      url: '/cet/paperinfo/newPaper',
      method: 'post',
      data: paper
  })
  },
  deletePaper(id){
    return request({
        url: '/cet/paperinfo/deletePaper',
        method: 'delete',
        params:{
            paperId: id,
        }
    })
}
}
