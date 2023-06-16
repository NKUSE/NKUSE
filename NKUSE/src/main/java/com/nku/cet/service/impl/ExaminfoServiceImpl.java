package com.nku.cet.service.impl;

import com.nku.cet.entity.Examinfo;
import com.nku.cet.mapper.ExaminfoMapper;
import com.nku.cet.service.IExaminfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
