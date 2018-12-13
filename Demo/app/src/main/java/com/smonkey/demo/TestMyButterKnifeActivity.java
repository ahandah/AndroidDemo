package com.smonkey.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.master.lib.annotation.BindView;

public class TestMyButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my_butter_knife);

        BindTestMyButterKnifeActivity.bindView(this);

        tv.setText("zidingyi ---- ");

        Integer i = 1;
        System.out.println(i.equals(1));

    }

}
