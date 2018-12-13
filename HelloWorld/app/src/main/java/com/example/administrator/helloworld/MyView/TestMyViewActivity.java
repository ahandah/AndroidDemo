package com.example.administrator.helloworld.MyView;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.helloworld.R;

import org.greenrobot.eventbus.EventBus;

public class TestMyViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my_view);
//        MediaRecorder.AudioSource.VOICE_CALL
    }

}
