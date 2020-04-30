package com.example.basicalertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStop = findViewById(R.id.stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 대화상자를 실행한다.
                // 다이어로그의 형태를 생성
                // 커스텀 테마 설정 하기전 코드 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme);
                builder.setTitle("종료 확인")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setCancelable(true) // 대화상자 밖을 누르면 없어지게 하려면 true, 그렇지 않으면 false
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish(); // activity 화면 종료
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                // 다이어로그 객체 생성
                AlertDialog dialog = builder.create();
                dialog.show(); // 화면에 다이어로그 생성
            }
        });
    }
}
