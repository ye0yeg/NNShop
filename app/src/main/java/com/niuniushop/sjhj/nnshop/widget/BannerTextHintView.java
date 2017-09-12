package com.niuniushop.sjhj.nnshop.widget;

import android.content.Context;

import com.jude.rollviewpager.HintView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.niuniushop.sjhj.nnshop.model.BannerDataModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class BannerTextHintView extends TextHintView {
    List<BannerDataModel> bannerDataModels;

    public BannerTextHintView(Context context, List<BannerDataModel> bannerDataModels) {
        super(context);
        this.bannerDataModels = bannerDataModels;
    }

    @Override
    public void setCurrent(int current) {
        setText(" " + (current + 1) + "/" + bannerDataModels.size() + "  " + bannerDataModels.get(current).getIntroduce());
    }
}
