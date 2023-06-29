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
    @GetMapping("/getSheetId")
    public Result<Map<String,Object>> getSheetId(@RequestParam("userId") String userId, @RequestParam("examId") Integer examId){
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getExamId, examId);
        wrapper.eq(Registinfo::getUserId, userId);
        Map<String,Object> res = new HashMap<>();
        Registinfo record = registinfoService.getOne(wrapper);
        Integer answersheetId = record.getAnswersheetId();
        System.out.println(answersheetId);
        if(record.getPaid() == 0) {return Result.fail(20001,"未完成缴费，无法进入考试");}
        if(answersheetId == null ||answersheetId == 0  ) {
            res.put("HasAnswersheet", 0);
            res.put("answersheetId", 0);
        }
        else {

            res.put("HasAnswersheet", 1);
            res.put("answersheetId", answersheetId);
        }
        return Result.success(res);
    }

    @PutMapping("/setSheetId")
    public Result<?> setSheetId(@RequestBody Registinfo reg){
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getExamId, reg.getExamId());
        wrapper.eq(Registinfo::getUserId,
                reg.getUserId());
        Map<String,Object> res = new HashMap<>();
        Registinfo record = registinfoService.getOne(wrapper);
        record.setAnswersheetId(reg.getAnswersheetId());
        registinfoService.update(record, wrapper);
        return Result.success("修改成功");
    }

    @GetMapping("/maxId")
    public Result<Map<String, Object>> getMaximumRegistId() {
        long maxRegistId = registinfoService.getBaseMapper().getMaximumRegistId();
        Map<String, Object> res = new HashMap<>();
        res.put("maximumRegistId", maxRegistId);
        return Result.success(res);
    }
    @PostMapping("/signup")
    public Result<?> signUp(@RequestBody Registinfo reginfo){
        //reg.setRegistId("000002");
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getExamId, reginfo.getExamId());
        wrapper.eq(Registinfo::getUserId,
                reginfo.getUserId());
        Registinfo record = registinfoService.getOne(wrapper);
        if(record != null) {return Result.fail(20001,"请勿重复报名！");}
        reginfo.setRoomId("1");
        reginfo.setSeatNumber(1);
        registinfoService.save(reginfo);
        return Result.success("新增成功");
    }

    @GetMapping("/getScore")
    public Result<Map<String,Object>> getScore(@RequestParam("user_id") String user_id, @RequestParam("examid") Integer examid){
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getExamId, examid);
        wrapper.eq(Registinfo::getUserId, user_id);
        Map<String,Object> res = new HashMap<>();
        Registinfo record = registinfoService.getOne(wrapper);
        Float score = record.getScore();
        System.out.println(score);
        res.put("score",score);
        return Result.success(res);
    }

    @GetMapping("/getRegist")
    public Result<Map<String,Object>> getRegist(@RequestParam("userid") String userid, @RequestParam("examid") Integer examid){
        LambdaQueryWrapper<Registinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Registinfo::getUserId, userid);
        wrapper.eq(Registinfo::getExamId, examid);
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
        }
        return Result.success(record_map);
    }
}
