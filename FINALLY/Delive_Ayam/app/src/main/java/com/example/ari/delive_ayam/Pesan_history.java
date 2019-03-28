package com.example.ari.delive_ayam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Pesan_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_history);
        getJSONTabel(Menu_pelanggan.txt_usr.getText().toString().trim());
    }


    private void getJSONTabel(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pesan_history.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(getApplication(),"Data "+s,Toast.LENGTH_LONG).show();
                showtabel(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(Config.KEY_ID_KONS2, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_DETAIL_HISTORY, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showtabel(String json){
        //Toast.makeText(getApplication(),"Data "+json,Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String kode = c.getString(Config.TAG_KD);
                String grand = c.getString(Config.TAG_GRANDTOTAL2);
                String status = c.getString(Config.TAG_STATUS);
                String tgl_pesan = c.getString(Config.TAG_TGL);


                detail_pesan(kode,grand,status,tgl_pesan);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void detail_pesan(String Kode, String Grand, String Status, String Tgl){
        TableLayout tablelayoutid = (TableLayout)this.findViewById(R.id.tablelayoutid2);
        // Inflate your row "template" and fill out the fields.
        TableRow row = (TableRow)getLayoutInflater().inflate(R.layout.layout_row2, null);
        ((TextView)row.findViewById(R.id.kode)).setText(Kode);
        ((TextView)row.findViewById(R.id.grand)).setText(Grand);
        ((TextView)row.findViewById(R.id.statush)).setText(Status);
        ((TextView)row.findViewById(R.id.tgl_pesanan)).setText(Tgl);
        tablelayoutid.addView(row);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, Menu_pelanggan.class);
        startActivity(intent);
    }
}
