package com.example.activityex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.button1);
        // 익명의 클래스
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 두번째 액티비티 실행
                Intent intent = new Intent(MainActivity.this, SecnondActivity.class);
                intent.putExtra("message", "안녕하세요!");
                startActivity(intent);
            }
        });
    }
}
