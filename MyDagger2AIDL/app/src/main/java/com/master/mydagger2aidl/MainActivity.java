package com.master.mydagger2aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.master.mydagger2.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android5.0之后无法通过隐式Intent绑定Service
        Intent it= new Intent();
        it.setPackage("com.master.mydagger2");//包名必须与目标项目一直
        it.setAction("com.master.mydagger2.action");

        bindService(it, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    System.out.println("connected    ------" + iMyAidlInterface.getUser());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);


//        try {
//            Toast.makeText(MainActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

    }
}
