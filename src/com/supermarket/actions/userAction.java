package com.supermarket.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.supermarket.domains.User;
import com.supermarket.interfaces.UserService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Alex on 2017/6/7.
 */
public class userAction extends ActionSupport implements SessionAware, ServletRequestAware {
    private UserService us;
    //添加用户表单信息
    private String add_user_name;
    private String add_user_password;
    private Integer add_user_sex;
    private Integer add_user_age;
    private String add_user_phone;
    private String add_user_address;
    private Integer add_user_power;


    //修改用户表单信息
    private Integer targetUserId;
    private String detail_user_password;
    private Integer detail_user_sex;
    private Integer detail_user_age;
    private String detail_user_phone;
    private String detail_user_address;
    private Integer detail_user_power;
    private String detail_user_name;

    //修改密码表单信息
    private String userNewPwd;
    private Integer changeTarget;


    private Map session;
    private HttpServletRequest request;
    //错误信息提示
    private String errorTitle;
    private String errorContext;

    public String addUser() throws Exception {
        User user = new User();
        user.setUserName(add_user_name);
        user.setUserPwd(add_user_password);
        user.setUserSex(add_user_sex.byteValue());
        user.setUserYear(add_user_age);
        user.setUserPhone(add_user_phone);
        user.setUserAddress(add_user_address);
        user.setUserRank(add_user_power.byteValue());
        //获取当前用户
        User currentUser = (User) session.get("user");
        String IP = request.getRemoteAddr();
        //保存数据
        us.addUser(currentUser, user, IP);
        return SUCCESS;
    }

    /**
     * 修改用户信息操作
     *
     * @return 返回地址
     * @throws Exception
     */
    public String changeUser() throws Exception {
        //获取当前用户
        User currentUser = (User) session.get("user");
        String IP = request.getRemoteAddr();
        if (currentUser.getUserRank() == 0 || currentUser.getUserId() == getTargetUserId()) {
            User user = new User();
            //用户ID
            user.setUserId(getTargetUserId());
            //
            user.setUserAddress(getDetail_user_address());
            user.setUserName(getDetail_user_name());
            user.setUserPhone(getDetail_user_phone());
            //普通用户修改自己信息，强制修改权限等级为普通用户
            if (currentUser.getUserRank() == 1) {
                user.setUserRank(Byte.parseByte("1"));
            } else {//允许经理用户修改权限
                user.setUserRank(getDetail_user_power().byteValue());
            }
            user.setUserYear(getDetail_user_age());
            user.setUserSex(getDetail_user_sex().byteValue());
            //调用服务类修改信息
            us.changedetails(currentUser, user, IP);
            return SUCCESS;
        } else {
            setErrorTitle("修改失败");
            setErrorContext("您的权限不足");
            return ERROR;
        }
    }

    /**
     * 删除用户操作
     *
     * @return
     */
    public String deleteUser() {
        //获取当前用户以及IP地址
        User currentUser = (User) session.get("user");
        String IP = request.getRemoteAddr();
        //权限不足
        User targetUser = new User();
        targetUser.setUserId(getTargetUserId());
        //判断权限
        if (currentUser.getUserRank().intValue() == 0) {
            us.deleteUser(currentUser, targetUser, IP);
            return SUCCESS;
        } else {
            setErrorTitle("修改失败");
            setErrorContext("您的权限不足");
            return ERROR;
        }
    }

    /**
     * 修改密码
     *
     * @return
     */
    public String changeUserPW() {
        //获取当前用户以及IP地址
        User currentUser = (User) session.get("user");
        String IP = request.getRemoteAddr();
        //权限不足
        User targetUser = new User();
        targetUser.setUserId(getChangeTarget());
        targetUser.setUserPwd(getUserNewPwd());
        //判断权限
        if (currentUser.getUserRank().intValue() == 0 || currentUser.getUserId().intValue() == getChangeTarget()) {
            //System.out.println("ID:[" + getChangeTarget() + "] 密码[" + getUserNewPwd() + "]");
            //调用服务类修改密码
            us.changePwd(currentUser, targetUser, IP);
            return SUCCESS;
        } else {
            setErrorTitle("修改失败");
            setErrorContext("您的权限不足");
            return ERROR;
        }
    }


    public UserService getUs() {
        return us;
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

    public void setUs(UserService us) {
        this.us = us;
    }

    public String getAdd_user_name() {
        return add_user_name;
    }

    public void setAdd_user_name(String add_user_name) {
        this.add_user_name = add_user_name;
    }

    public String getAdd_user_password() {
        return add_user_password;
    }

    public void setAdd_user_password(String add_user_password) {
        this.add_user_password = add_user_password;
    }

    public Integer getAdd_user_age() {
        return add_user_age;
    }

    public void setAdd_user_age(Integer add_user_age) {
        this.add_user_age = add_user_age;
    }

    public String getAdd_user_phone() {
        return add_user_phone;
    }

    public void setAdd_user_phone(String add_user_phone) {
        this.add_user_phone = add_user_phone;
    }

    public String getAdd_user_address() {
        return add_user_address;
    }

    public void setAdd_user_address(String add_user_address) {
        this.add_user_address = add_user_address;
    }

    public Map getSession() {
        return session;
    }

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Integer getAdd_user_sex() {
        return add_user_sex;
    }

    public void setAdd_user_sex(Integer add_user_sex) {
        this.add_user_sex = add_user_sex;
    }

    public Integer getAdd_user_power() {
        return add_user_power;
    }

    public void setAdd_user_power(Integer add_user_power) {
        this.add_user_power = add_user_power;
    }

    public String getDetail_user_password() {
        return detail_user_password;
    }

    public void setDetail_user_password(String detail_user_password) {
        this.detail_user_password = detail_user_password;
    }

    public Integer getDetail_user_sex() {
        return detail_user_sex;
    }

    public void setDetail_user_sex(Integer detail_user_sex) {
        this.detail_user_sex = detail_user_sex;
    }

    public Integer getDetail_user_age() {
        return detail_user_age;
    }

    public void setDetail_user_age(Integer detail_user_age) {
        this.detail_user_age = detail_user_age;
    }

    public String getDetail_user_phone() {
        return detail_user_phone;
    }

    public void setDetail_user_phone(String detail_user_phone) {
        this.detail_user_phone = detail_user_phone;
    }

    public String getDetail_user_address() {
        return detail_user_address;
    }

    public void setDetail_user_address(String detail_user_address) {
        this.detail_user_address = detail_user_address;
    }

    public Integer getDetail_user_power() {
        return detail_user_power;
    }

    public void setDetail_user_power(Integer detail_user_power) {
        this.detail_user_power = detail_user_power;
    }

    public String getDetail_user_name() {
        return detail_user_name;
    }

    public void setDetail_user_name(String detail_user_name) {
        this.detail_user_name = detail_user_name;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    public String getUserNewPwd() {
        return userNewPwd;
    }

    public void setUserNewPwd(String userNewPwd) {
        this.userNewPwd = userNewPwd;
    }

    public Integer getChangeTarget() {
        return changeTarget;
    }

    public void setChangeTarget(Integer changeTarget) {
        this.changeTarget = changeTarget;
    }

}
