package com.scrop.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.scrop.constant.Constants;
import com.scrop.entity.MemInfoBean;
import com.scrop.mimpl.PropertiesConfig;
import com.scrop.networking.CommonOkHttpClient;
import com.scrop.networking.listener.DisposeDataHandle;
import com.scrop.networking.listener.DisposeDataListener;
import com.scrop.networking.request.CommonRequest;
import com.scrop.networking.request.RequestParams;
import com.scrop.user.UserInfoManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scrop on 2017/8/7.
 */

public class YouCaileReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int i = intent.getIntExtra("MY_ACTION",-1);
        switch (i){
            case 11:
                updateUserInfo(context);
                break;
            default:
                break;
        }
    }

    // 更新用户信息
    private void updateUserInfo(final Context context) {
        String accesstoken = UserInfoManager.getInstance().getUserInfo().getAccesstoken();
        Map<String ,Object> mapParams = new HashMap<>();
        mapParams.put("action","getMemInfo");
        mapParams.put("accesstoken",accesstoken);
        PropertiesConfig config = new PropertiesConfig() {
            @Override
            protected Context getContext() {
                return context;
            }

            @Override
            protected String getPropertyFileName() {
                return "config.properties";
            }
        };
        String urlService = (config.getString(Constants.HOST ) + config
                .getString
                (Constants.PATH));

        CommonOkHttpClient.post(CommonRequest.createPostRequest(urlService, new RequestParams
                (mapParams)),new
                DisposeDataHandle(MemInfoBean.class,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                UserInfoManager.getInstance().getUserInfo().setMemInfoBean((MemInfoBean) responseObj);
//                ConcreteMySubject subject = new ConcreteMySubject();
                UserInfoManager.getInstance().notifyUpdateUserInfo();
                Log.i("onSuccess" ,responseObj.toString());
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.i("onFailure" ,responseObj.toString());
            }
        }));
    }


}
