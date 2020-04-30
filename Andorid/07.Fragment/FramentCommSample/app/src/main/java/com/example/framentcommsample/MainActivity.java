package com.example.framentcommsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment_list.ImageSelectedCallback {

    ListFragment listFragment;
    ViewFragment viewFragment;

    int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        listFragment = (ListFragment)manager.findFragmentById(R.id.listFrament);
        viewFragment = (ViewFragment)manager.findFragmentById(R.id.ViewFrament);
    }


    @Override
    public void onImageSelected(int position) {
        viewFragment.setImage(images[position]);
    }
}
