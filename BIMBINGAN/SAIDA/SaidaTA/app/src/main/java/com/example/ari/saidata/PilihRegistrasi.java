package com.example.ari.saidata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PilihRegistrasi extends AppCompatActivity {
String[] listArray={"Register Staff","Register Mahasiswa","Register Instruktur","Register Pimpinan"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_registrasi);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.pilreg_listview, listArray);
        final ListView listView = (ListView) findViewById(R.id.array_list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pilih = listView.getItemAtPosition(position).toString();
                if(pilih == "Register Staff"){
                    Intent staff = new Intent(PilihRegistrasi.this, RegStaff.class);
                    startActivity(staff);
                }else if(pilih == "Register Mahasiswa"){
                    Intent mhs = new Intent(PilihRegistrasi.this, RegMahasiswa.class);
                    startActivity(mhs);
                }
            }
        });
    }
}
