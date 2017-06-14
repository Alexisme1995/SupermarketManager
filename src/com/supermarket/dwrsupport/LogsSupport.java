package com.supermarket.dwrsupport;

import com.supermarket.domains.Logs;
import com.supermarket.domains.User;
import com.supermarket.dto.LogsTransfer;
import com.supermarket.interfaces.LogService;
import com.supermarket.utils.Page;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.guice.spring.WebApplicationContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by student on 2017/6/8.
 */
public class LogsSupport {
    private LogService ls;

    /**
     * 获取所有操作记录的方法
     *
     * @return 所有操作记录集合
     */
    public List<LogsTransfer> getLogs(Integer start, Integer max) {
        HttpSession session = WebContextFactory.get().getHttpServletRequest().getSession();
        User u = (User) session.getAttribute("user");
        List<LogsTransfer> list = new ArrayList<LogsTransfer>();
        List<Logs> logslist = ls.getLogsPage(u.getUserId(), start, max);
        //循环输出
        for (int i = 0; i < logslist.size(); i++) {
            //调用DTO进行转换.
            Logs logs = logslist.get(i);
            LogsTransfer transfer = new LogsTransfer(logs);
            list.add(transfer);
        }
        //返回集合
        return list;
    }

    /**
     * 操作历史纪录组合查询
     *
     * @param uid           用戶ID
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     * @param size          分页大小
     * @return 查询结果
     */
    public List<LogsTransfer> logCompoundQuery(Integer uid, Date startDateTime, Date endDateTime, Integer size) {
        List<LogsTransfer> list = new ArrayList<LogsTransfer>();
        //查询数据
        List<Logs> logslist = ls.logCompoundQuery(uid, startDateTime, endDateTime);
        //循环输出
        for (int i = 0; i < logslist.size(); i++) {
            //调用DTO进行转换.
            Logs logs = logslist.get(i);
            LogsTransfer transfer = new LogsTransfer(logs);
            list.add(transfer);
            if (i >= size) {
                break;
            }
        }
        //创建分页对象
        Page pages = new Page();
        pages.setColunmCount(logslist.size());
        pages.setSIZE(size);
        //创建存储集合
        List all = new ArrayList();
        List<int[]> pagelist = pages.getPages();
        List<Integer> startList = new ArrayList<Integer>();
        List<Integer> endList = new ArrayList<Integer>();
        Iterator<int[]> it = pagelist.iterator();
        //读取数据
        while (it.hasNext()) {
            int[] temp = it.next();
            startList.add(temp[0]);
            endList.add(temp[1]);
        }
        //添加
        all.add(list);
        all.add(startList);
        all.add(endList);
        System.out.println(all);
        //返回集合
        return all;
    }

    /**
     * @param uid           用户id
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     * @param start         开始位置
     * @param max           数量
     * @return 查询结果
     */
    public List<LogsTransfer> logCompoundQueryInputPages(Integer uid, Date startDateTime, Date endDateTime, Integer start, Integer max) {
        List<LogsTransfer> list = new ArrayList<LogsTransfer>();
        //查询数据
        List<Logs> logslist = ls.logCompoundQueryInputPages(uid, startDateTime, endDateTime, start, max);

        //循环输出
        for (int i = 0; i < logslist.size(); i++) {
            //调用DTO进行转换.
            Logs logs = logslist.get(i);
            LogsTransfer transfer = new LogsTransfer(logs);
            list.add(transfer);
        }
        
        return list;
    }

    /**
     * 获取份额对象
     *
     * @param uid  用户ID
     * @param size 分页大小
     * @return 分页集合
     */
    public List<int[]> getPages(int uid, int size) {
        Page pages = new Page();
        pages.setSIZE(size);
        User user = new User();
        user.setUserId(uid);
        int count = ls.LogsCount(user).intValue();
        pages.setColunmCount(count);
        List<int[]> list = pages.getPages();
        List all = new ArrayList();
        all.add(list);
        all.add(list);
        return all;
    }

    public LogService getLs() {
        return ls;
    }

    public void setLs(LogService ls) {
        this.ls = ls;
    }
}
