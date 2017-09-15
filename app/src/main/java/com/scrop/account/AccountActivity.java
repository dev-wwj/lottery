package com.scrop.account;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scrop.base.BaseActivity;
import com.scrop.entity.LoginBean;
import com.scrop.minterface.listener.NaviActionListener;
import com.scrop.navi.NaviLayout;
import com.scrop.networking.CommonOkHttpClient;
import com.scrop.networking.listener.DisposeDataHandle;
import com.scrop.networking.listener.DisposeDataListener;
import com.scrop.networking.request.CommonRequest;
import com.scrop.networking.request.RequestParams;
import com.scrop.user.UserInfoManager;
import com.scrop.youcaile.R;

import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends BaseActivity implements View.OnClickListener {
    private NaviLayout naviLayout;
    private AppCompatEditText eTusername ,eTPassword;
    private Button btnLogin,btnRegister ,btnForgetPwd;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initSubViews();
    }

    private void initSubViews() {
        naviLayout = (NaviLayout) findViewById(R.id.id_navi) ;
        eTusername = (AppCompatEditText) findViewById(R.id.id_et_username);
        eTPassword = (AppCompatEditText) findViewById(R.id.id_et_password);

        btnLogin = (Button) findViewById(R.id.id_btn_login);
        btnRegister = (Button) findViewById(R.id.id_btn_register);
        btnForgetPwd = (Button) findViewById(R.id.id_btn_forgetPwd);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnForgetPwd.setOnClickListener(this);

        naviLayout.setListener(new NaviActionListener() {
            @Override
            public void btnBackClickListener(View view) {
                Intent i = new Intent();
                i.setAction("com.scorp.youcaile");
                i.putExtra("action", 11);
                sendBroadcast(i);
                finish();
            }

            @Override
            public void textTitleClickListener(View view) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_btn_login:
                prepareLogin();
                break;
            case R.id.id_btn_register:
                prepareRegister();
                break;
            case R.id.id_btn_forgetPwd:
                break;
            default:
                break;
        }
    }

    //登录 功能
    private void prepareLogin() {
        if ("".equals(eTusername.getText().toString().trim())){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }else if ("".equals(eTPassword.getText().toString().trim())){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        willLogin(eTusername.getText().toString().trim(),eTPassword.getText().toString().trim());
    }

    private void willLogin(String username, String password) {
        Map<String ,Object>  mapParams = new HashMap<>();
        mapParams.put("action","login");
        mapParams.put("username",username);
        mapParams.put("password",password);
        mapParams.put("cat","202");
        RequestParams params = new RequestParams(mapParams);
       ;
        CommonOkHttpClient.post(CommonRequest.createPostRequest(URL_SERVICE,params),new
                DisposeDataHandle(LoginBean.class,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i("onSuccess" ,responseObj.toString());
                didLogin(responseObj);
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.i("onFailure" ,responseObj.toString());
            }
        }));
    }

    private void didLogin(Object responseObj) {
        LoginBean bean = (LoginBean)responseObj;
        if (bean.getValue().isIslogin()){
            UserInfoManager.getInstance().getUserInfo().setAccesstoken(((LoginBean) responseObj)
                    .getValue().getAccesstoken());
            Intent intent = new Intent();
            intent.setAction("com.scorp.youcaile");
            intent.putExtra("MY_ACTION",11);
            sendBroadcast(intent);
            finish();
        }else {
            Toast.makeText(this,bean.getValue().getErrmsg(),Toast.LENGTH_SHORT).show();
        }
    }

    //  注册功能
    private void prepareRegister() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
