package com.example.textviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        // XML 파일에 있는 EditText 객체 인식작업
        EditText editText = findViewById(R.id.input1);
        // EditText에서 입력한 내용을 문자열 변수에 저장
        String inputStr = editText.getText().toString();

        System.out.println("입력한 내용:" + inputStr);

        // 금방 떴다가 사라진다.
        Toast.makeText(getApplicationContext(), inputStr, Toast.LENGTH_LONG).show();
    }
}
