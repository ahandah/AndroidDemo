package com.example.administrator.helloworld.media;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.helloworld.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AudioRecordActivity extends AppCompatActivity implements View.OnClickListener {

    Button startRecordingButton, stopRecordingButton, play;//开始录音、停止录音
    File recordingFile;//储存AudioRecord录下来的文件
    boolean isRecording = false; //true表示正在录音
    AudioRecord audioRecord=null;
    File parent=null;//文件目录
    int bufferSize=0;//最小缓冲区大小
    int sampleRateInHz = 11025;//采样率
    int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO; //单声道
    int audioFormat = AudioFormat.ENCODING_PCM_16BIT; //量化位数
    String TAG="AudioRecord";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        init();
        initListener();

    }

    //初始化
    public void init(){
        startRecordingButton = (Button)findViewById(R.id.StartRecordingButton);
        stopRecordingButton = (Button)findViewById(R.id.StopRecordingButton);
        play = findViewById(R.id.play);

        bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz,channelConfig, audioFormat);//计算最小缓冲区
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRateInHz,channelConfig, audioFormat, bufferSize);//创建AudioRecorder对象

        parent = new File(getFilesDir()+ "/AudiioRecordTest");
        if(!parent.exists())
            parent.mkdirs();//创建文件夹
    }

    //初始化监听器
    public void initListener(){
        startRecordingButton.setOnClickListener(this);
        stopRecordingButton.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            //开始录音
            case R.id.StartRecordingButton:
            {
                record();
                break;
            }

            //停止录音
            case R.id.StopRecordingButton:
            {
                stopRecording();
                break;
            }

            case R.id.play:
//                Uri uri = Uri.fromFile(recordingFile);
//                System.out.println("file--"+recordingFile.exists()+"-file-size-"+recordingFile.length()+"-file-name-"+recordingFile.getAbsolutePath());
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                try {
//                    mediaPlayer.setDataSource(recordingFile.getAbsolutePath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                mediaPlayer.start();
                PlayRecord();
                break;
        }

    }

    //开始录音
    public void record() {
        isRecording = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                isRecording = true;

                recordingFile = new File(parent,"audiotest.pcm");
                if(recordingFile.exists()){
                    recordingFile.delete();
                }

                try {
                    recordingFile.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,"创建储存音频文件出错");
                }


                try {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(recordingFile)));
                    byte[] buffer = new byte[bufferSize];
                    audioRecord.startRecording();//开始录音
                    int r = 0;
                    while (isRecording) {
                        int bufferReadResult = audioRecord.read(buffer,0,bufferSize);
                        for (int i = 0; i < bufferReadResult; i++)
                        {
                            dos.write(buffer[i]);
                        }
                        r++;
                    }
                    audioRecord.stop();//停止录音
                    dos.close();
                } catch (Throwable t) {
                    Log.e(TAG, "Recording Failed");
                }

            }
        }).start();

    }

    //停止录音
    public void stopRecording()
    {
        isRecording = false;
    }


    //播放文件
    public void PlayRecord() {
        if(recordingFile == null){
            return;
        }
        //读取文件
        int musicLength = (int) (recordingFile.length() / 2);
        short[] music = new short[musicLength];
        try {
            InputStream is = new FileInputStream(recordingFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            int i = 0;
            while (dis.available() > 0) {
                music[i] = dis.readShort();
                i++;
            }
            dis.close();
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    sampleRateInHz, channelConfig, audioFormat,
                    musicLength * 2,
                    AudioTrack.MODE_STREAM);
            audioTrack.play();
            audioTrack.write(music, 0, musicLength);
            audioTrack.stop();
        } catch (Throwable t) {
            Log.e(TAG, "播放失败");
        }
    }
}
