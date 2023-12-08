package com.example.webview;

import android.app.Activity;
import android.webkit.CookieManager;
import java.net.HttpCookie;

public class CookieHandle {
    private CookieManager cookieManager;

    public void setCookies(Activity context, String url) {
        HttpCookie cookie = new HttpCookie("name", "value");
        cookie.setDomain("baidu.com"); //设置域名
        cookie.setPath("files"); //设置域名下的path
        cookie.setMaxAge(10000);  //设置过期时间

        // 调用CookieManager 的方法设置cookie
        // 具有相同的 host 和 path 和 name 的任何现有的 Cookie 将会被替换为新的 Cookie
        CookieManager.getInstance().setCookie(url, cookie.toString());
    }

    /**
     * 删除cookie操作：底层实现是异步清除数据库的记录
     */
    public void deleteCookie() {
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
    }
}
