package com.nku.cet.controller;

import com.nku.cet.entity.Userinfo;
import com.nku.cet.service.IUserinfoService;
import com.nku.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/cet/userinfo")
public class UserinfoController {
    @Autowired
    private IUserinfoService userinfoService;
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody Userinfo userinfo){
        Map<String,Object> res = userinfoService.login(userinfo);
        if(res != null) {
            return Result.success(res);
        }
        else{
            return Result.fail(20002, "失败");
        }
    }
    @GetMapping("/getInfo")
    public Result<Map<String, Object>> getInfo(@RequestParam("token") String token) {
        Map<String, Object> res = userinfoService.getUserInfo(token);
        if(res != null) {
            return Result.success(res);
        }
        else{
            return Result.fail(20002, "失败");
        }
    }
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        userinfoService.logout(token);
        return Result.success();
    }
}
