package com.example.eventprocex3;

import androidx.appcompat.app.AppCompatActivity;

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

        Button btnColor = findViewById(R.id.btn_color);
        final TextView tvOut = findViewById(R.id.text_color);

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int red = (int)(Math.random()*256);
                int green = (int)(Math.random()*256);
                int blue = (int)(Math.random()*256);
                tvOut.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });
    }
}
