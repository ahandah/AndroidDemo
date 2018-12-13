package com.example.administrator.helloworld.testview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/3/26.
 */

public class ViewGroup01 extends ViewGroup {
    public ViewGroup01(Context context) {
        super(context);
    }

    public ViewGroup01(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroup01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
