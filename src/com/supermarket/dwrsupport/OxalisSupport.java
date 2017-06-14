package com.supermarket.dwrsupport;

import com.supermarket.domains.Oxalis;
import com.supermarket.domains.Serve;
import com.supermarket.dto.OxalisTransfer;
import com.supermarket.interfaces.OxalisService;
import com.supermarket.interfaces.ServeService;
import com.supermarket.utils.Page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 2017/6/7.
 */
public class OxalisSupport {
    private OxalisService os;
    private ServeService ss;


    /**
     * 查询账单分页方法
     *
     * @param olsName  账单名
     * @param olsIsPay 账单付款情况
     * @param size     分页大小
     * @return List(账单数据集合, 开始位置集合, 加载数量集合)
     */
    public List<Oxalis> getOxalisQueryPage(String olsName, Integer olsIsPay, Integer size) {
        List all = new ArrayList();
        Page page = new Page();
        List<OxalisTransfer> list = new ArrayList<OxalisTransfer>();
        Long queryCount = os.getQueryOlsCount(olsName, olsIsPay);
        page.setSIZE(size);
        page.setColunmCount(queryCount.intValue());
        List<Oxalis> oxalislist = os.getQueryOlsTagPage(olsName, olsIsPay, 0, size);
        for (int i = 0; i < oxalislist.size(); i++) {
            //调用DTO进行转换.
            OxalisTransfer transfer = new OxalisTransfer(oxalislist.get(i));
            list.add(transfer);
        }
        Iterator<int[]> it = page.getPages().iterator();
        List start = new ArrayList();
        List max = new ArrayList();
        while (it.hasNext()) {
            int[] temp = it.next();
            start.add(temp[0]);
            max.add(temp[1]);
        }
        ;
        all.add(list);
        all.add(start);
        all.add(max);
        return all;
    }

    /**
     * 查询后dwr分页按钮翻页方法
     *
     * @param olsName  账单名
     * @param olsIsPay 账单付款清理
     * @param start    开始位置
     * @param max      加载数量
     * @return 账单对象集合
     */
    public List<OxalisTransfer> getOxalisQueryPage_etc(String olsName, Integer olsIsPay, Integer start, Integer max) {
        List<OxalisTransfer> list = new ArrayList<OxalisTransfer>();
        List<Oxalis> oxalislist = os.getQueryOlsTagPage(olsName, olsIsPay, start, max);
        for (int i = 0; i < oxalislist.size(); i++) {
            //调用DTO进行转换.
            OxalisTransfer transfer = new OxalisTransfer(oxalislist.get(i));
            list.add(transfer);
        }
        return list;
    }

    /**
     * 查询所有账单
     *
     * @return 所有账单对象集合
     */
    public List<OxalisTransfer> getOxalis() {
        List<OxalisTransfer> list = new ArrayList<OxalisTransfer>();
        List<Oxalis> oxalislist = os.QueryAllOxalis();
        //循环输出
        for (int i = 0; i < oxalislist.size(); i++) {
            //调用DTO进行转换.
            OxalisTransfer transfer = new OxalisTransfer(oxalislist.get(i));
            list.add(transfer);
        }
        //返回集合
        return list;
    }

    /**
     * 查询所有分页后的账单
     *
     * @param start 开始位置
     * @param count 加载数量
     * @return 分页后的账单对象
     */
    public List<OxalisTransfer> getOxalisByPage(Integer start, Integer count) {
        List<OxalisTransfer> list = new ArrayList<OxalisTransfer>();
        List<Oxalis> oxalislist = os.QueryOlsByPage(start, count);
        //循环输出
        for (int i = 0; i < oxalislist.size(); i++) {
            //调用DTO进行转换.
            OxalisTransfer transfer = new OxalisTransfer(oxalislist.get(i));
            list.add(transfer);
        }
        //返回集合
        return list;
    }

    /**
     * 根据账单ID查询账单
     *
     * @param olsId 账单ID
     * @return 账单对象
     */
    public OxalisTransfer QueryOxalisById(Integer olsId) {
        Oxalis ols = os.QueryOxalisById(olsId);
        List<Serve> serve = ss.QueryAllServe();
        OxalisTransfer transfer = new OxalisTransfer(ols, serve);
        return transfer;
    }

    public OxalisService getOs() {
        return os;
    }

    public void setOs(OxalisService os) {
        this.os = os;
    }

    public ServeService getSs() {
        return ss;
    }

    public void setSs(ServeService ss) {
        this.ss = ss;
    }
}
