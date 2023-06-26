package com.nku.cet.mapper;

import com.nku.cet.entity.Answersheet;
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
public interface AnswersheetMapper extends BaseMapper<Answersheet> {
    @Select("SELECT MAX(answerSheet_id) FROM cet6_answersheet")
    long getMaximumSheetId();
}
