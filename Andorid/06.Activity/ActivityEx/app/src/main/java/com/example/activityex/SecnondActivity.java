package com.example.activityex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecnondActivity extends AppCompatActivity {

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secnond);

        message = findViewById(R.id.message);

        Intent data = getIntent(); // 앞에서 전송된 인텐트 정보를 얻어온다.
        message.setText(data.getStringExtra("message"));

        Button btnBack = findViewById(R.id.button2);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SecnondActivity.this, MainActivity.class);
//                startActivity(intent);
                // 위에 문장은 계속 Stack에 쌓이니 아래 코드로 실행
                finish();
            }
        });


    }
}
