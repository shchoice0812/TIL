package com.example.eventprocex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOut;
    Button btnOk, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = findViewById(R.id.text_out);
        btnOk = findViewById(R.id.button_ok);
        btnCancel = findViewById(R.id.button_cancel);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                tvOut.setText("'확인' 버튼을 클릭하였습니다.");
                break;

            case R.id.button_cancel:
                tvOut.setText("'취소' 버튼을 클릭하였습니다.");
                break;
        }
    }
}
