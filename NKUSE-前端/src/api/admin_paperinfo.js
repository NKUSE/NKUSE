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
}
