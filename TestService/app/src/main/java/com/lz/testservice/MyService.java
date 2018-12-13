package com.lz.testservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/3/19.
 */

public class MyService extends Service {

    public static final String TAG = "MyService";

    private static final int REQUEST_CODE = 100;
    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_PLAY = "play";
    private static final String ACTION_PAUSE = "pause";
    private static final String ACTION_LAST = "last";
    private static final String ACTION_NEXT = "next";
    private static final String ACTION_BACKAPP = "backApp";
    private RemoteViews remoteViews;
    private Notification notification;
    private NotificationManager notificationManager;
    private Context context;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            System.out.println("broadcastReceiver " + action);
            if (action.equals(ACTION_PLAY)) {
                remoteViews.setViewVisibility(R.id.play, View.GONE);
                remoteViews.setViewVisibility(R.id.pause, View.VISIBLE);
                System.out.println(Global.data);
            }

            if (action.equals(ACTION_PAUSE)) {
                remoteViews.setViewVisibility(R.id.pause, View.GONE);
                remoteViews.setViewVisibility(R.id.play, View.VISIBLE);
            }

            if (action.equals(ACTION_BACKAPP)) {
                collapseStatusBar();
                Intent it = new Intent(MyService.this, MainActivity.class);
                startActivity(it);

            }

            notificationManager.notify(NOTIFICATION_ID, notification);

        }
    };

    private void collapseStatusBar() {
        try {
            Object statusBarManager = getSystemService("statusbar");
            Method collapse;
            if (Build.VERSION.SDK_INT <= 16) {
                collapse = statusBarManager.getClass().getMethod("collapse");
            } else {
                collapse = statusBarManager.getClass().getMethod("collapsePanels");
            }
            collapse.invoke(statusBarManager);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(TAG, "in onCreate");

        context = getApplicationContext();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_PLAY);
        intentFilter.addAction(ACTION_PAUSE);
        intentFilter.addAction(ACTION_LAST);
        intentFilter.addAction(ACTION_NEXT);
        intentFilter.addAction(ACTION_BACKAPP);

        Global.setData();

        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);

        remoteViews = new RemoteViews(this.getPackageName(), R.layout.layout_notification);

        Notification.Builder builder = new Notification.Builder(context);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            builder.setCustomContentView(remoteViews);
        } else {
            builder.setContent(remoteViews);
        }
        builder.setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round);
        notification = builder.build();


        Intent itPlay = new Intent(ACTION_PLAY);
        Intent itPause = new Intent(ACTION_PAUSE);
        Intent itLast = new Intent(ACTION_LAST);
        Intent itNext = new Intent(ACTION_NEXT);
        Intent itBackApp = new Intent(ACTION_BACKAPP);

        PendingIntent pItPlay = PendingIntent.getBroadcast(context, REQUEST_CODE, itPlay, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pItPause = PendingIntent.getBroadcast(context, REQUEST_CODE, itPause, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pItLast = PendingIntent.getBroadcast(context, REQUEST_CODE, itLast, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pItNext = PendingIntent.getBroadcast(context, REQUEST_CODE, itNext, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pItBackApp = PendingIntent.getBroadcast(context, REQUEST_CODE, itBackApp, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.play, pItPlay);
        remoteViews.setOnClickPendingIntent(R.id.pause, pItPause);
        remoteViews.setOnClickPendingIntent(R.id.last, pItLast);
        remoteViews.setOnClickPendingIntent(R.id.next, pItNext);
        remoteViews.setOnClickPendingIntent(R.id.backApp, pItBackApp);

        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        startForeground(NOTIFICATION_ID, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.w(TAG, "Service is onDestroy");
        stopForeground(true);
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();

    }
}
