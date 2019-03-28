package com.example.ari.saidata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PilihHasil extends AppCompatActivity {

    String[] listArray={"Persepsi","Harapan"};
    public static String tahun_ajaran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_hasil);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.pil_tahun_ajaran, listArray);
        final ListView listViewTA = (ListView) findViewById(R.id.array_list_ta);
        listViewTA.setAdapter(adapter);


        listViewTA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pilih = listViewTA.getItemAtPosition(position).toString();
                if(pilih=="Persepsi"){
                    Intent P = new Intent(PilihHasil.this, Tampil2.class);
                    startActivity(P);
                }else if(pilih=="Harapan"){
                    Intent h = new Intent(PilihHasil.this, Tampil.class);
                    startActivity(h);
                }
            }
        });
    }
}
