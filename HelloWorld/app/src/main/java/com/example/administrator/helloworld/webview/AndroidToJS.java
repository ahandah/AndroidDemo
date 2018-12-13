package com.example.administrator.helloworld.webview;

import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2018/2/2.
 */

public class AndroidToJS extends Object {

    @JavascriptInterface
    public void hello(String msg) {
        System.out.println("JS调用了Android的hello方法msg: " + msg);
    }
}
