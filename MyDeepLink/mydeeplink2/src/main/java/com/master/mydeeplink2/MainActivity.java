package com.master.mydeeplink2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Scheme(View view) {
        Uri data = Uri.parse("urlschemel://www.orangecpp.com:80/mypath?key=mykey");
        Intent intent = new Intent(Intent.ACTION_VIEW,data);
        //保证新启动的APP有单独的堆栈，如果希望新启动的APP和原有APP使用同一个堆栈则去掉该项
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivityForResult(intent, RESULT_OK);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "没有匹配的APP，请下载安装",Toast.LENGTH_SHORT).show();
        }
    }
}
