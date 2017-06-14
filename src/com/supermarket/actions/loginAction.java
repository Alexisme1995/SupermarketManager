package com.supermarket.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.supermarket.domains.User;
import com.supermarket.interfaces.UserService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6/006.
 */
public class loginAction extends ActionSupport implements SessionAware, ServletRequestAware {
    private UserService us;
    private String username;
    private String password;
    private Map session;
    private HttpServletRequest request;
    //错误信息提示
    private String errorTitle;
    private String errorContext;


    public String execute() throws Exception {
        String IP = request.getRemoteAddr();
        User u = us.login(username, password, IP);
        if (u == null) {
            setErrorTitle("登陆失败");
            setErrorContext("用户名或密码错误");
            return ERROR;
        } else {
            session.put("user",u);
            return SUCCESS;
        }
    }

    public UserService getUs() {
        return us;
    }

    public void setUs(UserService us) {
        this.us = us;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSession(Map map) {
        session = map;
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

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }
}
