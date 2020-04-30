package com.example.spinnerex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] items = {"여행", "도서", "음악", "수영", "등산", "요리"};
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        title = findViewById(R.id.select_item);

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        title.setText(items[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        title.setText(getResources().getString(R.string.spinner_title));
    }
}
