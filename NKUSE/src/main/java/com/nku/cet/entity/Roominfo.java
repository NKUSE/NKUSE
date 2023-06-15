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

    private String roomId;

    private String roomName;

    private String place;

    private Integer capacityTotal;

    private Integer capacityNow;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
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
    public Integer getCapacityTotal() {
        return capacityTotal;
    }

    public void setCapacityTotal(Integer capacityTotal) {
        this.capacityTotal = capacityTotal;
    }
    public Integer getCapacityNow() {
        return capacityNow;
    }

    public void setCapacityNow(Integer capacityNow) {
        this.capacityNow = capacityNow;
    }

    @Override
    public String toString() {
        return "Roominfo{" +
            "roomId=" + roomId +
            ", roomName=" + roomName +
            ", place=" + place +
            ", capacityTotal=" + capacityTotal +
            ", capacityNow=" + capacityNow +
        "}";
    }
}
