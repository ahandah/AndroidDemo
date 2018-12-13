package com.lz.mydemo01.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.lz.mydemo01.dao.entity.User;


/**
 * Created by Administrator on 2018/8/21.
 */
@Database(entities = {User.class}, version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  public abstract UserDao userDao();

  static final Migration MIGRATION_4_5 = new Migration(7, 8) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
      // Since we didn't alter the table, there's nothing else to do here.
      database.execSQL("ALTER TABLE User "
              + " ADD COLUMN testStr2 TEXT");
    }
  };

}
