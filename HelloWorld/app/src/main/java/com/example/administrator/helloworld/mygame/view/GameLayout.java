package com.example.administrator.helloworld.mygame.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.helloworld.R;

import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * Created by Administrator on 2018/1/25.
 */

public class GameLayout extends RelativeLayout {
    public GameLayout(Context context) {
        super(context);
    }

    public GameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Context context;
    private Paint paint;
    private Plane plane;
    private Me me;
    private int sWidth, sHeight, bgY, bgY2;
    private ImageView ivBg, ivBg2;
    private Handler gHandler;
    private ArrayList<Plane> planes;

    private void init(Context context) {
        setWillNotDraw(false);
        this.context = context;

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        sWidth = displayMetrics.widthPixels;
        sHeight = displayMetrics.heightPixels - 90;

        planes = new ArrayList<>();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        plane = new Plane(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher),
//                sWidth, sHeight, (int) (sWidth * Math.random()));
        me = new Me(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));

        for (int i = 0; i < 10; i ++) {
            plane = new Plane(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher),
                sWidth, sHeight, (int) (sWidth * Math.random()));
            plane.autoMove();
            planes.add(plane);
        }

        reSetMe();
        refreshScreen();
        isOver();

    }

    private void reSetMe() {
        if (me == null) {
            return;
        }
        me.x = sWidth / 2;
        me.y = sHeight - me.height;

    }

    private void refreshScreen() {
        gHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                invalidate();
                sendEmptyMessageDelayed(1, 10);
            }
        };
        gHandler.sendEmptyMessage(1);
    }

    private Handler serHandler;
    private void isOver() {

        HandlerThread search = new HandlerThread("search");
        search.start();
        serHandler = new Handler(search.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                int sizeX = Math.abs(me.x - plane.x);
//                int sizeY = Math.abs(me.y - plane.y);
                for (Plane plane : planes) {
                    int sizeX = Math.abs(me.x - plane.x);
                    int sizeY = Math.abs(me.y - plane.y);
                    if (sizeX < (me.width / 2 + plane.width / 2) - 30 && sizeY < (me.height / 2 + plane.height / 2) - 30) {
//                    gHandler.removeMessages(1);
//                    serHandler.removeMessages(2);
                        System.out.println("game---over---");
                    } else {
//                    serHandler.sendEmptyMessageDelayed(2, 10);
                    }
                }

                serHandler.sendEmptyMessageDelayed(2, 10);
            }
        };
        serHandler.sendEmptyMessage(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Plane plane : planes) {
            canvas.drawBitmap(plane.bitmap, plane.getX(), plane.getY(), paint);
        }

        canvas.drawBitmap(me.bitmap, me.getX(), me.getY(), paint);
    }

    private float downX, downY, moveX, moveY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();

        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (downX > me.x && downX < (me.x + me.width)) {
                if (downY > me.y && downY < (me.y + me.height)) {
                    moveX = event.getX() - downX;
                    moveY = event.getY() - downY;
                    me.x += moveX;
                    me.y += moveY;
                    downX = event.getX();
                    downY= event.getY();
                    invalidate();
                }
            }
        }
        return true;
    }
}
