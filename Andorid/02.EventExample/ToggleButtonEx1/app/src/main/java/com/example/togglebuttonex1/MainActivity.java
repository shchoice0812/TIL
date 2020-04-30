package com.example.togglebuttonex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ImageView bulb;
    ToggleButton toggle;
    Switch switch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bulb = findViewById(R.id.bulb);
        toggle = findViewById(R.id.toggle);
        switch2 = findViewById(R.id.switch2);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.toggle: // 토글 버튼
                        if (isChecked) { // 토글 버튼이 On이 될 경우 전구 이미지를 켜진 이미지로 교체
                            bulb.setImageResource(R.drawable.bulb_on);
                            switch2.setChecked(true);
                        } else {
                            bulb.setImageResource((R.drawable.bulb_off));
                            switch2.setChecked(false);
                        }
                        break;

                    case R.id.switch2: // 스위치 버튼
                        if (isChecked){
                            bulb.setImageResource(R.drawable.bulb_on);
                            toggle.setChecked(true);
                        } else {
                            bulb.setImageResource(R.drawable.bulb_off);
                            toggle.setChecked(false);
                        }
                        break;
                }
            }
        };
        toggle.setOnCheckedChangeListener(listener);
        switch2.setOnCheckedChangeListener(listener);
    }
}
