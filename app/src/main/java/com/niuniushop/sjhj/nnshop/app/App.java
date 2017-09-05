package com.niuniushop.sjhj.nnshop.app;

import android.animation.ObjectAnimator;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

/**
 * Created by Administrator on 2017/9/5.
 */

public class App extends Application{
    //单例
    private static App singleton;

    public static App getApp(){
        if(singleton == null){
            synchronized (App.class){
                singleton = new App();
            }
        }
        return singleton;
    }

    //文件目录表
    public enum Dir{
        Object
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Application 中初始化两个工具
        //fb的图像加载工具
        Fresco.initialize(this);
        //oschina提供的utils工具包
        JUtils.initialize(this);

        JFileManager.getInstance().init(this,Dir.values());

    }
}
