package com.supermarket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by student on 2017/6/8.
 */
public class transformDate {
    public static String transformTime(Date ctime){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        return sdf.format(ctime);
    }
}
