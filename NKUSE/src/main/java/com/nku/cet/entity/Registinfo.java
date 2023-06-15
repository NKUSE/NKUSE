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
@TableName("cet6_registinfo")
public class Registinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String registId;

    private String examId;

    private String userId;

    private String roomId;

    private Integer seatNumber;

    private Integer paid;

    private String answersheetId;

    private Float score;

    public String getRegistId() {
        return registId;
    }

    public void setRegistId(String registId) {
        this.registId = registId;
    }
    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }
    public String getAnswersheetId() {
        return answersheetId;
    }

    public void setAnswersheetId(String answersheetId) {
        this.answersheetId = answersheetId;
    }
    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Registinfo{" +
            "registId=" + registId +
            ", examId=" + examId +
            ", userId=" + userId +
            ", roomId=" + roomId +
            ", seatNumber=" + seatNumber +
            ", paid=" + paid +
            ", answersheetId=" + answersheetId +
            ", score=" + score +
        "}";
    }
}
