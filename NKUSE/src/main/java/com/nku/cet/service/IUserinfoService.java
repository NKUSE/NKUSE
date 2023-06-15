package com.nku.cet.service;

import com.nku.cet.entity.Userinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
public interface IUserinfoService extends IService<Userinfo> {

    Map<String, Object> login(Userinfo userinfo);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);
}
