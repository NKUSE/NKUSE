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
@TableName("ques_sub")
public class QuesSub implements Serializable {

    private static final long serialVersionUID = 1L;

    private String questionId;

    private String question;

    private Integer score;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuesSub{" +
            "questionId=" + questionId +
            ", question=" + question +
            ", score=" + score +
        "}";
    }
}
