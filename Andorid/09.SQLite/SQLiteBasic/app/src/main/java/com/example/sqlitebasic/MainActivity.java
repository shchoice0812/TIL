package com.example.sqlitebasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DBHandler myHandler;    //데이터베이스 upgrade, create 관리
    SQLiteDatabase sqlDB;   // 데이터베이스의 데이터를 처리
    EditText etName, etAddress, etNameResult, etAddressResult;
    Button btnDelete, btnInsert, btnSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHandler = new DBHandler(MainActivity.this);


        // 입력항목
        etName = findViewById(R.id.edtName);
        etAddress = findViewById(R.id.edtAddress);
        // 출력항목
        etNameResult = findViewById(R.id.edtNameResult);
        etAddressResult = findViewById(R.id.edtAddressResult);

        btnDelete = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);

        // myHandler.addStudent();
    }
}

class DBHandler extends SQLiteOpenHelper{

    public DBHandler(Context context){
        super(context, "testDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String COLLEGE_TABLE = "CREATE TABLE collegeTBL(name text, " +
                " address text, grade text)";
        db.execSQL(COLLEGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS collegeTBL");
        onCreate(db);
    }

    public void addStudent(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO collegeTBL values ('홍길동', '서울', '10')");
        db.execSQL("INSERT INTO collegeTBL values ('이순신', '서울', '30')");
    }
}