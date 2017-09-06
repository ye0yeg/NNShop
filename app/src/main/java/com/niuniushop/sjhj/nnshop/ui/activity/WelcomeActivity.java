package com.niuniushop.sjhj.nnshop.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.utils.JUtils;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.adapter.ViewPagerAdapter;
import com.niuniushop.sjhj.nnshop.config.Config;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/5.
 */

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.goComing)
    TextView goComing;

    @BindView(R.id.goLayout)
    RelativeLayout goLayout;

    @BindView(R.id.vp_viewPager)
    ViewPager viewPager;

    @BindView(R.id.goLogin)
    TextView goLogin;

    private ArrayList<SimpleDraweeView> views = new ArrayList<SimpleDraweeView>();

    //等一个viewPager Adapter
    private ViewPagerAdapter adapter;

    private int length;

    //底部指针
    private ImageView[] points;

    //当前指正
    private int currentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initData();
    }

    /*
    * 用于图片加载的初始化，初始化在有网络和无网的时候给出对应的操作。
    * （需要做相对于网络延迟的操作）
    *
    * */
    private void initData() {
        adapter = new ViewPagerAdapter(views);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        if (!JUtils.isNetWorkAvilable()) {
            //无网络的情况
            for (int i = 0; i < Config.pics.length; i++) {
                length = Config.pics.length;
                SimpleDraweeView iv = new SimpleDraweeView(this);
                iv.setLayoutParams(mParams);
                //防止图片不能全屏
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(Config.pics[i]);
                views.add(iv);
            }
            //有网络的情况
        }else{
            for (int i = 0 ; i <Config.pricUrls.length;i++){
                length = Config.pricUrls.length;
                SimpleDraweeView iv  = new SimpleDraweeView(this);
                iv.setLayoutParams(mParams);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                //加载图片资源
                iv.setImageURI(Uri.parse(Config.pricUrls[i]));
                views.add(iv);
            }
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new OnPageChangeListener());
        initPoint();
    }


    /*
    * 初始化原点
    * */
    private void initPoint() {
        //设定一个布局的参数
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.point_layout);
        points = new ImageView[length];
        //初始化
        for (int i = 0; i < length; i++) {
            points[i] = (ImageView) linearLayout.getChildAt(i);
            points[i].setEnabled(true);
            points[i].setOnClickListener(this);
            points[i].setTag(i);
        }
        //设置当前默认的位置
        currentIndex = 0;
        //设置为白色即选中的状态
        points[currentIndex].setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurrentView(position);
        setCurrentDot(position);
    }

    /* 监听滚动事件*/
    private final class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //在最后一个的时候显示有可以点击按钮的页面。
            setCurrentDot(position);
            if (position == length - 1) {
                goLayout.setVisibility(View.VISIBLE);
                goComing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                goLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("startUp", "welcome");
                        //下面这边应该是要转到LoginActivity
                        intent.setClass(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            } else {
                goLayout.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int position) {

        }
    }

    /**
     * 设置当前页面视图的状态
     *
     * @param position
     */
    private void setCurrentView(int position) {
        if (position < 0 || position >= length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }


    private void setCurrentDot(int position) {
        if (position < 0 || position >= length) {
            return;
        }
        points[position].setEnabled(false);
        points[currentIndex].setEnabled(true);
        currentIndex = position;
    }
}
