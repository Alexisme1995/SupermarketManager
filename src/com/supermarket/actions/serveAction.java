package com.supermarket.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.supermarket.domains.Serve;
import com.supermarket.domains.User;
import com.supermarket.interfaces.ServeService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by Alex on 2017/6/11.
 */
public class serveAction extends ActionSupport implements SessionAware, ServletRequestAware {
    private ServeService ss;

    private String add_provider_name;
    private String add_provider_discription;
    private String add_provider_contect;
    private String add_provider_phone;
    private String add_provider_address;

    private Integer detail_provider_Num;
    private String detail_provider_name;
    private String detail_provider_discription;
    private String detail_provider_contect;
    private String detail_provider_phone;
    private String detail_provider_address;

    private String errorTitle;
    private String errorContext;

    private Map session;
    private HttpServletRequest request;

    /**
     * 更改供应商信息方法
     * @throws Exception
     */
    public String updateServe() throws Exception {
        //获取当前用户
        User changer = (User)session.get("user");
        String IP = request.getRemoteAddr();
        if(changer.getUserRank() == 0){
            Serve s = new Serve();
            s.setServeId(getDetail_provider_Num());
            s.setServeName(getDetail_provider_name());
            s.setServePicture(getDetail_provider_discription());
            s.setServeRelation(getDetail_provider_contect());
            s.setServePhone(getDetail_provider_phone());
            s.setServeAddress(getDetail_provider_address());
            ss.updateServe(changer,s,IP);
            return SUCCESS;
        }
        setErrorTitle("修改失败");
        setErrorContext("您的权限不足");
        return ERROR;
    }

    /**
     * 添加新供应商的方法
     * @throws Exception
     */
    public String addServe() throws Exception{
        //获取当前用户
        User adder = (User)session.get("user");
        String IP = request.getRemoteAddr();
        if(adder.getUserRank()==0){
            Serve s = new Serve();
            s.setServeName(getAdd_provider_name());
            s.setServeAddress(getAdd_provider_address());
            s.setServePicture(getAdd_provider_discription());
            s.setServePhone(getAdd_provider_phone());
            s.setServeRelation(getAdd_provider_contect());
            s.setCreatetime(new Date());
            ss.addServe(adder,s,IP);
            return SUCCESS;
        }
            setErrorTitle("添加失败");
            setErrorContext("您的权限不足");
            return ERROR;
    }

    public String deleteServe() throws Exception{
        //获取当前用户
        User deleter = (User) session.get("user");
        String IP = request.getRemoteAddr();
        if(deleter.getUserRank() == 0){
            Serve s = new Serve();
            s.setServeId(getDetail_provider_Num());
            s.setServeName(getDetail_provider_name());
            s.setServePicture(getDetail_provider_discription());
            s.setServeRelation(getDetail_provider_contect());
            s.setServePhone(getDetail_provider_phone());
            s.setServeAddress(getDetail_provider_address());
            ss.deleteServe(deleter,s,IP);
            return SUCCESS;
        }
        setErrorTitle("删除失败");
        setErrorContext("您的权限不足");
        return ERROR;
    }
    public String getDetail_provider_name() {
        return detail_provider_name;
    }

    public void setDetail_provider_name(String detail_provider_name) {
        this.detail_provider_name = detail_provider_name;
    }

    public String getDetail_provider_discription() {
        return detail_provider_discription;
    }

    public void setDetail_provider_discription(String detail_provider_discription) {
        this.detail_provider_discription = detail_provider_discription;
    }

    public String getDetail_provider_contect() {
        return detail_provider_contect;
    }

    public void setDetail_provider_contect(String detail_provider_contect) {
        this.detail_provider_contect = detail_provider_contect;
    }

    public String getDetail_provider_phone() {
        return detail_provider_phone;
    }

    public void setDetail_provider_phone(String detail_provider_phone) {
        this.detail_provider_phone = detail_provider_phone;
    }

    public String getDetail_provider_address() {
        return detail_provider_address;
    }

    public void setDetail_provider_address(String detail_provider_address) {
        this.detail_provider_address = detail_provider_address;
    }

    public Integer getDetail_provider_Num() {
        return detail_provider_Num;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorContext() {
        return errorContext;
    }

    public void setErrorContext(String errorContext) {
        this.errorContext = errorContext;
    }

    public void setDetail_provider_Num(Integer detail_provider_Num) {
        this.detail_provider_Num = detail_provider_Num;
    }

    public String getAdd_provider_name() {
        return add_provider_name;
    }

    public void setAdd_provider_name(String add_provider_name) {
        this.add_provider_name = add_provider_name;
    }

    public String getAdd_provider_discription() {
        return add_provider_discription;
    }

    public void setAdd_provider_discription(String add_provider_discription) {
        this.add_provider_discription = add_provider_discription;
    }

    public String getAdd_provider_contect() {
        return add_provider_contect;
    }

    public void setAdd_provider_contect(String add_provider_contect) {
        this.add_provider_contect = add_provider_contect;
    }

    public String getAdd_provider_phone() {
        return add_provider_phone;
    }

    public void setAdd_provider_phone(String add_provider_phone) {
        this.add_provider_phone = add_provider_phone;
    }

    public String getAdd_provider_address() {
        return add_provider_address;
    }

    public void setAdd_provider_address(String add_provider_address) {
        this.add_provider_address = add_provider_address;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public ServeService getSs() {
        return ss;
    }

    public void setSs(ServeService ss) {
        this.ss = ss;
    }
}
