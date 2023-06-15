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
@TableName("cet6_obj_ques")
public class ObjQues implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer questionId;

    private String description;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private Integer answer;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ObjQues{" +
            "questionId=" + questionId +
            ", description=" + description +
            ", optionA=" + optionA +
            ", optionB=" + optionB +
            ", optionC=" + optionC +
            ", optionD=" + optionD +
            ", answer=" + answer +
        "}";
    }
}
