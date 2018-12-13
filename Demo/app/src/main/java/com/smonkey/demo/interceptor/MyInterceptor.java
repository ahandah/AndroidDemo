package com.smonkey.demo.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }


}
