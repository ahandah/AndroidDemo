package com.lz.mydemo01.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lz.mydemo01.dao.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();
    @Query("SELECT * FROM USER WHERE id = :id")
    User getUser(int id);
    @Insert
    void insertAll(User ... users);
    @Update
    void updata(User user);
    @Delete
    void delete(User user);
}
