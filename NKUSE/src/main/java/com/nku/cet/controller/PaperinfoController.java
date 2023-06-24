package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nku.cet.entity.ObjQues;
import com.nku.cet.entity.Paperinfo;
import com.nku.cet.service.impl.ObjQuesServiceImpl;
import com.nku.cet.service.impl.PaperinfoServiceImpl;
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
@RequestMapping("/cet/paperinfo")
public class PaperinfoController {
    @Autowired
    PaperinfoServiceImpl paperinfoService;
    @Autowired
    ObjQuesServiceImpl objinfoService;
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

    @GetMapping("/getPaperInfo")
    public  Result<?> getPaperInfo(@RequestParam("paperId") Integer paperId) {
        LambdaQueryWrapper<Paperinfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Paperinfo::getPaperId, paperId);
        Paperinfo item = paperinfoService.getOne(wrapper);
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> questionsSub = new LinkedList<>();
        Map<String, Object> sub1 = new HashMap<>();
        sub1.put("seq_num", "1");
        sub1.put("question", item.getQuestionTranslating());
        sub1.put("score", 20);
        Map<String, Object> sub2 = new HashMap<>();
        sub2.put("seq_num", "2");
        sub2.put("question", item.getQuestionWriting());
        sub2.put("score", 50);
        questionsSub.add(sub1);
        questionsSub.add(sub2);
        res.put("questionsSub", questionsSub);
        List<Map<String, Object>> questionsObj = new LinkedList<>();
        {
            Map<String, Object> obj1 = new HashMap<>();
            obj1.put("seq_num", "1");
            LambdaQueryWrapper<ObjQues> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(ObjQues::getQuestionId, item.getQuestionObj1());
            ObjQues item_obj1 = objinfoService.getOne(wrapper1);
            obj1.put("question", "Question 1: " + item_obj1.getDescription());
            List<String> list1 = new LinkedList<>();
            list1.add("A. " + item_obj1.getOptionA());
            list1.add("B. " + item_obj1.getOptionB());
            list1.add("C. " + item_obj1.getOptionC());
            list1.add("D. " + item_obj1.getOptionD());
            obj1.put("options", list1);
            obj1.put("check", item_obj1.getAnswer());
            obj1.put("score", 5);
            questionsObj.add(obj1);
        }
        {
            Map<String, Object> obj2 = new HashMap<>();
            obj2.put("seq_num", "2");
            LambdaQueryWrapper<ObjQues> wrapper2 = new LambdaQueryWrapper<>();
            wrapper2.eq(ObjQues::getQuestionId, item.getQuestionObj2());
            ObjQues item_obj2 = objinfoService.getOne(wrapper2);
            obj2.put("question", "Question 2: " + item_obj2.getDescription());
            List<String> list2 = new LinkedList<>();
            list2.add("A. " + item_obj2.getOptionA());
            list2.add("B. " + item_obj2.getOptionB());
            list2.add("C. " + item_obj2.getOptionC());
            list2.add("D. " + item_obj2.getOptionD());
            obj2.put("options", list2);
            obj2.put("check", item_obj2.getAnswer());
            obj2.put("score", 5);
            questionsObj.add(obj2);
        }{
            Map<String, Object> obj3 = new HashMap<>();
            obj3.put("seq_num", "3");
            LambdaQueryWrapper<ObjQues> wrapper3 = new LambdaQueryWrapper<>();
            wrapper3.eq(ObjQues::getQuestionId, item.getQuestionObj3());
            ObjQues item_obj3 = objinfoService.getOne(wrapper3);
            obj3.put("question", "Question 3: " + item_obj3.getDescription());
            List<String> list3 = new LinkedList<>();
            list3.add("A. " + item_obj3.getOptionA());
            list3.add("B. " + item_obj3.getOptionB());
            list3.add("C. " + item_obj3.getOptionC());
            list3.add("D. " + item_obj3.getOptionD());
            obj3.put("options", list3);
            obj3.put("check", item_obj3.getAnswer());
            obj3.put("score", 5);
            questionsObj.add(obj3);
        }{
            Map<String, Object> obj4 = new HashMap<>();
            obj4.put("seq_num", "4");
            LambdaQueryWrapper<ObjQues> wrapper4 = new LambdaQueryWrapper<>();
            wrapper4.eq(ObjQues::getQuestionId, item.getQuestionObj4());
            ObjQues item_obj4 = objinfoService.getOne(wrapper4);
            obj4.put("question", "Question 4: " + item_obj4.getDescription());
            List<String> list4 = new LinkedList<>();
            list4.add("A. " + item_obj4.getOptionA());
            list4.add("B. " + item_obj4.getOptionB());
            list4.add("C. " + item_obj4.getOptionC());
            list4.add("D. " + item_obj4.getOptionD());
            obj4.put("options", list4);
            obj4.put("check", item_obj4.getAnswer());
            obj4.put("score", 5);
            questionsObj.add(obj4);
        }{
            Map<String, Object> obj5 = new HashMap<>();
            obj5.put("seq_num", "5");
            LambdaQueryWrapper<ObjQues> wrapper5 = new LambdaQueryWrapper<>();
            wrapper5.eq(ObjQues::getQuestionId, item.getQuestionObj5());
            ObjQues item_obj5 = objinfoService.getOne(wrapper5);
            obj5.put("question", "Question 5: " + item_obj5.getDescription());
            List<String> list5 = new LinkedList<>();
            list5.add("A. " + item_obj5.getOptionA());
            list5.add("B. " + item_obj5.getOptionB());
            list5.add("C. " + item_obj5.getOptionC());
            list5.add("D. " + item_obj5.getOptionD());
            obj5.put("options", list5);
            obj5.put("check", item_obj5.getAnswer());
            obj5.put("score", 5);
            questionsObj.add(obj5);
        }{
            Map<String, Object> obj6 = new HashMap<>();
            obj6.put("seq_num", "6");
            LambdaQueryWrapper<ObjQues> wrapper6 = new LambdaQueryWrapper<>();
            wrapper6.eq(ObjQues::getQuestionId, item.getQuestionObj6());
            ObjQues item_obj6 = objinfoService.getOne(wrapper6);
            obj6.put("question", "Question 6: " + item_obj6.getDescription());
            List<String> list6 = new LinkedList<>();
            list6.add("A. " + item_obj6.getOptionA());
            list6.add("B. " + item_obj6.getOptionB());
            list6.add("C. " + item_obj6.getOptionC());
            list6.add("D. " + item_obj6.getOptionD());
            obj6.put("options", list6);
            obj6.put("check", item_obj6.getAnswer());
            obj6.put("score", 5);
            questionsObj.add(obj6);
        }{
            Map<String, Object> obj7 = new HashMap<>();
            obj7.put("seq_num", "7");
            LambdaQueryWrapper<ObjQues> wrapper7 = new LambdaQueryWrapper<>();
            wrapper7.eq(ObjQues::getQuestionId, item.getQuestionObj7());
            ObjQues item_obj7 = objinfoService.getOne(wrapper7);
            obj7.put("question", "Question 7: " + item_obj7.getDescription());
            List<String> list7 = new LinkedList<>();
            list7.add("A. " + item_obj7.getOptionA());
            list7.add("B. " + item_obj7.getOptionB());
            list7.add("C. " + item_obj7.getOptionC());
            list7.add("D. " + item_obj7.getOptionD());
            obj7.put("options", list7);
            obj7.put("check", item_obj7.getAnswer());
            obj7.put("score", 5);
            questionsObj.add(obj7);
        }{
            Map<String, Object> obj8 = new HashMap<>();
            obj8.put("seq_num", "8");
            LambdaQueryWrapper<ObjQues> wrapper8 = new LambdaQueryWrapper<>();
            wrapper8.eq(ObjQues::getQuestionId, item.getQuestionObj8());
            ObjQues item_obj8 = objinfoService.getOne(wrapper8);
            obj8.put("question", "Question 8: " + item_obj8.getDescription());
            List<String> list8 = new LinkedList<>();
            list8.add("A. " + item_obj8.getOptionA());
            list8.add("B. " + item_obj8.getOptionB());
            list8.add("C. " + item_obj8.getOptionC());
            list8.add("D. " + item_obj8.getOptionD());
            obj8.put("options", list8);
            obj8.put("check", item_obj8.getAnswer());
            obj8.put("score", 5);
            questionsObj.add(obj8);
        }{
            Map<String, Object> obj9 = new HashMap<>();
            obj9.put("seq_num", "9");
            LambdaQueryWrapper<ObjQues> wrapper9 = new LambdaQueryWrapper<>();
            wrapper9.eq(ObjQues::getQuestionId, item.getQuestionObj9());
            ObjQues item_obj9 = objinfoService.getOne(wrapper9);
            obj9.put("question", "Question 9: " + item_obj9.getDescription());
            List<String> list9 = new LinkedList<>();
            list9.add("A. " + item_obj9.getOptionA());
            list9.add("B. " + item_obj9.getOptionB());
            list9.add("C. " + item_obj9.getOptionC());
            list9.add("D. " + item_obj9.getOptionD());
            obj9.put("options", list9);
            obj9.put("check", item_obj9.getAnswer());
            obj9.put("score", 5);
            questionsObj.add(obj9);
        }{
            Map<String, Object> obj10 = new HashMap<>();
            obj10.put("seq_num", "10");
            LambdaQueryWrapper<ObjQues> wrapper10 = new LambdaQueryWrapper<>();
            wrapper10.eq(ObjQues::getQuestionId, item.getQuestionObj10());
            ObjQues item_obj10 = objinfoService.getOne(wrapper10);
            obj10.put("question", "Question 10: " + item_obj10.getDescription());
            List<String> list10 = new LinkedList<>();
            list10.add("A. " + item_obj10.getOptionA());
            list10.add("B. " + item_obj10.getOptionB());
            list10.add("C. " + item_obj10.getOptionC());
            list10.add("D. " + item_obj10.getOptionD());
            obj10.put("options", list10);
            obj10.put("check", item_obj10.getAnswer());
            obj10.put("score", 5);
            questionsObj.add(obj10);
        }{
            Map<String, Object> obj11 = new HashMap<>();
            obj11.put("seq_num", "11");
            LambdaQueryWrapper<ObjQues> wrapper11 = new LambdaQueryWrapper<>();
            wrapper11.eq(ObjQues::getQuestionId, item.getQuestionObj11());
            ObjQues item_obj11 = objinfoService.getOne(wrapper11);
            obj11.put("question", "Question 11: " + item_obj11.getDescription());
            List<String> list11 = new LinkedList<>();
            list11.add("A. " + item_obj11.getOptionA());
            list11.add("B. " + item_obj11.getOptionB());
            list11.add("C. " + item_obj11.getOptionC());
            list11.add("D. " + item_obj11.getOptionD());
            obj11.put("options", list11);
            obj11.put("check", item_obj11.getAnswer());
            obj11.put("score", 5);
            questionsObj.add(obj11);
        }{
            Map<String, Object> obj12 = new HashMap<>();
            obj12.put("seq_num", "12");
            LambdaQueryWrapper<ObjQues> wrapper12 = new LambdaQueryWrapper<>();
            wrapper12.eq(ObjQues::getQuestionId, item.getQuestionObj12());
            ObjQues item_obj12 = objinfoService.getOne(wrapper12);
            obj12.put("question", "Question 12: " + item_obj12.getDescription());
            List<String> list12 = new LinkedList<>();
            list12.add("A. " + item_obj12.getOptionA());
            list12.add("B. " + item_obj12.getOptionB());
            list12.add("C. " + item_obj12.getOptionC());
            list12.add("D. " + item_obj12.getOptionD());
            obj12.put("options", list12);
            obj12.put("check", item_obj12.getAnswer());
            obj12.put("score", 5);
            questionsObj.add(obj12);
        }{
            Map<String, Object> obj13 = new HashMap<>();
            obj13.put("seq_num", "13");
            LambdaQueryWrapper<ObjQues> wrapper13 = new LambdaQueryWrapper<>();
            wrapper13.eq(ObjQues::getQuestionId, item.getQuestionObj13());
            ObjQues item_obj13 = objinfoService.getOne(wrapper13);
            obj13.put("question", "Question 13: " + item_obj13.getDescription());
            List<String> list13 = new LinkedList<>();
            list13.add("A. " + item_obj13.getOptionA());
            list13.add("B. " + item_obj13.getOptionB());
            list13.add("C. " + item_obj13.getOptionC());
            list13.add("D. " + item_obj13.getOptionD());
            obj13.put("options", list13);
            obj13.put("check", item_obj13.getAnswer());
            obj13.put("score", 5);
            questionsObj.add(obj13);
        }{
            Map<String, Object> obj14 = new HashMap<>();
            obj14.put("seq_num", "14");
            LambdaQueryWrapper<ObjQues> wrapper14 = new LambdaQueryWrapper<>();
            wrapper14.eq(ObjQues::getQuestionId, item.getQuestionObj14());
            ObjQues item_obj14 = objinfoService.getOne(wrapper14);
            obj14.put("question", "Question 14: " + item_obj14.getDescription());
            List<String> list14 = new LinkedList<>();
            list14.add("A. " + item_obj14.getOptionA());
            list14.add("B. " + item_obj14.getOptionB());
            list14.add("C. " + item_obj14.getOptionC());
            list14.add("D. " + item_obj14.getOptionD());
            obj14.put("options", list14);
            obj14.put("check", item_obj14.getAnswer());
            obj14.put("score", 5);
            questionsObj.add(obj14);
        }{
            Map<String, Object> obj15 = new HashMap<>();
            obj15.put("seq_num", "15");
            LambdaQueryWrapper<ObjQues> wrapper15 = new LambdaQueryWrapper<>();
            wrapper15.eq(ObjQues::getQuestionId, item.getQuestionObj15());
            ObjQues item_obj15 = objinfoService.getOne(wrapper15);
            obj15.put("question", "Question 15: " + item_obj15.getDescription());
            List<String> list15 = new LinkedList<>();
            list15.add("A. " + item_obj15.getOptionA());
            list15.add("B. " + item_obj15.getOptionB());
            list15.add("C. " + item_obj15.getOptionC());
            list15.add("D. " + item_obj15.getOptionD());
            obj15.put("options", list15);
            obj15.put("check", item_obj15.getAnswer());
            obj15.put("score", 5);
            questionsObj.add(obj15);
        }{
            Map<String, Object> obj16 = new HashMap<>();
            obj16.put("seq_num", "16");
            LambdaQueryWrapper<ObjQues> wrapper16 = new LambdaQueryWrapper<>();
            wrapper16.eq(ObjQues::getQuestionId, item.getQuestionObj16());
            ObjQues item_obj16 = objinfoService.getOne(wrapper16);
            obj16.put("question", "Question 16: " + item_obj16.getDescription());
            List<String> list16 = new LinkedList<>();
            list16.add("A. " + item_obj16.getOptionA());
            list16.add("B. " + item_obj16.getOptionB());
            list16.add("C. " + item_obj16.getOptionC());
            list16.add("D. " + item_obj16.getOptionD());
            obj16.put("options", list16);
            obj16.put("check", item_obj16.getAnswer());
            obj16.put("score", 5);
            questionsObj.add(obj16);
        }{
            Map<String, Object> obj17 = new HashMap<>();
            obj17.put("seq_num", "17");
            LambdaQueryWrapper<ObjQues> wrapper17 = new LambdaQueryWrapper<>();
            wrapper17.eq(ObjQues::getQuestionId, item.getQuestionObj17());
            ObjQues item_obj17 = objinfoService.getOne(wrapper17);
            obj17.put("question", "Question 17: " + item_obj17.getDescription());
            List<String> list17 = new LinkedList<>();
            list17.add("A. " + item_obj17.getOptionA());
            list17.add("B. " + item_obj17.getOptionB());
            list17.add("C. " + item_obj17.getOptionC());
            list17.add("D. " + item_obj17.getOptionD());
            obj17.put("options", list17);
            obj17.put("check", item_obj17.getAnswer());
            obj17.put("score", 5);
            questionsObj.add(obj17);
        }{
            Map<String, Object> obj18 = new HashMap<>();
            obj18.put("seq_num", "18");
            LambdaQueryWrapper<ObjQues> wrapper18 = new LambdaQueryWrapper<>();
            wrapper18.eq(ObjQues::getQuestionId, item.getQuestionObj18());
            ObjQues item_obj18 = objinfoService.getOne(wrapper18);
            obj18.put("question", "Question 18: " + item_obj18.getDescription());
            List<String> list18 = new LinkedList<>();
            list18.add("A. " + item_obj18.getOptionA());
            list18.add("B. " + item_obj18.getOptionB());
            list18.add("C. " + item_obj18.getOptionC());
            list18.add("D. " + item_obj18.getOptionD());
            obj18.put("options", list18);
            obj18.put("check", item_obj18.getAnswer());
            obj18.put("score", 5);
            questionsObj.add(obj18);
        }{
            Map<String, Object> obj19 = new HashMap<>();
            obj19.put("seq_num", "19");
            LambdaQueryWrapper<ObjQues> wrapper19 = new LambdaQueryWrapper<>();
            wrapper19.eq(ObjQues::getQuestionId, item.getQuestionObj19());
            ObjQues item_obj19 = objinfoService.getOne(wrapper19);
            obj19.put("question", "Question 19: " + item_obj19.getDescription());
            List<String> list19 = new LinkedList<>();
            list19.add("A. " + item_obj19.getOptionA());
            list19.add("B. " + item_obj19.getOptionB());
            list19.add("C. " + item_obj19.getOptionC());
            list19.add("D. " + item_obj19.getOptionD());
            obj19.put("options", list19);
            obj19.put("check", item_obj19.getAnswer());
            obj19.put("score", 5);
            questionsObj.add(obj19);
        }{
            Map<String, Object> obj20 = new HashMap<>();
            obj20.put("seq_num", "20");
            LambdaQueryWrapper<ObjQues> wrapper20 = new LambdaQueryWrapper<>();
            wrapper20.eq(ObjQues::getQuestionId, item.getQuestionObj20());
            ObjQues item_obj20 = objinfoService.getOne(wrapper20);
            obj20.put("question", "Question 20: " + item_obj20.getDescription());
            List<String> list20 = new LinkedList<>();
            list20.add("A. " + item_obj20.getOptionA());
            list20.add("B. " + item_obj20.getOptionB());
            list20.add("C. " + item_obj20.getOptionC());
            list20.add("D. " + item_obj20.getOptionD());
            obj20.put("options", list20);
            obj20.put("check", item_obj20.getAnswer());
            obj20.put("score", 5);
            questionsObj.add(obj20);
        }
        res.put("questions",questionsObj);
        return Result.success(res);
        //return Result.fail(20001,"获取题目失败，试卷信息不完整");
    }
}
