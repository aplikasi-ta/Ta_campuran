package com.example.ari.saidata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PilihTAQuiz extends AppCompatActivity {
public static String npmMhs;
    public static TextView txt_npmmhs,txt_prodimhs;

    Button btn_lanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_taquiz);
        txt_npmmhs = (TextView) findViewById(R.id.txt_npm);
        txt_prodimhs = (TextView) findViewById(R.id.txt_prodi);


        final Intent intent = getIntent();
        npmMhs = intent.getStringExtra(MainActivity.npm);
        txt_npmmhs.setText(npmMhs);

        getJSON();

        btn_lanjut = (Button) findViewById(R.id.btnlanjutkan);
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PilihTAQuiz.this, TahunAjaran.class);
                startActivity(intent1);
            }
        });
    }

    private void getJSON(){
        final String data = txt_npmmhs.getText().toString().trim();
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PilihTAQuiz.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMap(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_LOG_NPM, data);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NPM, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showMap(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_NPM);
                JSONObject c = result.getJSONObject(0);

                String npm = c.getString(KonekDB.TAG_LOG_NPM);
                String prodi = c.getString(KonekDB.TAG_LOG_PRODI);
                //txt_npmmhs.setText(npm);
                txt_prodimhs.setText(prodi);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }
}
