package com.supermarket.services;

import com.supermarket.domains.Logs;
import com.supermarket.domains.Oxalis;
import com.supermarket.domains.Serve;
import com.supermarket.domains.User;
import com.supermarket.interfaces.LogService;
import com.supermarket.utils.IPLocationHandler;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7/007.
 */
public class LogServiceImpl extends HibernateDaoSupport implements LogService {
    @Override
    public List<Logs> logCompoundQueryInputPages(Integer uid, Date startTime, Date endTime, Integer start, Integer end) {
        //获取Session
        Session session = getSession();
        //创建query
        Query query = session.createQuery("from Logs logs where logs.logOP.userId=? and logs.logTime>:startdate and logs.logTime<:enddate order by logs.logTime DESC");
        //设置参数
        query.setInteger(0, uid);
        query.setTimestamp("startdate", startTime);
        query.setTimestamp("enddate", endTime);
        //设置限制
        query.setFirstResult(start);
        query.setMaxResults(end);
        //执行查询
        List<Logs> list = (List<Logs>) query.list();
        //返回集合
        return list;
    }

    @Override
    public List<Logs> logCompoundQuery(Integer uid, Date startTime, Date endTime) {
        //获取Session
        Session session = getSession();
        //创建query
        Query query = session.createQuery("from Logs logs where logs.logOP.userId=? and logs.logTime>:startdate and logs.logTime<:enddate order by logs.logTime DESC");
        //设置参数
        query.setInteger(0, uid);
        query.setTimestamp("startdate", startTime);
        query.setTimestamp("enddate", endTime);
        //执行查询
        List<Logs> list = (List<Logs>) query.list();
        //返回集合
        return list;
    }

    @Override
    public void oxalisLog(User user, Oxalis oxalis, String details, String IP) {
        //创建对象
        Logs log = new Logs();
        log.setLogTime(new Date());
        //添加关系
        log.setLogOP(user);
        log.setLogDetails(details);
        if (oxalis == null) {
            log.setLogTargetOxalis(null);
            log.setLogServe(null);
        } else {
            log.setLogTargetOxalis(oxalis);
            log.setLogServe(oxalis.getServeId());
        }
        log.setLogIP(IPLocationHandler.getIPLocation(IP));
        getHibernateTemplate().save(log);
    }

    @Override
    public void userLog(User currentUser, User target, String details, String IP) {
        //创建对象
        Logs log = new Logs();
        log.setLogTime(new Date());
        //添加关系
        log.setLogOP(currentUser);
        log.setLogTargetUser(target);
        log.setLogDetails(details);
        //调用工具类记录IP以及地理位置
        log.setLogIP(IPLocationHandler.getIPLocation(IP));
        //保存数据
        getHibernateTemplate().save(log);
    }

    @Override
    public void serveLog(User currentUser, Serve serve, String details, String IP) {
        //创建对象
        Logs log = new Logs();
        log.setLogTime(new Date());
        //添加关系
        log.setLogOP(currentUser);
        log.setLogServe(serve);
        log.setLogDetails(details);
        //调用工具类记录IP以及地理位置
        log.setLogIP(IPLocationHandler.getIPLocation(IP));
        //保存数据
        getHibernateTemplate().save(log);
    }

    /**
     * 查询所有操作记录的方法
     *
     * @return 所有操作记录集合
     */
    @Override
    public List<Logs> getAllLogs(Integer uid) {
        Session session = getSession();
        Query query = session.createQuery("from Logs logs where logs.logOP.userId=? order by logs.logTime DESC");
        query.setInteger(0, uid);
        List<Logs> list = (List<Logs>) query.list();
        return list;
    }

    @Override
    public List<Logs> getLogsPage(Integer uid, Integer startP, Integer count) {
        Session session = getSession();
        Query query = session.createQuery("from Logs logs where logs.logOP.userId=? order by logs.logTime DESC");
        query.setFirstResult(startP);
        query.setMaxResults(count);
        query.setInteger(0, uid);
        List<Logs> list = (List<Logs>) query.list();
        return list;
    }

    @Override
    public Long LogsCount(User user) {
        Session session = getSession();
        //使用HQL查询用户数量
        SQLQuery query = session.createSQLQuery("SELECT COUNT(*) FROM Logs where logOP=" + user.getUserId());
        //取第一条数据
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count;
    }
}
