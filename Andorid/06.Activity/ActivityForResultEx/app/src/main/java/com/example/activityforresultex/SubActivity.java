package com.example.activityforresultex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        etInput = findViewById(R.id.input);
        Button btnOk = findViewById(R.id.ok);
        // '확인' 버튼 클릭에 대한 처리
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outStr = etInput.getText().toString();
                System.out.println(outStr);
                if(TextUtils.isEmpty(outStr)){
                    Toast.makeText(SubActivity.this, "문장을 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else{
                    // 입력 내용을 메인 엑티비티로 전달
                    Intent data = new Intent();
                    data.putExtra("response", outStr);

                    // 엑티비티 응답 처리
                    setResult(RESULT_OK, data);
                    // 현재 엑티비티 종료
                    finish();
                }
            }
        });

        Button btnCancel = findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
