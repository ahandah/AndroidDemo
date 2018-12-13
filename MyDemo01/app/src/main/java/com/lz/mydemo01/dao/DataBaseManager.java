package com.lz.mydemo01.dao;

import android.arch.persistence.room.Room;
import android.content.Context;

import static com.lz.mydemo01.dao.AppDatabase.MIGRATION_4_5;


/**
 * Created by Administrator on 2018/8/21.
 */

public class DataBaseManager {

    private Context context;
    private AppDatabase database;

    public void init(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "db")
//                .fallbackToDestructiveMigration()//
                .addMigrations(MIGRATION_4_5)
                .build();
    }

    private static final DataBaseManager Instance = new DataBaseManager();

    public static DataBaseManager getInstance() {
        return Instance;
    }

    private DataBaseManager() {

    }

    public AppDatabase getDatabase() {
        if (database == null)
            throw new IllegalStateException("未初始化");
        return database;
    }

}
