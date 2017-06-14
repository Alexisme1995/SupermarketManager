package com.supermarket.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hi_old on 2017/4/20.
 */
public class Page {
    //每页数量
    private int SIZE;
    private int ColunmCount;

    public Page(int SIZE, int colunmCount) {
        this.SIZE = SIZE;
        ColunmCount = colunmCount;
    }

    public Page() {
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public int getColunmCount() {
        return ColunmCount;
    }

    public void setColunmCount(int colunmCount) {
        ColunmCount = colunmCount;
    }

    /**
     * 获取分页信息
     *
     * @return 数组集合
     */
    public List<int[]> getPages() {
        List<int[]> list = new ArrayList<int[]>();
        //循环次数
        int times = ColunmCount / SIZE;
        //取余数
        int md = ColunmCount % SIZE;
        //有余数
        for (int i = 0; i < times; i++) {
            int[] t = {i * SIZE, SIZE};
            list.add(t);
        }
        if (md != 0) {
            int[] t = {times * SIZE, md};
            list.add(t);
        }
        return list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "SIZE=" + SIZE +
                ", ColunmCount=" + ColunmCount +
                '}';
    }
}
