package com.example.sandykurniawan.skripsi_maps;

import android.view.View;

/**
 * Created by user on 16/12/2017.
 */


public interface ClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);

    void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept);
}