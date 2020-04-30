package com.example.savedandrestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txvCount;
    Button btnIncrease;
    Button btnDecrease;
    CheckBox check1, check2;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvCount = findViewById(R.id.count);
        btnIncrease = findViewById(R.id.increase);
        btnDecrease = findViewById(R.id.decrease);

        //증가버튼 누를때
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                txvCount.setText("현재 개수 = " + count);
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if (count < 0) count =0;
                txvCount.setText("현재 개수 = " + count);
            }
        });

        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);

        if (savedInstanceState != null){
            txvCount.setText("현재 갯수 = " + savedInstanceState.getInt("count"));

            check1.setChecked(savedInstanceState.getBoolean("check1"));
            check2.setChecked(savedInstanceState.getBoolean("check2"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        boolean chk1 = false, chk2 = false;
        outState.putInt("count", count);
        chk1 = check1.isChecked();
        outState.putBoolean("check1", chk1);
        chk2 = check2.isChecked();
        outState.putBoolean("check2", chk2);
    }
}