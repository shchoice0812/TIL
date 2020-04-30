package com.example.singlechoicedialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout container;
    Button btnChange;
    int selectItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        btnChange = findViewById(R.id.change);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCheckDialog();
            }
        });
    }
    public void showCheckDialog() {
        //strings.xml에서 메뉴 목록을 읽어온다.
        String[] items = getResources().getStringArray(R.array.colorChoice);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("배경색을 선택하세요.");
        // 라디오 버튼을 포함하는 다이어로그 생성
        // 아래에 생성된 OnclickListener는 라디오 버튼을 선택 시 수행되는 이벤트 리스너
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() { // -1은 아무것도 선택을 하지 않았기에.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which : 선택된 라디오 버튼의 인덱스
                selectItem = which; // 선택된 라디오 버튼의 인덱스 저장
            }
        })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (selectItem){
                            case 0:
                                container.setBackgroundColor(Color.RED);
                                break;
                            case 1:
                                container.setBackgroundColor(Color.GREEN);
                                break;
                            case 2:
                                container.setBackgroundColor(Color.BLUE);
                                break;
                        }
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
    }
}
