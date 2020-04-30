package com.example.contextactionmodeex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActionMode.Callback, View.OnLongClickListener{

    ActionMode mActionMode; // 현재 컨텍스트 메뉴가 켜져있는지 여부 저장
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text1 = findViewById(R.id.text1);
        text1.setOnLongClickListener(this); // View.OnLongCllickListener 객체가 있기에
    }

    // startActionMode()를 호출하여 액션모드가 생성될 때 호출된다.
    // 컨텍스트 메뉴가 생성된다.
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch(item.getItemId()){
            case R.id.share:
                Toast.makeText(getApplicationContext(), "공유 선택", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                Toast.makeText(getApplicationContext(), "검색 선택", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.edit:
                Toast.makeText(getApplicationContext(), "편집 선택", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    // 사용자가 액션 모드를 나가면 실행된다.
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
    }

    @Override
    public boolean onLongClick(View view) {
        if (mActionMode != null){  // 현재 컨텍스트 메뉴가 켜져있는 상태이므로 바로 리턴
            return false;
        }

        // 컨택스트 모드를 시작하는 콜벡 메소드 호출
        mActionMode = this.startActionMode(this);
        view.setSelected(true); // 현재 길게 눌려진 뷰를 컨텍스트 메뉴의 대상으로 설정

        return false;
    }
}
