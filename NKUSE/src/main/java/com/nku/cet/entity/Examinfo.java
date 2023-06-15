package com.nku.cet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
@TableName("cet6_examinfo")
public class Examinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String examId;

    private String examName;

    private Integer examPaperid;

    private LocalDateTime examTime;

    private Integer examState;

    private Float examFee;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
    public Integer getExamPaperid() {
        return examPaperid;
    }

    public void setExamPaperid(Integer examPaperid) {
        this.examPaperid = examPaperid;
    }
    public LocalDateTime getExamTime() {
        return examTime;
    }

    public void setExamTime(LocalDateTime examTime) {
        this.examTime = examTime;
    }
    public Integer getExamState() {
        return examState;
    }

    public void setExamState(Integer examState) {
        this.examState = examState;
    }
    public Float getExamFee() {
        return examFee;
    }

    public void setExamFee(Float examFee) {
        this.examFee = examFee;
    }

    @Override
    public String toString() {
        return "Examinfo{" +
            "examId=" + examId +
            ", examName=" + examName +
            ", examPaperid=" + examPaperid +
            ", examTime=" + examTime +
            ", examState=" + examState +
            ", examFee=" + examFee +
        "}";
    }
}
