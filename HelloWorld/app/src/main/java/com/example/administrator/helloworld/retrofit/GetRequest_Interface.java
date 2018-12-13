package com.example.administrator.helloworld.retrofit;

import java.util.List;
import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/11/20.
 */

public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
//    @GET("vector/2006-4/2006420114643989.jpg")
//    @GET("/")
    Call<Translation> getCall();
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    rx.Observable<Translation> getCall2();

    @GET
    rx.Observable<HttpResult<List<Ads>>> getObservable(@Url String url);

    @GET
    Call<ResponseBody> get(@Url String url);

}
