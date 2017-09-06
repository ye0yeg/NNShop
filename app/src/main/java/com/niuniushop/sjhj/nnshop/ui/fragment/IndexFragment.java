package com.niuniushop.sjhj.nnshop.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.config.Config;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2017/9/6.
 */

public class IndexFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());

        SimpleDraweeView sdv = new SimpleDraweeView(getActivity());
        sdv.setImageResource(Config.pics[1]);

        tv.setText("IndexFragment");


        return sdv;
    }
}
