package com.niuniushop.sjhj.nnshop.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.model.BannerDataModel;
import com.niuniushop.sjhj.nnshop.ui.activity.MainActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class ImageLoopAdapter extends DynamicPagerAdapter {

    private List<BannerDataModel> mBannerDataModelList;
    private Context mContext;


    public ImageLoopAdapter(List<BannerDataModel> bannerDataModels) {
        this.mBannerDataModelList = bannerDataModels;
    }

    public ImageLoopAdapter(Context context) {
        mContext = context;
    }

    ;

    @Override
    public View getView(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_rollviewpager, null);
        SimpleDraweeView simpleDraweeView = view.findViewById(R.id.viewPager_img);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "轮播图：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        simpleDraweeView.setImageURI(Uri.parse(mBannerDataModelList.get(position).getImageUrl()));
        return view;
    }

    @Override
    public int getCount() {
        if (mBannerDataModelList != null) {
            return mBannerDataModelList.size();
        } else {
            return 0;
        }
    }

    /*
    * Little confuse about this methods
    * */
    public void setBannerDataModels(List<BannerDataModel> bannerDataModels) {
        this.mBannerDataModelList = bannerDataModels;
    }
}
