package com.niuniushop.sjhj.nnshop.model;

import com.niuniushop.sjhj.nnshop.config.Config;

/**
 * Created by Administrator on 2017/9/6.
 */

public class TabModel {
    public static String[] getTabTexts() {
        if (Config.tabs.length > 0) {
            return Config.tabs;
        }
        return null;
    }


    /*
    * 获取tab图标
    * */
    public static int[] getTabImgs() {
        if (Config.tabImgs.length > 0) {
            return Config.tabImgs;
        }
        return null;
    }

    /*
    * fragment 工厂类型
    * */
    public static Class[] getFragments() {
        if (Config.tabClass.length > 0) {
            return Config.tabClass;
        }
        return null;
    }
}
