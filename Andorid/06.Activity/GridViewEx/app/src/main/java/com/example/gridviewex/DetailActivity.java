package com.example.gridviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Integer[] bookCovers = {R.drawable.book_img1,  R.drawable.book_img2, R.drawable.book_img3,
            R.drawable.book_img4,  R.drawable.book_img5, R.drawable.book_img6,
            R.drawable.book_img7,  R.drawable.book_img8, R.drawable.book_img9,
            R.drawable.book_img10,  R.drawable.book_img11, R.drawable.book_img12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ivCover = findViewById(R.id.book_cover);
        TextView txvTitle = findViewById(R.id.book_title);
        TextView txvAuthor = findViewById(R.id.book_author);

        Intent intent = getIntent();    // MainActivity에서 인텐트 객체를 얻어온다.
        System.out.println("Image index="+intent.getStringExtra("cover_index"));
        int index = intent.getIntExtra("cover_index", 0);
        ivCover.setImageResource(bookCovers[index]);
        txvTitle.setText(intent.getStringExtra("title"));
        txvAuthor.setText(intent.getStringExtra("author"));
    }
}
