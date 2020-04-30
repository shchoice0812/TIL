package com.example.inputwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPhone, etEmail;
    CheckBox chkItem1, chkItem2, chkItem3;

    Button btnShow;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.name);
        etPhone = findViewById(R.id.phone);
        etEmail = findViewById(R.id.email);

        chkItem1 = findViewById(R.id.checkBox1);
        chkItem2 = findViewById(R.id.checkBox2);
        chkItem3 = findViewById(R.id.checkBox3);

        btnShow = findViewById(R.id.show_button);
        tvOutput = findViewById(R.id.output);

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                sb.append("이름: " + etName.getText().toString()+"\n");
                sb.append("전화번호: " + etPhone.getText().toString()+"\n");
                sb.append("email: " + etEmail.getText().toString()+"\n");
                sb.append("좋아하는 스포츠: ");
                if (chkItem1.isChecked()){
                    sb.append("테니스, ");
                }
                if(chkItem2.isChecked()){
                    sb.append("축구, ");
                }
                if (chkItem3.isChecked()){
                    sb.append("농구, ");
                }
                tvOutput.setText(sb.toString());
            }
        });

        CheckConfirm checkListener  = new CheckConfirm();
        chkItem1.setOnCheckedChangeListener(checkListener);
        chkItem2.setOnCheckedChangeListener(checkListener);
        chkItem3.setOnCheckedChangeListener(checkListener);
    }

    // 체크박스가 체크되거나 체크해지될 때 호출되는 콜백 메소드
    class CheckConfirm implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton view, boolean isChecked) {
            // 어던 체크박스를 체크했는지 알려주는 뷰 ID
            switch(view.getId()){
                case R.id.checkBox1:
                    if (isChecked){
                        Toast.makeText(getApplicationContext(), chkItem1.getText().toString()+":선택", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), chkItem1.getText().toString()+":선택해제", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.checkBox2:
                    if (isChecked){
                        Toast.makeText(getApplicationContext(), chkItem2.getText().toString()+":선택", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), chkItem2.getText().toString()+":선택해제", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.checkBox3:
                    if (isChecked){
                        Toast.makeText(getApplicationContext(), chkItem3.getText().toString()+":선택", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), chkItem3.getText().toString()+":선택해제", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}
