package com.niuniushop.sjhj.nnshop.ui.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RollViewPagerItemView implements com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter.ItemView {

    private Context context;

    public RollViewPagerItemView(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText("dd");

        return tv;
    }

    @Override
    public void onBindView(View headerView) {

    }
}
