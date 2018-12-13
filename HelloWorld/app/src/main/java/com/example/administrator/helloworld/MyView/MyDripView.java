package com.example.administrator.helloworld.MyView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.helloworld.Utils;


/**
 * Created by 40660 on 2018/1/20.
 */

public class MyDripView extends ViewGroup {
    public MyDripView(Context context) {
        super(context);
    }

    public MyDripView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyDripView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint;
    private Point cP1, cP2;
    public float width, height, downY, moveY, radius = 80;
    private Context context;
    private AttributeSet attributeSet;
    private MyCircle myCircle;
    int minSize = (int) (radius * 7) + 50;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    private void init(Context context, AttributeSet attributeSet) {

//        this.setMinimumWidth();
        setWillNotDraw(false);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#bbbbbb"));
        this.context = context;
        this.attributeSet = attributeSet;
        myCircle = new MyCircle(context);
        addView(myCircle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
//        if (widthMeasureMode == MeasureSpec.AT_MOST && heightMeasureMode == MeasureSpec.AT_MOST) {
            setMinimumWidth(minSize);
            setMinimumHeight((int) (minSize + moveY));
//        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        width = this.getWidth();
//        height = this.getHeight();
        height = radius + 30;
//        width = radius * 2;
//        height = radius * 2;
        cP1 = new Point(width / 2, radius + 30);
        cP2 = new Point(width / 2, radius + 30);
        myCircle.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cP1.x, cP1.y, 80, paint);
        canvas.drawCircle(cP2.x, cP2.y, radius, paint);
        Path path = new Path();
        path.moveTo(cP1.x - 80, cP1.y);
        path.quadTo(cP2.x - radius / 2, (cP2.y + cP1.y) / 2, cP2.x - radius, cP2.y);
        path.lineTo(cP2.x + radius, cP2.y);
        path.moveTo(cP1.x - 80, cP1.y);
        path.lineTo(cP1.x + 80, cP1.y);
        path.quadTo(cP2.x + radius / 2, (cP2.y + cP1.y) / 2, cP2.x + radius, cP2.y);
//        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downY = event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            moveY = event.getY() - downY;
            cP2.y += moveY;
            if (cP2.y < (height / 2)) {
                cP2.y = height / 2;
            } else if (cP2.y > (height + radius * 6)) {
                cP2.y = height + radius * 6;
            } else {
                radius -= moveY / 6;
            }
            invalidate();
            downY = event.getY();
//            setMinimumHeight((int) (minSize + moveY));
//            LayoutParams layoutParams = getLayoutParams();
//            layoutParams.height = (int) (minSize + moveY);
//            setLayoutParams(layoutParams);
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(MyDripView.this, "radius", radius, 80);
            objectAnimator.setDuration(1000);
            cP2.animatorY();
            objectAnimator.start();
            myCircle.rotateAnimator();
        }
        return true;
    }

    class Point {
        public float x, y;

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
            invalidate();
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
        public void animatorY() {
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this, "y", this.y, height);
            objectAnimator1.setDuration(1000);
            objectAnimator1.start();

        }
    }

    class MyCircle extends View {

        private float degress;

        public float getDegress() {
            return degress;
        }

        public void setDegress(float degress) {
            this.degress = degress;
            invalidate();
        }

        public MyCircle(Context context) {
            super(context);
        }

        public MyCircle(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            float w = this.getWidth() / 2, h = height;
            canvas.rotate(degress, w, h);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(Utils.dpToPixel(2));
            Path path = new Path();
            RectF rectF = new RectF(w - 50, h - 50, w + 50, h + 50);
            path.addArc(rectF, -90, -300);
//            path.moveTo(w - 50, h - 50);
//            path.lineTo(w, h - 50);
//            path.lineTo(w - 140 / 4, h -10);
            path.moveTo(w - 10, h - 70);
            path.lineTo(w + 10, h - 50);
            path.lineTo(w - 10, h - 30);
            path.moveTo(w, h - 50);
            path.lineTo(w + 10, h - 50);
            canvas.drawPath(path, paint);

//            canvas.rotate(degress, w / 2, h / 2);
        }

        public void rotateAnimator() {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(MyCircle.this, "degress", 0, 360);
            objectAnimator.setDuration(1000);
            objectAnimator.start();
        }
    }
}
