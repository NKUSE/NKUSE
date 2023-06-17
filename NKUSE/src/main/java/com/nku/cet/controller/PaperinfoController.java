package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nku.cet.entity.Paperinfo;
import com.nku.cet.service.impl.PaperinfoServiceImpl;
import com.nku.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
