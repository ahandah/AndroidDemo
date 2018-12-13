package com.lz.mydemo01.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/8/28.
 */
@Module
public class PersonModule {
    int mySetInt;
    String sss;
//    @Singleton
    @Provides
    Person providesPerson( String s) {
        System.out.println("person provides ----------------- " + "    " + s);
        return new Person(s);
    }




    @Provides
    Person2 providesPerson2() {
        System.out.println("person provides 2");
        return new Person2();
    }
}
