package com.example.activityforresultex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int REQ_OUTPUT = 100;
    TextView txvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = findViewById(R.id.send);
        txvOut = findViewById(R.id.response);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                // 두번째 엑티비티를 호출
                startActivityForResult(intent, REQ_OUTPUT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_OUTPUT){
            if (resultCode == RESULT_OK){
                System.out.println(data.getStringExtra("response"));
                txvOut.setText(data.getStringExtra("response"));
            } else {
                Toast.makeText(MainActivity.this, "응답을 받지 못하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
