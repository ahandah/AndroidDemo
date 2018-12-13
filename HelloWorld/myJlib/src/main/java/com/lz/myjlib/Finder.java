package com.lz.myjlib;

import android.app.Activity;

/**
 * Created by Administrator on 2018/3/7.
 */

public enum Finder {

    Activity {
        @Override
        public void setContentView(Object activity, int id) {
            ((Activity)activity).setContentView(id);
        }
    };
    public abstract void setContentView(Object activity, int id);
}

