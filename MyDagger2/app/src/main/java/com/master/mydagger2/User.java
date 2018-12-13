package com.master.mydagger2;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String name = "UserName";

    public User() {

    }
    //Parcelable自动生成
    protected User(Parcel in) {
        name = in.readString();
    }
    //Parcelable自动生成
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    //Parcelable自动生成   默认返回0即可
    @Override
    public int describeContents() {
        return 0;
    }
    //Parcelable自动生成  写入值到Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
