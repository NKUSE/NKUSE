package com.nku.stu.controller;

import com.nku.common.vo.Result;
import com.nku.stu.entity.UserInfo;
import com.nku.stu.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyh
 * @since 2023-05-19
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private IUserInfoService userService;
    @GetMapping("/all")
    public Result<List<UserInfo>> getAllUser(){
        List<UserInfo> list=userService.list();
        return Result.success(list);
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody UserInfo userinfo)
    {
        Map<String,Object> data=userService.login(userinfo);
        if(data!=null){
            return Result.success(data);
        }
        return Result.fail(20002,"用户名或密码错误");
    }
    @GetMapping("/info")
    public Result<Map<String, Object>> getInfo(@RequestParam("token")String token){
        Map<String, Object> data = userService.getUserInfo(token);
        if(data != null) {
            return Result.success(data);
        }
        return Result.fail(20002, "请重新登陆");
    }

    @PostMapping("/signup")
    public Result<?> stuSignUp(@RequestBody UserInfo userinfo){
        //userService.save(userinfo);
        return Result.success("注册成功");
    }
}
