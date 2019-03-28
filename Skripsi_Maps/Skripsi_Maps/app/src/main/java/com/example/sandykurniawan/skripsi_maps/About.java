package com.example.sandykurniawan.skripsi_maps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class About extends Fragment {
    public About(){}
    View about;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , @Nullable ViewGroup container,@Nullable
            Bundle savedInstanceState) {
        getActivity().setTitle("About");
        return inflater.inflate(R.layout.about,container,false);

    }
}
