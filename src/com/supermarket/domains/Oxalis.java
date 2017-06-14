package com.supermarket.domains;

import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/6/006.
 */
public class Oxalis {
    private Integer olsId;// 账单ID，唯一，主键
    private User userId;// 创建这张账单的用户ID，链接user表,numberolsMoney;
    private Integer olsMoney;// 交易金额,必填number
    private String olsUnit;// 交易单位,varchar2
    private Integer olsNunber;// 交易数number
    private String olsWarename;// 商品名称，varchar2
    private String olsPicture;// 商品描述，vahrchar2
    private Serve serveId;// 所属供应商
    private Byte olsPayment;// 是否付款，（0:未付款1:已付款）number
    private Date createtime;//创建时间
    private Set<Logs> logs;

    public Serve getServeId() {
        return serveId;
    }

    public void setServeId(Serve serveId) {
        this.serveId = serveId;
    }

    public Integer getOlsNunber() {
        return olsNunber;
    }

    public void setOlsNunber(Integer olsNunber) {
        this.olsNunber = olsNunber;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getOlsId() {
        return olsId;
    }

    public void setOlsId(Integer olsId) {
        this.olsId = olsId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getOlsUnit() {
        return olsUnit;
    }

    public void setOlsUnit(String olsUnit) {
        this.olsUnit = olsUnit;
    }

    public String getOlsWarename() {
        return olsWarename;
    }

    public void setOlsWarename(String olsWarename) {
        this.olsWarename = olsWarename;
    }

    public String getOlsPicture() {
        return olsPicture;
    }

    public void setOlsPicture(String olsPicture) {
        this.olsPicture = olsPicture;
    }

    public Byte getOlsPayment() {
        return olsPayment;
    }

    public void setOlsPayment(Byte olsPayment) {
        this.olsPayment = olsPayment;
    }

    public Integer getOlsMoney() {
        return olsMoney;
    }

    public void setOlsMoney(Integer olsMoney) {
        this.olsMoney = olsMoney;
    }

    public Set<Logs> getLogs() {
        return logs;
    }

    public void setLogs(Set<Logs> logs) {
        this.logs = logs;
    }
}
