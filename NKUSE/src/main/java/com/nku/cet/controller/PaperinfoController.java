package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nku.cet.entity.Paperinfo;
import com.nku.cet.service.impl.PaperinfoServiceImpl;
import com.nku.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
@RequestMapping("/cet/paperinfo")
public class PaperinfoController {
    @Autowired
    PaperinfoServiceImpl paperinfoService;
    @GetMapping("/getById")
    public Result<Map<String, Object>> getById(@RequestParam("paperId") Integer paperId){
        LambdaQueryWrapper<Paperinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paperinfo::getPaperId, paperId);
        Paperinfo res = paperinfoService.getOne(wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", res);
        return Result.success(map);
    }
    @GetMapping("/getMaxId")
    public Result<Map<String, Object>> getMaxId(){
        long maxPaperId = paperinfoService.getBaseMapper().getMaximumPaperId();
        Map<String, Object> res = new HashMap<>();
        res.put("maximumPaperId", maxPaperId);
        return Result.success(res);
    }
    @PostMapping("/newPaper")
    public Result<Map<String, Object>> newExamInfo(@RequestBody Paperinfo paper){
        Integer maxPaperId = paperinfoService.getBaseMapper().getMaximumPaperId();
        paper.setPaperId(maxPaperId + 1);
        paperinfoService.save(paper);
        Map<String, Object> res = new HashMap<>();
        res.put("InsertId", maxPaperId + 1);
        return Result.success("新增成功", res);
    }
    @DeleteMapping("/deletePaper")
    public Result<?> deletePaper(@RequestParam("paperId") Integer paperId){
        LambdaQueryWrapper<Paperinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paperinfo::getPaperId, paperId);
        paperinfoService.remove(wrapper);
        return Result.success("删除成功");
    }
}
