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
@TableName("room_info")
public class RoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String examId;

    private String place;

    private String room;

    private Integer capacity;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RoomInfo{" +
            "examId=" + examId +
            ", place=" + place +
            ", room=" + room +
            ", capacity=" + capacity +
        "}";
    }
}
