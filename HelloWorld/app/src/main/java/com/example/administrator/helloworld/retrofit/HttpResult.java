package com.example.administrator.helloworld.retrofit;

/**
 * Created by Administrator on 2018/1/15.
 */

public class HttpResult <T> {

    private int Code;
    private String ErrorDesc;

    private T Result;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getErrorDesc() {
        return ErrorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        ErrorDesc = errorDesc;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }
}
