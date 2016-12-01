package com.seb.sempleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.seb.debuger.DEBUG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DEBUG.e("error");
        DEBUG.i("info");
        DEBUG.d("debug");
        DEBUG.w("warning");
    }
}
