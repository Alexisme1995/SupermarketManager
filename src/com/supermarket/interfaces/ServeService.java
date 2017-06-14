package com.supermarket.interfaces;

import com.supermarket.domains.Serve;
import com.supermarket.domains.User;

import java.util.List;

/**
 * Created by Alex on 2017/6/7.
 */
public interface ServeService {
    public List<Serve> QueryAllServe();

    public Serve QueryServeById(Integer serveId);

    public void updateServe(User current, Serve serve, String IP);

    public void addServe(User current, Serve serve, String IP);

    public void deleteServe(User current, Serve serve, String IP);

    public Long getServeCount();

    public List<Serve> QueryServeByPage(Integer start, Integer count);

    public Long serveCompoundQueryCount(String servName, String servDetail);

    public List<Serve> serveCompoundQueryInputPages(String serveName, String serveDetail, Integer start, Integer max);
}
