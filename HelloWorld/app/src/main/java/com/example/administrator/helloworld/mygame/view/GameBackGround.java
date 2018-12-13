package com.example.administrator.helloworld.mygame.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2018/1/26.
 */

public class GameBackGround extends RelativeLayout {


    public GameBackGround(Context context) {
        super(context);
        init(context);
    }

    public GameBackGround(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameBackGround(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;
    private int height, bgY, bgY2;
    ImageView ivBg;
    ImageView ivBg2;
    public void init(Context context) {
        setWillNotDraw(false);
        this.context = context;
        height = context.getResources().getDisplayMetrics().heightPixels;
        bgY = -height;
        bgY2 = 0;

        ivBg = new ImageView(context);
        ivBg.setImageResource(R.mipmap.ic_launcher);
        ivBg2 = new ImageView(context);
        ivBg2.setImageResource(R.mipmap.ic_launcher);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ivBg.setLayoutParams(layoutParams);
        ivBg2.setLayoutParams(layoutParams);

        addView(ivBg);
        addView(ivBg2);


        ivBg.setTranslationY(bgY);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (bgY > height) {
                    bgY = -height;
                }
                if (bgY2 > height) {
                    bgY2 = -height;
                }
                bgY += 1;
                bgY2 += 1;
                ivBg.setTranslationY(bgY);
                ivBg2.setTranslationY(bgY2);
                invalidate();
                sendEmptyMessageDelayed(1, 10);
            }
        };
        handler.sendEmptyMessage(1);
    }



}
