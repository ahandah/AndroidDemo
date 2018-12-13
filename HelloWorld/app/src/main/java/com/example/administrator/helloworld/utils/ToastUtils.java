package com.example.administrator.helloworld.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2018/3/27.
 */

public class ToastUtils {

    public static final int LENGTH_SHORT = 0,
                            LENGTH_LONG = 1;
    private static Context context;
    private static Toast toast;

    private ToastUtils(Context context, Toast toast) {
        this.context = context;
        this.toast = toast;

    }

    public static void ShortText() {
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void ShortText(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static class Builder {

        public Builder(Context context) {
            ToastUtils.context = context;
            toast = new Toast(context);
        }
        public Builder setView(View view) {
            toast.setView(view);
            return Builder.this;
        }
        public Builder setDuration(int duration) {
            toast.setDuration(duration);
            return Builder.this;
        }
        public Builder setGravity(int gravity, int xOffset, int yOffset) {
            toast.setGravity(gravity, xOffset, yOffset);
            return Builder.this;
        }
        public ToastUtils build() {
            return new ToastUtils(context, toast);
        }
    }

}
