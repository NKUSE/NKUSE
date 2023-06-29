package com.nku.cet.service;

import com.nku.cet.entity.Answersheet;
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
public interface IAnswersheetService extends IService<Answersheet> {
    Map<String, Object> getSheetByExamId(Integer examId);
    Map<String, Object> getAllSheetByExamId(Integer examId);
}
