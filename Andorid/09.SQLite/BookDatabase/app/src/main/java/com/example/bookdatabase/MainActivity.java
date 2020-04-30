package com.example.bookdatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookdatabase.helper.MySQLiteHelper;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper db;
    Button btnAdd, btnDelete, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.button_add);
        btnDelete = (Button) findViewById(R.id.button_delete);
        btnSearch = (Button) findViewById(R.id.button_search);

        db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Books
        /*db.addBook(new Book("Android Application Development Cookbook", "Wei Meng Lee", "Prentice Hall"));
        db.addBook(new Book("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips and Brian Hardy", "DongAh"));
        db.addBook(new Book("Learn Android App Development", "Wallace Jackson", "Longman"));
        */
        // get all books
        //List<Book> list = db.getAllBooks();

        // delete one book
        //db.deleteBook(list.get(0));

        // get all books
        //db.getAllBooks();
    }

    public void search(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void search_all(View view) {
        Intent intent = new Intent(this, BooklistActivity.class);
        startActivity(intent);
    }

    public void add(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("도서정보 삭제확인")
                .setMessage("도서정보를 삭제하십니까?")
                .setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteAll();
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

        Toast.makeText(getApplicationContext(), "도서정보가 모두 삭제되었습니다.",
                Toast.LENGTH_SHORT).show();
    }
}
