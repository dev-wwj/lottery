package com.scrop.networking;

import com.scrop.networking.cookie.SimpleCookieJar;
import com.scrop.networking.listener.DisposeDataHandle;
import com.scrop.networking.response.CommonFileCallback;
import com.scrop.networking.response.CommonJsonCallback;
import com.scrop.networking.ssl.HttpsUtils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Scrop on 2016/12/28.
 */

public class CommonOkHttpClient {

    private static final int TIME_OUT = 300;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * Https 认证
         */
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        builder.cookieJar(new SimpleCookieJar());
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.followRedirects(true);
//        builder.sslSocketFactory(null);  //https工厂
//        builder.sslSocketFactory(HttpsUtils.getSslSocketFactory());
        mOkHttpClient = builder.build();
    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates)
    {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates,
                null, null)).build();
    }

    /**
     * 指定client信任所有证书
     */
    public static void setCertificates()
    {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory());
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     *
     */

    public static void get(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
    }

    public static void post(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;

    }


}
