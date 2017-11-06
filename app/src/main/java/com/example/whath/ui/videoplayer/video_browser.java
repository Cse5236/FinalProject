package com.example.whath.ui.videoplayer;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.whath.ui.R;


/**
 * Created by Liu.3502 on 2017/11/5.
 */

    public class video_browser extends Activity {

    //private WebView mWebView;
//    private InsideWebChromeClient mInsideWebChromeClient;
//
//    Button b1;
//    EditText ed1;
//
//    public FrameLayout mFrameLayout;
//    private WebView wv1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.mFrameLayout);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.browser);
//
//
//
//        b1=(Button)findViewById(R.id.button);
//        ed1=(EditText)findViewById(R.id.editText);
//
//        wv1=(WebView)findViewById(R.id.webView);
//        wv1.setWebViewClient(new MyBrowser());
//
//
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = ed1.getText().toString();
//
//                wv1.getSettings().setLoadsImagesAutomatically(true);
//                wv1.getSettings().setJavaScriptEnabled(true);
//                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//                initWebView();
//                wv1.loadUrl(url);
//            }
//            private void initWebView(){
//                WebSettings settings = wv1.getSettings();
//                settings.setJavaScriptEnabled(true);
//                settings.setJavaScriptCanOpenWindowsAutomatically(true);
//                settings.setPluginState(WebSettings.PluginState.ON);
//                //settings.setPluginsEnabled(true);
//                settings.setAllowFileAccess(true);
//                settings.setLoadWithOverviewMode(true);
//                settings.setUseWideViewPort(true);
//                settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//                mInsideWebChromeClient = new InsideWebChromeClient();
//                InsideWebViewClient mInsideWebViewClient = new InsideWebViewClient();
//                //javascriptInterface = new JavascriptInterface();
//                //mWebView.addJavascriptInterface(javascriptInterface, "java2js_laole918");
//                wv1.setWebChromeClient(mInsideWebChromeClient);
//                wv1.setWebViewClient(mInsideWebViewClient);
//            }
//        });
//
//
//
//    }
//
//    private class MyBrowser extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//
//    }
//
//    //WebChromeClient
//    private class InsideWebChromeClient extends WebChromeClient {
//        private View mCustomView;
//        private CustomViewCallback mCustomViewCallback;
//
//        @Override
//        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
//            super.onShowCustomView(view, callback);
//            if (mCustomView != null) {
//                callback.onCustomViewHidden();
//                return;
//            }
//            mCustomView = view;
//            mFrameLayout.addView(mCustomView);
//            mCustomViewCallback = callback;
//            wv1.setVisibility(View.GONE);
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//
//        public void onHideCustomView() {
//            wv1.setVisibility(View.VISIBLE);
//            if (mCustomView == null) {
//                return;
//            }
//            mCustomView.setVisibility(View.GONE);
//            mFrameLayout.removeView(mCustomView);
//            mCustomViewCallback.onCustomViewHidden();
//            mCustomView = null;
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            super.onHideCustomView();
//        }
//    }
//
//    private class InsideWebViewClient extends WebViewClient {
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            // TODO Auto-generated method stub
//            view.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            //mWebView.loadUrl(javascript);
//        }
//
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration config) {
//        super.onConfigurationChanged(config);
//        switch (config.orientation) {
//            case Configuration.ORIENTATION_LANDSCAPE:
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                break;
//            case Configuration.ORIENTATION_PORTRAIT:
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//                break;
//        }
//    }
//
//
//    //webView
//    @Override
//    public void onPause() {
//        super.onPause();
//        wv1.onPause();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        wv1.onResume();
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (wv1.canGoBack()) {
//            wv1.goBack();
//            return;
//        }
//        super.onBackPressed();
//    }
//
//    @Override
//    public void onDestroy() {
//        wv1.destroy();
//        super.onDestroy();
//    }

}
