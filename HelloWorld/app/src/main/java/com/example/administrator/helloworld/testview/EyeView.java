package com.example.administrator.helloworld.testview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2018/3/29.
 */

public class EyeView extends View {
    public EyeView(Context context) {
        super(context);
        init();
    }

    public EyeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EyeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int width, height, halfWidth, halfHeight,
            animationSize1 = 0, animationSize2 = 0, radio = 50;
    private Paint paint;

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        width = getWidth();
        height = getHeight();
        halfWidth = width / 2;
        halfHeight = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path pathCircle = new Path();
        pathCircle.addCircle(x, y, radio, Path.Direction.CW);

        Path path1 = new Path();
        path1.lineTo(width, 0);
        path1.lineTo(width, halfHeight);
        path1.quadTo(halfWidth, halfHeight - animationSize1, 0, halfHeight);
        path1.op(pathCircle, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, paint);

        Path path2 = new Path();
        path2.moveTo(0, halfHeight);
        path2.lineTo(0, height);
        path2.lineTo(width, height);
        path2.lineTo(width, halfHeight);
        path2.quadTo(halfWidth, halfHeight + animationSize1, 0, halfHeight);
        path2.op(pathCircle, Path.Op.DIFFERENCE);
        canvas.drawPath(path2, paint);

    }

    public void setAnimationSize1(int animationSize) {
        this.animationSize1 = animationSize;
        invalidate();
    }

    public void setRadio(int radio) {
        this.radio = radio;
        invalidate();
    }

    public void startAnimation1() {

        System.out.println("start animation");
        final ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "radio",
                50, 200, 50);
        objectAnimator.setDuration(4000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.start();
    }

    public void startAnimation2() {

        System.out.println("start animation");
        final ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(this, "animationSize1",
                0, halfHeight / 2, 0);
        objectAnimator1.setDuration(4000);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator1.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator1.start();
    }

    public void startAnimation3() {

        System.out.println("start animation");
        final ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(this, "animationSize2",
                0, halfHeight / 2, 0);
        objectAnimator1.setDuration(4000);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator1.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator1.start();
    }

    private float x, y;
    private boolean isStartAnimation = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        x = event.getX();
//        y = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            System.out.println("down" + halfHeight / 2);
//            startAnimation();
            if (! isStartAnimation) {
                startAnimation1();
                startAnimation2();
                isStartAnimation = true;
            }
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            System.out.println("move");
            x = event.getX();
            y = event.getY();
//            invalidate();
        }
//        return super.onTouchEvent(event);
        return true;
    }
}
