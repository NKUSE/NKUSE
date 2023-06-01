package com.nku;

import com.nku.stu.entity.UserInfo;
import com.nku.stu.mapper.UserInfoMapper;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Cet6ApplicationTests {

    @Resource
    private UserInfoMapper userMapper;

    @Test
    void testMapper(){
        List<UserInfo> users=userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}

