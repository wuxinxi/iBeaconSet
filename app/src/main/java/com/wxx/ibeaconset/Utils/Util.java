package com.wxx.ibeaconset.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Tangren_ on 2017/4/21 0021.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class Util {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * long型时间转换
     *
     * @param s
     * @return
     */
    public static String long2Date(long s) {
        Date date = new Date(s*1000L);
        return format.format(date);
    }

    /**
     * 设备ID激活状态
     *
     * @param status
     * @return
     */
    public static String getStatus(int status) {
        if (status == 0)
            return "未激活";
        else if (status == 1)
            return "已激活";
        else return "未知";
    }


}
