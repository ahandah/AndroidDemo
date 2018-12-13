package com.master.mydeeplink;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent =getIntent();
        if (intent.getScheme() != null) {
            Log.e(TAG, "scheme:" +intent.getScheme());
            Uri uri =intent.getData();
            Log.e(TAG, "scheme: "+uri.getScheme());
            Log.e(TAG, "host: "+uri.getHost());
            Log.e(TAG, "port: "+uri.getPort());
            Log.e(TAG, "path: "+uri.getPath());
            Log.e(TAG, "queryString: "+uri.getQuery());
            Log.e(TAG, "queryParameter: "+uri.getQueryParameter("key"));
        }

    }
}
