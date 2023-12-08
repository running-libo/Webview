package com.example.webview

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        var settings = WebViewSettings()
        settings.setSettings(webView)

        webView.loadUrl("https://www.baidu.com")  //最简单默认加载方式
//        webView.loadUrl("tel:18201497620")

        webView.webViewClient = MyWebViewClient(this)
        webView.webChromeClient = MyWebChromeClient(this)
    }

}