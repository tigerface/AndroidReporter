package com.example.lihu.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.lihu.androidreporter.R;
import com.lihu.androidreporter.core.Reporter;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }

    public void click(View v){
        Reporter.get().log(TAG,"我点击了按钮");
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }


}

