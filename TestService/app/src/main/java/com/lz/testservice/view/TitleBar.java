package com.lz.testservice.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lz.testservice.Global;

/**
 * Created by Administrator on 2018/3/20.
 */

public class TitleBar extends LinearLayout {
    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;
    private void init(Context context) {

        this.context = context;
    }

    private void setTopView() {
        int statusBarHeight = Global.getStatusBarHeight(context);
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height += statusBarHeight;
        setLayoutParams(lp);
        setPadding(getPaddingLeft(), getPaddingTop() + statusBarHeight, getPaddingRight(), getPaddingBottom());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        setTopView();
        super.onLayout(changed, l, t, r, b);
    }


}
