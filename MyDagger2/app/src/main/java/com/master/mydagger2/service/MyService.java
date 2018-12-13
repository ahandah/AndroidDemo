package com.master.mydagger2.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.master.mydagger2.IMyAidlInterface;
import com.master.mydagger2.User;


public class MyService extends Service {

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        return null;
        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) throws RemoteException {

        }

        //aidl  getName()接口的具体实现
        @Override
        public String getName() throws RemoteException {
            return "name";
        }

        //aidl  getUser()接口的具体实现
        @Override
        public User getUser() throws RemoteException {
            return new User();
        }


    }
}
