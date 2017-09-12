package com.niuniushop.sjhj.nnshop.ui.viewholder;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.adapter.MainMenuAdapter;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RecommendListViewHolder extends BaseViewHolder<RecommendContentModel> implements View.OnClickListener {

    private SimpleDraweeView menuView;
    private TextView title;
    private RecommendContentModel data;


    public RecommendListViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend_list);
        title = $(R.id.menu_title);
        menuView =$(R.id.menu_img);

        menuView.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(),"点击了"+data.getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(RecommendContentModel data) {
        super.setData(data);
        this.data = data;
        menuView.setImageResource(R.mipmap.buy);
        title.setText("title");
    }
}
