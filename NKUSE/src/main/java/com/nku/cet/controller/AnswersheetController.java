package com.nku.cet.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nku.cet.entity.*;
import com.nku.cet.service.impl.AnswersheetServiceImpl;
import com.nku.cet.service.impl.ExaminfoServiceImpl;
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
@RequestMapping("/cet/answersheet")
public class AnswersheetController {
    @Autowired
    AnswersheetServiceImpl answersheetService;
    @Autowired
    ExaminfoServiceImpl examinfoService;
    @Autowired
    PaperinfoServiceImpl paperinfoService;
    @Autowired
    ObjQuesServiceImpl objQuesService;
    @PostMapping("/newAnswerSheet")
    public Result<Map<String, Object> > newAnswerSheet(@RequestBody Answersheet answersheet){
        answersheet.setScoreObj(-1.0f);
        long maxSheetId = answersheetService.getBaseMapper().getMaximumSheetId();
        Integer nextid =  (int)maxSheetId + 1;
        answersheet.setAnswersheetId(nextid);
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
        System.out.println("score: " + sc);
        if(sc == null || sc == -1.0f) {
            res.put("hasScore", 0);
        }
        else {
            res.put("hasScore", 1);
        }
        return Result.success(res);
    }
    @GetMapping("/getSheet")
    public Result<Map<String,Object>> getSheet(@RequestParam("sheetid") Integer sheetid){

        LambdaQueryWrapper<Answersheet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answersheet::getAnswersheetId, sheetid);
        Map<String,Object> res = new HashMap<>();
        Answersheet record = answersheetService.getOne(wrapper);
        List<Integer> list = new LinkedList<>();
        list.add(record.getAnswerObj1());
        list.add(record.getAnswerObj2());
        list.add(record.getAnswerObj3());
        list.add(record.getAnswerObj4());
        list.add(record.getAnswerObj5());
        list.add(record.getAnswerObj6());
        list.add(record.getAnswerObj7());
        list.add(record.getAnswerObj8());
        list.add(record.getAnswerObj9());
        list.add(record.getAnswerObj10());
        list.add(record.getAnswerObj11());
        list.add(record.getAnswerObj12());
        list.add(record.getAnswerObj13());
        list.add(record.getAnswerObj14());
        list.add(record.getAnswerObj15());
        list.add(record.getAnswerObj16());
        list.add(record.getAnswerObj17());
        list.add(record.getAnswerObj18());
        list.add(record.getAnswerObj19());
        list.add(record.getAnswerObj20());
        res.put("objlist", list);
        List<String> sublist = new LinkedList<>();
        sublist.add(record.getAnswerTrans());
        sublist.add(record.getAnswerWriting());
        res.put("sublist", sublist);
        return Result.success(res);
    }


    @PutMapping("/updateSheet")
    public Result<Map<String,Object>> updateSheet(@RequestBody Answersheet answersheet) {
        Integer sheetId = answersheet.getAnswersheetId();
        LambdaQueryWrapper<Answersheet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answersheet::getAnswersheetId, sheetId);
        answersheetService.update(answersheet, wrapper);
        return Result.success("保存成功");
    }

    @PutMapping("/submitSheet")
    public Result<Map<String,Object>> submitSheet(@RequestBody Answersheet answersheet) {
        Integer sheetId = answersheet.getAnswersheetId();
        LambdaQueryWrapper<Answersheet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answersheet::getAnswersheetId, sheetId);
        //计算分数
        Float scores = 0.0f;

        //查询得到examID
        Integer examID = answersheet.getExamId();
        LambdaQueryWrapper<Examinfo> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Examinfo::getExamId, examID);
        Examinfo examinfo = examinfoService.getOne(wrapper2);
        //查询得到paperID
        Integer paperID = examinfo.getExamPaperid();
        LambdaQueryWrapper<Paperinfo> wrapper3 = new LambdaQueryWrapper<>();
        wrapper3.eq(Paperinfo::getPaperId, paperID);
        Paperinfo paperinfo = paperinfoService.getOne(wrapper3);
        //查询得到objID
        Integer obj1 = paperinfo.getQuestionObj1();
        LambdaQueryWrapper<ObjQues> wrapperobj1 = new LambdaQueryWrapper<>();
        wrapperobj1.eq(ObjQues::getQuestionId, obj1);
        ObjQues objitem1 = objQuesService.getOne(wrapperobj1);
        Integer answer1 = objitem1.getAnswer();
        if (answer1 == answersheet.getAnswerObj1()) {
            scores += 1.5f;
        }

        Integer obj2 = paperinfo.getQuestionObj2();
        LambdaQueryWrapper<ObjQues> wrapperobj2 = new LambdaQueryWrapper<>();
        wrapperobj2.eq(ObjQues::getQuestionId, obj2);
        ObjQues objitem2 = objQuesService.getOne(wrapperobj2);
        Integer answer2 = objitem2.getAnswer();
        if (answer2 == answersheet.getAnswerObj2()) {
            scores += 1.5f;
        }

        Integer obj3 = paperinfo.getQuestionObj3();
        LambdaQueryWrapper<ObjQues> wrapperobj3 = new LambdaQueryWrapper<>();
        wrapperobj3.eq(ObjQues::getQuestionId, obj3);
        ObjQues objitem3 = objQuesService.getOne(wrapperobj3);
        Integer answer3 = objitem3.getAnswer();
        if (answer3 == answersheet.getAnswerObj3()) {
            scores += 1.5f;
        }

        Integer obj4 = paperinfo.getQuestionObj4();
        LambdaQueryWrapper<ObjQues> wrapperobj4 = new LambdaQueryWrapper<>();
        wrapperobj4.eq(ObjQues::getQuestionId, obj4);
        ObjQues objitem4 = objQuesService.getOne(wrapperobj4);
        Integer answer4 = objitem4.getAnswer();
        if (answer4 == answersheet.getAnswerObj4()) {
            scores += 1.5f;
        }

        Integer obj5 = paperinfo.getQuestionObj5();
        LambdaQueryWrapper<ObjQues> wrapperobj5 = new LambdaQueryWrapper<>();
        wrapperobj5.eq(ObjQues::getQuestionId, obj5);
        ObjQues objitem5 = objQuesService.getOne(wrapperobj5);
        Integer answer5 = objitem5.getAnswer();
        if (answer5 == answersheet.getAnswerObj5()) {
            scores += 1.5f;
        }

        Integer obj6 = paperinfo.getQuestionObj6();
        LambdaQueryWrapper<ObjQues> wrapperobj6 = new LambdaQueryWrapper<>();
        wrapperobj6.eq(ObjQues::getQuestionId, obj6);
        ObjQues objitem6 = objQuesService.getOne(wrapperobj6);
        Integer answer6 = objitem6.getAnswer();
        if (answer6 == answersheet.getAnswerObj6()) {
            scores += 1.5f;
        }

        Integer obj7 = paperinfo.getQuestionObj7();
        LambdaQueryWrapper<ObjQues> wrapperobj7 = new LambdaQueryWrapper<>();
        wrapperobj7.eq(ObjQues::getQuestionId, obj7);
        ObjQues objitem7 = objQuesService.getOne(wrapperobj7);
        Integer answer7 = objitem7.getAnswer();
        if (answer7 == answersheet.getAnswerObj7()) {
            scores += 1.5f;
        }

        Integer obj8 = paperinfo.getQuestionObj8();
        LambdaQueryWrapper<ObjQues> wrapperobj8 = new LambdaQueryWrapper<>();
        wrapperobj8.eq(ObjQues::getQuestionId, obj8);
        ObjQues objitem8 = objQuesService.getOne(wrapperobj8);
        Integer answer8 = objitem8.getAnswer();
        if (answer8 == answersheet.getAnswerObj8()) {
            scores += 1.5f;
        }

        Integer obj9 = paperinfo.getQuestionObj9();
        LambdaQueryWrapper<ObjQues> wrapperobj9 = new LambdaQueryWrapper<>();
        wrapperobj9.eq(ObjQues::getQuestionId, obj9);
        ObjQues objitem9 = objQuesService.getOne(wrapperobj9);
        Integer answer9 = objitem9.getAnswer();
        if (answer9 == answersheet.getAnswerObj9()) {
            scores += 1.5f;
        }

        Integer obj10 = paperinfo.getQuestionObj10();
        LambdaQueryWrapper<ObjQues> wrapperobj10 = new LambdaQueryWrapper<>();
        wrapperobj10.eq(ObjQues::getQuestionId, obj10);
        ObjQues objitem10 = objQuesService.getOne(wrapperobj10);
        Integer answer10 = objitem10.getAnswer();
        if (answer10 == answersheet.getAnswerObj10()) {
            scores += 1.5f;
        }

        Integer obj11 = paperinfo.getQuestionObj11();
        LambdaQueryWrapper<ObjQues> wrapperobj11 = new LambdaQueryWrapper<>();
        wrapperobj11.eq(ObjQues::getQuestionId, obj11);
        ObjQues objitem11 = objQuesService.getOne(wrapperobj11);
        Integer answer11 = objitem11.getAnswer();
        if (answer11 == answersheet.getAnswerObj11()) {
            scores += 1.5f;
        }

        Integer obj12 = paperinfo.getQuestionObj12();
        LambdaQueryWrapper<ObjQues> wrapperobj12 = new LambdaQueryWrapper<>();
        wrapperobj12.eq(ObjQues::getQuestionId, obj12);
        ObjQues objitem12 = objQuesService.getOne(wrapperobj12);
        Integer answer12 = objitem12.getAnswer();
        if (answer12 == answersheet.getAnswerObj12()) {
            scores += 1.5f;
        }

        Integer obj13 = paperinfo.getQuestionObj13();
        LambdaQueryWrapper<ObjQues> wrapperobj13 = new LambdaQueryWrapper<>();
        wrapperobj13.eq(ObjQues::getQuestionId, obj13);
        ObjQues objitem13 = objQuesService.getOne(wrapperobj13);
        Integer answer13 = objitem13.getAnswer();
        if (answer13 == answersheet.getAnswerObj13()) {
            scores += 1.5f;
        }

        Integer obj14 = paperinfo.getQuestionObj14();
        LambdaQueryWrapper<ObjQues> wrapperobj14 = new LambdaQueryWrapper<>();
        wrapperobj14.eq(ObjQues::getQuestionId, obj14);
        ObjQues objitem14 = objQuesService.getOne(wrapperobj14);
        Integer answer14 = objitem14.getAnswer();
        if (answer14 == answersheet.getAnswerObj14()) {
            scores += 1.5f;
        }

        Integer obj15 = paperinfo.getQuestionObj15();
        LambdaQueryWrapper<ObjQues> wrapperobj15 = new LambdaQueryWrapper<>();
        wrapperobj15.eq(ObjQues::getQuestionId, obj15);
        ObjQues objitem15 = objQuesService.getOne(wrapperobj15);
        Integer answer15 = objitem15.getAnswer();
        if (answer15 == answersheet.getAnswerObj15()) {
            scores += 1.5f;
        }

        Integer obj16 = paperinfo.getQuestionObj16();
        LambdaQueryWrapper<ObjQues> wrapperobj16 = new LambdaQueryWrapper<>();
        wrapperobj16.eq(ObjQues::getQuestionId, obj16);
        ObjQues objitem16 = objQuesService.getOne(wrapperobj16);
        Integer answer16 = objitem16.getAnswer();
        if (answer16 == answersheet.getAnswerObj16()) {
            scores += 1.5f;
        }

        Integer obj17 = paperinfo.getQuestionObj17();
        LambdaQueryWrapper<ObjQues> wrapperobj17 = new LambdaQueryWrapper<>();
        wrapperobj17.eq(ObjQues::getQuestionId, obj17);
        ObjQues objitem17 = objQuesService.getOne(wrapperobj17);
        Integer answer17 = objitem17.getAnswer();
        if (answer17 == answersheet.getAnswerObj17()) {
            scores += 1.5f;
        }

        Integer obj18 = paperinfo.getQuestionObj18();
        LambdaQueryWrapper<ObjQues> wrapperobj18 = new LambdaQueryWrapper<>();
        wrapperobj18.eq(ObjQues::getQuestionId, obj18);
        ObjQues objitem18 = objQuesService.getOne(wrapperobj18);
        Integer answer18 = objitem18.getAnswer();
        if (answer18 == answersheet.getAnswerObj18()) {
            scores += 1.5f;
        }

        Integer obj19 = paperinfo.getQuestionObj19();
        LambdaQueryWrapper<ObjQues> wrapperobj19 = new LambdaQueryWrapper<>();
        wrapperobj19.eq(ObjQues::getQuestionId, obj19);
        ObjQues objitem19 = objQuesService.getOne(wrapperobj19);
        Integer answer19 = objitem19.getAnswer();
        if (answer19 == answersheet.getAnswerObj19()) {
            scores += 1.5f;
        }

        Integer obj20 = paperinfo.getQuestionObj20();
        LambdaQueryWrapper<ObjQues> wrapperobj20 = new LambdaQueryWrapper<>();
        wrapperobj20.eq(ObjQues::getQuestionId, obj20);
        ObjQues objitem20 = objQuesService.getOne(wrapperobj20);
        Integer answer20 = objitem20.getAnswer();
        if (answer20 == answersheet.getAnswerObj20()) {
            scores += 1.5f;
        }
        answersheet.setScoreObj(scores);

        answersheetService.update(answersheet, wrapper);
        return Result.success("保存成功");
    }

    @GetMapping("/maxId")
    public Result<Map<String, Object>> getMaximumSheetId() {
        long maxSheetId = answersheetService.getBaseMapper().getMaximumSheetId();
        Map<String, Object> res = new HashMap<>();
        res.put("maximumSheetId", maxSheetId);
        return Result.success(res);
    }
}
