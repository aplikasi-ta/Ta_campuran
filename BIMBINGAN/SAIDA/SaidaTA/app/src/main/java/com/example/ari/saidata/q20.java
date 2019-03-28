package com.example.ari.saidata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class q20 extends AppCompatActivity {
    Button btn2;
    TextView txtTanya, txtTanya2;
    private RadioGroup radioGroupNb,radioGroupNb2;
    private RadioButton radioButtonNb,radioButtonNb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q20);
        btn2 = (Button) findViewById(R.id.txt_next);
        txtTanya = (TextView) findViewById(R.id.textView1);
        txtTanya2 = (TextView) findViewById(R.id.textView2);

        radioGroupNb = (RadioGroup) findViewById(R.id.GropuP20);
        radioGroupNb2 = (RadioGroup) findViewById(R.id.GropuH20);

        btn2 = (Button) findViewById(R.id.txt_next);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pilih radio button yang ada di radio button group
                int selectedId = radioGroupNb.getCheckedRadioButtonId();
                int selectID2 = radioGroupNb2.getCheckedRadioButtonId();

                // mencari radio button
                radioButtonNb = (RadioButton) findViewById(selectedId);
                radioButtonNb2 = (RadioButton) findViewById(selectID2);

                if(radioButtonNb == null || radioButtonNb2 == null){
                    Toast.makeText(getApplication(),"Anda Belum Memilih Jawaban !", Toast.LENGTH_LONG).show();
                }else {
                    emphatyP20();
                    emphatyH20();
                    goTo2();
                }
            }
        });
    }

    void emphatyP20(){
        final String pertanyaan = txtTanya.getText().toString().trim();
        final String jawaban = radioButtonNb.getText().toString().trim();
        final String prodi = PilihTAQuiz.txt_prodimhs.getText().toString().trim();
        final String ta = q1.txt_tahun_ta.getText().toString().trim();
        final String npm = PilihTAQuiz.txt_npmmhs.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(q20.this,"Mohon Tunggu","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(q20.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_EMP_TANYAEMPATHYP,pertanyaan);
                params.put(KonekDB.KEY_EMP_JAWABEMPATHYP, jawaban);
                params.put(KonekDB.KEY_EMP_PRODI_P7, prodi);
                params.put(KonekDB.KEY_EMP_TA_P7, ta);
                params.put(KonekDB.KEY_EMP_NPM_P7, npm);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_SIMPAN_EMPATHYP, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    void emphatyH20(){
        final String pertanyaan = txtTanya2.getText().toString().trim();
        final String jawaban = radioButtonNb2.getText().toString().trim();
        final String prodi = PilihTAQuiz.txt_prodimhs.getText().toString().trim();
        final String ta = q1.txt_tahun_ta.getText().toString().trim();
        final String npm = PilihTAQuiz.txt_npmmhs.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(q20.this,"Mohon Tunggu","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(q20.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_EMP_TANYAEMPATHYH,pertanyaan);
                params.put(KonekDB.KEY_EMP_JAWABEMPATHYH, jawaban);
                params.put(KonekDB.KEY_EMP_PRODI_H10, prodi);
                params.put(KonekDB.KEY_EMP_TA_H10, ta);
                params.put(KonekDB.KEY_EMP_NPM_H10, npm);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_SIMPAN_EMPATHYH, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    void goTo2(){
        Intent quis2 = new Intent(q20.this, q21.class);
        startActivity(quis2);
    }
    private void getJSON(final String npmMhs){
        class GerJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(q20.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
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
                nama_lok.put(KonekDB.KEY_EMP_NPM, npmMhs);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NPM,nama_lok);
                return s;
            }
        }


    }


    private void showMap (String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_NPM);
            JSONObject c = result.getJSONObject(0);

            String npm = c.getString(KonekDB.TAG_NPM);


            Toast.makeText(getApplication(),npm,Toast.LENGTH_LONG).show();


        }catch (JSONException a){
            a.printStackTrace();
        }
    }
}
