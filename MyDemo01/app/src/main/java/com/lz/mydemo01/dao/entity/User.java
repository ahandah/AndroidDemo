package com.lz.mydemo01.dao.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 2018/8/21.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String value;
//    public int value2;
    public String testStr;
    public String testStr2;

    public User(String value) {

        this.value = value;
    }

}
