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
@TableName("cet6_roominfo")
public class Roominfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roomId;

    private String roomName;

    private String place;

    private Integer capacity;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


    @Override
    public String toString() {
        return "Roominfo{" +
                "roomId=" + roomId +
                ", roomName=" + roomName +
                ", place=" + place +
                ", capacity=" + capacity +
                "}";
    }
}
