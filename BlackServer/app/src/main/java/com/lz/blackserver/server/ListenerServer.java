package com.lz.blackserver.server;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;

import com.lz.blackserver.MainActivity;
import com.lz.blackserver.R;

import static android.app.PendingIntent.getActivity;

public class ListenerServer extends Service {
    public ListenerServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println(" ----------------------------- start server ");

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(" ----------------------------- start server ");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

//        Notification notification = new Notification.Builder(this).build();
//        startForeground(100, notification);


        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(" ----------------------------- destroy server ");
    }
}
