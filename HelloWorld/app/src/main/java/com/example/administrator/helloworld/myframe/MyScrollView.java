package com.example.administrator.helloworld.myframe;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/3/27.
 */

public class MyScrollView extends ViewGroup {
    public MyScrollView(Context context) {
        super(context);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Context context;
    private void init() {
//        setWillNotDraw(false);
        context = getContext();
    }

    /**
     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
     */
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childView = this.getChildAt(0);
        childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private float downX, downY, moveX, moveY, resultY;
    private boolean isRefresh;
    private View childView;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            System.out.println("down");
            downX = ev.getX();
            downY = ev.getY();
        }

        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            System.out.println("move   downY  " + downY + " moveY " + moveY);
            moveX = ev.getX();
            moveY = ev.getY();
            resultY = moveY - downY;
            if (resultY != 0 && Math.abs(resultY) > 10) {
                childView.setTranslationY(resultY);
                isRefresh = true;
            }
        }

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            System.out.println("up");
            if (isRefresh) {
                startAnimation();
                isRefresh = false;
//                downX = 0;
//                downY = 0;
//                moveX = 0;
//                moveY = 0;
                return true;
            }
        }

        return false;
    }

    private void startAnimation() {

//        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(childView, "translationY", resultY, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

}
