package com.example.zhangjiawen.education.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangjiawen on 2017/1/23.
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * 静态方法以供全局调用Application的context
     *
     * @return Application的Context对象
     */
    public static Context getContext() {
        return context;
    }
}
