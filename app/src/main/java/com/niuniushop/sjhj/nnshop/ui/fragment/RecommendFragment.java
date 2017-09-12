package com.niuniushop.sjhj.nnshop.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.adapter.RecommendAdapter;
import com.niuniushop.sjhj.nnshop.model.ProuctDataModel;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;
import com.niuniushop.sjhj.nnshop.model.RecommendModel;
import com.niuniushop.sjhj.nnshop.ui.db.DBManager;
import com.niuniushop.sjhj.nnshop.ui.viewholder.RollViewPagerItemView;

import org.w3c.dom.Text;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/9/6.
 */
/*
*
*   该Fragment中
*   有ListView  （Adapter Required）
*
* */
public class RecommendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecommendAdapter mRecommendAdapter;

    private EasyRecyclerView mRecyclerView;

    private GridLayoutManager mGridLayoutManager;

    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecommendAdapter = new RecommendAdapter(getActivity());
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mGridLayoutManager.setSpanSizeLookup(mRecommendAdapter.obtainTipSpanSizeLookUp());
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (EasyRecyclerView) view.findViewById(R.id.easy_recyclerview);
        mRecyclerView.setErrorView(R.layout.view_net_error);
        mRecommendAdapter.addHeader(new RollViewPagerItemView(mRecyclerView.getSwipeToRefresh()));

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapterWithProgress(mRecommendAdapter);

        mRecyclerView.setRefreshListener(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        }, 1000);
        return view;
    }

    @Override
    public void onRefresh() {
        //取数据
        RecommendModel.getProductsDataFromNet(new Subscriber<List<ProuctDataModel>>() {
            @Override
            public void onCompleted() {
                mRecyclerView.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "xxNoNetWork!!", Toast.LENGTH_SHORT).show();
                if (mRecommendAdapter.getCount() == 0) {
                    mRecyclerView.showError();
                }
                mRecyclerView.setRefreshing(false);
            }

            @Override
            public void onNext(List<ProuctDataModel> prouctDataModels) {
                DBManager.getManager(getContext()).removeAllProducts();
                DBManager.getManager(getContext()).addAllProducts(prouctDataModels);
                getDataFromCache(true);
            }
        });


    }

    private void getDataFromCache(final boolean isUpadate) {
        RecommendModel.getProductsFromDB(getContext()).subscribe(new Action1<List<RecommendContentModel>>() {
            @Override
            public void call(List<RecommendContentModel> listDatas) {
                if (listDatas.size() != 0 && listDatas != null) {
                    if (isUpadate && mRecommendAdapter.getCount() != 0) {
                        mRecommendAdapter.clear();
                    }
                    mRecommendAdapter.addAll(listDatas);
                    if (mRecyclerView != null && !isUpadate) {
                        mRecyclerView.setRefreshing(true);

                    }
                }
            }
        });
    }
}
