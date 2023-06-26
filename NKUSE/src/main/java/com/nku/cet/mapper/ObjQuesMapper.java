package com.nku.cet.mapper;

import com.nku.cet.entity.ObjQues;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
public interface ObjQuesMapper extends BaseMapper<ObjQues> {
    @Select("SELECT MAX(question_id) FROM cet6_obj_ques")
    long getMaximumExamId();
}
