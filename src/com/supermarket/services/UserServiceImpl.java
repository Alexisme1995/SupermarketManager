package com.supermarket.services;

import com.supermarket.domains.Logs;
import com.supermarket.domains.Oxalis;
import com.supermarket.domains.User;
import com.supermarket.interfaces.LogService;
import com.supermarket.interfaces.UserService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.sql.Update;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/6/006.
 */

public class UserServiceImpl extends HibernateDaoSupport implements UserService {
    private LogService logService;

    /**
     * 修改密码
     *
     * @param current 当前用户
     * @param user    目标用户
     * @param IP      修改者IP
     */
    @Override
    public void changePwd(User current, User user, String IP) {
        Session session = getSession();
        User temp = (User) session.get(User.class, user.getUserId());
        temp.setUserPwd(user.getUserPwd());
        //记录日志
        logService.userLog(current, user, "修改密码", IP);
        //与数据库同步
        session.flush();
        session.clear();
    }

    /**
     * 删除用户
     *
     * @param current 操作者
     * @param user    目标用户
     * @param IP      操作者IP
     */
    @Override
    public void deleteUser(User current, User user, String IP) {
        //调用删除方法
        Session session = getSession();
        User targetuser = (User) session.get(User.class, user.getUserId());
        //获取所有与当前用户相关的内容
        session.delete(targetuser);
        //记录日志
        //目标引用必须设置成null,不然无法删除
        logService.userLog(current, null, "删除了用户ID:" + targetuser.getUserId() + "用户名:" + targetuser.getUserName(), IP);
        //与数据库同步
        session.flush();
        session.clear();
    }

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return null:登录失败  User:成功
     */
    @Override
    public User login(String username, String password, String IP) {
        Session session = getSession();
        Query query = session.createQuery("from User u where u.userName=? and u.userPwd=?");
        query.setString(0, username);
        query.setString(1, password);
        List list = (List) query.list();
        //获取登陆的用户
        if (list.size() == 0) {
            logService.userLog(null, null, "尝试以账号[" + username + "] , 密码[" + password + "] 登录,失败!", IP);
        } else {
            User user = (User) list.get(0);
            //记录日志
            logService.userLog(user, null, "登录成功", IP);

        }
        //与数据库同步
        session.flush();
        session.clear();
        //返回用户
        if (list.size() == 0) {
            return null;
        } else {
            User user = (User) list.get(0);
            return user;
        }
    }

    /**
     * 添加用户方法
     *
     * @param current 当前操作用户
     * @param user    目标用户
     * @param IP      操作IP
     */
    @Override
    public void addUser(User current, User user, String IP) {
        Session session = getSession();
        session.save(user);
        //记录日志
        String rankTemp;
        //调用intValue才能比较
        if (user.getUserRank().intValue() == 0) {
            rankTemp = "经理管理员";
        } else {
            rankTemp = "普通用户组";
        }
        logService.userLog(current, user, "添加一名" + rankTemp + "成员", IP);
        //与数据库同步
        session.flush();
        session.clear();
    }


    /**
     * 更新用户信息的方法
     *
     * @param current 当前操作者对象
     * @param target  更改用户对象
     * @param IP      操作者IP
     */
    @Override
    public void changedetails(User current, User target, String IP) {
        Session session = getSession();
        User u = (User) session.get(User.class, target.getUserId());
        u.setUserPwd(target.getUserPwd());
        u.setUserYear(target.getUserYear());
        u.setUserSex(target.getUserSex());
        u.setUserAddress(target.getUserAddress());
        u.setUserName(target.getUserName());
        u.setUserPhone(target.getUserPhone());
        u.setUserRank(target.getUserRank());
        session.update(u);
        //与数据库同步
        session.flush();
        session.clear();
        //记录日志
        logService.userLog(current, target, "用户信息被修改", IP);
    }

    /**
     * 查询所有用户方法
     *
     * @return 所有用户集
     */
    @Override
    public List<User> QueryAllUsers() {
        Session session = getSession();
        Query query = session.createQuery("from User");
        List<User> list = (List<User>) query.list();
        //与数据库同步
        session.flush();
        session.clear();
        return list;
    }

    /**
     * 根据用户ID查询用户数据的方法
     *
     * @param userId 用户id
     * @return 用户对象
     */
    @Override
    public User QueryUserById(Integer userId) {
        Session session = getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, userId);
        //与数据库同步
        session.flush();
        session.clear();
        return user;
    }

    /**
     * 用户数据分页方法
     * @param startP 开始位置
     * @param count  数量
     * @return 分页用户集合
     */
    @Override
    public List<User> getUserPage(Integer startP, Integer count) {
        Session session = getSession();
        Query query = session.createQuery("from User");
        query.setFirstResult(startP);
        query.setMaxResults(count);
        List<User> list = (List<User>) query.list();
        return list;
    }

    /**
     * 查询用户总数量的方法
     * @return 用户总数量
     */
    @Override
    public Long getUserCount() {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("SELECT COUNT(*) from USERS ");
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count;
    }

    /**
     * 查询获取用户的数量的方法
     * @param uname 用户名
     * @return 用户数量
     */
    @Override
    public Long getQueryUserCount(String uname) {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery("SELECT COUNT(*) from USERS where userName LIKE :Uname");
        query.setString("Uname","%"+uname+"%");
        Long count = Long.parseLong(query.uniqueResult().toString());
        return count;
    }

    /**
     * 查询后获取用户数据的方法
     * @param uname 用户名
     * @param start 开始位置
     * @param max 加载数量
     * @return 用户数据
     */
    @Override
    public List<User> getQueryUserTagPage(String uname, Integer start, Integer max) {
        Session session = getSession();
        Query query = session.createQuery("FROM User u where u.userName like :uName");
        query.setString("uName","%"+uname+"%");
        query.setFirstResult(start);
        query.setMaxResults(max);
        List<User> list = (List<User>)query.list();
        return list;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
