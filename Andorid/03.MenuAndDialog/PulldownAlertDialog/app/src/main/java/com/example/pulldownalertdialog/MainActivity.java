package com.example.pulldownalertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView title = findViewById(R.id.title);

        Button btnChange = findViewById(R.id.change);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메뉴 목록에 사용할 배열 생성
                CharSequence[] items = {"Orange", "LightGreen", "LightBlue"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("색상을 선택하십시오:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 매개변수: which - 선택된 메뉴의 인덱스 번호
                        switch(which){
                            case 0:
                                title.setBackgroundColor(getResources().getColor(R.color.orange));
                                break;
                            case 1:
                                title.setBackgroundColor(getResources().getColor(R.color.light_green));
                                break;
                            case 2:
                                title.setBackgroundColor(getResources().getColor(R.color.light_blue));
                                break;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
