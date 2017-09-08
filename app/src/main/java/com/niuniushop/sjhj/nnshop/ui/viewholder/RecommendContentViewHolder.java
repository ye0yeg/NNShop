package com.niuniushop.sjhj.nnshop.ui.viewholder;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RecommendContentViewHolder extends BaseViewHolder<RecommendContentModel> implements View.OnClickListener {
    private SimpleDraweeView mSimpleDraweeView;
    private CardView mCardView;
    private TextView title;
    private TextView content;
    private RecommendContentModel data;

    public RecommendContentViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.itemview_recommend_content);
        mSimpleDraweeView = $(R.id.recomend_img);
        title =$(R.id.recommend_title);
        content = $(R.id.recommend_content);
        mCardView = $(R.id.recommend_cardview);
    }

    @Override
    public void setData(RecommendContentModel data) {
        super.setData(data);
        this.data = data;
        mSimpleDraweeView.setImageURI(Uri.parse(data.getImageUrl()));
        title.setText(data.getTitle());
        content.setText("money:"+data.getPrice());
        mCardView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(),"点击:" + data.getTitle(),Toast.LENGTH_SHORT).show();

    }
}
