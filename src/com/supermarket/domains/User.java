package com.supermarket.domains;

import java.util.Set;

/**
 * Created by Administrator on 2017/6/6/006.
 */
public class User {
    private Integer userId; //用户ID，唯一，主键，取到创建用户的时间并转换为用户ID,number
    private String userName; //用户名(用来登陆的)，必填,varchar2
    private String userPwd; //用户密码，必填,varchar2
    private Byte userSex; //用户性别,(1:男,0:女)number
    private Integer userYear; //用户年龄，必填number
    private String userPhone; //用户电话，必填varchar2
    private String userAddress; //用户地址,varchar2
    private Byte userRank; //用户权限，自选必填(0:普通用户，1:经理)number
    private Set<Logs> forMylogs;
    private Set<Logs> myLogs;
    private Set<Oxalis> oxalis;

    public Set<Oxalis> getOxalis() {
        return oxalis;
    }

    public void setOxalis(Set<Oxalis> oxalis) {
        this.oxalis = oxalis;
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

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
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

    public Byte getUserRank() {
        return userRank;
    }

    public void setUserRank(Byte userRank) {
        this.userRank = userRank;
    }

    public Set<Logs> getForMylogs() {
        return forMylogs;
    }

    public void setForMylogs(Set<Logs> forMylogs) {
        this.forMylogs = forMylogs;
    }

    public Set<Logs> getMyLogs() {
        return myLogs;
    }

    public void setMyLogs(Set<Logs> myLogs) {
        this.myLogs = myLogs;
    }
}
