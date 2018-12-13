package com.example.administrator.helloworld.MyView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2018/1/24.
 */

public class VDHLayout extends LinearLayout {

    public ViewDragHelper viewDragHelper;

    public VDHLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == dragView || child == autoBackView || child == btn;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                if (left > getWidth() - child.getMeasuredWidth()) {
                    left = getWidth() - child.getMeasuredWidth();
                }
                if (left < 0) {
                    left = 0;
                }
                return left;
            }


            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == autoBackView) {
                    System.out.println("autoBackView--released--true--");
                    viewDragHelper.settleCapturedViewAt(autoBackViewOriginLeft, autoBackViewOriginTop);
                    invalidate();
                }
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                viewDragHelper.captureChildView(edgeDragView, pointerId);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                System.out.println("HorizontalDragRange--" + (getMeasuredHeight() - child.getMeasuredHeight()));
                return getMeasuredWidth() - child.getMeasuredWidth();
//                return child.getWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                System.out.println("VerticalDragRange--" + (getMeasuredHeight() - child.getMeasuredHeight()));
                return getMeasuredHeight() - child.getMeasuredHeight();
//                return child.getMeasuredHeight();
            }
        });
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return viewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true))
        {
            invalidate();
        }
    }

    private View dragView;
    private View edgeDragView;
    private View autoBackView;
    private View btn;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        dragView = findViewById(R.id.dragView);
//        edgeDragView = findViewById(R.id.edgeDragView);
//        autoBackView = findViewById(R.id.autoBackView);
//        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn--click--");
            }
        });
    }

    int autoBackViewOriginLeft;
    int autoBackViewOriginTop;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        autoBackViewOriginLeft = autoBackView.getLeft();
        autoBackViewOriginTop = autoBackView.getTop();
    }
}
