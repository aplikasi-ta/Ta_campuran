package com.example.ari.pengaduan_apps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Frm_menu_plg extends AppCompatActivity {
ImageButton im_helpdesk, im_profil, im_tentang, im_keluar;
    public static TextView txt_id, txt_kecamatan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_menu_plg);
        im_helpdesk = (ImageButton) findViewById(R.id.btn_pengaduan);
        im_profil = (ImageButton) findViewById(R.id.btn_helpdesk);
        im_keluar = (ImageButton) findViewById(R.id.btn_exit);
        im_tentang = (ImageButton) findViewById(R.id.btn_tentang);

        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_kecamatan = (TextView) findViewById(R.id.txt_kecamatan);

        im_helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Frm_menu_plg.this, frm_map_tower.class);
                startActivity(intent);
            }
        });

        im_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Frm_menu_plg.this, Frm_edit_user.class);
                startActivity(intent);
            }
        });

        im_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Frm_menu_plg.this, Frm_tentang.class);
                startActivity(intent);
            }
        });

        im_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        getJSON(Frm_login.txt_pass.getText().toString().trim());
    }

    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Frm_menu_plg.this,"Loading Aplikasi","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(getApplication(),"Data "+s,Toast.LENGTH_LONG).show();
                showDetail(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(config.TAG_NOTELKOM, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(config.URL_DETAIL_USER, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void showDetail(String json){
        //Toast.makeText(getApplication(),"Data Camat "+json,Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String id_pengguna  = c.getString("id_pelanggan");
                String kec  = c.getString("kecamatan");
                txt_id.setText(id_pengguna);
                txt_kecamatan.setText(kec);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(getApplication(),"Data Slaah "+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, Frm_menu_plg.class);
        startActivity(intent);
    }

}
