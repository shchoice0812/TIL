package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isFragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragmentBorC) != null){
            FragmentManager fm = getSupportFragmentManager();

            // Fragment 트랜잭션 시작
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            // FrameLayout에 FragmentB 추가
            fragmentTransaction.add(R.id.fragmentBorC, new FragmentB());
            // 트랜잭션 완료
            fragmentTransaction.commit();
        }

        Button btnChange = findViewById(R.id.change);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment();
            }
        });
    }

    private void switchFragment() {
        Fragment curFragment; // 현재 표시된 프래그먼트 저장

        FragmentB fragmentB = new FragmentB();
        FragmentC fragmentC = new FragmentC();

        isFragmentB = !isFragmentB;

        if(isFragmentB){
            curFragment = fragmentC;
        } else {
            curFragment = fragmentB;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        // 프래그먼트를 교체한다.
        fragmentTransaction.replace(R.id.fragmentBorC, curFragment);
        fragmentTransaction.commit();
    }
}
