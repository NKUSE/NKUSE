package com.nku.cet.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nku.cet.entity.Userinfo;
import com.nku.cet.mapper.UserinfoMapper;
import com.nku.cet.service.IUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {
    @Resource
    RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(Userinfo userinfo) {
        LambdaQueryWrapper<Userinfo> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Userinfo::getUserId,userinfo.getUserId());
        wrapper.eq(Userinfo::getUserPassword,userinfo.getUserPassword());
        Userinfo loginUser = this.getOne(wrapper);
        if(loginUser != null){
            Map<String, Object> data = new HashMap<>();
            String key = "user::" + UUID.randomUUID();
            data.put("token", key);    // 待优化，最终方案jwt
            loginUser.setUserPassword(null);
            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        Object obj = redisTemplate.opsForValue().get(token);
        if(obj != null) {
            Userinfo loginUser = JSON.parseObject(JSON.toJSONString(obj),Userinfo.class);
            Map<String,Object> data = new HashMap<>();
            data.put("name", loginUser.getUserName());
            data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            String role = this.baseMapper.getRoleNameByUserId(loginUser.getUserId());
            data.put("roles", role);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
