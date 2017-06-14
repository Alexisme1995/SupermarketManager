package com.supermarket.customtagsupport;

import com.supermarket.domains.User;
import com.supermarket.interfaces.LogService;
import com.supermarket.utils.Page;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8/008.
 */
public class PageTagSupport extends SimpleTagSupport {
    private Integer size;
    private Page pageObj;

    @Override
    public void doTag() throws JspException, IOException {
        //webApplicationContext
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        //获取服务类
            LogService service = (LogService) webApplicationContext.getBean("logService");
        //获取Session
        PageContext page = (PageContext) this.getJspContext();
        HttpSession session = page.getSession();
        User currentUser = (User) session.getAttribute("user");
        pageObj = new Page();
        pageObj.setColunmCount((service.LogsCount(currentUser)).intValue());
        pageObj.setSIZE(size);
        List<int[]> pages = pageObj.getPages();
        JspWriter out = getJspContext().getOut();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pages.size(); i++) {
            int[] temp = pages.get(i);

            sb.append("<a href=\"javascript:loadHistory(" + temp[0] + "," + temp[1] + ")\">");
            sb.append("第" + (i + 1) + "页");
            sb.append("</a>&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        out.println(sb.toString());
    }

    public Page getPageObj() {
        return pageObj;
    }

    public void setPageObj(Page pageObj) {
        this.pageObj = pageObj;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
