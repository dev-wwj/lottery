package com.scrop.user;

import com.scrop.entity.MemInfoBean;

/**
 * Created by Scrop on 2017/8/3.
 */

public class UserInfo {
    private String accesstoken = "";

    private MemInfoBean memInfoBean;

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public MemInfoBean getMemInfoBean() {
        return memInfoBean;
    }

    public void setMemInfoBean(MemInfoBean memInfoBean) {
        this.memInfoBean = memInfoBean;
    }

    public boolean isLogin() {
        if (getAccesstoken().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    //实名认证
    public boolean isRealName() {
        if (memInfoBean.getValue().getUsername().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    //支付密码
    public boolean isSetCoinPwd() {
        if (memInfoBean.getValue().getCoinpass().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    //绑定银行卡
    public boolean isBindingBankCard() {
        if (memInfoBean.getValue().getBankcardid().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

}
