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
@TableName("cet6_enum_usertype")
public class EnumUsertype implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer type;

    private String description;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EnumUsertype{" +
            "type=" + type +
            ", description=" + description +
        "}";
    }
}
