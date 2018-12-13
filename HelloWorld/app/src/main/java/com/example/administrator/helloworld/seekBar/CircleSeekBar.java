package com.example.administrator.helloworld.seekBar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2018/3/14.
 */

public class CircleSeekBar extends View {
    public CircleSeekBar(Context context) {
        super(context);
        init();
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;
    private Path path;
    private int width, height, x, y, r = 500, sweep = 0;
    private Bitmap bitmap;
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        path = new Path();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        x = width / 2;
        y = height / 2;
        bitmapX = x - bitmap.getWidth() / 2;
        bitmapY = y - r - bitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, r, paint);
        RectF rect = new RectF(x - r, y - r, x + r, y + r);
        path.addArc(rect, -90, sweep);
//        path.rQuadTo(x, y - r, bitmapX, bitmapY);
        paint.setColor(Color.GREEN);
        canvas.drawPath(path, paint);
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint);
    }
    float downX = 0, downY = 0, moveX, moveY, rX, rY, bitmapX, bitmapY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        float downX = 0, downY = 0, moveX, moveY, rX, rY;
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            downX = event.getX();
//            downY = event.getY();
//        }

//        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            moveX = event.getX() - downX;
//            moveY = event.getY() - downY;

        rX = event.getX();
        rY = event.getY();
            moveX = event.getX() - x;
            moveY = event.getY() - y;
            if ( Math.pow(moveX, 2) + Math.pow(moveY, 2) > Math.pow(r - 50, 2)
                    && Math.pow(moveX, 2) + Math.pow(moveY, 2)  < Math.pow(r + 50, 2)) {
                System.out.println("true");
                bitmapX = rX - bitmap.getWidth() / 2;
                bitmapY = rY - bitmap.getHeight() / 2;
                invalidate();
            } else {

                System.out.println("false");
            }
//            sweep +=
//        }
        return true;
    }
}
