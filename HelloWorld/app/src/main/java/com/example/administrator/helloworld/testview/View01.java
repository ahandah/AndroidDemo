package com.example.administrator.helloworld.testview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2018/3/26.
 */

public class View01 extends View {
    public View01(Context context) {
        super(context);
        init();
    }

    public View01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public View01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Context context;
    private Paint paint;
    private int rId = R.mipmap.ic_launcher;
    public void init() {
        context = getContext();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        System.out.println("init");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("onMeasure");
        int widthMod = MeasureSpec.getMode(widthMeasureSpec);
        int heightMod = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMod == MeasureSpec.AT_MOST) {
            widthSize = BitmapFactory.decodeResource(getResources(), rId).getWidth();
        }

        if (heightMod == MeasureSpec.AT_MOST) {
            heightSize = BitmapFactory.decodeResource(getResources(), rId).getHeight();
        }
        System.out.println("widthSize - " + widthSize + " - heightSize - " + heightSize);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(-30, getWidth()/2, getHeight()/2);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), rId), 0, 0, paint);
    }
}
