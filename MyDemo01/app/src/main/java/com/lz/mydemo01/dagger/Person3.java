package com.lz.mydemo01.dagger;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/8/28.
 */

public class Person3 {
    @Inject
    public Person3() {  //在需要注入的
        System.out.println("Person3 created by self ");
    }
}
