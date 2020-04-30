package com.example.optionsmenubycode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        MenuItem item1 = menu.add(0, 1, 0, "사과");
        item1.setIcon(R.drawable.ic_launch_black_24dp);
        item1.setAlphabeticShortcut('a');

        // add(int groupId, int itemId, int order, CharSequence title)
        menu.add(0, 2, 0, "포도").setIcon(R.drawable.ic_launch_black_24dp);
        menu.add(0, 3, 0, "바나나").setIcon(R.drawable.ic_launch_black_24dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){ // 선택된 메뉴 항목의 아이디별로 처리
            case 1:
                showApple();
                return true;
            case 2:
                showGrape();
                return true;
            case 3:
                showBanana();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showApple(){
        Toast.makeText(getApplicationContext(), "포도 선택", Toast.LENGTH_SHORT).show();
    }

    public void showGrape(){
        Toast.makeText(getApplicationContext(), "사과 선택", Toast.LENGTH_LONG).show();
    }

    public void showBanana(){
        Toast.makeText(getApplicationContext(), "바나나 선택", Toast.LENGTH_SHORT).show();
    }
}
