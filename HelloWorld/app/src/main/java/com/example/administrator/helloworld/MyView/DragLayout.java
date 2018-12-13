package com.example.administrator.helloworld.MyView;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/1/25.
 */

public class DragLayout extends RelativeLayout {
    public DragLayout(Context context) {
        super(context);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Context context;
    private ViewDragHelper viewDragHelper;
    private View background;
    private View content;
    private int width, height;
    private void init(final Context context) {
        this.context = context;
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == content;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (left < 0) {
                    left = 0;
                }
                if (left > width * 3 / 5) {
                    left = width * 3 / 5;
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top < 0) {
                    top = 0;
                }
                if (top > height / 10) {
                    top = height / 10;
                }
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == content) {
                    if (releasedChild.getLeft() < width * 2 / 5) {
                        viewDragHelper.settleCapturedViewAt(0, 0);
                    } else {
                        viewDragHelper.settleCapturedViewAt( width * 3 /5, height / 10);
                    }
                }
                invalidate();
            }
        });
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (viewDragHelper.continueSettling(true))
        {
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        background = this.getChildAt(0);
        content = this.getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        width = r;
        height = b;
    }
}
