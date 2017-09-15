package com.scrop.networking.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scrop.networking.exception.HttpException;
import com.scrop.networking.listener.DisposeDataHandle;
import com.scrop.networking.listener.DisposeDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by Scrop on 2017/7/18.
 */

public class CommonJsonCallback implements Callback {
    protected final String RESULT_CODE = "code";
    protected final int RESULT_CODE_VALUE = 101;
    protected final String ERROR_MSG = "未知错误";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie";

    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private DisposeDataListener listener;
    private Class<?> mClass;
    private Handler mDelieverHandler;

    public CommonJsonCallback(DisposeDataHandle handle){
        listener = handle.listener;
        mClass = handle.mClass;
        mDelieverHandler =  new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDelieverHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailure(new HttpException(NETWORK_ERROR,e.getLocalizedMessage()));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDelieverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i= 0; i< headers.size(); i++){
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)){
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    private void handleResponse(String result){
        if (result == null || result.equals("")){
            listener.onFailure(new HttpException(NETWORK_ERROR,ERROR_MSG));
            return;
        }
        try {
            Log.i("network_result:",result + "---end");
            JSONObject resultObj = new JSONObject(result);
            if (resultObj.has(RESULT_CODE)){
                if (resultObj.optInt(RESULT_CODE) == RESULT_CODE_VALUE){
                    if (mClass == null){
                        listener.onSuccess(resultObj);
                    }else {
                        Gson gson = new GsonBuilder().create();
                        Object obj = gson.fromJson(String.valueOf(resultObj),mClass);
                        if (obj == null){
                            listener.onFailure( new HttpException(JSON_ERROR,ERROR_MSG));
                        }else {
                            listener.onSuccess(obj);
                        }
                    }
                }else {
                    if (resultObj.has(ERROR_MSG)){
                        listener.onFailure(new HttpException(resultObj.optInt(RESULT_CODE), resultObj.optString(ERROR_MSG)));
                    }else{
                        listener.onFailure(new HttpException(JSON_ERROR,EMPTY_MSG));
                    }
                }
            }else {

                listener.onFailure(new HttpException(OTHER_ERROR,resultObj.optString(ERROR_MSG)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFailure(new HttpException(OTHER_ERROR,e.getMessage()));
        }
    }

}
