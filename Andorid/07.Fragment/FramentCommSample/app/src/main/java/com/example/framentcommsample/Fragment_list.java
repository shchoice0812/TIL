package com.example.framentcommsample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Fragment_list extends Fragment {
    String[] values = {"첫번째 이미지", "두번째 이미지", "세번째 이미지"};

    public static interface ImageSelectedCallback{
        public void onImageSelected(int position);
    }

    public ImageSelectedCallback callback;

    /*
     * 프레그먼트가 생성이 되어 액티비티에 포함이 될 경우 호출되는 메소드
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ImageSelectedCallback){
            callback = (ImageSelectedCallback)context; // context MainActivity를 참조하고 있음.
        }
    }

    public Fragment_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = rootView.findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, values);

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
