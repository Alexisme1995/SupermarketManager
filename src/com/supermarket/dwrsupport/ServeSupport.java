package com.supermarket.dwrsupport;

import com.supermarket.domains.Serve;
import com.supermarket.interfaces.ServeService;
import com.supermarket.utils.Page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by student on 2017/6/8.
 */
public class ServeSupport {
    private ServeService ss;

    /**
     * 获取所有供应商信息
     *
     * @return 所有供应商对象集合
     */
    public List<Serve> getServe() {
        List<Serve> list = ss.QueryAllServe();
        return list;
    }

    /**
     * 根据供应商ID查询供应商信息的方法
     *
     * @param serveId 供应商ID
     * @return 供应商对象
     */
    public Serve QueryServeById(Integer serveId) {
        Serve serve = ss.QueryServeById(serveId);
        return serve;
    }

    /**
     * 根据开始位置和加载数量获取分页对象
     *
     * @param start 开始位置
     * @param count 加载数量
     * @return 分页对象
     */
    public List<Serve> getServeByPage(Integer start, Integer count) {
        List<Serve> list = ss.QueryServeByPage(start, count);
        return list;
    }

    /**
     * 第一次加载调用方法
     *
     * @param serveName   供应商名
     * @param serveDetail 供应商详细信息
     * @param size        分页大小
     * @return 结果集(初次数据, 开始位置集合, 大小集合)
     */
    public List<Serve> serveCompoundQueryInputPages(String serveName, String serveDetail, Integer size) {
        //存储所有信息的集合
        List all = new ArrayList();
        Page page = new Page();
        //获取数量
        Long rowcount = ss.serveCompoundQueryCount(serveName, serveDetail);
        page.setSIZE(size);
        page.setColunmCount(rowcount.intValue());
        //查询具体数据
        List<Serve> serveList = ss.serveCompoundQueryInputPages(serveName, serveDetail, 0, size);

        Iterator<int[]> it = page.getPages().iterator();
        List start = new ArrayList();
        List max = new ArrayList();
        //读取数据
        while (it.hasNext()) {
            int[] temp = it.next();
            start.add(temp[0]);
            max.add(temp[1]);
        }
        //数据放置
        all.add(serveList);
        all.add(start);
        all.add(max);

        return all;
    }


    /**
     * 再次加载数据
     *
     * @param serveName   供应商名
     * @param serveDetail 供应商详细信息
     * @param start       开始位置
     * @param max         查询数量
     * @return 结果集(初次数据, 开始位置集合, 大小集合)
     */
    public List<Serve> serveCompoundQueryInputPages_ecc(String serveName, String serveDetail, Integer start, Integer max) {
        //查询具体数据
        List<Serve> serveList = ss.serveCompoundQueryInputPages(serveName, serveDetail, start, max);
        return serveList;
    }

    public ServeService getSs() {
        return ss;
    }

    public void setSs(ServeService ss) {
        this.ss = ss;
    }
}
