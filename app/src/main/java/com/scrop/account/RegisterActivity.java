package com.scrop.account;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scrop.base.BaseActivity;
import com.scrop.entity.CheckMobileResBean;
import com.scrop.entity.RegisterResBean;
import com.scrop.entity.SendVerificationCodeResBean;
import com.scrop.minterface.listener.NaviActionListener;
import com.scrop.navi.NaviLayout;
import com.scrop.networking.CommonOkHttpClient;
import com.scrop.networking.exception.HttpException;
import com.scrop.networking.listener.DisposeDataHandle;
import com.scrop.networking.listener.DisposeDataListener;
import com.scrop.networking.request.CommonRequest;
import com.scrop.networking.request.RequestParams;
import com.scrop.tool.MD5Security;
import com.scrop.user.UserInfoManager;
import com.scrop.youcaile.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scrop on 2017/8/3.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private NaviLayout naviLayout;
    private EditText etPhoneNum,etVerificationCode,etPwd,etPwdAgain;
    private Button btnSendVerificationCode,btnRegister;
    String jym = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initSubViews();
    }

    private void initSubViews() {
        naviLayout = (NaviLayout) findViewById(R.id.id_navi);
        naviLayout.setListener(new NaviActionListener() {
            @Override
            public void btnBackClickListener(View view) {
                finish();
            }

            @Override
            public void textTitleClickListener(View view) {

            }
        });

        etPhoneNum = (EditText) findViewById(R.id.id_et_phonenum);
        etVerificationCode = (EditText) findViewById(R.id.id_et_VerificationCode);
        etPwd = (EditText) findViewById(R.id.id_et_pwd);
        etPwdAgain  = (EditText) findViewById(R.id.id_et_pwd_again);
        btnSendVerificationCode = (Button) findViewById(R.id.id_btn_sendCode);
        btnRegister = (Button) findViewById(R.id.id_btn_register);

        btnSendVerificationCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_btn_sendCode){
            prepareSendVerificationCode();
        }
        if (v.getId() == R.id.id_btn_register){
            prepareRegister();
        }
    }

    // -------start ------ 发送验证码------
    private void prepareSendVerificationCode() {
        if ("".equals(etPhoneNum.getText().toString().trim())){
            Toast.makeText(this,"请输入手机号码",Toast.LENGTH_SHORT).show();
            return;
        }
        chkMobile();
    }

    private void chkMobile(){
        String phoneNum =  etPhoneNum.getText().toString().trim();
        Map<String ,Object> mapParams = new HashMap<>();
        mapParams.put("action","chkMobile");
        mapParams.put("mobile",phoneNum);
        CommonOkHttpClient.post(CommonRequest.createPostRequest(URL_SERVICE,new RequestParams
                (mapParams)),new DisposeDataHandle(CheckMobileResBean.class,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                chkMobileFinish(responseObj);
            }

            @Override
            public void onFailure(Object responseObj) {
                HttpException e = (HttpException) responseObj;
                Toast.makeText(RegisterActivity.this,e.getEmsg().toString(),Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void chkMobileFinish(Object responseObj) {
        CheckMobileResBean bean =  (CheckMobileResBean)responseObj;
        if (bean.getValue().isIsok()){
            sendVerificationCode();;
        }else {
            Toast.makeText(this,bean.getValue().getErrmsg(),Toast.LENGTH_SHORT).show();
        }

    }

    private void sendVerificationCode() {
        String phoneNum =  etPhoneNum.getText().toString().trim();

        Map<String ,Object> mapParams = new HashMap<>();
        mapParams.put("action","getSjjym");
        mapParams.put("mobile",phoneNum);
        mapParams.put("cat","jymReg");
        RequestParams params = new RequestParams(mapParams);
        CommonOkHttpClient.post(CommonRequest.createPostRequest(URL_SERVICE,params),new
                DisposeDataHandle(SendVerificationCodeResBean.class,new DisposeDataListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSuccess(Object responseObj) {
               onSendVerificationCodeFinish(responseObj);
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.i("onFailure" ,responseObj.toString());
                HttpException e = (HttpException) responseObj;
                Toast.makeText(RegisterActivity.this,e.getEmsg().toString(),Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onSendVerificationCodeFinish(Object responseObj) {
        SendVerificationCodeResBean bean = (SendVerificationCodeResBean) responseObj;
        if (bean.getValue().isIsok()){
            String string = "短信已发送至尾号" + bean.getValue().getMobile() + ",请查看";
            jym = bean.getValue().getJym();
            Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
            sendVerificationCodeSuccess();
        }else {
            Toast.makeText(this,bean.getValue().getErrmsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendVerificationCodeSuccess() {
        btnSendVerificationCode.setEnabled(false);
        btnSendVerificationCode.setBackgroundTintList(ColorStateList.valueOf(ContextCompat
                .getColor(this,R.color.colorLightGray0)));
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(1000 * 60,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnSendVerificationCode.setText((millisUntilFinished/1000) + "秒");
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onFinish() {
            btnSendVerificationCode.setEnabled(true);
            btnSendVerificationCode.setText(getResources().getString(R.string.str_sendercode));
            btnSendVerificationCode.setBackgroundTintList(ColorStateList.valueOf(ContextCompat
                    .getColor(RegisterActivity.this,R.color.colorGreen)));
        }
    };

    // -------end -----发送验证码-----
    // -------start ------ 注册------
    private void prepareRegister() {
        Log.e("jym:",jym);
        Log.e("jym_input:",etVerificationCode.getText().toString().trim());
        Log.e("jym_md5:",MD5Security.md5(etVerificationCode.getText().toString().trim()));
        String md5Resource = etPhoneNum.getText().toString().trim() + ";" + etVerificationCode
                .getText()
                .toString().trim();
        if ("".equals(etPhoneNum.getText().toString().trim())){
            Toast.makeText(this,"请输入手机号码",Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(etVerificationCode.getText().toString().trim())){
            Toast.makeText(this,"请输入手机验证码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!jym.equalsIgnoreCase(MD5Security.md5(md5Resource))){
            Toast.makeText(this,"您输入的验证码有误",Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(etPwd.getText().toString().trim())){
            Toast.makeText(this,"请输入登录密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(etPwdAgain.getText().toString().trim())){
            Toast.makeText(this,"请确认登录密码",Toast.LENGTH_SHORT).show();
            return;
        }
        doRegister();
    }

    private void doRegister() {
        String phoneNum =  etPhoneNum.getText().toString().trim();
        String password = etPwd.getText().toString().trim();
        Map<String ,Object> mapParams = new HashMap<>();
        mapParams.put("action","register");
        mapParams.put("mobile",phoneNum);
        mapParams.put("password",password);
        RequestParams params = new RequestParams(mapParams);
        CommonOkHttpClient.post(CommonRequest.createPostRequest(URL_SERVICE,params),new
                DisposeDataHandle(RegisterResBean.class,new DisposeDataListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSuccess(Object responseObj) {
                registerFinish(responseObj);
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.i("onFailure" ,responseObj.toString());
                HttpException e = (HttpException) responseObj;
                Toast.makeText(RegisterActivity.this,e.getEmsg().toString(),Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void registerFinish(Object responseObj) {
        RegisterResBean bean = (RegisterResBean) responseObj;
        if (bean.getValue().isIsok()){
            goRegisterSuccess();
            UserInfoManager.getInstance().getUserInfo().setAccesstoken(bean.getValue().getAccesstoken());
        }else {
            Toast.makeText(RegisterActivity.this,bean.getValue().getErrmsg().toString(),Toast.LENGTH_SHORT)
            .show();
        }
    }

    private void goRegisterSuccess() {
        Intent i = new Intent(this,RegisterSuccessActivity.class);
        startActivity(i);
    }
}
