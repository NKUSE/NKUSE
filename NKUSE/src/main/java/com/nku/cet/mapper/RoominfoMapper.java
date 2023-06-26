package com.nku.cet.mapper;

import com.nku.cet.entity.Roominfo;
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
public interface RoominfoMapper extends BaseMapper<Roominfo> {
    @Select("SELECT MAX(room_Id) FROM cet6_roominfo")
    long getMaximumRoomId();
}
