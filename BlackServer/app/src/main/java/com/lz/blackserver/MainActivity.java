package com.lz.blackserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lz.blackserver.server.ListenerServer;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);

        final Intent it = new Intent(this, ListenerServer.class);
//        startActivity(it);
        startService(it);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(it);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(it);
                System.exit(0);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
