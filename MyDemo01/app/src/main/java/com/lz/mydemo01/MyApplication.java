package com.lz.mydemo01;

import android.app.Application;

//import com.lz.mydemo01.dagger.DaggerPersonComponent;
//import com.lz.mydemo01.dagger.PersonComponent;
//import com.lz.mydemo01.dagger.PersonModule;
import com.lz.mydemo01.dao.DataBaseManager;

/**
 * Created by Administrator on 2018/8/21.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseManager.getInstance().init(this);
    }

//    private static PersonComponent component = DaggerPersonComponent.builder()
//            .personModule(new PersonModule()).build();
//
//    public PersonComponent getComponent() {
//        return component;
//    }

    private static MyApplication myApplication = new MyApplication();

    public static MyApplication getApplication() {
        return myApplication;
    }
}
