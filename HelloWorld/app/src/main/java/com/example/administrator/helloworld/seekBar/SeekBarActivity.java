package com.example.administrator.helloworld.seekBar;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.retrofit.MyRetrofit;
import com.example.administrator.helloworld.retrofit.SubscriberListener;

import java.io.File;
import java.util.List;

public class SeekBarActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, SubscriberListener{

    private SeekBar seekBar;
    private TextView tv;
    private MyRetrofit myRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

//        seekBar = findViewById(R.id.seekBar);
//        tv = findViewById(R.id.tv);
//
//        seekBar.setOnSeekBarChangeListener(this);
//        seekBar.setSecondaryProgress(80);

        myRetrofit = new MyRetrofit(this, this);
        myRetrofit.retrofit();
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRetrofit.get("https://www.mocky.io/v2/5185415ba171ea3a00704eed");
            }
        });

        File file = new File(Environment.getExternalStorageDirectory().toString());
        if (file.isDirectory()) {
            System.out.println("true----------------------------------" + file.exists() + file.listFiles().length);
        } else {
            System.out.println("false");
        }
        File[] files = file.listFiles();
        for (File file2 : files) {
            System.out.println(file2.getName());
        }


    }



    public static void getEnvironmentDirectories() {
        //:/system
        String rootDir = Environment.getRootDirectory().toString();
        System.out.println("Environment.getRootDirectory()=:" + rootDir);

        //:/data 用户数据目录
        String dataDir = Environment.getDataDirectory().toString();
        System.out.println("Environment.getDataDirectory()=:" + dataDir);

        //:/cache 下载缓存内容目录
        String cacheDir = Environment.getDownloadCacheDirectory().toString();
        System.out.println("Environment.getDownloadCacheDirectory()=:" + cacheDir);

        //:/mnt/sdcard或者/storage/emulated/0或者/storage/sdcard0 主要的外部存储目录
        String storageDir = Environment.getExternalStorageDirectory().toString();
        System.out.println("Environment.getExternalStorageDirectory()=:" + storageDir);

        //:/mnt/sdcard/Pictures或者/storage/emulated/0/Pictures或者/storage/sdcard0/Pictures
        String publicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        System.out.println("Environment.getExternalStoragePublicDirectory()=:" + publicDir);

        //获取SD卡是否存在:mounted
        String storageState = Environment.getExternalStorageState().toLowerCase();
        System.out.println("Environment.getExternalStorageState()=:" + storageState);

        //设备的外存是否是用内存模拟的，是则返回true。(API Level 11)
        boolean isEmulated = Environment.isExternalStorageEmulated();
        System.out.println("Environment.isExternalStorageEmulated()=:" + isEmulated);

        boolean isRemovable = Environment.isExternalStorageRemovable();
        System.out.println("Environment.isExternalStorageRemovable()=</span>:" + isRemovable);
    }

    public static void getApplicationDirectories(Context context) {

        //获取当前程序路径 应用在内存上的目录 :/data/data/com.mufeng.toolproject/files
        String filesDir = context.getFilesDir().toString();
        System.out.println("context.getFilesDir()=:" + filesDir);

        //应用的在内存上的缓存目录 :/data/data/com.mufeng.toolproject/cache
        String cacheDir = context.getCacheDir().toString();
        System.out.println("context.getCacheDir()=:" + cacheDir);

        //应用在外部存储上的目录 :/storage/emulated/0/Android/data/com.mufeng.toolproject/files/Movies
        String externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString();
        System.out.println("context.getExternalFilesDir()=:" + externalFilesDir);

        //应用的在外部存储上的缓存目录 :/storage/emulated/0/Android/data/com.mufeng.toolproject/cache
        String externalCacheDir = context.getExternalCacheDir().toString();
        System.out.println("context.getExternalCacheDir()=:" + externalCacheDir);

        //获取该程序的安装包路径 :/data/app/com.mufeng.toolproject-3.apk
        String packageResourcePath = context.getPackageResourcePath();
        System.out.println("context.getPackageResourcePath()=:" + packageResourcePath);

        //获取程序默认数据库路径 :/data/data/com.mufeng.toolproject/databases/mufeng
        String databasePat = context.getDatabasePath("mufeng").toString();
        System.out.println("context.getDatabasePath(\"mufeng\")=:" + databasePat);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        System.out.println("progress " + progress + " fromUser " + fromUser);
        tv.setText(progress + "");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onSuccess(Object object) {
        String msg = (String) object;
        System.out.println("onSuccess - msg ");
        System.out.println("msg - " + msg);
    }

    @Override
    public void onFault() {

    }
}
