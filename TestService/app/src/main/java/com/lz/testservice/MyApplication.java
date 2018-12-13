package com.lz.testservice;

import android.app.Application;

/**
 * Created by Administrator on 2018/3/20.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    public static MyApplication getApplication() {
        if (myApplication == null) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
