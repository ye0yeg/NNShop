package com.niuniushop.sjhj.nnshop.ui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.model.MainMenuModel;
import com.niuniushop.sjhj.nnshop.model.RecommendContentModel;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2017/9/12.
 */

public class MainMenuViewHolder extends BaseViewHolder<RecommendContentModel> {

    private SimpleDraweeView cirView;
    private TextView title;

    private RecommendContentModel data;



    public MainMenuViewHolder(ViewGroup parent) {
        super(parent, R.layout.main_menu);
        cirView = $(R.id.civ_menu);
        title = $(R.id.tv_meni_title);
    }

    @Override
    public void setData(RecommendContentModel data) {
        super.setData(data);
        this.data= data;
        cirView.setImageResource(R.mipmap.buy);
        title.setText("ii0");
    }
}
