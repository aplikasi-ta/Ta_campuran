package com.example.ari.cekdozen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginApps extends AppCompatActivity {
Button btn_reg_doz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_apps);
        btn_reg_doz = (Button) findViewById(R.id.btn_reg);

        btn_reg_doz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginApps.this, RegDozen.class);
                startActivity(intent);
            }
        });
    }
}
