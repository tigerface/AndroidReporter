package com.example.lihu.test;

import android.app.Activity;
import android.os.Bundle;

import com.lihu.androidreporter.core.Reporter;

/**
 * Created by lihu on 2016-4-5.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Reporter.log(this.getClass().getSimpleName(),"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Reporter.log(this.getClass().getSimpleName(),"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Reporter.log(this.getClass().getSimpleName(),"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Reporter.log(this.getClass().getSimpleName(),"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Reporter.log(this.getClass().getSimpleName(),"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Reporter.log(this.getClass().getSimpleName(),"onDestroy");
    }


}
