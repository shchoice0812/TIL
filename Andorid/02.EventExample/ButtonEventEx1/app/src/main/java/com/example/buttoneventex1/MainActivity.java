package com.example.buttoneventex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 전역변수 선언
    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = findViewById(R.id.text_out);
        Button btnOk = findViewById(R.id.button_ok);
        Button btnCancel = findViewById(R.id.button_cancel);

        /*
        // (1) 무명의 이벤트 리스너 클래스를 톻해 등록하는 방법
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("'확인' 버튼을 클릭하였습니다.");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("'취소' 버튼을 클릭하였습니다.");
            }
        });
        */

        // (2) View.OnClickListener 상속을 통한 구현 방법
        ButtonClickListener listener = new ButtonClickListener();
        LongClickListener long_listener = new LongClickListener();

        // 각 위젯에 이벤트 리스너 등록
        btnOk.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
        btnOk.setOnLongClickListener(long_listener);
        btnCancel.setOnLongClickListener(long_listener);
    }


    class ButtonClickListener implements View.OnClickListener {
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

    class LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.button_ok:
                    tvOut.setText("확인 버튼을 길게 눌렀습니다.");
                    break;
                case R.id.button_cancel:
                    tvOut.setText("취소 버튼을 길게 눌렀습니다.");
                    break;
            }
            return true;
        }
    }
}
