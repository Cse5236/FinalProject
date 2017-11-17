package com.example.whath.ui.videoplayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.app.Activity;
import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.whath.ui.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.io.PrintStream;

import static android.content.ContentValues.TAG;
//import android.widget.ProgressBar;

/**
 * Created by Star on 2017/11/5.
 */

public class WebVideoActivity extends Activity {


    private WebView webView;
//    public ProgressBar progressbar;

    /** 视频全屏参数 */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(1900, ViewGroup.LayoutParams.MATCH_PARENT,Gravity.RIGHT);
    //COVER_SCREEN_PARAMS.gravity=Gravity.START;
    //COVER_SCREEN_PARAMS.setMargins(-2000,176,0,0);
    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    //private ValueEventListener ClickListener;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.browser);
        webView = (WebView) findViewById(R.id.webView);
        initWebView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        webView.reload();
    }

    /** 展示网页界面 **/
    public void initWebView() {
        WebChromeClient wvcc = new WebChromeClient();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        //webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Url");

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info.isAvailable())
        {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }else
        {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);//不使用网络，只加载缓存
        }

        webView.setWebChromeClient(wvcc);

//        webView.progressbar.setProgress(10);
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                //myRef.setValue(url);
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

        };


        //webView.setWebViewClient(wvc);
        webView.setWebViewClient(new WebViewClient()
        {

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url)
            {
                if (url.contains("[tag]"))
                {
                    String localPath = url.replaceFirst("^http.*[tag]\\]", "");
                    try
                    {
                        InputStream is = getApplicationContext().getAssets().open(localPath);
                        //Log.d(TAG, "shouldInterceptRequest: localPath " + localPath);
                        String mimeType = "text/javascript";
                        if (localPath.endsWith("css"))
                        {
                            mimeType = "text/css";
                        }
                        return new WebResourceResponse(mimeType, "UTF-8", is);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        return null;
                    }
                }
                else
                {
                    return null;
                }

            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            /*** 视频播放相关的方法 **/

            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(WebVideoActivity.this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                showCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                hideCustomView();
            }

        });


        myRef.setValue("https://youtu.be/W48v6hF6qZg");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = (String) dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                // 加载Web地址
                webView.loadUrl(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }

//            @Override
//            public void shouldOverrideUrlLoading(View view) {
//                String seturl = webView.getUrl();
//                myRef.setValue(seturl);
//            }
        });
//
//        webView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                String urlstr = webView.getUrl();
//                Log.d(TAG, urlstr);
//                myRef.setValue(urlstr);
//            }
//        });
//
        Button mButtonSync;
        mButtonSync = (Button) findViewById(R.id.button_sync);
        mButtonSync.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String urlstr = webView.getUrl();
                Log.d(TAG, "urlstr = "+urlstr);
                myRef.setValue(urlstr);
            }
        });

        //ValueEventListener ClickListener = new ValueEventListener(){
        //};
        //myRef.addValueEventListener(ClickListener);
    }

    /** 视频播放全屏 **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }

        WebVideoActivity.this.getWindow().getDecorView();

        FrameLayout decor = (FrameLayout)getWindow().getDecorView();

        fullscreenContainer = new FullscreenHolder(WebVideoActivity.this);

        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);

        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);

        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
    }

    /** 隐藏视频全屏 */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }

        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        webView.setVisibility(View.VISIBLE);
    }

    /** 全屏容器界面 */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                /** 回退键 事件处理 优先级:视频播放全屏-网页回退-关闭页面 */
                if (customView != null) {
                    hideCustomView();
                } else if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }
}