package com.nku.cet.mapper;

import com.nku.cet.entity.Registinfo;
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
public interface RegistinfoMapper extends BaseMapper<Registinfo> {
    @Select("SELECT MAX(regist_Id) FROM cet6_registinfo")
    long getMaximumRegistId();
}
