package com.example.administrator.helloworld;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.helloworld.greenDao.gen.DaoMaster;
import com.example.administrator.helloworld.greenDao.gen.DaoSession;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Created by Administrator on 2017/11/21.
 */

public class MyApplication extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MyApplication instances;
    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);

//        UMShareConfig config = new UMShareConfig();
//        config.isNeedAuthOnGetUserInfo(false);
//        UMShareAPI.get(this).setShareConfig(config);

    }
    public static MyApplication getInstances(){
        return instances;
    }
    private void setDatabase(){
        mHelper = new DaoMaster.DevOpenHelper(this, "note-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }
    public SQLiteDatabase getDb(){
        return db;
    }




}
