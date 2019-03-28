package com.example.ari.saidata;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class hasil_servqual extends AppCompatActivity {
TableLayout tb_hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_servqual);
        tb_hasil = (TableLayout) findViewById(R.id.tablelayoutid);
        tabel_hasil();
    }


    public void tabel_hasil(){
        TableRow newRow = new TableRow(hasil_servqual.this);

        TextView HasilA = new TextView(hasil_servqual.this);
        TextView HasilA2 = new TextView(hasil_servqual.this);
        TextView n_b1 = new TextView(hasil_servqual.this);
        TextView n_b2 = new TextView(hasil_servqual.this);
        TextView n_b3 = new TextView(hasil_servqual.this);
        TextView n_b4 = new TextView(hasil_servqual.this);
        TextView n_b5 = new TextView(hasil_servqual.this);
        TextView n_ch = new TextView(hasil_servqual.this);
        TextView n_dh = new TextView(hasil_servqual.this);
        TextView n_servqual = new TextView(hasil_servqual.this);

        HasilA.setText(Tampil.hasilA.toString());
        HasilA2.setText(Tampil.hasilA2.toString());
        n_b1.setText(Tampil.nilaiB1.toString());
        n_b2.setText(Tampil.nilaiB2.toString());
        n_b3.setText(Tampil.nilaiB3.toString());
        n_b4.setText(Tampil.nilaiB4.toString());
        n_b5.setText(Tampil.nilaiB5.toString());
        n_ch.setText(Tampil.nilaiCH.toString());
        n_dh.setText(Tampil.nilaiDH.toString());
        n_servqual.setText(Tampil.hasilservqual.toString());

        newRow.addView(HasilA);
        newRow.addView(HasilA2);
        newRow.addView(n_b1);
        newRow.addView(n_b2);
        newRow.addView(n_b3);
        newRow.addView(n_b4);
        newRow.addView(n_b5);
        newRow.addView(n_ch);
        newRow.addView(n_dh);
        newRow.addView(n_servqual);

        tb_hasil.addView(newRow);
    }
}
