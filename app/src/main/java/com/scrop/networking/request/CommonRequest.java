package com.scrop.networking.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Scrop on 2017/7/17.
 */

public class CommonRequest {
    /**
     * Get
     * @param url
     * @param  params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null){
            for (Map.Entry<String,String> entry :params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length() - 1 )).get()
                .build();

    }

    /**
     * Post 请求
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params){
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null){
            for (Map.Entry<String,String> entry : params.urlParams.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody body = builder.build();
        return new Request.Builder().url(url).post(body).build();
    }

    /**
     * 文件上传
     */

    private static final MediaType FILE_TYPE = MediaType.parse("application/octext-stream");

    public static Request createMultiPostRequest(String url, RequestParams params){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null){
            for (Map.Entry<String,Object> entry:params.fileParams.entrySet()){
                if (entry.getValue() instanceof File){
                    builder.addPart(MultipartBody.Part.createFormData(entry.getKey(),null, RequestBody.create(FILE_TYPE,(File)entry.getValue())));
                }else {
                    builder.addFormDataPart(entry.getKey(),String.valueOf(entry.getValue()));
                }
            }
        }
        return new Request.Builder().url(url).post(builder.build()).build();

    }

}
