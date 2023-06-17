package com.nku.cet.mapper;

import com.nku.cet.entity.Paperinfo;
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
public interface PaperinfoMapper extends BaseMapper<Paperinfo> {
    @Select("SELECT MAX(paper_Id) FROM cet6_paperinfo")
    Integer getMaximumPaperId();
}
