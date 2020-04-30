package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 오바라이드이기에 반드시 구현해야함, 제일 먼저 찾는 것이 onCreate이며 처음 시작할때 onCreate가 실행된다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // res/layout/activity_main.xml이 실행됨을 알 수가 있다. activity_main 이 아니라 activity_secnod로 만들어 할 수도 있다.
        setContentView(R.layout.activity_main);
    }

    // 전화걸기 버튼을 누르면 실행되는 메소드
    public void clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-5678"));
        // 화면을 띄어주는 명령
        startActivity(intent);
    }
}
