package com.example.administrator.helloworld.retrofit;

import android.content.Context;
import android.content.DialogInterface;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/1/15.
 */

public class DialogSubscriber<T> extends Subscriber<T> implements DialogListener {

    private Context context;
    private LoadingDialog loadingDialog;
    private SubscriberListener subscriberListener;

    public DialogSubscriber(Context context, SubscriberListener subscriberListener) {
        this.context = context;
        this.subscriberListener = subscriberListener;
    }

    private void initDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
            loadingDialog.setCanceledOnTouchOutside(true);
            loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    onDialogCancel();
                }
            });
        }

        if (! loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void showDialog() {
        initDialog();
    }


    public void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onCompleted() {

        dismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("throwable---" + e.getMessage());
        dismissDialog();
    }

    @Override
    public void onNext(T t) {
        if (subscriberListener != null) {
            subscriberListener.onSuccess(t);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        showDialog();
    }

    @Override
    public void onDialogCancel() {
        if (! this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
