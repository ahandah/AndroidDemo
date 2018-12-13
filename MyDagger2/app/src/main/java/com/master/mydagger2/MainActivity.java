package com.master.mydagger2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.master.mydagger2.receiver.MyBroadcastReceiver;
import com.master.mydagger2.service.MyService;

import javax.inject.Inject;

import static android.content.Intent.ACTION_NEW_OUTGOING_CALL;

public class MainActivity extends AppCompatActivity {

    @Inject
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerMainActivityComponent.create().inject(this);

        DaggerMainActivityComponent
                .builder()
                .utilsModule(new UtilsModule(12))
                .build()
                .inject(this);

//        registerReceiver(new MyBroadcastReceiver(), new IntentFilter(ACTION_NEW_OUTGOING_CALL));

//        Intent it = new Intent(this, MyService.class);
//        Intent it= new Intent();
//        it.setPackage("com.master.mydagger2");
//        it.setAction("com.master.mydagger2.action");
//        it.setComponent(new ComponentName("com.master.mydagger2.service", "com.master.mydagger2.service.MyService"));
//        bindService(it, new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                System.out.println("connected");
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//                System.out.println("name");
//            }
//        }, BIND_AUTO_CREATE);


        utils.say();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
