package com.example.ari.delive_ayam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Dt_ayam_daging extends AppCompatActivity {
    private ListView listView;

    private String JSON_STRING;
    public static String id_aym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dt_ayam_daging);
        listView = (ListView) findViewById(R.id.listViewdaging);
        //listView.setOnItemClickListener(this);
        getJSON();
    }

    private void showAyamBersih(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String jenis = jo.getString(Config.TAG_JENIS);
                String ukuran = jo.getString(Config.TAG_UKURAN);
                String harga = jo.getString(Config.TAG_HARGA);
                String stok = jo.getString(Config.TAG_STOCK);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_ID,id);
                employees.put(Config.TAG_JENIS,jenis);
                employees.put(Config.TAG_UKURAN,ukuran);
                employees.put(Config.TAG_HARGA,harga);
                employees.put(Config.TAG_STOCK,stok);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                Dt_ayam_daging.this, list, R.layout.list_item_daging,
                new String[]{Config.TAG_UKURAN,Config.TAG_HARGA,Config.TAG_STOCK,Config.TAG_ID},
                new int[]{R.id.ukuranDaging, R.id.hargaDaging,R.id.stockDaging,R.id.id_ayam2});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(view.getContext(), adapter.getItem(position),Toast.LENGTH_SHORT).show();
                id_aym = ((TextView) view.findViewById(R.id.id_ayam2)).getText().toString();
                Intent intent = new Intent(Dt_ayam_daging.this, Pesan_ayam.class);
                intent.putExtra(Config.TAG_ID, id_aym);
                finish();
                startActivity(intent);
            }
        });

    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Dt_ayam_daging.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showAyamBersih();
                //Toast.makeText(getApplication(),"Data "+s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_BUBUT);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Dt_ayam_bersih.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.TAG_JENIS,empId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, Menu_pelanggan.class);
        startActivity(intent);
    }
}
