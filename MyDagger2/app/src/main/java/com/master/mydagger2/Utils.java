package com.master.mydagger2;

import javax.inject.Inject;

public class Utils {
//    @Inject
//    public Utils() {
//
//    }

    private int i;

    @Inject
    public Utils(int i) {
        this.i = i;
    }

    public void say() {
        System.out.println("this is Utils" + i);
    }

}
