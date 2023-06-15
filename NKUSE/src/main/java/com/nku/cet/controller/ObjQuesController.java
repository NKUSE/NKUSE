package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nku.cet.entity.ObjQues;
import com.nku.cet.service.impl.ObjQuesServiceImpl;
import com.nku.common.vo.Result;
import org.apache.catalina.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.net.Inet4Address;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/cet/objQues")
public class ObjQuesController {
    @Autowired
    ObjQuesServiceImpl objQuesService;
    //增post 改put 删delete 查get
    @GetMapping("/getAll")
    public Result<Map<String,Object>> getAllItems(@RequestParam("pageNo") Integer pageNo,
                                                  @RequestParam("pageSize") Integer pageSize){
        Page<ObjQues> page = new Page<>(pageNo, pageSize);
        objQuesService.page(page);
        Map<String,Object> res = new HashMap<>();
        res.put("total", page.getTotal());
        res.put("rows", page.getRecords());
        //Map<String,Object> res = objQuesService.getAll();
        return Result.success(res);
    }
    @GetMapping("/getOne")
    public Result<Map<String,Object>> getOneItems(@RequestParam("quesId") Integer quesId){
        LambdaQueryWrapper<ObjQues> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjQues::getQuestionId, quesId);
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> data = new LinkedList<>();
        ObjQues record = objQuesService.getOne(wrapper);
        Map<String,Object> record_map = new HashMap<>();
        if(record != null){
            record_map.put("questionId",record.getQuestionId());
            record_map.put("description",record.getDescription());
            record_map.put("optionA",record.getOptionA());
            record_map.put("optionB",record.getOptionB());
            record_map.put("optionC",record.getOptionC());
            record_map.put("optionD",record.getOptionD());
            record_map.put("answer",record.getAnswer());
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

    @PostMapping("/newQues")
    public Result<?> newQuestion(@RequestBody ObjQues ques){
        objQuesService.save(ques);
        return Result.success("新增成功");
    }

    @PutMapping("/updateQues")
    public Result<?> updateQuestion(@RequestBody ObjQues ques){
        Integer quesId = ques.getQuestionId();
        LambdaQueryWrapper<ObjQues> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjQues::getQuestionId, quesId);
        objQuesService.update(ques, wrapper);
        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteQues")
    public Result<?> deleteQuestion(@RequestParam("quesId") Integer quesId){
        LambdaQueryWrapper<ObjQues> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjQues::getQuestionId, quesId);
        objQuesService.remove(wrapper);
        return Result.success("删除成功");
    }
}
