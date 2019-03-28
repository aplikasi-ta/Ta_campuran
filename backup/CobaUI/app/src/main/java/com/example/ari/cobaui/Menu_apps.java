package com.example.ari.cobaui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu_apps extends AppCompatActivity {
ImageButton im_diary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_apps);
        im_diary = (ImageButton) findViewById(R.id.btn_diary);
        im_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_apps.this, Test.class);
                startActivity(intent);
            }
        });

    }
}
