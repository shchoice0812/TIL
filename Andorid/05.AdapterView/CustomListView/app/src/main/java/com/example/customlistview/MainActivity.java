package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String titles[];
    Integer[] images = {
            R.drawable.movie1,
            R.drawable.movie2,
            R.drawable.movie3,
            R.drawable.movie4,
            R.drawable.movie5,
            R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7 };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        titles = getResources().getStringArray(R.array.titles);

        listView = findViewById(R.id.list);

        CustomAdapter adapter = new CustomAdapter(MainActivity.this);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, titles[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdapter extends BaseAdapter {
        private final Activity context;

        public CustomAdapter(Activity context) {
            //super();
            this.context = context;
        }
        // ListView에서 표시할 항목의 갯수를 반환
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /*
         * 데이터 소스에서 데이터를 찾아서 뷰를 생성하고 만든 뷰를 반환
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.list_item, null, false);

            // list_item.xml에 들어 있는 각 뷰의 객체를 생성
            ImageView imageView = rowView.findViewById(R.id.poster);
            TextView  title = rowView.findViewById(R.id.title);
            TextView  rating = rowView.findViewById(R.id.rating);
            TextView  genre  = rowView.findViewById(R.id.genre);
            TextView  releaseYear = rowView.findViewById(R.id.releaseYear);

            // 위에서 생성한 각 뷰 항목에 데이터를 설정해서 반환
            imageView.setImageResource(images[position]);
            title.setText(titles[position]);
            rating.setText("9.0"+position);
            genre.setText("DRAMA");
            releaseYear.setText("193"+position);

            return rowView;
        }
    }

}












