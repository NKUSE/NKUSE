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
@TableName("answer_sub")
public class AnswerSub implements Serializable {

    private static final long serialVersionUID = 1L;

    private String stuId;

    private String examinationId;

    private String subId;

    private String stuAns;

    private Integer stuScore;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }
    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }
    public String getStuAns() {
        return stuAns;
    }

    public void setStuAns(String stuAns) {
        this.stuAns = stuAns;
    }
    public Integer getStuScore() {
        return stuScore;
    }

    public void setStuScore(Integer stuScore) {
        this.stuScore = stuScore;
    }

    @Override
    public String toString() {
        return "AnswerSub{" +
            "stuId=" + stuId +
            ", examinationId=" + examinationId +
            ", subId=" + subId +
            ", stuAns=" + stuAns +
            ", stuScore=" + stuScore +
        "}";
    }
}
