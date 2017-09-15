package com.scrop.mine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.LinearLayout;

import com.scrop.base.BaseActivity;
import com.scrop.youcaile.R;

public class PasswordManagerActivity extends BaseActivity implements View.OnClickListener {
    LinearLayout llSetLoginPwd, llSetPayPwd;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager);
        initSubviews();
    }

    private void initSubviews() {
        findViewById(R.id.id_ll_LoginPwd).setOnClickListener(this);
        findViewById(R.id.id_ll_pawPwd).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_ll_LoginPwd:
                startLoginPwdActivity();
                break;
            case R.id.id_ll_pawPwd:
                break;
            default:
                break;
        }
    }

    public void startLoginPwdActivity(){
        Intent i = new Intent(this,SetPwdActivity.class);
        startActivity(i);
    }
}
