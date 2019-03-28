package com.example.ari.proba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ShareLokasi extends AppCompatActivity {
private EditText txt_lokasi, txt_lat, txt_long;
    private Button btn_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_lokasi);
        txt_lokasi = (EditText) findViewById(R.id.txtLokasi);
        txt_lat = (EditText) findViewById(R.id.txtLat);
        txt_long = (EditText) findViewById(R.id.txtLong);
        btn_share = (Button) findViewById(R.id.btn_share);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nama_lokasi = txt_lokasi.getText().toString().trim();
                final String data_lat = txt_lat.getText().toString().trim();
                final String data_long = txt_long.getText().toString().trim();

                class TambahData extends AsyncTask<Void, Void, String>{
                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(ShareLokasi.this,"Proses Kirim Data...","Wait...",false,false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(ShareLokasi.this, s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String,String> params = new HashMap<>();

                        params.put(config.KEY_EMP_LOKASI, nama_lokasi);
                        params.put(config.KEY_EMP_LAT, data_lat);
                        params.put(config.KEY_EMP_LONG, data_long);

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(config.URL_ADD, params);
                        return res;
                    }
                }
                TambahData tb = new TambahData();
                tb.execute();
            }
        });
    }
}
