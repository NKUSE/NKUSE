package com.nku.cet.mapper;

import com.nku.cet.entity.Userinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {
    public String getRoleNameByUserId(String userId);
}
