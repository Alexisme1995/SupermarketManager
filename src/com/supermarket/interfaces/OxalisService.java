package com.supermarket.interfaces;

import com.supermarket.domains.Oxalis;
import com.supermarket.domains.User;

import java.util.List;

/**
 * Created by Alex on 2017/6/7.
 */
public interface OxalisService {
    public List<Oxalis> QueryAllOxalis();

    public Oxalis QueryOxalisById(Integer olsId);

    public void updateOxalis(User current,Oxalis ols,String IP);

    public void addOxalis(User current,Oxalis ols,String IP);

    public void deleteOxalis(User current,Oxalis ols,String IP);

    public Long getOlsCount();

    public List<Oxalis> QueryOlsByPage(Integer start,Integer count);

    public Long getQueryOlsCount(String olsName,Integer olsIsPay);

    public List<Oxalis>  getQueryOlsTagPage(String olsName,Integer olsIsPay,Integer start,Integer max);
}
