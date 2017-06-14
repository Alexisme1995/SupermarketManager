package com.supermarket.customtagsupport;

import com.supermarket.domains.User;
import com.supermarket.interfaces.LogService;
import com.supermarket.interfaces.UserService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 海鸥_令主 on 2017/6/11/011.
 */
public class UserSelectSupport extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        //获取输出流
        JspWriter out = getJspContext().getOut();
        //获取服务类
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        UserService service = (UserService) webApplicationContext.getBean("userService");
        //获取User集合
        List<User> users = service.QueryAllUsers();
        Iterator<User> iterator = users.iterator();
        StringBuilder sb = new StringBuilder("<select id=\"logs_user_id\" type=\"text\" name=\"userName\" class=\"myselect\">");
        while (iterator.hasNext()) {
            User temp = iterator.next();
            sb.append("<option value=\"" + temp.getUserId() + "\">" + temp.getUserName() + "</option>");
        }
        sb.append("</select>");
        out.print(sb.toString());
    }
}
