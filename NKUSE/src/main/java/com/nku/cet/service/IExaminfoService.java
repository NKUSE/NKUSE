package com.nku.cet.service;

import com.nku.cet.entity.Examinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
public interface IExaminfoService extends IService<Examinfo> {

    long getMaxId();

    Map<String, Object> getUserExams(String user_id);

    Map<String, Object> getExamsbyState();

    Map<String, Object> getFinishedExams();
}
