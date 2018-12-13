package com.example.administrator.helloworld.myframe;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.utils.LogUtils;
import com.example.administrator.helloworld.utils.ToastUtils;

public class TestFrameActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_frame);
        LogUtils.init();

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils toastUtils = new ToastUtils.Builder(TestFrameActivity.this)
                                                    .setView(LayoutInflater.from(TestFrameActivity.this).inflate(R.layout.view_ads_img, null))
                                                    .setDuration(ToastUtils.LENGTH_LONG)
                                                    .setGravity(Gravity.CENTER, 0,0)
                                                    .build();

//                toastUtils.ShortText("sfsdfsfd");
//                Glide.with(this).load("sdf").init
                ToastUtils.ShortText();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), TestFrame2Activity.class);
                startActivity(it);
            }
        });
    }
}
