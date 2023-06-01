package com.nku.stu.service;

import com.nku.stu.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyh
 * @since 2023-05-19
 */
public interface IUserInfoService extends IService<UserInfo> {

    Map<String, Object> login(UserInfo userinfo);

    Map<String, Object> getUserInfo(String token);

    Map<String,Object> stuSignUp(UserInfo userinfo);
}
