package com.niuniushop.sjhj.nnshop.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.ui.fragment.CategorizeFragment;
import com.niuniushop.sjhj.nnshop.ui.fragment.IndexFragment;
import com.niuniushop.sjhj.nnshop.ui.fragment.ShoppingFragment;
import com.niuniushop.sjhj.nnshop.ui.fragment.UserFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/9/6.
 */

public class MyTabFragmentAdapter extends FragmentStatePagerAdapter {

    private static HashMap<String, Fragment> fragments;

    public static String[] tabs;

    public MyTabFragmentAdapter(Context ccontext, FragmentManager fm) {
        super(fm);
        tabs = ccontext.getResources().getStringArray(R.array.tab);
        fragments = new HashMap<String, Fragment>();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /*
    * 顶部tab的实现
    * */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                fragment = new IndexFragment();
                break;
            case 1:
                fragment = new CategorizeFragment();
                bundle.putInt("type", 0);
                break;
            case 2:
                fragment = new IndexFragment();
                bundle.putInt("type", 1);

                break;
            case 3:
                fragment = new CategorizeFragment();
                bundle.putInt("type", 2);
                break;
            case 4:
                fragment = new IndexFragment();
                bundle.putInt("type", 3);

                break;
            case 5:
                fragment = new CategorizeFragment();
                break;
            default:
                fragment = new IndexFragment();
        }
        fragment.setArguments(bundle);
        fragments.put(String.valueOf(position), fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }


    public static Fragment getFragment(int position) {
        return fragments.get(String.valueOf(position));
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
