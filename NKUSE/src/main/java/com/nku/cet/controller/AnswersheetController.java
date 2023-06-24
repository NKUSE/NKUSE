package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nku.cet.entity.Answersheet;
import com.nku.cet.entity.Examinfo;
import com.nku.cet.entity.Registinfo;
import com.nku.cet.service.impl.AnswersheetServiceImpl;
import com.nku.cet.service.impl.ExaminfoServiceImpl;
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
@RequestMapping("/cet/answersheet")
public class AnswersheetController {
    @Autowired
    AnswersheetServiceImpl answersheetService;
    @PostMapping("/newAnswerSheet")
    public Result<Map<String, Object> > newAnswerSheet(@RequestBody Answersheet answersheet){
        //answersheet.setScoreObj(-1.0f);
        answersheetService.save(answersheet);
        Map<String, Object> res = new HashMap<>();
        String Userid = answersheet.getUserId();
        Integer Examid = answersheet.getExamId();
        LambdaQueryWrapper<Answersheet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answersheet::getExamId, Examid);
        wrapper.eq(Answersheet::getUserId, Userid);
        Answersheet answersheet_res = answersheetService.getOne(wrapper);
        Integer answersheet_id = answersheet_res.getAnswersheetId();
        res.put("sheetId", answersheet_id);

        return Result.success("新增成功", res);
    }

    @GetMapping("/checkScore")
    public Result<Map<String,Object>> checkScore(@RequestParam("sheetid") Integer sheetid){
        LambdaQueryWrapper<Answersheet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answersheet::getAnswersheetId, sheetid);
        Map<String,Object> res = new HashMap<>();
        Answersheet record = answersheetService.getOne(wrapper);
        Float sc = record.getScoreObj();
        if(sc == null) {
            res.put("hasScore", 0);
        }
        else {
            res.put("hasScore", 1);
        }
        return Result.success(res);
    }
}
