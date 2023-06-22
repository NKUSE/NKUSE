import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/cet/userinfo/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/cet/userinfo/getInfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/cet/userinfo/logout',
    method: 'post'
  })
}
export function getInfoById(id) {
  return request({
    url: '/cet/userinfo/getInfoById',
    method: 'get',
    params: {
      user_id: userid
    }
  })
}
