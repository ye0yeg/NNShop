package com.niuniushop.sjhj.nnshop.utils;

/**
 * Created by Administrator on 2017/9/8.
 */

public class BaseUtils {

    /**
     * 去掉空格并把字符串变为小写
     *
     * @param str
     * @return
     */
    public static String tranLowCase(String str) {
        String string = str.replaceAll(" ", "");
        return string.toLowerCase();
    }

}
