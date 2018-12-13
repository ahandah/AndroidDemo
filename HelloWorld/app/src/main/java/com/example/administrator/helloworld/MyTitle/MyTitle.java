package com.example.administrator.helloworld.MyTitle;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2017/12/19.
 */

public class MyTitle extends LinearLayout {

    private TextView tvContext;
    private ImageView ivBack;
    private Context context;
    private TypedArray typedArray;
    private String titleName;
    public MyTitle(Context context) {
        super(context);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitle);
        titleName = typedArray.getString(R.styleable.MyTitle_titleName);
        init(context);
    }

    private void init(final Context context){

        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_my_title, this, true);
        tvContext = findViewById(R.id.tvContext);
        tvContext.setText(titleName);
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
                System.out.println("123123123123");
            }
        });
    }
}
