package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nku.cet.entity.Roominfo;
import com.nku.cet.service.impl.RoominfoServiceImpl;
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
 * @author jzk
 * @since 2023-06-17
 */
@RestController
@RequestMapping("/cet/Room")
public class RoominfoController {
    @Autowired
    RoominfoServiceImpl RoominfoService;
    //增post 改put 删delete 查get
    @GetMapping("/getAll")
    public Result<Map<String,Object>> getAllItems(@RequestParam("pageNo") Integer pageNo,
                                                  @RequestParam("pageSize") Integer pageSize){
        Page<Roominfo> page = new Page<>(pageNo, pageSize);
        RoominfoService.page(page);
        Map<String,Object> res = new HashMap<>();
        res.put("total", page.getTotal());
        res.put("rows", page.getRecords());
        //Map<String,Object> res = RoominfoService.getAll();
        return Result.success(res);
    }
    @GetMapping("/getOne")
    public Result<Map<String,Object>> getOneItems(@RequestParam("roomId") Integer roomId){
        LambdaQueryWrapper<Roominfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Roominfo::getRoomId, roomId);
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> data = new LinkedList<>();
        Roominfo record = RoominfoService.getOne(wrapper);
        Map<String,Object> record_map = new HashMap<>();
        if(record != null){
            record_map.put("roomId",record.getRoomId());
            record_map.put("place",record.getPlace());
            record_map.put("roomName",record.getRoomName());
            record_map.put("capacity",record.getCapacity());
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

    @PostMapping("/newRoom")
    public Result<?> newRoom(@RequestBody Roominfo room){
        RoominfoService.save(room);
        return Result.success("新增成功");
    }

    @PutMapping("/updateRoom")
    public Result<?> updateRoom(@RequestBody Roominfo room){
        Integer roomId = room.getRoomId();
        LambdaQueryWrapper<Roominfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Roominfo::getRoomId, roomId);
        RoominfoService.update(room, wrapper);
        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteRoom")
    public Result<?> deleteRoom(@RequestParam("roomId") Integer roomId){
        LambdaQueryWrapper<Roominfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Roominfo::getRoomId, roomId);
        RoominfoService.remove(wrapper);
        return Result.success("删除成功");
    }

    @GetMapping("/maxId")
    public Result<Map<String, Object>> getMaximumRoomId() {
        long maxRoomId = RoominfoService.getBaseMapper().getMaximumRoomId();
        Map<String, Object> res = new HashMap<>();
        res.put("maximumRoomId", maxRoomId);
        return Result.success(res);
    }
}


