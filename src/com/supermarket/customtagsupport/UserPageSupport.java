package com.supermarket.customtagsupport;

import com.supermarket.interfaces.UserService;
import com.supermarket.utils.Page;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 2017/6/13.
 */
public class UserPageSupport extends SimpleTagSupport {
    private Integer size;
    private Page page;

    @Override
    public void doTag() throws JspException, IOException {
        WebApplicationContext  webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        UserService userService = (UserService)webApplicationContext.getBean("userService");
        page = new Page(size,userService.getUserCount().intValue());
        List<int[]> pageInt = page.getPages();
        JspWriter out = getJspContext().getOut();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < pageInt.size();i++){
            int[] temp = pageInt.get(i);
            sb.append("<a href=\"javascript:loadUsers(" + temp[0] + "," + temp[1] + ")\">");
            sb.append("第" + (i + 1) + "页");
            sb.append("</a>&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        out.println(sb.toString());
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
