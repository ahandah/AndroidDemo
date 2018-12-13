package com.example.administrator.helloworld.retrofit;

import android.content.Context;

import com.example.administrator.helloworld.BuildConfig;
import com.example.administrator.helloworld.MainActivity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/15.
 */

public class MyRetrofit {

    public static final String URL =
            "http://t.968967.com:8019/API/";
    private Context context;
    private SubscriberListener subscriberListener;
    private Retrofit retrofit;
    public GetRequest_Interface getRequest;


    public MyRetrofit(Context context, SubscriberListener subscriberListener) {

        this.context = context;
        this.subscriberListener = subscriberListener;
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 0) {
                throw new ApiException(httpResult.getCode());
            }
            return httpResult.getResult();
        }
    }

    public void retrofit() {
        final LoadingDialog loadingDialog = new LoadingDialog(context);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getRequest = retrofit.create(GetRequest_Interface.class);

//        DialogSubscriber<HttpResult<List<Ads>>> dialogSubscriber = new DialogSubscriber<HttpResult<List<Ads>>>(context, subscriberListener);
//        getRequest.getObservable("Act604").subscribeOn(Schedulers.io())
//                .map(new HttpResultFunc<List<Ads>>())
//                .observeOn(Schedulers.io())
//                .subscribe(new DialogSubscriber<List<Ads>>(context, subscriberListener));
//        BuildConfig.DEBUG
    }

    public void get(String url) {

        Call<ResponseBody> call = getRequest.get("https://www.baidu.com/");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("onresponse - ");
//                try {
//                    System.out.println(response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                try {
                    subscriberListener.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("onfailure - " + t);
                subscriberListener.onFault();
            }
        });
    }
}
