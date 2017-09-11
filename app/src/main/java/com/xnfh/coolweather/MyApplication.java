package com.xnfh.coolweather;

import android.app.Application;

import org.litepal.LitePal;


/**
 * Created by wangxuewei on 2017/9/11.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

    }
}
