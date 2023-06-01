package com.nku.stu.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nku.stu.entity.UserInfo;
import com.nku.stu.mapper.UserInfoMapper;
import com.nku.stu.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
 * @since 2023-05-19
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(UserInfo userinfo){
        //根据用户名和密码查询
        LambdaQueryWrapper<UserInfo> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getUserName,userinfo.getUserName());
        wrapper.eq(UserInfo::getPassword,userinfo.getPassword());

        UserInfo loginUser=this.baseMapper.selectOne(wrapper);
        //结果不为空，则生成token，并将用户信息存入redis
        if(loginUser!=null){
            //暂时用UUID,终极方案是jwt
            String key="user:"+ UUID.randomUUID();

            loginUser.setPassword(null);
            //存入redis
            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
            //返回数据
            Map<String,Object> data=new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }
    @Override
    public Map<String, Object> getUserInfo(String token) {
        Object obj = redisTemplate.opsForValue().get(token);

        UserInfo user = JSON.parseObject(JSON.toJSONString(obj),UserInfo.class);
        if(user != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", user.getUserName());
            data.put("id", user.getUserId());
            //data.put("avatar",user.getAvatar());
            data.put("role", user.getUserType());
            //data.put("roles", roleList);
            return data;
        }
        return null;
    }

    @Override
    public Map<String,Object> stuSignUp(UserInfo userinfo){
        return null;
    }
}
