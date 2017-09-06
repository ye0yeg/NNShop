package com.niuniushop.sjhj.nnshop.config;


import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.ui.fragment.CategorizeFragment;
import com.niuniushop.sjhj.nnshop.ui.fragment.IndexFragment;
import com.niuniushop.sjhj.nnshop.ui.fragment.ShoppingFragment;
import com.niuniushop.sjhj.nnshop.ui.fragment.UserFragment;

/**
 * Created by Administrator on 2017/9/5.
 */

public final class Config {

    /**
     * 引导界面本地图片
     */
    public static final int[] pics = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    /**
     * 引导界面服务器地址上的图片
     */
    public static final String[] pricUrls = {
            "http://pic.taopic.com/uploads/allimg/110910/2518-11091022502144-lp.jpg",
            "http://pic.taopic.com/uploads/allimg/110910/2518-11091022102134-lp.jpg",
            "http://pic.taopic.com/uploads/allimg/110910/2518-11091022401754-lp.jpg",
            "http://pic.taopic.com/uploads/allimg/110910/2518-110910220A748-lp.jpg"

    };
    /**
     * 底部导航菜单项
     */
    public static final String[] tabs = {
            "首页", "分类", "购物车", "我的"
    };


    /**
     * 底部导航菜单项对应的图片
     */
    public static final int[] tabImgs = {
            R.drawable.home, R.drawable.categorize, R.drawable.shop, R.drawable.user
    };


    /**
     * 底部导航菜单点击事件对应的fragment
     */
    public static final Class[] tabClass = {
            IndexFragment.class, CategorizeFragment.class, ShoppingFragment.class, UserFragment.class
    };

}
