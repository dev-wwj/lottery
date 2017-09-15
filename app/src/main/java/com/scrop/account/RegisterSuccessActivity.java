package com.scrop.account;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.scrop.base.BaseActivity;
import com.scrop.youcaile.MainActivity;
import com.scrop.youcaile.R;

public class RegisterSuccessActivity extends BaseActivity {

    Button btnGotoMain,btnCompleteInfo;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        initSubviews();
    }

    private void initSubviews() {

        findViewById(R.id.id_btn_completeInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.id_btn_gotoMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterSuccessActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
