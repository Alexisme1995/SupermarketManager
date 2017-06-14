package com.supermarket.customtagsupport;

import com.supermarket.interfaces.ServeService;
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
public class ServePageSupport extends SimpleTagSupport {
    private Integer size;
    private Page page;

    @Override
    public void doTag() throws JspException, IOException {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServeService ss = (ServeService)webApplicationContext.getBean("serveService") ;
        page = new Page(size,ss.getServeCount().intValue());
        List<int[]> list = page.getPages();
        JspWriter out = getJspContext().getOut();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size() ; i++){
            int[] temp = list.get(i);
            sb.append("<a href=\"javascript:loadServe(" + temp[0] + "," + temp[1] + ")\">");
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
