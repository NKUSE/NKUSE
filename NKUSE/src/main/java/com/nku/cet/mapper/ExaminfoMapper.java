package com.nku.cet.mapper;

import com.nku.cet.entity.Examinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
@Mapper
public interface ExaminfoMapper extends BaseMapper<Examinfo> {
    @Select("SELECT MAX(exam_Id) FROM cet6_examinfo")
    long getMaximumExamId();

    public void updateExamPaperIDToNull(String examId);

    public List<Examinfo> getByUserId(String examId);

    public List<Examinfo> getByState();

    public List<Examinfo> getByExamState(Integer state);
}

