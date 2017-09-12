package com.niuniushop.sjhj.nnshop.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.model.MainMenuModel;
import com.niuniushop.sjhj.nnshop.ui.viewholder.MainMenuViewHolder;

/**
 * Created by Administrator on 2017/9/12.
 */

public class MainMenuAdapter extends RecyclerArrayAdapter<MainMenuModel> {


    public MainMenuAdapter(Context context) {
        super(context);


    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

        return new MainMenuViewHolder(parent);
    }
}
