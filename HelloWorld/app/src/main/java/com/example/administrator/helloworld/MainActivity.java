package com.example.administrator.helloworld;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.helloworld.eventBus.FirstEventBus;
import com.example.administrator.helloworld.retrofit.Ads;
import com.example.administrator.helloworld.retrofit.GetRequest_Interface;
import com.example.administrator.helloworld.retrofit.HttpResult;
import com.example.administrator.helloworld.retrofit.LoadingDialog;
import com.example.administrator.helloworld.retrofit.MyRetrofit;
import com.example.administrator.helloworld.retrofit.SubscriberListener;
import com.example.administrator.helloworld.retrofit.Translation;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SubscriberListener {

    private TextView tv;
    public static final String URL = "http://t.968967.com:8019/API/";
    private MyRetrofit myRetrofit;

    private SubscriberListener subscriberListener = new SubscriberListener() {
        @Override
        public void onSuccess(Object object) {
            List<Ads> list = (List<Ads>) object;
            System.out.println("ImageUrl---" + list.get(0).getImageUrl());
        }

        @Override
        public void onFault() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if ()
        EventBus.getDefault().register(this);
        tv = findViewById(R.id.tv);
        myRetrofit = new MyRetrofit(MainActivity.this, subscriberListener);

//        request();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void btnClick(View view){
//        Intent intent = new Intent(this, Main2Activity.class);
//        startActivity(intent);
        myRetrofit.retrofit();
//        retrofit();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MainThread(FirstEventBus firstEventBus){
        String msg = "onEventMainThread收到了消息：" + firstEventBus.getMsg();
        System.out.println("");
        Log.d("harvic", msg);
        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void MainThread2(FirstEventBus firstEventBus){
        String msg = "onEventMainThread2收到了消息：" + firstEventBus.getMsg();
        Log.d("harvic2", msg);
        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    public void retrofit() {
        final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface getRequest = retrofit.create(GetRequest_Interface.class);
        rx.Observable<HttpResult<List<Ads>>> observable = getRequest.getObservable("Act604");
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<HttpResult<List<Ads>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        System.out.println("throwable----e---"+e);
                    }

                    @Override
                    public void onNext(HttpResult<List<Ads>> responseBody) {
                        System.out.println("responseBody--" + responseBody.getResult().get(0).getImageUrl());
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        System.out.println("start--------------------------------------");
                        loadingDialog.show();
                    }
                });

    }

    @Override
    public void onSuccess(Object object) {

//        List<Ads> list = ((HttpResult<List<Ads>>) object).getResult();
        System.out.println(((List<Ads>)object).get(0).getImageUrl());
    }

    @Override
    public void onFault() {

    }

//    public void request() {
//
//        //步骤4:创建Retrofit对象
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
////                .baseUrl("http://img.sootuu.com/")
////                .baseUrl("https://www.baidu.com")
////                .addConverterFactory(new Converter.Factory() {
////                    @Override
////                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
////                        return new Converter<ResponseBody, Object>() {
////                            @Override
////                            public Object convert(ResponseBody value) throws IOException {
////                                return value.string();
////                            }
////                        };
////                    }
////                })
//                .addConverterFactory(GsonConverterFactory.create())//设置使用Gson解析(记得加入依赖)
//                .build();
//
//        // 步骤5:创建 网络请求接口 的实例
//        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
//
//        //对 发送请求 进行封装
//        Call<Translation> call = request.getCall();
//
//        //步骤6:发送网络请求(异步)
//        call.enqueue(new Callback<Translation>() {
////            @Override
////            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
////
////                System.out.println("---2--"+response.body());
////            }
////
////            @Override
////            public void onFailure(Call<RequestBody> call, Throwable t) {
////                System.out.println("连接失败"+t);
////            }
//
//            //请求成功时回调
//            @Override
//            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                // 步骤7：处理返回的数据结果
//                response.body().show();
//            }
//
//            //请求失败时回调
//            @Override
//            public void onFailure(Call<Translation> call, Throwable throwable) {
//                System.out.println("连接失败");
//            }
//
//        });
//    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
