package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nku.cet.entity.Userinfo;
import com.nku.cet.service.IUserinfoService;
import com.nku.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
            return Result.fail(20002, "账号或密码错误");
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
    @GetMapping("/getAll")
    public Result<Map<String,Object>> getAllItems(@RequestParam("pageNo") Integer pageNo,
                                                  @RequestParam("pageSize") Integer pageSize){
        Page<Userinfo> page = new Page<>(pageNo, pageSize);
        userinfoService.page(page);
        Map<String,Object> res = new HashMap<>();
        res.put("total", page.getTotal());
        res.put("rows", page.getRecords());
        //Map<String,Object> res = objQuesService.getAll();
        return Result.success(res);
    }

    @GetMapping("/getOne")
    public Result<Map<String,Object>> getOneItems(@RequestParam("userId") Integer userId){
        LambdaQueryWrapper<Userinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Userinfo::getUserId, userId);
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> data = new LinkedList<>();
        Userinfo record = userinfoService.getOne(wrapper);
        Map<String,Object> record_map = new HashMap<>();
        if(record != null){
            record_map.put("userId",record.getUserId());
            record_map.put("userName",record.getUserName());
            record_map.put("userPassword",record.getUserPassword());
            record_map.put("userEmail",record.getUserEmail());
            record_map.put("userPhonenumber",record.getUserPhonenumber());
            record_map.put("userIDnumber",record.getUserIdnumber());
            record_map.put("userRole",record.getUserRole());
            data.add(record_map);
        }
        if(data.size() != 0) {
            res.put("total", 1);
            res.put("rows", data);
        }
        else{
            res.put("total", 0);
            res.put("rows", null);
        }
        return Result.success(res);
    }

    @PostMapping("/newUser")
    public Result<?> newUser(@RequestBody Userinfo user){
        userinfoService.save(user);
        return Result.success("新增成功");
    }

    @PutMapping("/updateUser")
    public Result<?> updateUser(@RequestBody Userinfo user){
        String userId = user.getUserId();
        LambdaQueryWrapper<Userinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Userinfo::getUserId, userId);
        userinfoService.update(user, wrapper);
        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteUser")
    public Result<?> deleteUser(@RequestParam("userId") String userId){
        LambdaQueryWrapper<Userinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Userinfo::getUserId, userId);
        userinfoService.remove(wrapper);
        return Result.success("删除成功");
    }
    @GetMapping("/getInfoById")
    public Result<Map<String, Object>> getInfoById(@RequestParam("user_id") String userid) {
        Map<String, Object> res = userinfoService.getUserAllInfo(userid);
        return Result.success(res);
    }
}
