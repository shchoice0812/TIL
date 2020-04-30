package com.example.bookdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookdatabase.helper.MySQLiteHelper;



    public class AddActivity extends AppCompatActivity {

        MySQLiteHelper db;
        EditText inputTitle, inputAuthor, inputPress;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);

            db = new MySQLiteHelper(this);

            inputTitle = (EditText) findViewById(R.id.in_title);
            inputAuthor = (EditText) findViewById(R.id.in_author);
            inputPress = (EditText) findViewById(R.id.in_press);
        }

        public void book_add(View view) {
            Book book = new Book();

            if (validationCheck()) {
                book.setTitle(String.valueOf(inputTitle.getText()));
                book.setAuthor(String.valueOf(inputAuthor.getText()));
                book.setPress(String.valueOf(inputPress.getText()));

                db.addBook(book);

                Toast.makeText(getApplicationContext(), "도서정보가 저장되었습니다!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        public boolean validationCheck() {
            boolean result = true;

            if (inputTitle.getText().equals("")) {
                Toast.makeText(getApplicationContext(), "도서 제목을 입력하세요",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            if (inputAuthor.getText().equals("")) {
                Toast.makeText(getApplicationContext(), "저자명을 입력하세요",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            if (inputPress.getText().equals("")) {
                Toast.makeText(getApplicationContext(), "출판사명을 입력하세요",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            return result;
        }
    }
