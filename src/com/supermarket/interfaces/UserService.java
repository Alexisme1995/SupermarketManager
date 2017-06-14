package com.supermarket.interfaces;

import com.supermarket.domains.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6/006.
 */
public interface UserService {
    public User login(String username, String password, String IP);

    public void addUser(User current, User user, String IP);

    public void changedetails(User current, User target, String IP);

    public List<User> QueryAllUsers();

    public User QueryUserById(Integer userId);

    public void deleteUser(User current, User user, String IP);

    public void changePwd(User current, User user, String IP);

    public List<User> getUserPage(Integer startP,Integer count);

    public Long getUserCount();

    public Long getQueryUserCount(String uname);

    public List<User> getQueryUserTagPage(String uname,Integer start,Integer max);
}
