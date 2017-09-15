package com.scrop.youcaile;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;

import com.scrop.base.BaseActivity;
import com.scrop.tabbar.TabbarFragment;

public class MainActivity extends BaseActivity {
    TabbarFragment tabbarFragment = null;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (tabbarFragment == null){
            tabbarFragment = new TabbarFragment();
        }
        transaction.add(R.id.id_content,tabbarFragment).commit();
    }
}
