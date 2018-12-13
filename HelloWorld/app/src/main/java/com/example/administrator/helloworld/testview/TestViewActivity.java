package com.example.administrator.helloworld.testview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.helloworld.R;

public class TestViewActivity extends AppCompatActivity {

    private View01 view1;
    private EyeView eyeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        eyeView = findViewById(R.id.eyeView);

//        view1 = findViewById(R.id.view1);
//        view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("click  view  01  ");
//            }
//        });
//        view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("clicke view 02");
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
