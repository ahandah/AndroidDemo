package com.lz.mydemo01.dagger;

/**
 * Created by Administrator on 2018/8/28.
 */

public class Person {

    public Person() {
        System.out.println("Person created by self");
    }

    public int i = 1;
    public Person(String i) {

    }

    public Person(int i, String s) {

    }
}
