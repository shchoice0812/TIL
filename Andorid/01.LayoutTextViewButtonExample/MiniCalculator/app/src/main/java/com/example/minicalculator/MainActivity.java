package com.example.minicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etInput1, etInput2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput1 = findViewById(R.id.input1);
        etInput2 = findViewById(R.id.input2);

        tvResult = findViewById(R.id.tx_result);

    }

    // 더하기 버튼 클릭 시 수행되는 메소드
    public void add(View view) {
        // EditText에서 값을 읽어서 저장
        String strNum1 = etInput1.getText().toString();
        String strNum2 = etInput2.getText().toString();

        // 덧셈 수행
        int result = Integer.parseInt(strNum1) + Integer.parseInt(strNum2);

        tvResult.setText("계산 결과 : " + result);
    }

    public void sub(View view) {
        int num1 =Integer.parseInt((etInput1.getText().toString()));
        int num2 =Integer.parseInt((etInput1.getText().toString()));

        //int result = Integer.parseInt(strNum1) + Integer.parseInt(strNum2);
        //tvResult.setText("계산 결과: " + result);
        tvResult.setText("계산 결과: " + (num1 - num2));
    }

    public void mul(View view) {
        String stnNum1 = etInput1.getText().toString();
        String strNum2 = etInput2.getText().toString();

        int result = Integer.parseInt(stnNum1) * Integer.parseInt(strNum2);

        tvResult.setText("계산 결과: " + result);
    }


    public void div(View view) {
        double num1 =Double.parseDouble((etInput1.getText().toString()));
        double num2 =Double.parseDouble((etInput1.getText().toString()));

        tvResult.setText("계산 결과: " + (num1 / num2));
    }
}
