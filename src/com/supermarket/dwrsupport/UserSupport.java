package com.supermarket.dwrsupport;

import com.supermarket.domains.User;
import com.supermarket.dto.UserTransfer;
import com.supermarket.interfaces.UserService;
import com.supermarket.utils.Page;
import org.directwebremoting.WebContextFactory;
import org.hibernate.mapping.Array;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7/007.
 */
public class UserSupport {
    private UserService service;


    /**
     * 获取查询后用户数据的方法
     * @param uname 用户名
     * @param size 每页加载多少条数据
     * @return 用户数据
     */
    public List<User> getQueryUsers(String uname,Integer size){
        List all = new ArrayList();
        Page page = new Page();
        Long queryCount = service.getQueryUserCount(uname);
        page.setColunmCount(queryCount.intValue());
        page.setSIZE(size);
        List<User> userList = service.getQueryUserTagPage(uname,0,size);
        Iterator<int[]> it = page.getPages().iterator();
        List start = new ArrayList();
        List max = new ArrayList();
        if(it.hasNext()){
            int[] temp = it.next();
            start.add(temp[0]);
            max.add(temp[1]);
        }
        all.add(userList);
        all.add(start);
        all.add(max);
        return all;
    }

    /**
     * 加载查询后用户数据的方法
     * @param uname 用户名
     * @param start 开始位置
     * @param max 加载数量
     * @return 用户数据
     */
    public List<User> getQueryUsers_etc(String uname,Integer start,Integer max){
        List<User> list = service.getQueryUserTagPage(uname,start,max);
        return list;
    }
    /**
     * 查询所有用户
     *
     * @return 用户集合
     */
    public List<User> getUsers() {
        List<User> list;
        list = service.QueryAllUsers();
        return list;
    }

    public UserTransfer QueryUserById(Integer userId) {
        User user = service.QueryUserById(userId);
        //获取Session
        HttpSession session = WebContextFactory.get().getHttpServletRequest().getSession();
        //从Session获取当前用户
        User currentUser = (User) session.getAttribute("user");
        UserTransfer transfer = new UserTransfer(currentUser.getUserId(), currentUser.getUserRank().intValue(), user);
        return transfer;
    }

    /**
     * 获取每页的用户对象
     * @param start 开始位置
     * @param count 加载数量
     * @return 用户对象
     */
    public List<User> QueryUserPages(Integer start,Integer count){
        List<User> list = service.getUserPage(start,count);
        return list;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }
}
