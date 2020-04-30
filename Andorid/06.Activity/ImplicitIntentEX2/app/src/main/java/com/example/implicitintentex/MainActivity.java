package com.example.implicitintentex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class MainActivity<Butto> extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;

    Button btnAction;
    Spinner spinnerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 카메라 사용을 위한 권한 설정
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
        }

        String[] items = getResources().getStringArray(R.array.intents);

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMenu = findViewById(R.id.menu);
        spinnerMenu.setAdapter(adapter);

        btnAction = findViewById(R.id.confirm);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                // 스피너에 클릭한 위치를 구한다.
                int position = spinnerMenu.getSelectedItemPosition();

                switch(position){
                    case 0: // Browser Open
                        //암시적인 인텐트를 생성
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                        break;
                    case 1: // 전화 Dial을 표시하는 화면을 호출
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(010)1234-5667"));
                        break;
                    case 2: // 구글 지도를 보여주는 화면 호출
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.498149, 127.027701?z=17"));
                        break;
                    case 3: // 연락처 보기
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                        break;
                    case 4: // 연락처 편집
                        intent = new Intent(Intent.ACTION_EDIT, Uri.parse("content://contacts/people/1"));
                        break;
                    case 5: // 카메라 이미지 캡쳐
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        break;

                }
                if (intent != null){
                    startActivity(intent);
                }
            }
        });
    }
}
