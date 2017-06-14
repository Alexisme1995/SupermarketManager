package com.supermarket.domains;

import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/6/006.
 */
public class Serve {
    private Integer serveId;// 供应商ID,唯一
    private String serveName;// 供应商名称，必填，varchar2
    private String servePicture;// 供应商描述，varchar2
    private String serveRelation;// 供应商联系人，varchar2
    private String servePhone;// 供应商电话，number
    private String serveAddress;// 供应商地址，varchar2
    private Date createtime;//创建时间
    private Set<Oxalis> oxalis;
    private Set<Logs> logs;

    public Set<Logs> getLogs() {
        return logs;
    }

    public void setLogs(Set<Logs> logs) {
        this.logs = logs;
    }

    public Set<Oxalis> getOxalis() {
        return oxalis;
    }

    public void setOxalis(Set<Oxalis> oxalis) {
        this.oxalis = oxalis;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getServeId() {
        return serveId;
    }

    public void setServeId(Integer serveId) {
        this.serveId = serveId;
    }

    public String getServeName() {
        return serveName;
    }

    public void setServeName(String serveName) {
        this.serveName = serveName;
    }

    public String getServePicture() {
        return servePicture;
    }

    public void setServePicture(String servePicture) {
        this.servePicture = servePicture;
    }

    public String getServeRelation() {
        return serveRelation;
    }

    public void setServeRelation(String serveRelation) {
        this.serveRelation = serveRelation;
    }

    public String getServePhone() {
        return servePhone;
    }

    public void setServePhone(String servePhone) {
        this.servePhone = servePhone;
    }

    public String getServeAddress() {
        return serveAddress;
    }

    public void setServeAddress(String serveAddress) {
        this.serveAddress = serveAddress;
    }

}