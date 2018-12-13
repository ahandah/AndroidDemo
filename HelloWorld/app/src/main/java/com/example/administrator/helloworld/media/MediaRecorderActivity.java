package com.example.administrator.helloworld.media;


import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.helloworld.R;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MediaRecorderActivity extends AppCompatActivity implements View.OnClickListener {

    private Button recorder, play, stop;
    private String fileName;
    private File file;
    private MediaRecorder mediaRecorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder);

        recorder = findViewById(R.id.recorder);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);

        recorder.setOnClickListener(this);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startRecorder() {

        mediaRecorder = new MediaRecorder();
//        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
//        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        fileName = DateFormat.format("yyMMdd", new Date()).toString() + ".m4a";
        file = new File(this.getFilesDir(), fileName);
//        System.out.println("start--file--exist--" + file.exists());
//        if (file.exists()) {
//            file.delete();
//        }
//        mediaRecorder.setOutputFile(file.getAbsolutePath());
//        try {
//            mediaRecorder.prepare();
//            mediaRecorder.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //录音文件保存的格式，这里保存为 mp4
        mediaRecorder.setOutputFile(file.getAbsolutePath()); // 设置录音文件的保存路径
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioChannels(1);
        // 设置录音文件的清晰度
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setAudioEncodingBitRate(96000);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            Log.e("Log.e", "prepare() failed");
        }

    }

    public void stopRecord() {
        if (mediaRecorder == null) {
            System.out.println("mediaRecorder---is---null---");
            return;
        }
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
//            fileName = "";
        } catch (RuntimeException e) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;

//            File file = new File(fileName);
//            if (file.exists())
//                file.delete();

//            fileName = "";
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.recorder:
                startRecorder();
                break;
            case R.id.play:
                File file = new File(getFilesDir(), fileName);
                System.out.println("file--"+file.exists()+"-file-size-"+file.length()+"-file-name-"+fileName);
                Uri uri = Uri.fromFile(file);
                MediaPlayer mediaPlayer = MediaPlayer.create(this, uri);
                mediaPlayer.start();

                break;
            case R.id.stop:
                stopRecord();
                break;
        }
    }


}
