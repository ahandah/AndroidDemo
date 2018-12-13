package com.example.administrator.helloworld.webview;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.administrator.helloworld.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class WebJSActivity extends AppCompatActivity {

    private Button btn;
    private WebView webView;
    private WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_js);

        btn = findViewById(R.id.btn);
        webView = findViewById(R.id.webView);

        webSettings = webView.getSettings();

//        webViewToJS();
        JSToWebView();

    }


    private void webViewToJS() {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl("file:///android_asset/javascript.html");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    webView.loadUrl("javascript:callJS()");
                } else {
                    webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            System.out.println("value-----" + value);
                        }
                    });
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

                System.out.println("url--" + url + "-message-" + message + "-result-" + result);
                AlertDialog.Builder builder = new AlertDialog.Builder(WebJSActivity.this);
                builder.setTitle("Alert");
                builder.setMessage(message);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
                return true;
            }

        });
    }

    private void JSToWebView() {

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("file:///android_asset/javascript2.html");

        //方法一
//        webView.addJavascriptInterface(new AndroidToJS(), "test");


        //方法二 复写WebViewClient类的shouldOverrideUrlLoading方法
//        webView.setWebViewClient(new WebViewClient() {
//
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//
//                // 步骤2：根据协议的参数，判断是否是所需要的url
//                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
//                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
//
//                Uri uri = request.getUrl();
//                // 如果url的协议 = 预先约定的 js 协议
//                // 就解析往下解析参数
//                if ( uri.getScheme().equals("js")) {
//
//                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
//                    // 所以拦截url,下面JS开始调用Android需要的方法
//                    if (uri.getAuthority().equals("webview")) {
//
//                        //  步骤3：
//                        // 执行JS所需要调用的逻辑
//                        System.out.println("js调用了Android的方法");
//                        // 可以在协议上带有参数并传递到Android上
//                        HashMap<String, String> params = new HashMap<>();
//                        Set<String> collection = uri.getQueryParameterNames();
//                    }
//
//                    return true;
//                }
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        });


//       方法三
        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                return super.onJsAlert(view, url, message, result);
//            }
//
//            @Override
//            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
//                return super.onJsConfirm(view, url, message, result);
//            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

                System.out.println("onJsPrompt------");
                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")) {
                    System.out.println("js-------");
                    if (uri.getAuthority().equals("webview")) {
                        System.out.println("webview-----");
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();

                        //参数result:代表消息框的返回值(输入值)
                        result.confirm("js调用了Android的方法成功啦");
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

        });

    }

}
