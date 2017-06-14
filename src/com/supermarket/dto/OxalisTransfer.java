package com.supermarket.dto;

import com.supermarket.domains.Oxalis;
import com.supermarket.domains.Serve;
import com.supermarket.interfaces.ServeService;
import com.supermarket.services.ServeServiceImpl;
import com.supermarket.utils.transformDate;

import java.util.List;

/**
 * Created by Alex on 2017/6/8.
 */
public class OxalisTransfer {
    private Integer olsId;// 账单ID，唯一，主键
    private Integer olsMoney;// 交易金额,必填number
    private String olsUnit;// 交易单位,varchar2
    private Integer olsNunber;// 交易数number
    private String olsWarename;// 商品名称，varchar2
    private String olsPicture;// 商品描述，vahrchar2
    private Byte olsPayment;// 是否付款，（0:未付款1:已付款）number
    private String createtime;//创建时间

    //DTO for Serve
    private Integer serveId;// 所属供应商Integer
    private String serveName;
    private List<Serve> allServe;//所有供应商集合
    //DTO for User
    private Integer userId;
    private String userName;

    /**
     * 构造方法赋值
     */
    public OxalisTransfer(Oxalis oxalis,List<Serve> serve) {
        setOlsId(oxalis.getOlsId());
        setOlsMoney(oxalis.getOlsMoney());
        setOlsUnit(oxalis.getOlsUnit());
        setOlsNunber(oxalis.getOlsNunber());
        setOlsWarename(oxalis.getOlsWarename());
        setOlsPicture(oxalis.getOlsPicture());
        setOlsPayment(oxalis.getOlsPayment());
        setCreatetime(transformDate.transformTime(oxalis.getCreatetime()));
        //DTO for Oxalis
        setServeId(oxalis.getServeId().getServeId());
        setServeName(oxalis.getServeId().getServeName());
        setAllServe(serve);
        //DTO for User
        setUserId(oxalis.getUserId().getUserId());
        setUserName(oxalis.getUserId().getUserName());
    }

    public OxalisTransfer(Oxalis oxalis) {
        setOlsId(oxalis.getOlsId());
        setOlsMoney(oxalis.getOlsMoney());
        setOlsUnit(oxalis.getOlsUnit());
        setOlsNunber(oxalis.getOlsNunber());
        setOlsWarename(oxalis.getOlsWarename());
        setOlsPicture(oxalis.getOlsPicture());
        setOlsPayment(oxalis.getOlsPayment());
        setCreatetime(transformDate.transformTime(oxalis.getCreatetime()));
        //DTO for Oxalis
        setServeId(oxalis.getServeId().getServeId());
        setServeName(oxalis.getServeId().getServeName());
        //DTO for User
        setUserId(oxalis.getUserId().getUserId());
        setUserName(oxalis.getUserId().getUserName());
    }

    public OxalisTransfer() {

    }
    public Integer getOlsId() {
        return olsId;
    }

    public void setOlsId(Integer olsId) {
        this.olsId = olsId;
    }

    public Integer getOlsMoney() {
        return olsMoney;
    }

    public void setOlsMoney(Integer olsMoney) {
        this.olsMoney = olsMoney;
    }

    public String getOlsUnit() {
        return olsUnit;
    }

    public void setOlsUnit(String olsUnit) {
        this.olsUnit = olsUnit;
    }

    public Integer getOlsNunber() {
        return olsNunber;
    }

    public void setOlsNunber(Integer olsNunber) {
        this.olsNunber = olsNunber;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
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

    public List<Serve> getAllServe() {
        return allServe;
    }

    public void setAllServe(List<Serve> allServe) {
        this.allServe = allServe;
    }
}
