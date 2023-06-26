package com.nku.cet.service.impl;

import com.nku.cet.entity.Examinfo;
import com.nku.cet.mapper.ExaminfoMapper;
import com.nku.cet.service.IExaminfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nku.common.vo.Result;
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
public class ExaminfoServiceImpl extends ServiceImpl<ExaminfoMapper, Examinfo> implements IExaminfoService {

    @Override
    public long getMaxId() {
        return this.baseMapper.getMaximumExamId();
    }

    @Override
    public Map<String, Object> getUserExams(String user_id) {
        List<Examinfo> res = this.baseMapper.getByUserId(user_id);
        Map<String, Object> map = new HashMap<>();
        map.put("total", res.size());
        map.put("exams", res);
        return map;
    }

    @Override
    public  Map<String, Object> getExamsbyState(){
        List<Examinfo> res = this.baseMapper.getByState();
        Map<String,Object> map=new HashMap<>();
        map.put("exams",res);
        return map;
    }
}
