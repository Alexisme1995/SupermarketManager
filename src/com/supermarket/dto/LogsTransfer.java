package com.supermarket.dto;

import com.supermarket.domains.Logs;
import com.supermarket.utils.transformDate;

/**
 * Created by student on 2017/6/8.
 */
public class LogsTransfer {
    private Integer logID;//ID
    private String logDetails;//具体记录
    private String logTime;//记录时间
    private String logIP;//操作者IP

    //DTP for users
    private Integer opID;//操作者id
    private String opName;//操作者用户名
    private Integer targetID;//目标用户ID
    private String targetName;//目标用户名
    //DTO for oxalis
    private Integer targetOxlID;//目标账单ID
    private String targetOxlName;//目标账单商品名
    //DTO for serve
    private Integer targetServeID;//目标供应商ID
    private String targetServeName;//目标供应商名

    public LogsTransfer(Logs logs) {
        //操作者
        if (logs.getLogOP() != null) {
            setOpID(logs.getLogOP().getUserId());
            setOpName(logs.getLogOP().getUserName());
        }
        //目标对象
        if (logs.getLogTargetUser() != null) {
            setTargetID(logs.getLogTargetUser().getUserId());
            setTargetName(logs.getLogTargetUser().getUserName());
        }
        //供应商
        if (logs.getLogServe() != null) {
            setTargetServeID(logs.getLogServe().getServeId());
            setTargetServeName(logs.getLogServe().getServeName());
        }
        //账单
        if (logs.getLogTargetOxalis() != null) {
            setTargetOxlID(logs.getLogTargetOxalis().getOlsId());
            setTargetOxlName(logs.getLogTargetOxalis().getOlsWarename());
        }

        setLogDetails(logs.getLogDetails());
        setLogTime(transformDate.transformTime(logs.getLogTime()));
        setLogIP(logs.getLogIP());
    }

    public LogsTransfer() {
    }

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getLogIP() {
        return logIP;
    }

    public void setLogIP(String logIP) {
        this.logIP = logIP;
    }

    public Integer getOpID() {
        return opID;
    }

    public void setOpID(Integer opID) {
        this.opID = opID;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public Integer getTargetID() {
        return targetID;
    }

    public void setTargetID(Integer targetID) {
        this.targetID = targetID;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Integer getTargetOxlID() {
        return targetOxlID;
    }

    public void setTargetOxlID(Integer targetOxlID) {
        this.targetOxlID = targetOxlID;
    }

    public String getTargetOxlName() {
        return targetOxlName;
    }

    public void setTargetOxlName(String targetOxlName) {
        this.targetOxlName = targetOxlName;
    }

    public Integer getTargetServeID() {
        return targetServeID;
    }

    public void setTargetServeID(Integer targetServeID) {
        this.targetServeID = targetServeID;
    }

    public String getTargetServeName() {
        return targetServeName;
    }

    public void setTargetServeName(String targetServeName) {
        this.targetServeName = targetServeName;
    }
}
