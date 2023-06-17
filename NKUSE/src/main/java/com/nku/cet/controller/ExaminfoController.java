package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nku.cet.entity.Examinfo;
import com.nku.cet.mapper.ExaminfoMapper;
import com.nku.cet.service.impl.ExaminfoServiceImpl;
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
@RequestMapping("/cet/examinfo")
public class ExaminfoController {
    @Autowired
    ExaminfoServiceImpl examinfoService;
    // ExaminfoMapper examinfoMapper;

    @GetMapping("/getAll")
    public Result<Map<String,Object>> getAllItems(@RequestParam("pageNo") Integer pageNo,
                                                  @RequestParam("pageSize") Integer pageSize){
        Page<Examinfo> page = new Page<>(pageNo, pageSize);
        examinfoService.page(page);
        Map<String,Object> res = new HashMap<>();
        res.put("total", page.getTotal());
        res.put("rows", page.getRecords());
        //Map<String,Object> res = objQuesService.getAll();
        return Result.success(res);
    }

    @GetMapping("/getOne")
    public Result<Map<String,Object>> getOneItems(@RequestParam("examId") Integer examId){
        LambdaQueryWrapper<Examinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Examinfo::getExamId, examId);
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> data = new LinkedList<>();
        Examinfo record = examinfoService.getOne(wrapper);
        Map<String,Object> record_map = new HashMap<>();
        if(record != null){
            record_map.put("examId",record.getExamId());
            record_map.put("examName",record.getExamName());
            record_map.put("examTime",record.getExamTime());
            record_map.put("examState",record.getExamState());
            record_map.put("examFee",record.getExamFee());
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

    @PutMapping("/updateExamInfo")
    public Result<?> updateExamInfo(@RequestBody Examinfo exam){
        String examId = exam.getExamId();
        LambdaQueryWrapper<Examinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Examinfo::getExamId, examId);
        examinfoService.update(exam, wrapper);
        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteExamInfo")
    public Result<?> deleteExamInfo(@RequestParam("examId") Integer examId){
        LambdaQueryWrapper<Examinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Examinfo::getExamId, examId);
        examinfoService.remove(wrapper);
        return Result.success("删除成功");
    }

    @PostMapping("/newExamInfo")
    public Result<?> newExamInfo(@RequestBody Examinfo exam){
        examinfoService.save(exam);
        return Result.success("新增成功");
    }

    @GetMapping("/maxId")
    public Result<Map<String, Object>> getMaximumExamId() {
        long maxExamId = examinfoService.getBaseMapper().getMaximumExamId();
        Map<String, Object> res = new HashMap<>();
        res.put("maximumExamId", maxExamId);
        return Result.success(res);
    }

    @PutMapping("/removeExamInfoPaper")
    public Result<?> removeExamInfoPaper(@RequestBody Examinfo exam){
        String examId = exam.getExamId();
        examinfoService.getBaseMapper().updateExamPaperIDToNull(examId);
        return Result.success("修改成功");
    }
}
