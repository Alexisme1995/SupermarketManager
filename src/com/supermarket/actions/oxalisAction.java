package com.supermarket.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.supermarket.domains.Oxalis;
import com.supermarket.domains.Serve;
import com.supermarket.domains.User;
import com.supermarket.interfaces.OxalisService;
import com.supermarket.interfaces.ServeService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by Alex on 2017/6/11.
 */
public class oxalisAction extends ActionSupport implements SessionAware,ServletRequestAware {
    private OxalisService os;
    private ServeService ss;

    private Integer add_bill_money;
    private String add_bill_util;
    private Integer add_bill_count;
    private String add_bill_name;
    private String add_bill_discription;
    private Integer add_bill_belong_provider;
    private Byte add_bill_isPay;

    private Integer detail_bill_Num;
    private Integer detail_bill_money;
    private String detail_bill_util;
    private Integer detail_bill_count;
    private String detail_bill_name;
    private String detail_bill_discription;
    private Integer detail_bill_belong_provider;
    private Byte detail_bill_isPay;

    private Map session;
    private HttpServletRequest request;

    public String addOls() throws Exception{
        User adder = (User) session.get("user");
        String IP = request.getRemoteAddr();
        Oxalis ols = new Oxalis();
        ols.setOlsWarename(getAdd_bill_name());
        ols.setOlsPayment(getAdd_bill_isPay());
        ols.setOlsPicture(getAdd_bill_discription());
        ols.setOlsNunber(getAdd_bill_count());
        ols.setOlsUnit(getAdd_bill_util());
        ols.setUserId(adder);
        ols.setCreatetime(new Date());
        Serve s = new Serve();
        s.setServeId(getAdd_bill_belong_provider());
        ols.setServeId(s);
        ols.setOlsMoney(getAdd_bill_money());
        os.addOxalis(adder,ols,IP);
        return SUCCESS;
    }

    public String updateOls() throws Exception {
        User changer = (User) session.get("user");
        String IP = request.getRemoteAddr();
            Oxalis ols = new Oxalis();
            ols.setOlsId(getDetail_bill_Num());
            ols.setOlsMoney(getDetail_bill_money());
            ols.setOlsUnit(getDetail_bill_util());
            ols.setOlsNunber(getDetail_bill_count());
            ols.setOlsWarename(getDetail_bill_name());
            ols.setOlsPicture(getDetail_bill_discription());
            Serve s = new Serve();
            s.setServeId(getDetail_bill_belong_provider());
            ols.setServeId(s);
            ols.setOlsPayment(getDetail_bill_isPay());
            os.updateOxalis(changer,ols,IP);
            return SUCCESS;
    }

    /**
     * 删除账单方法
     * @throws Exception
     */
    public String deleteOls() throws Exception{
        User deleter = (User) session.get("user");
        String IP = request.getRemoteAddr();
        Oxalis ols = new Oxalis();
        ols.setOlsId(getDetail_bill_Num());
        os.deleteOxalis(deleter,ols,IP);
        return SUCCESS;
    }

    public Integer getAdd_bill_money() {
        return add_bill_money;
    }

    public void setAdd_bill_money(Integer add_bill_money) {
        this.add_bill_money = add_bill_money;
    }

    public String getAdd_bill_util() {
        return add_bill_util;
    }

    public void setAdd_bill_util(String add_bill_util) {
        this.add_bill_util = add_bill_util;
    }

    public Integer getAdd_bill_count() {
        return add_bill_count;
    }

    public void setAdd_bill_count(Integer add_bill_count) {
        this.add_bill_count = add_bill_count;
    }

    public String getAdd_bill_name() {
        return add_bill_name;
    }

    public void setAdd_bill_name(String add_bill_name) {
        this.add_bill_name = add_bill_name;
    }

    public String getAdd_bill_discription() {
        return add_bill_discription;
    }

    public void setAdd_bill_discription(String add_bill_discription) {
        this.add_bill_discription = add_bill_discription;
    }

    public Integer getAdd_bill_belong_provider() {
        return add_bill_belong_provider;
    }

    public void setAdd_bill_belong_provider(Integer add_bill_belong_provider) {
        this.add_bill_belong_provider = add_bill_belong_provider;
    }

    public Byte getAdd_bill_isPay() {
        return add_bill_isPay;
    }

    public void setAdd_bill_isPay(Byte add_bill_isPay) {
        this.add_bill_isPay = add_bill_isPay;
    }

    public Integer getDetail_bill_Num() {
        return detail_bill_Num;
    }

    public void setDetail_bill_Num(Integer detail_bill_Num) {
        this.detail_bill_Num = detail_bill_Num;
    }

    public Integer getDetail_bill_money() {
        return detail_bill_money;
    }

    public void setDetail_bill_money(Integer detail_bill_money) {
        this.detail_bill_money = detail_bill_money;
    }

    public String getDetail_bill_util() {
        return detail_bill_util;
    }

    public void setDetail_bill_util(String detail_bill_util) {
        this.detail_bill_util = detail_bill_util;
    }

    public Integer getDetail_bill_count() {
        return detail_bill_count;
    }

    public void setDetail_bill_count(Integer detail_bill_count) {
        this.detail_bill_count = detail_bill_count;
    }

    public String getDetail_bill_name() {
        return detail_bill_name;
    }

    public void setDetail_bill_name(String detail_bill_name) {
        this.detail_bill_name = detail_bill_name;
    }

    public String getDetail_bill_discription() {
        return detail_bill_discription;
    }

    public void setDetail_bill_discription(String detail_bill_discription) {
        this.detail_bill_discription = detail_bill_discription;
    }

    public Integer getDetail_bill_belong_provider() {
        return detail_bill_belong_provider;
    }

    public void setDetail_bill_belong_provider(Integer detail_bill_belong_provider) {
        this.detail_bill_belong_provider = detail_bill_belong_provider;
    }

    public Byte getDetail_bill_isPay() {
        return detail_bill_isPay;
    }

    public void setDetail_bill_isPay(Byte detail_bill_isPay) {
        this.detail_bill_isPay = detail_bill_isPay;
    }

    public ServeService getSs() {
        return ss;
    }

    public void setSs(ServeService ss) {
        this.ss = ss;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public OxalisService getOs() {
        return os;
    }

    public void setOs(OxalisService os) {
        this.os = os;
    }
}
