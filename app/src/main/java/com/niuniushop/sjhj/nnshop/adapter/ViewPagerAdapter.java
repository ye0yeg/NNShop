package com.niuniushop.sjhj.nnshop.adapter;

import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/5.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<SimpleDraweeView> list;

    public ViewPagerAdapter(ArrayList<SimpleDraweeView> views) {
        this.list = views;
    }


    @Override
    public int getCount() {
        if (list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /*
    * 销毁位置界面
    * */

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
    /*
    * 初始化界面
    * */

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position), 0);
        return list.get(position);
    }
}
