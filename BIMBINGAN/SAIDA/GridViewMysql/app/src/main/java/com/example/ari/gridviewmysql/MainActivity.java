package com.example.ari.gridviewmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Web api url
    public static final String DATA_URL = "http://probalam.com/probal_server/Beranda2.php";

    //Tag values to read from json
    public static final String TAG_IMAGE_URL = "photo";
    public static final String TAG_NAME = "nama_lokasi";

    //GridView Object
    private GridView gridView;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
    }
}
