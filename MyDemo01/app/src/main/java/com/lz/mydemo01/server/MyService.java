package com.lz.mydemo01.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import static java.lang.Thread.sleep;

public class MyService extends Service {

    private int a = 1;
    private MyBind myBind = new MyBind();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("onBind -----------------");
        return myBind;
    }

    public class MyBind extends Binder {

        public MyBind() {
            System.out.println("Test Print MyBind");
        }

        public int getA() {
            return 1;
        }
    }

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Test Print onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Test Print onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Test Print onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        System.out.println("Test Print onRebind");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Test Print onStartCommand");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println("Server int a : " + (a ++) + "     Thread    " + Thread.currentThread());
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
}
