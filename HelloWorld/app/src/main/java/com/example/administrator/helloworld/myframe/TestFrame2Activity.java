package com.example.administrator.helloworld.myframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.utils.ToastUtils;

public class TestFrame2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_frame2);

        new ToastUtils.Builder(this).setView(LayoutInflater.from(this).inflate(R.layout.view_ads_img, null)).build().ShortText();
    }
}
