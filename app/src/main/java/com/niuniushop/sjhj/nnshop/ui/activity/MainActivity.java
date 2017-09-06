package com.niuniushop.sjhj.nnshop.ui.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.adapter.MyTabFragmentAdapter;
import com.niuniushop.sjhj.nnshop.config.Config;
import com.niuniushop.sjhj.nnshop.model.TabModel;
import com.niuniushop.sjhj.nnshop.widget.MyViewPager;
import com.search.material.library.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6.
 */

public class MainActivity extends BaseThemeSettingActivity {


    //    按钮
    protected FragmentTabHost tabHost;

    // 顶部适配器
    private MyTabFragmentAdapter myTabFragmentAdapter;

    //
    private TypedArray mNavMenuIconsTypeArray;

    //
    private TypedArray mNavMenuIconTintTypeArray;

    //    menu
    private ListView mDrawerMenu;

    //    menu的title
    private String[] mNavMenuTitles;

//    //    Item的List
//    private ArrayList<NavDrawerItemModel> mDrawerItems;
//
//    //抽屉监控
//    private NavDrawerListAdapter mNavDrawerAdapter;

    //   浮空
    public static FloatingActionButton floatBtn;


    //    自助ViewPager
    public static MyViewPager staticViewPager;


    //是否选择tab
    public static boolean selectTab = false;

    //管理器
    private FragmentManager fragmentManager;

    //调度器
    private FragmentTransaction fragmentTransaction;


//    private ExitUtils exit = new ExitUtils();


    @BindView(R.id.tab_toolbar)
    Toolbar tab_toolbar;

    @BindView(R.id.tab_bar_layout)
    RelativeLayout tab_bar_layout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_drawer_layout)
    LinearLayout nav_drawer_layout;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.content_viewPager)
    MyViewPager viewPager;

    @BindView(R.id.tab_AppBarLayout)
    AppBarLayout tab_AppBarLayout;

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.user_img)
    SimpleDraweeView user_img;

    @BindView(R.id.tv_name)
    TextView userName;

    @BindView(R.id.tv_signName)
    TextView signName;

    @BindView(R.id.login_tip)
    TextView loginTip;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabHost = (FragmentTabHost) super.findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager()
                , R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        initSearchView();
        initTab();
        initAppBarSetting();
        initMyTab();
        setSupportActionBar(toolbar);
        setDrawerLayout(toolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

    }

    private void initMyTab() {
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        myTabFragmentAdapter = new MyTabFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(myTabFragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (!fab.isShown()) {
                    fab.show();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initTab() {
        String[] tabTexts = TabModel.getTabTexts();
        for (int i = 0; i < tabTexts.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabTexts[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabModel.getFragments()[i], null);
            tabHost.setOnTabChangedListener(new OnTabChangeListener());
            tabHost.setTag(i);
        }
    }

    private final class OnTabChangeListener implements TabHost.OnTabChangeListener {
        @Override
        public void onTabChanged(String tabId) {

            if (tabId.equals(Config.tabs[0])) {
                selectTab = false;
                setSupportActionBar(toolbar);
                //顶部  带有文字和搜索图标的tab bar
                tab_bar_layout.setVisibility(View.GONE);
                //appbar，选择分页
                appBarLayout.setVisibility(View.VISIBLE);
                setDrawerLayout(toolbar);
                addFragment(viewPager.getCurrentItem());
                viewPager.setScrollble(true);
            } else {
                tab_bar_layout.setVisibility(View.VISIBLE);
                setSupportActionBar(tab_toolbar);
                setDrawerLayout(tab_toolbar);
                selectTab = true;
                deleteFragment(viewPager.getCurrentItem());
                viewPager.setScrollble(false);
                if (tabId.equals(Config.tabs[2]) || tabId.equals(Config.tabs[3])) {
                    tab_bar_layout.setVisibility(View.GONE);
                    appBarLayout.setVisibility(View.GONE);
                }
            }
            closeDrawer();
            updateTabs();
        }
    }

    /*
    * 关闭抽屉
    * */
    private void closeDrawer() {
        if (drawerLayout == null) {
            return;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        if (fab.isShown()) {
            fab.hide();
        }

    }


    /*
    *
    * */
    private void updateTabs() {
        TabWidget tabw = tabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            ImageView iv = (ImageView) view.findViewById(R.id.ivImg);
            if (i == tabHost.getCurrentTab()) {
                iv.setEnabled(true);
                ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabModel.getTabImgs()[i]);
            } else {
                ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabModel.getTabImgs()[i]);
                iv.setEnabled(false);
            }
        }

    }


    private void deleteFragment(int currentItem) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(MyTabFragmentAdapter.getFragment(currentItem));
        fragmentTransaction.commit();
    }

    private void addFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (index == 0) {
            fragmentTransaction.add(MyTabFragmentAdapter.getFragment(index), "IndexFragment");
        }
    }

    /**
     * 获取tab对应的视图
     *
     * @param position
     * @return
     */
    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_footer, null);
        if (position == 0) {
            (view.findViewById(R.id.ivImg)).setEnabled(true);
        } else {
            ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabModel.getTabImgs()[position]);
            (view.findViewById(R.id.ivImg)).setEnabled(false);
        }
        return view;
    }

    private void initSearchView() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putString("search", query);
                Intent intent = new Intent();
                intent.putExtra("search", bundle);
//                intent.setClass(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mean_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    public void setDrawerLayout(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                selectTab = false;
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                selectTab = true;
                if (fab.isShown()) {
                    fab.hide();
                }
                super.onDrawerOpened(drawerView);
            }
        };

    }

    /**
     * 初始化AppBar的设置
     */
    private void initAppBarSetting() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0 && !fab.isShown()) {
                    if (selectTab) {
                        fab.hide();
                    } else {
                        fab.show();
                    }

                }

            }
        });
    }
    /*
* 左边< - >菜单选中
* */

    private void seletctItem(int position, String title) {
        switch (position) {
            case 0:
                //首页
                tabHost.setCurrentTab(0);
                viewPager.setCurrentItem(0);
                break;
            case 1:
                break;
        }

    }
}
