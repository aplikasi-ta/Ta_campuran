package com.example.ari.saidata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TahunAjaran extends AppCompatActivity {
    public static String npmMhs;
    String[] listArray={"20162"};
    public static String tahun_ajaran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahun_ajaran);


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.pil_tahun_ajaran, listArray);
        final ListView listViewTA = (ListView) findViewById(R.id.array_list_ta);
        listViewTA.setAdapter(adapter);


        listViewTA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pilih = listViewTA.getItemAtPosition(position).toString();
                Intent Quiz1 = new Intent(TahunAjaran.this, q1.class);
                tahun_ajaran = pilih;
                Quiz1.putExtra(tahun_ajaran, tahun_ajaran);
                startActivity(Quiz1);
            }
        });
    }
}
