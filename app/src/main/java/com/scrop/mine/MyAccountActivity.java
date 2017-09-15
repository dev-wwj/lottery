package com.scrop.mine;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scrop.base.BaseActivity;
import com.scrop.minterface.listener.NaviActionListener;
import com.scrop.navi.NaviLayout;
import com.scrop.user.UserInfoManager;
import com.scrop.youcaile.R;

public class MyAccountActivity extends BaseActivity implements View.OnClickListener {
    NaviLayout naviLayout;
    private TextView tvLeftCoin;
    private Button btnRecharge,btnWithdraw;
    private LinearLayout llOrderHistorySel,llPayPwdSet;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
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

        tvLeftCoin = (TextView) findViewById(R.id.id_tv_left_coin);
        btnRecharge = (Button) findViewById(R.id.id_btn_recharge);
        btnWithdraw = (Button) findViewById(R.id.id_btn_withdraw);
        llOrderHistorySel = (LinearLayout) findViewById(R.id.id_ll_order_history_sel);
        llPayPwdSet = (LinearLayout) findViewById(R.id.id_ll_paypwd_set);

        btnRecharge.setOnClickListener(this);
        btnWithdraw.setOnClickListener(this);
        llOrderHistorySel.setOnClickListener(this);
        llPayPwdSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_btn_recharge:
                prepareRecharge();
                break;
            case R.id.id_btn_withdraw:
                prepareWithdraw();
                break;
            case R.id.id_ll_order_history_sel:
                prepareOrderHistorySel();
                break;
            case R.id.id_ll_paypwd_set:
                preparePaypwdSet();
                break;
            default:
                break;
        }
    }

    private void prepareRecharge() {

        if (UserInfoManager.getInstance().getUserInfo().isSetCoinPwd()){
            gotoRecharge();
        }else {
            //去设置支付密码
            alertDialogShow(1);
        }
    }

    private void prepareWithdraw() {
        if (!UserInfoManager.getInstance().getUserInfo().isSetCoinPwd()){
            //去设置支付密码
            alertDialogShow(1);

            return;
        }
        if (!UserInfoManager.getInstance().getUserInfo().isBindingBankCard()){
            //去绑定银行卡
            alertDialogShow(2);
            return;
        }
        gotoWithdraw();
    }

    private void alertDialogShow(final int i) {
        int message = -1;
        if (i == 1){
            message = R.string.str_plssetcoinpwd;
        }else if( 1 == 2){
            message = R.string.str_plsbindbankcard;
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.str_alert);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (i == 1){
                    //去设置支付密码
                }else if(i == 2){
                    //去绑定银行卡
                }
            }
        });
        alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void prepareOrderHistorySel() {

    }

    private void preparePaypwdSet() {

    }

    private void gotoRecharge() {

    }

    private void gotoWithdraw() {

    }



}
