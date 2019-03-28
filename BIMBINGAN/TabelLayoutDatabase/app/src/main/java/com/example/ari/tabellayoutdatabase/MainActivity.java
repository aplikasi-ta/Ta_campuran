package com.example.ari.tabellayoutdatabase;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        table = (TableLayout) findViewById(R.id.tbHasil);

       tabelData();

    }

    void tabelData(){
        TableRow newRow = new TableRow(MainActivity.this);

        TextView tahun = new TextView(MainActivity.this);
        TextView jumTahun = new TextView(MainActivity.this);
        TextView suppTahun = new TextView(MainActivity.this);
        TextView confTahun = new TextView(MainActivity.this);

        tahun.setText("1998");
        jumTahun.setText("90");
        suppTahun.setText("900");
        confTahun.setText("0.90");

        newRow.addView(tahun);
        newRow.addView(jumTahun);
        newRow.addView(suppTahun);
        newRow.addView(confTahun);

        table.addView(newRow);
    }
}
