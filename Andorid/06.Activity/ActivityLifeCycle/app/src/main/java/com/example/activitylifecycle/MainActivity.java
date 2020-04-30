package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="LifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate() 호출");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() 호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() 호출");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() 호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() 호출");
    }
}









