package com.example.administrator.helloworld.retrofit;

/**
 * Created by Administrator on 2018/1/16.
 */

public class ApiException extends RuntimeException {

    public ApiException(int code) {
        super(getApiExceptionMessage(code));
    }

    public ApiException(String message) {
        super(message);
    }

    private static String getApiExceptionMessage(int code) {

        String message = "";
        switch (code) {
            default:
                message = "未知错误,代码:" + code;
        }
        return message;
    }
}
