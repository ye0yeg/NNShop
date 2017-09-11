package com.niuniushop.sjhj.nnshop.ui.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.utils.JFileManager;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.adapter.ImageLoopAdapter;
import com.niuniushop.sjhj.nnshop.app.App;
import com.niuniushop.sjhj.nnshop.model.BannerDataModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RollViewPagerItemView implements com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter.ItemView {

    RollPagerView mRollPagerView;

    ImageLoopAdapter mAdapter;

    private Context context;

    SwipeRefreshLayout mSwipeRefreshLayout;

    private static List<BannerDataModel> banner;

    JFileManager.Folder mFolder;

    public RollViewPagerItemView(SwipeRefreshLayout swipeRefreshLayout) {
       mSwipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        context =  parent.getContext();
        mFolder = JFileManager.getInstance().getFolder(App.Dir.Object);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_rollviewpager, parent, false);
        mRollPagerView = view.findViewById(R.id.roll_view_pager);


        return view;
    }

    @Override
    public void onBindView(View headerView) {

    }
}
