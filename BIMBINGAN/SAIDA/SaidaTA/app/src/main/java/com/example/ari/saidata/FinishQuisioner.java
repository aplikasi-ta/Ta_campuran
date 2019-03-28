package com.example.ari.saidata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishQuisioner extends AppCompatActivity {
TextView txt_terima;
    Button btn_selesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quisioner);
        txt_terima = (TextView) findViewById(R.id.txt_Terimakasih);
        btn_selesai = (Button) findViewById(R.id.btn_keluar);

        txt_terima.setText("Terima kasih pengguna dengan NPM : "+PilihTAQuiz.txt_npmmhs.getText().toString().trim());

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(FinishQuisioner.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
