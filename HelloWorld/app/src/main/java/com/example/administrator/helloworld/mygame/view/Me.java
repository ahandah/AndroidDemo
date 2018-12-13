package com.example.administrator.helloworld.mygame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Administrator on 2018/1/25.
 */

public class Me extends Plane {

    public Me(Bitmap bitmap) {
        super(bitmap, 0, 0 , 0);
//        this.context = context;
    }

    private float downX, downY, moveX, moveY;
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            moveX = event.getX() - downX;
            moveY = event.getY() - downY;
            System.out.println("moveX--" + moveX);
            x += moveX;
            y += moveY;
            downX = event.getX();
            downY = event.getY();
        }
        return true;
    }

}
