package com.niuniushop.sjhj.nnshop.ui.viewholder;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.utils.JFileManager;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.adapter.ImageLoopAdapter;
import com.niuniushop.sjhj.nnshop.app.App;
import com.niuniushop.sjhj.nnshop.model.BannerDataModel;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;
import com.niuniushop.sjhj.nnshop.model.RecommendModel;
import com.niuniushop.sjhj.nnshop.widget.BannerTextHintView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RollViewPagerItemView implements RecyclerArrayAdapter.ItemView {

    RollPagerView mRollPagerView;

    ImageLoopAdapter mAdapter;

     Context context;

    SwipeRefreshLayout mSwipeRefreshLayout;

    private static List<BannerDataModel> banner;

    JFileManager.Folder mFolder;

    public RollViewPagerItemView(SwipeRefreshLayout swipeRefreshLayout) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        context = parent.getContext();
        mFolder = JFileManager.getInstance().getFolder(App.Dir.Object);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_rollviewpager, parent, false);
        mRollPagerView = view.findViewById(R.id.roll_view_pager);
        mRollPagerView.getViewPager().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_MOVE:
                        mSwipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mSwipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });
        setData();
        return view;
    }

    private void setData() {
        //加载数据
        getBannerFromCache();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RecommendModel.getRecommendBanners(new Subscriber<List<BannerDataModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BannerDataModel> listDatas) {
                        mFolder.writeObjectToFile(listDatas, "banner");
                        mAdapter = new ImageLoopAdapter();
                        RollViewPagerItemView.this.banner = listDatas;
                        mAdapter.setBannerDataModels(listDatas);
                        mRollPagerView.setHintView(new BannerTextHintView(context,listDatas));
                        mRollPagerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        },1000);
    }

    private void getBannerFromCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                banner = (List<BannerDataModel>) mFolder.readObjectFromFile("banner");
                if (banner != null && banner.size() != 0) {
                    mRollPagerView.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new ImageLoopAdapter();
                            mAdapter.setBannerDataModels(banner);
                            mRollPagerView.setHintView(new BannerTextHintView(context, banner));
                            mRollPagerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onBindView(View headerView) {

    }
}
