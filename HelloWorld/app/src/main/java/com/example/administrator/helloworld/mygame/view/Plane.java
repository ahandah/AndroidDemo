package com.example.administrator.helloworld.mygame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2018/1/25.
 */

public class Plane{

    public int x = 0, y = 0, stayX, dX, dY, width, height, sWidth, sHeight;
    public Bitmap bitmap;
    private Context context;
    public Plane(Bitmap bitmap, int sWidth, int sHeight, int stayX) {

        this.bitmap = bitmap;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.stayX = stayX;
        x = stayX;
//        dX = x - width / 2;
//        dY = y - height / 2;
    }

    public void autoMove() {
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (y > sHeight) {
                    y = 0;
                }
                if (x > sWidth) {
                    x = stayX;
                }
                y += 50;
                sendEmptyMessageDelayed(1, 1000);
            }
        };
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    public void start() {

    }

    public int getX() {
        return x - width / 2;
    }

    public int getY() {
        return y - height / 2;
    }
}
