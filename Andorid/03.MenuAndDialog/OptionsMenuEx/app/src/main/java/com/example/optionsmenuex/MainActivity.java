package com.example.optionsmenuex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 옵션 메뉴 파일을 읽어서 자동으로 메뉴를 생성 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴 파일을 화면에 생성시켜주는 객체 생성
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);

        return true;
    }

    // 메뉴 선택 시 처리하는 메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){ // 선택된 메뉴 항목의 아이디별로 처리
            case R.id.new_game:
                newGame();
                return true;
            case R.id.file:
                createFile();
                return true;
            case R.id.open:
                OpenFile();
                return true;
            case R.id.help:
                showHelp();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void newGame(){
        Toast.makeText(getApplicationContext(), "새로운 게임 선택", Toast.LENGTH_SHORT).show();
    }

    public void createFile(){
        Toast.makeText(getApplicationContext(), "파일을 생성합니다.", Toast.LENGTH_LONG).show();
    }

    public void OpenFile(){
        Toast.makeText(getApplicationContext(), "파일을 open합니다.", Toast.LENGTH_SHORT).show();
    }

    public void showHelp(){
        Toast.makeText(getApplicationContext(), "도움말을 표시합니다.", Toast.LENGTH_SHORT).show();
    }
}
