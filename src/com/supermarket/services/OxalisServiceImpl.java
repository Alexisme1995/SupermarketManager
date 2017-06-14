package com.supermarket.services;

import com.supermarket.domains.Oxalis;
import com.supermarket.domains.Serve;
import com.supermarket.domains.User;
import com.supermarket.interfaces.LogService;
import com.supermarket.interfaces.OxalisService;
import oracle.jdbc.ttc7.O3log;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.web.context.request.SessionScope;

import java.util.List;

/**
 * Created by Alex on 2017/6/7.
 */
public class OxalisServiceImpl extends HibernateDaoSupport implements OxalisService {
    private LogService logService;

    /**
     * 查询所有账单方法
     *
     * @return 所有账单对象的集合
     */
    @Override
    public List<Oxalis> QueryAllOxalis() {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Oxalis");
        List<Oxalis> list = (List<Oxalis>) query.list();
        session.getTransaction().commit();
        return list;
    }

    /**
     * 根据账单ID查询账单方法
     *
     * @param olsId 账单ID
     * @return 账单对象
     */
    @Override
    public Oxalis QueryOxalisById(Integer olsId) {
        Session session = getSession();
        session.beginTransaction();
        Oxalis ols = (Oxalis) session.get(Oxalis.class, olsId);
        session.flush();
        session.clear();
        return ols;
    }

    /**
     * 更改账单信息的方法
     *
     * @param current 当前用户对象
     * @param ols     新账单对象
     * @param IP      操作IP
     */
    @Override
    public void updateOxalis(User current, Oxalis ols, String IP) {
        //获取serveId
        int servid = ols.getServeId().getServeId();
        Session session = getSession();
        //加载Oxalis对象
        Oxalis oxalis = (Oxalis) session.get(Oxalis.class, ols.getOlsId());
        //加载Serve对象
        Serve serve = (Serve) session.get(Serve.class, servid);
        oxalis.getServeId().getServeId();
        oxalis.setOlsMoney(ols.getOlsMoney());
        oxalis.setOlsNunber(ols.getOlsNunber());
        oxalis.setOlsUnit(ols.getOlsUnit());
        oxalis.setOlsWarename(ols.getOlsWarename());
        oxalis.setOlsPicture(ols.getOlsPicture());
        //修改Serve
        oxalis.setServeId(serve);
        oxalis.setOlsPayment(ols.getOlsPayment());
        session.update(oxalis);

        logService.oxalisLog(current, ols, "更改账单信息", IP);
        session.flush();
        session.clear();
    }

    /**
     * 添加账单的方法
     *
     * @param current 当前用户对象
     * @param ols     账单对象
     * @param IP      操作IP
     */
    @Override
    public void addOxalis(User current, Oxalis ols, String IP) {
        Session session = getSession();
        //加载Serve对象
        Serve serve = (Serve) session.get(Serve.class, ols.getServeId().getServeId());
        ols.setServeId(serve);
        session.save(ols);
        session.flush();
        session.clear();
        logService.oxalisLog(current, ols, "添加一张账单", IP);
    }

    /**
     * 删除账单方法
     *
     * @param current 当前用户对象
     * @param ols     账单对象
     * @param IP      操作IP
     */
    @Override
    public void deleteOxalis(User current, Oxalis ols, String IP) {
        Session session = getSession();
        Oxalis targetOls = (Oxalis) session.get(Oxalis.class, ols.getOlsId());
        //删除前将用户外链与供应商外链解除
        targetOls.setUserId(null);
        targetOls.setServeId(null);
        session.delete(targetOls);
        logService.oxalisLog(current, null, "删除" + targetOls.getOlsWarename() + "账单", IP);
        session.flush();
        session.clear();
    }

    /**
     * 获取所有账单数量的方法
     *
     * @return 账单数量
     */
    @Override
    public Long getOlsCount() {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("SELECT COUNT(*) from Oxalis");
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count;
    }

    /**
     * 查询分页账单对象的方法
     *
     * @param start 开始位置
     * @param count 加载数量
     * @return 分页账单对象
     */
    @Override
    public List<Oxalis> QueryOlsByPage(Integer start, Integer count) {
        Session session = getSession();
        Query query = session.createQuery("from Oxalis ");
        query.setFirstResult(start);
        query.setMaxResults(count);
        List<Oxalis> list = (List<Oxalis>) query.list();
        return list;
    }

    /**
     * 获取查询后的账单数量
     *
     * @param olsName  账单名称
     * @param olsIsPay 账单付款情况
     * @return 查询后账单数量
     */
    @Override
    public Long getQueryOlsCount(String olsName, Integer olsIsPay) {
        Session session = getSession();
        Byte olsisPayByte = olsIsPay.byteValue();
        SQLQuery query;
        if(olsIsPay == 2){
            query = session.createSQLQuery("SELECT COUNT(*) from Oxalis WHERE olsWarename LIKE :olsname");
        }else{
            query = session.createSQLQuery("SELECT COUNT(*) from Oxalis WHERE olsWarename LIKE :olsname and olsPayment = :olsIsPay ");
            query.setByte("olsIsPay", olsisPayByte);
        }
        query.setString("olsname", "%" + olsName + "%");
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count;
    }

    /**
     * 查询后的账单分页对象
     *
     * @param olsName  账单名称
     * @param olsIsPay 账单是否已付款
     * @param start    开始位置
     * @param max      加载数量
     * @return 账单分页对象
     */
    @Override
    public List<Oxalis> getQueryOlsTagPage(String olsName, Integer olsIsPay, Integer start, Integer max) {
        Session session = getSession();
        Byte olsisPayByte = olsIsPay.byteValue();
        Query query;
        if(olsIsPay == 2){
            query = session.createQuery("FROM Oxalis o where o.olsWarename like :olsname");
        }else {
            query = session.createQuery("FROM Oxalis o where o.olsWarename like :olsname and o.olsPayment = :olsispay");
            query.setByte("olsispay", olsisPayByte);
        }
        query.setString("olsname", "%" + olsName + "%");
        query.setFirstResult(start);
        query.setMaxResults(max);
        List<Oxalis> list = query.list();
        return list;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
