package com.scrop.networking.request;

import com.scrop.base.MyApplication;
import com.scrop.networking.rsa.Base64;
import com.scrop.networking.rsa.RSASignature;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Scrop on 2017/7/17.
 */

public class RequestParams {
    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();
    public ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap<String, Object>();

    public RequestParams(){
        this((Map<String,Object>)null);
    }


    public  RequestParams(Map<String, Object> source){
        if (source != null){
            buildParams(source);
        }
    }

    public RequestParams(final String key, final Object value){
        this(new HashMap<String, Object>(){
            {put(key,value);}
        });
    }

    private void put(String key, String value){
        if (key != null && value != null){
            urlParams.put(key,value);
        }
    }

    public void put(String key, Object object) throws FileNotFoundException{
        if (key != null) {
            fileParams.put(key, object);
        }
    }

    final private String HTTP_SOURCE_IPHONE = "3000";
    final private String HTTP_KEY_IPHONE = "YOUCAILE";
    private void buildParams(Map<String, Object> map) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String sDate = format.format(new Date());
        urlParams.put("time", sDate);
        urlParams.put("source", HTTP_SOURCE_IPHONE);
        String pamBase64 = null;
        try {
            pamBase64 = paramsBase64(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        urlParams.put("param", pamBase64);
        String sign = signEncode(urlParams);
        urlParams.put("sign", sign);
    }

    // rsa私钥
    //
    String privateKey = null;


    private String signEncode(Map<String,String> map) {
        String time = map.get("time");
        String source = map.get("source");
        String signRead = time + source;
        String signStr = RSASignature.sign(signRead, readKey(null));
        return signStr;
    }

    private String paramsBase64(Map<String ,Object> map) throws Exception{
        JSONObject jsonObject = new JSONObject(map);
        byte[] bs = jsonObject.toString().getBytes();
        return Base64.encode(bs);
    }

    final String KEYSTORE_FILE     = "file:///android_asset/private_key";
    final String KEYSTORE_PASSWORD = "123698";
    final String KEYSTORE_ALIAS    = "alias";

    private PrivateKey readKey(File file){
        try {

            KeyStore ks = KeyStore.getInstance("PKCS12");
            InputStream fis =  MyApplication.getContextObject().getAssets()
                    .open("private_key.p12");
            char[] password = null;
            if (KEYSTORE_PASSWORD == null ||KEYSTORE_PASSWORD.trim().equals("")){
                password = null;
            }else {
                password = KEYSTORE_PASSWORD.toCharArray();
            }
            ks.load(fis,password);
            fis.close();

            Enumeration enumeration = ks.aliases();
            String  keyAlias = null;
            if (enumeration.hasMoreElements()){
                keyAlias = (String) enumeration.nextElement();
            }
            PrivateKey privateKey = (PrivateKey) ks.getKey(keyAlias, password);
            Certificate certificate = ks.getCertificate(keyAlias);
            PublicKey publicKey = (PublicKey) certificate.getPublicKey();
            return privateKey;
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

}
