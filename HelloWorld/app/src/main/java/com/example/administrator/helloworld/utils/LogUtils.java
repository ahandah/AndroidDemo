package com.example.administrator.helloworld.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2018/3/27.
 */

public class LogUtils {

    private static final int V = 0, D = 1, I = 2, W = 3, E = 4;
    private static String TAG;
    private static boolean isLogcat;

    public static void init() {
        TAG = "LogUtils";
        isLogcat = true;
    }
    public static void init(String tag) {
        TAG = tag;
        isLogcat = true;
    }

    private static String generateTag(String tag) {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stacks[5];
        String format = "Tag[" + tag + "]-%s-[%s, %d]";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        return String.format(format, callerClazzName, caller.getMethodName(), caller.getLineNumber());
    }

    public static void setIsLogcat(boolean logcat) {
        isLogcat = logcat;
    }

    public static void v(String msg) {
        printLog(V,  msg, null);
    }

    public static void v(String msg, Throwable t) {
        printLog(V, msg, t);
    }

    public static void d(String msg) {
        printLog(D, msg, null);
    }

    public static void d(String msg, Throwable t) {
        printLog(D, msg, t);
    }

    public static void i(String msg) {
        printLog(I, msg, null);
    }

    public static void i(String msg, Throwable t) {
        printLog(I, msg, t);
    }

    public static void w(String msg) {
        printLog(W, msg, null);
    }

    public static void w(String msg, Throwable t) {
        printLog(W, msg, t);
    }

    public static void e(String msg) {
        printLog(E, msg, null);
    }

    public static void e(String msg, Throwable t) {
        printLog(E, msg, t);
    }

    private static void printLog(int type, String msg, Throwable t) {

        if (isLogcat) {
            switch (type) {
                case V:
                    Log.v(generateTag(TAG), msg, t);
                    break;
                case D:
                    Log.d(generateTag(TAG), msg, t);
                    break;
                case I:
                    Log.i(generateTag(TAG), msg, t);
                    break;
                case W:
                    Log.w(generateTag(TAG), msg, t);
                    break;
                case E:
                    Log.e(generateTag(TAG), msg, t);
                    break;
            }

        }

    }

}
