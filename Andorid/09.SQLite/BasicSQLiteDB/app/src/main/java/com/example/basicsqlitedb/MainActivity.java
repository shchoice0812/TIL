package com.example.basicsqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        //myHandler.addStudent();


        // 입력항목
        etName = findViewById(R.id.edtName);
        etAddress = findViewById(R.id.edtAddress);
        // 출력항목
        etNameResult = findViewById(R.id.edtNameResult);
        etAddressResult = findViewById(R.id.edtAddressResult);

        btnDelete = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHandler.getWritableDatabase();
//                sqlDB.execSQL("INSERT INTO collegeTBL VALUES('"
//                        + etName.getText().toString()+"','"
//                        + etAddress.getText().toString()+"', 10)");

                ContentValues values = new ContentValues();
                values.put("name", etName.getText().toString());
                values.put("address", etAddress.getText().toString());
                values.put("grade", "6");

                sqlDB.insert("collegeTBL", null, values);

                sqlDB.close();

                Toast.makeText(MainActivity.this, "데이터가 입력되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHandler.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("select * from collegeTBL", null);

                // 조회된 커서의 레코드가 존재할 때까지 반복
                String strNames = "  이름 : \n" + "----------------------\n";
                String strAddress = "  주소 : \n" + "----------------------\n";

                cursor.moveToFirst();
                while(cursor.moveToNext()){
                    strNames += cursor.getString(cursor.getColumnIndex("name")) + "\n";
                    strAddress += cursor.getString(cursor.getColumnIndex("address")) + "\n";
                }

                etNameResult.setText(strNames);
                etAddressResult.setText(strAddress);

                cursor.close();
                sqlDB.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHandler.getWritableDatabase();
                myHandler.onUpgrade(sqlDB, 1,2);
                sqlDB.close();
            }
        });
    }
}

class DBHandler extends SQLiteOpenHelper {

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
