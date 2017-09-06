package com.niuniushop.sjhj.nnshop.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jude.utils.JUtils;
import com.niuniushop.sjhj.nnshop.R;
import com.niuniushop.sjhj.nnshop.app.App;

/**
 * Created by Administrator on 2017/9/6.
 */

public class BaseThemeSettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关于设置主题，主题就先放着
//        setCustomTheme();
    }

    private void setCustomTheme() {
        int theme = JUtils.getSharedPreference().getInt("THEME", 0);
        switch (theme) {
            case 1:
                setTheme(R.style.AppTheme1);
                break;
            case 2:
                setTheme(R.style.AppTheme2);
                break;
            case 3:
                setTheme(R.style.AppTheme3);
                break;
            case 4:
                setTheme(R.style.AppTheme4);
                break;
            case 5:
                setTheme(R.style.AppTheme5);
                break;
            default:
                setTheme(R.style.AppThemeDefault);
                break;
        }

    }


}
