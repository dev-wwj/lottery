package com.scrop.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.githang.statusbar.StatusBarCompat;
import com.scrop.mimpl.PropertiesConfig;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/17.
 */

public class BaseActivity extends AppCompatActivity {
    static public PropertiesConfig propertiesConfig = null;
    public String URL_SERVICE = null;

    public BaseActivity() {
    }

    @Override
    public void finish() {
        super.finish();
        animaExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        animaStartActivity();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        animaStartActivity();
    }

    public void animaExit(){
        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
    }

    public void animaStartActivity(){
        overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPtrpertiesConfig();
        hideNavigationBar();
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.colorPrimaryDark,
                this.getTheme())
                ,false);

    }


    private void initPtrpertiesConfig(){
        this.propertiesConfig = new PropertiesConfig() {
            @Override
            protected Context getContext() {
                return BaseActivity.this;
            }

            @Override
            protected String getPropertyFileName() {
                return "config.properties";
            }
        };
        this.URL_SERVICE = (propertiesConfig.getString(Constants.HOST ) + propertiesConfig.getString
                (Constants.PATH));

    }

    public void hideNavigationBar() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
