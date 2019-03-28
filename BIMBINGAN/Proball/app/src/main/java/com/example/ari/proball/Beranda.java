



package com.example.ari.proball;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Beranda extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView listView;

    private String JSON_STRING;
    ImageView liks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        liks = (ImageView) findViewById(R.id.like);
        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Koneksi.TAG_JSON_ARRAY_BERANDA);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String alamat = jo.getString(Koneksi.TAG_ALAMATS);
                String lts = jo.getString(Koneksi.TAG_LATS);
                String lgs = jo.getString(Koneksi.TAG_LONGS);


                HashMap<String,String> employees = new HashMap<>();
                employees.put(Koneksi.TAG_ALAMATS,alamat);
                employees.put(Koneksi.KEY_EMP_LATS, lts);
                employees.put(Koneksi.KEY_EMP_LONGS, lgs);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                Beranda.this, list, R.layout.item_list,
                new String[]{Koneksi.TAG_ALAMATS,Koneksi.TAG_LATS, Koneksi.TAG_LONGS},
                new int[]{R.id.alamat, R.id.latitude, R.id.longitude});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Beranda.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Koneksi.URL_GET_beranda);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        likess(position);
        /*Intent intent = new Intent(this, Beranda.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Koneksi.TAG_ID).toString();
        intent.putExtra(Koneksi.OSM_ID,empId);
        startActivity(intent);*/
    }


    private void likess(final int posisi){

        //final String name = liks.getT.toString().trim();
        final  String lk = String.valueOf(posisi);

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Beranda.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Beranda.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Koneksi.KEY_EMP_LIKES, lk);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Koneksi.URL_GET_likess, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }
}
