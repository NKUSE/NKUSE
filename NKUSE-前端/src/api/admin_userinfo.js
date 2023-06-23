import request from '@/utils/request'

export default {
  getAllUser(searchModel) {
    return request({
      url: '/cet/userinfo/getAll',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize
      }
    }
    )
  },
  getOneUser(searchModel) {
    return request({
      url: '/cet/userinfo/getOne',
      method: 'get',
      params: {
        userId: searchModel.userId,
      }
    }
    )
  },
  getOneUser_2(id) {
    return request({
      url: '/cet/userinfo/getOne',
      method: 'get',
      params: {
        userId: id
      }
    }
    )
  },
  updateUser(user) {
    return request({
      url: '/cet/userinfo/updateUser',
      method: 'put',
      data: user
    })
  },
  newUser(user) {
    return request({
      url: '/cet/userinfo/newUser',
      method: 'post',
      data: user
    })
  },
  deleteUser(id) {
    return request({
      url: '/cet/userinfo/deleteUser',
      method: 'delete',
      params: {
        userId: id
      }
    })
  },
}
