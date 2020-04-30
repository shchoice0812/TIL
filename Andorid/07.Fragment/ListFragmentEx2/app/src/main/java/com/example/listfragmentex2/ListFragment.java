package com.example.listfragmentex2;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    String[] values = {"첫번째 이미지", "두번째 이미지", "세번째 이미지"};

    public static interface ImageSelectedCallback {
        public void onImageSelected(int postion);
    }

    public ImageSelectedCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ImageSelectedCallback) {
            callback = (ImageSelectedCallback) context;
        }
    }


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        ListView listView = rootView.findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                 *  리스트 항목 클릭 시, ViewFragment와 통신 구현
                 */
                if (callback != null) {
                    // MainActivity로 선택된 인덱스를 넘겨준다.
                    callback.onImageSelected(position);
                }
            }
        });

        return rootView;
    }
}
