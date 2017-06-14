package com.supermarket.interfaces;

import com.supermarket.domains.Logs;
import com.supermarket.domains.Oxalis;
import com.supermarket.domains.Serve;
import com.supermarket.domains.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7/007.
 */
public interface LogService {
    public void oxalisLog(User user, Oxalis oxalis, String details, String IP);

    public void userLog(User currentUser, User target, String details, String IP);

    public void serveLog(User currentUser, Serve serve, String details, String IP);

    public List<Logs> getAllLogs(Integer uid);

    public List<Logs> getLogsPage(Integer uid, Integer startP, Integer count);

    public Long LogsCount(User user);

    public List<Logs> logCompoundQuery(Integer uid, Date startTime, Date endTime);

    public List<Logs> logCompoundQueryInputPages(Integer uid, Date startTime, Date endTime, Integer start, Integer end);
}
