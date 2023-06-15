package com.nku.cet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyh
 * @since 2023-06-14
 */
@TableName("cet6_answersheet")
public class Answersheet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String answersheetId;

    private String userId;

    private String examId;

    private String answerTrans;

    private String answerWriting;

    private Integer answerObj1;

    private Integer answerObj2;

    private Integer answerObj3;

    private Integer answerObj4;

    private Integer answerObj5;

    private Integer answerObj6;

    private Integer answerObj7;

    private Integer answerObj8;

    private Integer answerObj9;

    private Integer answerObj10;

    private Integer answerObj11;

    private Integer answerObj12;

    private Integer answerObj13;

    private Integer answerObj14;

    private Integer answerObj15;

    private Integer answerObj16;

    private Integer answerObj17;

    private Integer answerObj18;

    private Integer answerObj19;

    private Integer answerObj20;

    private Float scoreObj;

    private Float scoreTrans;

    private Float scoreWriting;

    public String getAnswersheetId() {
        return answersheetId;
    }

    public void setAnswersheetId(String answersheetId) {
        this.answersheetId = answersheetId;
    }
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
    public String getAnswerTrans() {
        return answerTrans;
    }

    public void setAnswerTrans(String answerTrans) {
        this.answerTrans = answerTrans;
    }
    public String getAnswerWriting() {
        return answerWriting;
    }

    public void setAnswerWriting(String answerWriting) {
        this.answerWriting = answerWriting;
    }
    public Integer getAnswerObj1() {
        return answerObj1;
    }

    public void setAnswerObj1(Integer answerObj1) {
        this.answerObj1 = answerObj1;
    }
    public Integer getAnswerObj2() {
        return answerObj2;
    }

    public void setAnswerObj2(Integer answerObj2) {
        this.answerObj2 = answerObj2;
    }
    public Integer getAnswerObj3() {
        return answerObj3;
    }

    public void setAnswerObj3(Integer answerObj3) {
        this.answerObj3 = answerObj3;
    }
    public Integer getAnswerObj4() {
        return answerObj4;
    }

    public void setAnswerObj4(Integer answerObj4) {
        this.answerObj4 = answerObj4;
    }
    public Integer getAnswerObj5() {
        return answerObj5;
    }

    public void setAnswerObj5(Integer answerObj5) {
        this.answerObj5 = answerObj5;
    }
    public Integer getAnswerObj6() {
        return answerObj6;
    }

    public void setAnswerObj6(Integer answerObj6) {
        this.answerObj6 = answerObj6;
    }
    public Integer getAnswerObj7() {
        return answerObj7;
    }

    public void setAnswerObj7(Integer answerObj7) {
        this.answerObj7 = answerObj7;
    }
    public Integer getAnswerObj8() {
        return answerObj8;
    }

    public void setAnswerObj8(Integer answerObj8) {
        this.answerObj8 = answerObj8;
    }
    public Integer getAnswerObj9() {
        return answerObj9;
    }

    public void setAnswerObj9(Integer answerObj9) {
        this.answerObj9 = answerObj9;
    }
    public Integer getAnswerObj10() {
        return answerObj10;
    }

    public void setAnswerObj10(Integer answerObj10) {
        this.answerObj10 = answerObj10;
    }
    public Integer getAnswerObj11() {
        return answerObj11;
    }

    public void setAnswerObj11(Integer answerObj11) {
        this.answerObj11 = answerObj11;
    }
    public Integer getAnswerObj12() {
        return answerObj12;
    }

    public void setAnswerObj12(Integer answerObj12) {
        this.answerObj12 = answerObj12;
    }
    public Integer getAnswerObj13() {
        return answerObj13;
    }

    public void setAnswerObj13(Integer answerObj13) {
        this.answerObj13 = answerObj13;
    }
    public Integer getAnswerObj14() {
        return answerObj14;
    }

    public void setAnswerObj14(Integer answerObj14) {
        this.answerObj14 = answerObj14;
    }
    public Integer getAnswerObj15() {
        return answerObj15;
    }

    public void setAnswerObj15(Integer answerObj15) {
        this.answerObj15 = answerObj15;
    }
    public Integer getAnswerObj16() {
        return answerObj16;
    }

    public void setAnswerObj16(Integer answerObj16) {
        this.answerObj16 = answerObj16;
    }
    public Integer getAnswerObj17() {
        return answerObj17;
    }

    public void setAnswerObj17(Integer answerObj17) {
        this.answerObj17 = answerObj17;
    }
    public Integer getAnswerObj18() {
        return answerObj18;
    }

    public void setAnswerObj18(Integer answerObj18) {
        this.answerObj18 = answerObj18;
    }
    public Integer getAnswerObj19() {
        return answerObj19;
    }

    public void setAnswerObj19(Integer answerObj19) {
        this.answerObj19 = answerObj19;
    }
    public Integer getAnswerObj20() {
        return answerObj20;
    }

    public void setAnswerObj20(Integer answerObj20) {
        this.answerObj20 = answerObj20;
    }
    public Float getScoreObj() {
        return scoreObj;
    }

    public void setScoreObj(Float scoreObj) {
        this.scoreObj = scoreObj;
    }
    public Float getScoreTrans() {
        return scoreTrans;
    }

    public void setScoreTrans(Float scoreTrans) {
        this.scoreTrans = scoreTrans;
    }
    public Float getScoreWriting() {
        return scoreWriting;
    }

    public void setScoreWriting(Float scoreWriting) {
        this.scoreWriting = scoreWriting;
    }

    @Override
    public String toString() {
        return "Answersheet{" +
            "answersheetId=" + answersheetId +
            ", userId=" + userId +
            ", examId=" + examId +
            ", answerTrans=" + answerTrans +
            ", answerWriting=" + answerWriting +
            ", answerObj1=" + answerObj1 +
            ", answerObj2=" + answerObj2 +
            ", answerObj3=" + answerObj3 +
            ", answerObj4=" + answerObj4 +
            ", answerObj5=" + answerObj5 +
            ", answerObj6=" + answerObj6 +
            ", answerObj7=" + answerObj7 +
            ", answerObj8=" + answerObj8 +
            ", answerObj9=" + answerObj9 +
            ", answerObj10=" + answerObj10 +
            ", answerObj11=" + answerObj11 +
            ", answerObj12=" + answerObj12 +
            ", answerObj13=" + answerObj13 +
            ", answerObj14=" + answerObj14 +
            ", answerObj15=" + answerObj15 +
            ", answerObj16=" + answerObj16 +
            ", answerObj17=" + answerObj17 +
            ", answerObj18=" + answerObj18 +
            ", answerObj19=" + answerObj19 +
            ", answerObj20=" + answerObj20 +
            ", scoreObj=" + scoreObj +
            ", scoreTrans=" + scoreTrans +
            ", scoreWriting=" + scoreWriting +
        "}";
    }
}
