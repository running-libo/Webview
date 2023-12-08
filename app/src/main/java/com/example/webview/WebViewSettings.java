package com.example.webview;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewSettings {

    public void setSettings(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); //设置支持javascript

        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); //缩放至屏幕的大小

        webSettings.setTextZoom(100);//字体百分比，替代原API:setTextSize

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //原生的缩放控件 开关

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置四种缓存模式，这里是优先使用缓存，否则请求网络
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //不使用缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8"); //设置编码格式

    }

    /**
     * webview操作事件
     */
    public void webViewEvent(WebView webView) {
        webView.clearCache(true); //清除缓存
        webView.clearHistory(); //清除历史记录
        webView.reload(); //重新加载
    }

    /**
     * Https 加载 Http 混合模式
     */
    public void loadMixedContentMode(WebSettings webSettings) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
}
