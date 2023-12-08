package com.example.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient {

    private Activity context;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    public static final int FILECHOOSER_RESULTCODE = 200;

    public MyWebChromeClient(Activity context) {
        this.context = context;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        //这个方法会在网页加载过程中多次触发，当 newProgress = 100 时，可以认为网页加载完成。这个方法比 onPageFinished 更为准确，一般用来实现自定义进度条加载。
        Log.i("minfo", "当前加载进度： " + newProgress);
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        //显示页面标题 title为该页面标题
        Log.i("minfo", "页面title： " + title);
        super.onReceivedTitle(view, title);
    }

    /**
     * 页面提示框
     * @param message alert 弹出窗口中的提示信息（提示或警告信息对话框，仅一个确认按钮）
     * @param result 向网页中的 Javascript 代码反馈本次操作结果(result.confirm 代表点击了确定按钮，result.cancel 代表点击了取消按钮)
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        //页面提示框 onJsAlert()
        new AlertDialog.Builder(context)
                .setTitle("JsAlert")
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                })
                .setCancelable(false)
                .show();

        return super.onJsAlert(view, url, message, result);
    }

    /**
     * 页面选择框
     * @param message confirm 弹出窗口中的提示信息（确认对话框，有确认、取消两个按钮）
     * @param result 向网页中的 Javascript 代码反馈本次操作结果(result.confirm 代表点击了确定按钮，result.cancel 代表点击了取消按钮)
     */
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        mUploadCallbackAboveL = filePathCallback;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        context.startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
        return true;
    }
}
