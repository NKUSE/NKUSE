package com.nku.stu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * <p>
 * 
 * </p>
 *
 * @author lyh
 * @since 2023-05-19
 */
@TableName("exam_info")
public class ExamInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String examinationId;

    private LocalDateTime time;

    private Integer fee;

    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "ExamInfo{" +
            "examinationId=" + examinationId +
            ", time=" + time +
            ", fee=" + fee +
        "}";
    }
}
