package com.scrop.mine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

import com.scrop.base.BaseActivity;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.view.MenuLayout;
import com.scrop.youcaile.R;

public class SignActivity extends BaseActivity {
    MenuLayout menuLayout;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initSubviews();
    }

    private void initSubviews() {
        menuLayout = (MenuLayout) findViewById(R.id.id_menuLayout);
        menuLayout.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("MenuLayout: click", String.valueOf(position));
            }
        });
        menuLayout.setDefaultChecked(0);
    }
}
