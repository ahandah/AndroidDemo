package com.lz.mydemo01.dagger;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Administrator on 2018/8/28.
 */
@Component(modules = {PersonModule.class})
//@Component
public interface PersonComponent {

    void inject(DaggerActivity daggerActivity);
//    int getInt();

//    int setInt = 1;

    @Component.Builder
    interface Builder {
        @BindsInstance
//        Builder setInt(int i);
        Builder setString(String s);
        PersonComponent build();
    }
}
