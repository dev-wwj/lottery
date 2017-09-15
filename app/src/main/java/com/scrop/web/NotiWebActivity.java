package com.scrop.web;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.scrop.base.BaseActivity;
import com.scrop.minterface.listener.NaviActionListener;
import com.scrop.navi.NaviLayout;
import com.scrop.youcaile.R;

public class NotiWebActivity extends BaseActivity {
    WebView webView;
    NaviLayout naviLayout;
    String url;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        url = getIntent().getStringExtra("url");
        Log.i("url:",url);
        naviLayout = (NaviLayout) findViewById(R.id.id_navi);
        initWebView();
        naviLayout.setListener(new NaviActionListener() {
            @Override
            public void btnBackClickListener(View view) {
                finish();
            }

            @Override
            public void textTitleClickListener(View view) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initWebView() {
        webView = (WebView) findViewById(R.id.id_webView);
        webView.loadUrl(url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); //支持通过JS打开新窗口
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //不使用缓存

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //设定加载开始的操作
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //设定加载结束的操作
                String getTitleJS = "$(\"header i\").html()";
                webView.evaluateJavascript(getTitleJS, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        String title = value.replace("\"","");
                        NotiWebActivity.this.naviLayout.textTitle.setText(title);
                    }
                });
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //设定加载资源的操作
            }


            //步骤1：写一个html文件（error_handle.html），用于出错时展示给用户看的提示页面
            //步骤2：将该html文件放置到代码根目录的assets文件夹下
            //步骤3：复写WebViewClient的onRecievedError方法
            //该方法传回了错误码，根据错误类型可以进行不同的错误分类处理
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                switch (error.getErrorCode()){
//                    case HttpStatus.SC_NOT_FOUND:
//                        view.loadUrl("file:///android_assets/error_handle.html");
//                        break;

                }
            }
        });


    }



}
