package com.niuniushop.sjhj.nnshop.ui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.config.Config;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;
import com.niuniushop.sjhj.nnshop.model.RecommendModel;
import com.niuniushop.sjhj.nnshop.ui.activity.MainActivity;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RecommendTipVewHolder extends BaseViewHolder<RecommendContentModel> implements View.OnClickListener {

    //文字
    private TextView tip;
    private Button btn;
    private RecommendContentModel data;

    public RecommendTipVewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend_tip);
        tip = $(R.id.recommend_tip);
        btn = $(R.id.recommend_btn);
    }

    @Override
    public void setData(RecommendContentModel data) {
        super.setData(data);
        this.data = data;
        tip.setText(data.getTip());
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        for (int i = 0; i < Config.recommdendTips.length; i++) {
//            if (data.getTip().equals(Config.recommdendTips[i])) {
//                MainActivity.staticViewPager.setCurrentItem(i + 1);
//                break;
//            }
//        }

        Toast.makeText(getContext(), " 点击了第" + data.getTip(), Toast.LENGTH_SHORT).show();
    }
}
