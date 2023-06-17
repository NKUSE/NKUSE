package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nku.cet.entity.ObjQues;
import com.nku.cet.entity.Registinfo;
import com.nku.cet.service.IRegistinfoService;
import com.nku.cet.service.impl.RegistinfoServiceImpl;
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
@RequestMapping("/cet/registinfo")
public class RegistinfoController {
    @Autowired
    RegistinfoServiceImpl registinfoService;
    @GetMapping("/getAll")
    public Result<Map<String,Object>> getAllRegInfo(@RequestParam("pageNo") Integer pageNo,
                                                    @RequestParam("pageSize") Integer pageSize){
        Page<Registinfo> page=new Page<>(pageNo,pageSize);
        registinfoService.page(page);
        Map<String,Object> res=new HashMap<>();
        res.put("total",page.getTotal());
        res.put("rows",page.getRecords());
        return Result.success(res);
    }
    @GetMapping("/getOne")
    public Result<Map<String,Object>> getOneInfo(@RequestParam("registId") Integer regId){
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getRegistId, regId);
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> data = new LinkedList<>();
        Registinfo record = registinfoService.getOne(wrapper);
        Map<String,Object> record_map = new HashMap<>();
        if(record != null){
            record_map.put("registId",record.getRegistId());
            record_map.put("userId",record.getUserId());
            record_map.put("examId",record.getExamId());
            record_map.put("roomId",record.getRoomId());
            record_map.put("seatNumber",record.getSeatNumber());
            record_map.put("paid",record.getPaid());
            record_map.put("score",record.getScore());
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
    @PostMapping("/newReg")
    public Result<?> newReg(@RequestBody Registinfo reg){
        //reg.setRegistId("000002");
        reg.setPaid(1);
        registinfoService.save(reg);
        return Result.success("新增成功");
    }
    @PutMapping("/updateReg")
    public Result<?> updateReg(@RequestBody Registinfo reg){
        Integer regId = reg.getRegistId();
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getRegistId, regId);
        registinfoService.update(reg, wrapper);
        return Result.success("修改成功");
    }
    @DeleteMapping("/deleteReg")
    public Result<?> deleteReg(@RequestParam("regId") Integer regId){
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getRegistId, regId);
        registinfoService.remove(wrapper);
        return Result.success("删除成功");
    }
}
