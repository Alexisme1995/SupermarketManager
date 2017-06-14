package com.supermarket.domains;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/6/006.
 */
public class Logs {
    private Integer logID;//ID
    private User logOP;//操作者
    private User logTargetUser;//目标对象
    private Oxalis logTargetOxalis;//目标账单
    private Serve logServe;//目标供应商
    private String logDetails;//具体记录
    private Date logTime;//记录时间
    private String logIP;//操作者IP

    public String getLogIP() {
        return logIP;
    }

    public void setLogIP(String logIP) {
        this.logIP = logIP;
    }

    public Serve getLogServe() {
        return logServe;
    }

    public void setLogServe(Serve logServe) {
        this.logServe = logServe;
    }

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public User getLogOP() {
        return logOP;
    }

    public void setLogOP(User logOP) {
        this.logOP = logOP;
    }

    public User getLogTargetUser() {
        return logTargetUser;
    }

    public void setLogTargetUser(User logTargetUser) {
        this.logTargetUser = logTargetUser;
    }

    public Oxalis getLogTargetOxalis() {
        return logTargetOxalis;
    }

    public void setLogTargetOxalis(Oxalis logTargetOxalis) {
        this.logTargetOxalis = logTargetOxalis;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

}
