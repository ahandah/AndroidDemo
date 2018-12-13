package com.example.administrator.helloworld;

import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNotification1, btnNotification2, btnCancelNotification;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnNotification1 = findViewById(R.id.btnNotification1);
        btnNotification1.setOnClickListener(this);
        btnNotification2 = findViewById(R.id.btnNotification2);
        btnNotification2.setOnClickListener(this);
        btnCancelNotification = findViewById(R.id.btnCancelNotification);
        btnCancelNotification.setOnClickListener(this);

    }

    public void notification1() {
        manager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(NotificationActivity.this, null);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        builder.setContentTitle("title");
        builder.setContentText("contentText");
    }

    public void notification2() {
        manager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(NotificationActivity.this, null);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        builder.setContentTitle("title2");
        builder.setContentText("contentText2");
        builder.setProgress(100, 0, false);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnNotification1:
                notification1();
                manager.notify(1, builder.build());
                break;
            case R.id.btnNotification2:
                notification2();
                manager.notify(2, builder.build());
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i <= 100; i ++) {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            builder.setProgress(100, i, false);
                            builder.setContentTitle("正在下载");
                            builder.setContentText("正在下载");
                            manager.notify(2, builder.build());
                        }
                        builder.setContentTitle("下载完成");
                        builder.setContentText("下载完成");
                        manager.notify(2, builder.build());
                    }
                }).start();
                break;
            case R.id.btnCancelNotification:
                manager.cancel(1);
                break;
        }
    }
}
