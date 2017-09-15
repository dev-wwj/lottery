package com.scrop.mine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.scrop.base.BaseActivity;
import com.scrop.youcaile.R;

public class IdentityInfoActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_info);
    }
}
