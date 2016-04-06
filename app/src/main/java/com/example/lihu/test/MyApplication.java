package com.example.lihu.test;

import android.app.Application;

import com.lihu.androidreporter.core.Reporter;

/**
 * Created by lihu on 2016-3-30.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        ACRA.init(this);
        try {
            Reporter.get().init(this,null);
            Reporter.get().setDebugMode(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
