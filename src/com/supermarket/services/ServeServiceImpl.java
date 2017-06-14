package com.supermarket.services;

import com.supermarket.domains.Oxalis;
import com.supermarket.domains.Serve;
import com.supermarket.domains.User;
import com.supermarket.interfaces.LogService;
import com.supermarket.interfaces.ServeService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Alex on 2017/6/7.
 */
public class ServeServiceImpl extends HibernateDaoSupport implements ServeService {
    private LogService logService;

    /**
     * 查询记录数量
     *
     * @param servName   供应商名
     * @param servDetail 详细信息
     * @return 记录数量
     */
    @Override
    public Long serveCompoundQueryCount(String servName, String servDetail) {
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(*) FROM Serve WHERE serveName LIKE :servname and servePicture LIKE :servedetail");
        sqlQuery.setString("servname", "%" + servName + "%");
        sqlQuery.setString("servedetail", "%" + servDetail + "%");
        //取第一条数据
        Long count = Long.parseLong(sqlQuery.uniqueResult().toString());
        return count;
    }

    /**
     * 供应商查询分页
     *
     * @param serveName   供应商名
     * @param serveDetail 详细信息
     * @param start       开始位置
     * @param max         结束位置
     * @return 结果集合
     */
    @Override
    public List<Serve> serveCompoundQueryInputPages(String serveName, String serveDetail, Integer start, Integer max) {
        Session session = getSession();
        Query query = session.createQuery("FROM Serve s where s.serveName like :sname and s.servePicture like :sdetails");
        query.setString("sname", "%" + serveName + "%");
        query.setString("sdetails", "%" + serveDetail + "%");
        query.setFirstResult(start);
        query.setMaxResults(max);
        List<Serve> list = query.list();
        return list;
    }

    /**
     * 查询所有供应商的方法
     *
     * @return 所有供应商对象
     */


    @Override
    public List<Serve> QueryAllServe() {
        Session session = getSession();
        Query query = session.createQuery("from Serve");
        List<Serve> list = (List<Serve>) query.list();
        session.flush();
        session.clear();
        return list;
    }

    /**
     * 根据供应商id查询供应商方法
     *
     * @param serveId 供应商ID
     * @return 供应商对象
     */
    @Override
    public Serve QueryServeById(Integer serveId) {
        Session session = getSession();
        Serve serve = (Serve) session.get(Serve.class, serveId);
        //与数据库同步
        session.flush();
        session.clear();
        return serve;
    }

    /**
     * 更新供应商信息的方法
     *
     * @param current 当前用户对象
     * @param serve   新供应商信息
     * @param IP      操作IP地址
     */
    @Override
    public void updateServe(User current, Serve serve, String IP) {
        Session session = getSession();
        Serve s = (Serve) session.get(Serve.class, serve.getServeId());
        s.setServeAddress(serve.getServeAddress());
        s.setServeName(serve.getServeName());
        s.setServePhone(serve.getServePhone());
        s.setServePicture(serve.getServePicture());
        s.setServeRelation(serve.getServeRelation());
        session.update(s);
        //与数据库同步
        session.flush();
        session.clear();
        //日志信息
        logService.serveLog(current, serve, "供应商信息被修改", IP);
    }

    /**
     * 添加供应商的方法
     *
     * @param current 当前用户对象
     * @param serve   供应商信息
     * @param IP      操作IP
     */
    @Override
    public void addServe(User current, Serve serve, String IP) {
        Session session = getSession();
        session.save(serve);
        session.flush();
        session.clear();
        logService.serveLog(current, serve, "添加一位供应商", IP);
    }

    /**
     * 删除供应商的方法
     *
     * @param current 当前用户对象
     * @param serve   供应商对象
     * @param IP      操作IP
     */
    @Override
    public void deleteServe(User current, Serve serve, String IP) {
        Session session = getSession();
        Serve targetServe = (Serve) session.get(Serve.class, serve.getServeId());
        session.delete(targetServe);
        logService.serveLog(current, null, "删除" + serve.getServeName() + "供应商", IP);
        session.flush();
        session.clear();
    }

    /**
     * 获取所有供应商数量的方法
     *
     * @return 所有供应商数量
     */
    @Override
    public Long getServeCount() {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("SELECT COUNT(*) from Serve");
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count;
    }

    /**
     * 获取供应商分页对象的方法
     *
     * @param start 开始位置
     * @param count 加载数量
     * @return 供应商分页对象
     */
    @Override
    public List<Serve> QueryServeByPage(Integer start, Integer count) {
        Session session = getSession();
        Query query = session.createQuery("from Serve");
        query.setFirstResult(start);
        query.setMaxResults(count);
        List<Serve> list = (List<Serve>) query.list();
        return list;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
