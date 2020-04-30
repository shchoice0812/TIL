package com.example.listviewex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] booksArr = {"장발장", "15소년 표류기", "주홍글씨", "로빈슨크루소", "톰소여의 모험",
                        "Java 프로그래밍", "HTML 기초", "안드로이드 프로그래밍", "JavaScript", "별자리 이야기",
                        "디자인 패턴", "자료구조", "알고리즘,", "게임 프로그래밍", "운영체제", "시스템 프로그래밍"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);

        // 데이터소스와 뷰를 제공할 어댑터 생성
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.list_item, booksArr);
        //안드로이드에서 제공하는 뷰 레이아웃 사용
        //ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, booksArr);

        // 위에서 생성한 어댑터를 ListView에 연결
        listView.setAdapter(adapter);
    }
}
