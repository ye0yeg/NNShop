package com.niuniushop.sjhj.nnshop.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;
import com.niuniushop.sjhj.nnshop.model.RecommendModel;
import com.niuniushop.sjhj.nnshop.ui.activity.MainActivity;
import com.niuniushop.sjhj.nnshop.ui.viewholder.RecommendContentViewHolder;
import com.niuniushop.sjhj.nnshop.ui.viewholder.RecommendListViewHolder;
import com.niuniushop.sjhj.nnshop.ui.viewholder.RecommendTipVewHolder;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RecommendAdapter extends RecyclerArrayAdapter<RecommendContentModel> {


    /*
    tip = 提示
    content =  内容
    list  = 列表
     */

    private int tip = 0;
    private int content = 1;
    private int list = 2;

    private FloatingActionButton fab;

    public RecommendAdapter(Context context) {
        super(context);
        //获取上浮点的引用
        this.fab = MainActivity.floatBtn;

    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == tip) {
            //顶部提示框和更多按钮
            return new RecommendTipVewHolder(parent);
        } else if (viewType == content) {

            return new RecommendContentViewHolder(parent);
        } else if (viewType == list) {

            return new RecommendTipVewHolder(parent);
//            return new RecommendListViewHolder(parent);
        }
        return null;
    }

    @Override
    public int getViewType(int position) {
//        if (position == getCount() - 1) {
//            if (fab.isShown()) {
//                fab.hide();
//            }
//        }
        if (getItem(position).isJudgeType()) {
            return tip;
        } else if (getItem(position).isListType()) {
            return list;
        } else {
            return content;
        }
    }



    public class TipSpanSizeLookUp extends GridSpanSizeLookup {

        public TipSpanSizeLookUp() {
            //列表默认2
            super(2);
        }

        @Override
        public int getSpanSize(int position) {
            if (position < getHeaderCount() || position >= getCount() + getHeaderCount()) {
                return 2;
            } else {
                if (getItem(position - 1).isJudgeType() || getItem(position - 1).isListType()) {
                    //该tip item 占2
                    return 2;
                } else {

                    return 1;
                }
            }
        }
    }

    public TipSpanSizeLookUp obtainTipSpanSizeLookUp() {
        return new TipSpanSizeLookUp();
    }
}
