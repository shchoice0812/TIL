package com.example.radiobuttonex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup1;
    RadioButton rdRed, rdBlack, rdWhite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup1 = findViewById(R.id.radio_grp1);
        rdRed = findViewById(R.id.rd_red);
        rdBlack = findViewById(R.id.rd_black);
        rdWhite = findViewById(R.id.rd_white);

        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String color = "";
                switch (checkedId){
                    case R.id.rd_red:
                        color = rdRed.getText().toString();
                        break;
                    case R.id.rd_black:
                        color = rdBlack.getText().toString();
                        break;
                    case R.id.rd_white:
                        color = rdWhite.getText().toString();
                        break;

                }

                Toast.makeText(getApplicationContext(), "색상선택: "+color, Toast.LENGTH_SHORT).show();
            }
        };

        radioGroup1.setOnCheckedChangeListener(listener);
    }
}
