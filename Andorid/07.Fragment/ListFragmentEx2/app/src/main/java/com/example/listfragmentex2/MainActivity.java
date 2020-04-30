package com.example.listfragmentex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ListFragment listFragment;
    ViewFragment viewFragment;

    int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        listFragment = (ListFragment)manager.findFragmentById(R.id.listFragment);
        viewFragment = (ViewFragment)manager.findFragmentById(R.id.viewFragment);
    }

    @Override
    public void onImageSelected(int position) {
        // 리스트프래그먼트의 메뉴에서 선택된 내용을 뷰프래그먼트에 전달
        viewFragment.setImage(images[position]);
    }
}
