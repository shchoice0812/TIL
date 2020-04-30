package com.example.framentcommsample;


import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    ImageView imageView;

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_view, container, false);

        imageView = rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int resID){
        imageView.setImageResource(resID);
    }

}
