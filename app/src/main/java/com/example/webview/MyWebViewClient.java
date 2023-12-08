package com.example.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    private Context context;

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //用户可选择是否拦截加载 URL
        //如果返回值为 true，拦截 WebView 加载 url，false 允许 WebView 加载 url
        //所以在实际项目中，可以在这里处理自定义的一些跳转协议。

        if (url.startsWith("tel:")) {  //比如点击到已经定义好的 url 协议 电话号码 tel:// 时，那么可以在这里做拦截，跳转到系统拨号界面。
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        //开始载入页面调用的
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //在页面加载结束时调用
        super.onPageFinished(view, url);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        //在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
        super.onLoadResource(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        //App里面使用webview控件的时候遇到了诸如404这类的错误的时候，若也显示浏览器里面的那种错误提示页面就显得很丑陋了，那么这个时候我们的app就需要加载一个本地的错误提示页面
//                view.loadUrl("file:///android_assets/error_handle.html");
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        //响应服务器返回的 Http 错误，当一个 http 正常响应时，状态码会是 200，当状态码异常时可以用该方法监听。
        int code = errorResponse.getStatusCode();
        switch (code) {
            case 400:
                // 重新登录
                break;
        }
        super.onReceivedHttpError(view, request, errorResponse);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        //SSL 证书验证错误
        super.onReceivedSslError(view, handler, error);
    }
}
