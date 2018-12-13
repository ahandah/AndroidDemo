package com.lz.myjlib;
/**
 * Created by Administrator on 2018/3/7.
 */

public interface InjectInterface<T> {
    void inject(Finder finder, T target);
}
