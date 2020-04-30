package com.example.preferenceex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity<onClick> extends AppCompatActivity implements OnClickListener {

    public static final String myPrefs = "myprefs";
    public static final String NameKey = "name";
    public static final String EmailKey = "email";

    SharedPreferences prefs;
    Button btnSave, btnRetr, btnClear;
    TextView txvName, txvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvName = findViewById(R.id.etName);
        txvEmail = findViewById(R.id.etEmail);

        btnSave = findViewById(R.id.btnSave);
        btnClear = findViewById(R.id.btnClear);
        btnRetr = findViewById(R.id.btnRetr);

        btnSave.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnRetr.setOnClickListener(this);
        prefs = getSharedPreferences(myPrefs, MODE_PRIVATE);

    }
        public void onClick (View v){
            switch(v.getId()){
                case R.id.btnSave:
                    String name = txvName.getText().toString();
                    String email = txvEmail.getText().toString();

                    // 편집을 위한 에디터 객체를 얻는다.
                    SharedPreferences.Editor editor = prefs.edit();

                    editor.putString(NameKey, name);
                    editor.putString(EmailKey, email);
                    editor.commit();

                    Toast.makeText(MainActivity.this, "내용이 저장되었습니다.", Toast.LENGTH_SHORT).show();

                    txvName.setText("");
                    txvEmail.setText("");
                    break;

                case R.id.btnRetr:
                    if (prefs == null){
                        prefs = getSharedPreferences(myPrefs, MODE_PRIVATE);
                    } else{
                        txvName.setText(prefs.getString(NameKey, ""));
                        txvEmail.setText(prefs.getString(EmailKey, ""));
                    }
                    break;

                case R.id.btnClear:
                    txvName.setText("");
                    txvEmail.setText("");
                    break;
            }
        }
}
