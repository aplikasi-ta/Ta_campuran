package com.polda.ari.abdi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Md_dt_ijin extends AppCompatActivity {
    private ListView listView;
    NiftyDialogBuilder dialogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_dt_ijin);
        dialogs = NiftyDialogBuilder.getInstance(this);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        listView = (ListView) findViewById(R.id.listViewIjin);
        getJSON(MenuApps.txt_nips.getText().toString().trim());
    }

    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Md_dt_ijin.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(getApplication(),"Data "+id,Toast.LENGTH_LONG).show();
                showDetail(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(config.TAG_NIP, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(config.URL_IJIN, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void showDetail(String json){
        //Toast.makeText(getApplication(),"Data Dokter "+json,Toast.LENGTH_LONG).show();
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String nama_karyawan  = c.getString(config.TAG_KARYAWAN);
                String waktu  = c.getString(config.TAG_WAKTU_ABSEN);
                String termin  = c.getString(config.TAG_TERMIN);
                String status  = c.getString(config.TAG_STATUS);
                String keterangan  = c.getString(config.TAG_KETERANGAN);

                HashMap<String, String> employees = new HashMap<>();
                employees.put(config.TAG_KARYAWAN, nama_karyawan);
                employees.put(config.TAG_WAKTU_ABSEN, waktu);
                employees.put(config.TAG_TERMIN, termin);
                employees.put(config.TAG_STATUS, status);
                employees.put(config.TAG_KETERANGAN, keterangan);
                list.add(employees);
                //Toast.makeText(getApplication(),"Data "+hari,Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplication(),"Data "+e,Toast.LENGTH_LONG).show();
        }

        ListAdapter adapter = new SimpleAdapter(
                Md_dt_ijin.this, list, R.layout.list_ijin,
                new String[]{config.TAG_KARYAWAN,config.TAG_WAKTU_ABSEN, config.TAG_TERMIN, config.TAG_STATUS,config.TAG_KETERANGAN},
                new int[]{R.id.txt_nama_k, R.id.txt_jam_ijin, R.id.txt_termin_ijin, R.id.txt_status_ijin, R.id.txt_alasan});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(view.getContext(), adapter.getItem(position),Toast.LENGTH_SHORT).show();
                String nama = ((TextView) view.findViewById(R.id.txt_nama_k)).getText().toString();
                String jam = ((TextView) view.findViewById(R.id.txt_jam_ijin)).getText().toString();
                String termin = ((TextView) view.findViewById(R.id.txt_termin_ijin)).getText().toString();
                String status = ((TextView) view.findViewById(R.id.txt_status_ijin)).getText().toString();
                String alasan = ((TextView) view.findViewById(R.id.txt_alasan)).getText().toString();
                dialogs
                        .withTitle("Informasi")
                        .withMessage(nama+"\nTermin : "+termin+"\nJam : "+jam+"\nStatus : "+status+"\nKeterangan : "+alasan)
                        .withDialogColor("#1abc9c")
                        .withButton1Text("OK")
                        .withEffect(Effectstype.RotateLeft);
                dialogs.isCancelableOnTouchOutside(true);
                dialogs.setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogs.dismiss();
                    }
                });
                dialogs.show();
            }
        });

    }


}
