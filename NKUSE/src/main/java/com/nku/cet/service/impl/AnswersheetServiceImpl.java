package com.nku.cet.service.impl;

import com.nku.cet.entity.Answersheet;
import com.nku.cet.mapper.AnswersheetMapper;
import com.nku.cet.service.IAnswersheetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
@Service
public class AnswersheetServiceImpl extends ServiceImpl<AnswersheetMapper, Answersheet> implements IAnswersheetService {
    @Override
    public Map<String, Object> getSheetByExamId(Integer examId) {
        List<Answersheet> res = this.baseMapper.getByExamId(examId);
        Map<String, Object> map = new HashMap<>();
        map.put("total", res.size());
        map.put("sheets", res);
        return map;
    }
    @Override
    public Map<String, Object> getAllSheetByExamId(Integer examId) {
        List<Answersheet> res = this.baseMapper.getAllByExamId(examId);
        Map<String, Object> map = new HashMap<>();
        map.put("total", res.size());
        map.put("sheets", res);
        return map;
    }
}
