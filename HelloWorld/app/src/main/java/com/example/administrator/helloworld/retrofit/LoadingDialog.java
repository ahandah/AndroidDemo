package com.example.administrator.helloworld.retrofit;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.helloworld.R;


/**
 * Created by Administrator on 2017/12/13.
 */

public class LoadingDialog extends Dialog {

    private Context context;
    private boolean isLoading = false;

    public LoadingDialog(Context context){
        super(context, R.style.Dialog_Fullscreen);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.dialog_loading);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = displayMetrics.widthPixels * 3/4;
        window.setAttributes(lp);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        if (isLoading == false){
            super.show();
            isLoading = true;
        }
    }

    @Override
    public void dismiss() {
        if (isLoading == true){
            super.dismiss();
        }
    }


    public void loadingShow(){

        isLoading = true;
        show();
    }

    public void loadingDismiss(){

        if (isLoading == true){
            isLoading = false;
            dismiss();
        }
    }
}
