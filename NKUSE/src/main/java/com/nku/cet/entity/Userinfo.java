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
@TableName("cet6_userinfo")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userPhonenumber;

    private String userIdnumber;

    private Integer userRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }
    public String getUserIdnumber() {
        return userIdnumber;
    }

    public void setUserIdnumber(String userIdnumber) {
        this.userIdnumber = userIdnumber;
    }
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
            "userId=" + userId +
            ", userName=" + userName +
            ", userPassword=" + userPassword +
            ", userEmail=" + userEmail +
            ", userPhonenumber=" + userPhonenumber +
            ", userIdnumber=" + userIdnumber +
            ", userRole=" + userRole +
        "}";
    }
}
