package com.scrop.mine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scrop.account.AccountActivity;
import com.scrop.base.BaseFragment;
import com.scrop.minterface.MyObserver;
import com.scrop.tool.imageloader.GlideCircleTransform;
import com.scrop.user.UserInfo;
import com.scrop.user.UserInfoManager;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/21.
 * 个人中心
 */

public class MineFragment extends BaseFragment implements View.OnClickListener,MyObserver{
    ImageButton ibMsg;
    RoundedImageView rIViewHeader;
    TextView tvUsername,tvScore;
    TextView tvYe,tvLev;  // 余额，会员等级
    Button btnRecharge,btnWithDraw,btnSign;
    LinearLayout ll0,ll1,ll2,ll3,ll4,ll5,ll6,ll7;
    Button btnLoginOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine,container,false);
        initSubView(v);
        return v;
    }

    private void initSubView(View v) {
        UserInfoManager.getSubject().addObserver(this);

        ibMsg = (ImageButton) v.findViewById(R.id.id_ib_msg);

        rIViewHeader = (RoundedImageView) v.findViewById(R.id.id_riv_header);

        tvUsername = (TextView) v.findViewById(R.id.id_tv_username);
        tvScore = (TextView) v.findViewById(R.id.id_tv_score);

        tvYe = (TextView) v.findViewById(R.id.id_tv_ye);
        tvLev = (TextView) v.findViewById(R.id.id_tv_level);

        btnRecharge = (Button) v.findViewById(R.id.id_btn_recharge);
        btnWithDraw = (Button) v.findViewById(R.id.id_btn_withdraw);
        btnSign = (Button) v.findViewById(R.id.id_btn_sign);

        ll0 = (LinearLayout) v.findViewById(R.id.id_lm_0);
        ll1 = (LinearLayout) v.findViewById(R.id.id_lm_1);
        ll2 = (LinearLayout) v.findViewById(R.id.id_lm_2);
        ll3 = (LinearLayout) v.findViewById(R.id.id_lm_3);
        ll4 = (LinearLayout) v.findViewById(R.id.id_lm_4);
        ll5 = (LinearLayout) v.findViewById(R.id.id_lm_5);
        ll6 = (LinearLayout) v.findViewById(R.id.id_lm_6);
        ll7 = (LinearLayout) v.findViewById(R.id.id_lm_7);

        btnLoginOut = (Button) v.findViewById(R.id.id_btn_loginOut);

        ibMsg.setOnClickListener(this);
        rIViewHeader.setOnClickListener(this);

        btnRecharge.setOnClickListener(this);
        btnWithDraw.setOnClickListener(this);
        btnSign.setOnClickListener(this);

        ll0.setOnClickListener(this);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        ll7.setOnClickListener(this);

        btnLoginOut.setOnClickListener(this);

//        valueValue();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void update() {
        valueValue();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void valueValue() {
        Log.e("MineFragmet:", "---UpdateUserInfo---");
        UserInfo info = UserInfoManager.getInstance().getUserInfo();
        String imgHeaderLoc = "https://www.youcaile.com/static/upload/mempic/";
        String imgURL = imgHeaderLoc + info.getMemInfoBean().getValue().getPic();
        Glide.with(this).load(imgURL).centerCrop().placeholder(R.mipmap.img_account)
                .transform(new GlideCircleTransform(getContext(),1,this.getResources().getColor(R
                        .color
                        .colorWhite)))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(rIViewHeader);

        tvUsername.setText(info.getMemInfoBean().getValue().getUsername());
        tvScore.setText("积分：" + String.valueOf (info.getMemInfoBean().getValue().getPnt()));
        tvYe.setText(Html.fromHtml("余额: " + "<font color='red'>"+ (double) info.getMemInfoBean()
                .getValue()
                .getCoin() +"</font>"));
        tvLev.setText(Html.fromHtml("会员等级: " + "<font size=18 color='red'>LV"+ info.getMemInfoBean()
                .getValue()
                .getRolemem() +"</font>"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.id_riv_header:
                gotoLogin();
                break;
            case R.id.id_ib_msg:
                //消息
                gotoMessage();
                break;
            case R.id.id_btn_recharge:
                //充值
                gotoMyAccount();
                break;
            case R.id.id_btn_withdraw:
                //提现
                break;
            case R.id.id_btn_sign:
                //签到
                gotoSign();
                break;
            case R.id.id_lm_0:
                //投注记录
                break;
            case R.id.id_lm_1:
                //资金流水
                break;
            case R.id.id_lm_2:
                //积分记录
                break;
            case R.id.id_lm_3:
                //身份信息
                gotoIdentityInfo();
                break;
            case R.id.id_lm_4:
                gotoContact();
                //联系信息
                break;
            case R.id.id_lm_5:
                gotoBankCardInfo();
                //银行卡信息
                break;
            case R.id.id_lm_6:
                //密码管理
                gotoPwdManager();
                break;
            case R.id.id_lm_7:
                //客服
                break;
        }

    }


    private void gotoLogin() {
        if (UserInfoManager.getInstance().getUserInfo().isLogin()){
            return;
        }else {
            Intent i =  new Intent(getContext(),AccountActivity.class);
            getContext().startActivity(i);
        }
    }

    private void gotoMessage() {
        Intent i = new Intent(getContext(),MessageActivity.class);
        getContext().startActivity(i);
    }

    private void gotoMyAccount() {
        Intent i = new Intent(getContext(),MyAccountActivity.class);
        getContext().startActivity(i);
    }

    private void gotoSign() {
        Intent i = new Intent(getContext(),SignActivity.class);
        getContext().startActivity(i);
    }

    private void gotoIdentityInfo() {
        Intent i = new Intent(getContext(),IdentityInfoActivity.class);
        getContext().startActivity(i);
    }

    private void gotoContact() {
        Intent i = new Intent(getContext(),ContactActivity.class);
        getContext().startActivity(i);
    }

    private void gotoBankCardInfo() {
        Intent i = new Intent(getContext(),BankCardInfoActivity.class);
        getContext().startActivity(i);
    }

    private void gotoPwdManager() {
        Intent i = new Intent(getContext(),PasswordManagerActivity.class);
        getContext().startActivity(i);
    }




}
