package com.polda.ari.abdi;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class Md_pilihan extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_pilihan);

        TabHost tabhost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, Md_riwayat.class);//content pada tab yang akan kita buat
        spec = tabhost.newTabSpec("hadir").setIndicator("HADIR",null).setContent(intent);//mengeset nama tab dan mengisi content pada menu tab anda.
        tabhost.addTab(spec);//untuk membuat tabbaru disini bisa diatur sesuai keinginan anda

        intent = new Intent().setClass(this, Md_dt_ijin.class);
        spec = tabhost.newTabSpec("ijin").setIndicator("IJIN",null).setContent(intent);
        tabhost.addTab(spec);

    }
}
