package com.nku.stu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyh
 * @since 2023-05-19
 */
@TableName("regist_info")
public class RegistInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String examId;

    private String examinationId;

    private Integer seatNum;

    private Integer score;

    private Integer feed;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }
    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public Integer getFeed() {
        return feed;
    }

    public void setFeed(Integer feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "RegistInfo{" +
            "userId=" + userId +
            ", examId=" + examId +
            ", examinationId=" + examinationId +
            ", seatNum=" + seatNum +
            ", score=" + score +
            ", feed=" + feed +
        "}";
    }
}
