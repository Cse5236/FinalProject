package com.example.whath.ui.videoplayer;

/**
 * Created by Liu.3502 on 2017/11/5.
 */

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.whath.ui.R;

public class Html5VideoAutoPlay extends Activity {
    WebView webview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.html5video);

        webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            /**
             * 当前网页的链接仍在webView中跳转
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * 处理ssl请求
             */
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            /**
             * 页面载入完成回调
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:try{autoplay();}catch(e){}");
            }
        });

        webview.setWebChromeClient(new WebChromeClient() {
            /**
             * 显示自定义视图，无此方法视频不能播放
             */
            @Override
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
            }
        });
        webview.loadUrl("file:///sdcard/html/video.html");
    }

    @Override
    protected void onPause() {
        if(null != webview) {
            webview.onPause();
        }
        super.onPause();
    }
}