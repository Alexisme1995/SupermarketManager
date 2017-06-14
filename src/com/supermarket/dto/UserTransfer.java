package com.supermarket.dto;

import com.supermarket.domains.User;

/**
 * Created by student on 2017/6/9.
 */
public class UserTransfer {
    private Integer userId; //用户ID，唯一，主键，取到创建用户的时间并转换为用户ID,number
    private String userName; //用户名(用来登陆的)，必填,varchar2
    private String userPwd; //用户密码，必填,varchar2
    private Integer userSex; //用户性别,(1:男,0:女)number
    private Integer userYear; //用户年龄，必填number
    private String userPhone; //用户电话，必填varchar2
    private String userAddress; //用户地址,varchar2
    private Integer userRank; //用户权限，自选必填(0:普通用户，1:经理)number

    //是否为当前用户
    private Integer isCurrentUser;
    //当前用户权限等级
    private Integer currentUserRank;


    public UserTransfer(Integer userId, Integer currentuserRank, User user) {
        setUserId(user.getUserId());
        setUserName(user.getUserName());
        setUserAddress(user.getUserAddress());
        setUserPhone(user.getUserPhone());
        setUserPwd(user.getUserPwd());
        setUserSex(user.getUserSex().intValue());
        setUserRank(user.getUserRank().intValue());
        setUserYear(user.getUserYear());
        setCurrentUserRank(currentuserRank);
        if (userId.intValue() == user.getUserId().intValue()) {
            setIsCurrentUser(1);
        } else {
            setIsCurrentUser(0);
        }

    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserYear() {
        return userYear;
    }

    public void setUserYear(Integer userYear) {
        this.userYear = userYear;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    public Integer getIsCurrentUser() {
        return isCurrentUser;
    }

    public void setIsCurrentUser(Integer isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
    }

    public Integer getCurrentUserRank() {
        return currentUserRank;
    }

    public void setCurrentUserRank(Integer currentUserRank) {
        this.currentUserRank = currentUserRank;
    }
}
