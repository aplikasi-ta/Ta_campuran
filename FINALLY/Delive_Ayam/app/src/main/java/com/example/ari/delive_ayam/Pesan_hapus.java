package com.example.ari.delive_ayam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pesan_hapus extends AppCompatActivity {
    public static String id_ay,id_kons;
    public static String JSON_STRING="";
    EditText txtUkuran,txtHarga,txtKode;
    ProgressDialog loading;
    NiftyDialogBuilder dialogs;
    Button btn_pesan;
    TextView txt_user;
    Button btn_hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_hapus);
        txtKode = (EditText) findViewById(R.id.kodeD);
        txtUkuran = (EditText) findViewById(R.id.ukuranD);
        txtHarga = (EditText) findViewById(R.id.hargaD);
        btn_pesan = (Button) findViewById(R.id.btn_pesanD);
        txt_user = (TextView) findViewById(R.id.id_userD);

        dialogs = NiftyDialogBuilder.getInstance(this);
        txtUkuran.setEnabled(false);
        txtHarga.setEnabled(false);
        txtKode.setEnabled(false);

        final Intent intent = getIntent();
        id_ay = intent.getStringExtra(Config.TAG_ID);
        //Toast.makeText(getApplication(),"Data "+id_ay,Toast.LENGTH_LONG).show();

        getJSON(id_ay);

        //

        getJSONUser(MainActivity.txtUser.getText().toString().trim());

        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delCart(Pesan_detail.txtkd.getText().toString().trim(),id_ay,MainActivity.txtUser.getText().toString().trim());

            }
        });


































    }

    private void getJSONUser(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pesan_hapus.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showUser(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(Config.KEY_TELP, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_DETAIL_USER, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showUser(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject c = result.getJSONObject(0);

            String id_user = c.getString(Config.TAG_USER);
            //Toast.makeText(getApplication(),"Data User "+id_user,Toast.LENGTH_LONG).show();
            txt_user.setText(id_user);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //=======================================================

    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pesan_hapus.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDetail(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(Config.KEY_ID, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_DETAIL_AYAM, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showDetail(String json){

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject c = result.getJSONObject(0);

            String kode = c.getString(Config.TAG_ID_AYAM);
            String ukuran = c.getString(Config.TAG_UKURAN);
            String harga = c.getString(Config.TAG_HARGA);

            txtKode.setText(kode);
            txtUkuran.setText(ukuran);
            txtHarga.setText(harga);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void delCart(final String Kode, final String Id_ayam, final String Id_kons){
        //Toast.makeText(getApplication(),"Dsata"+Kode+"\n"+Id_ayam+"\n"+Id_kons,Toast.LENGTH_LONG).show();

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("kode_tr", Kode));
                nameValuePairs.add(new BasicNameValuePair("id_ayam", Id_ayam));
                nameValuePairs.add(new BasicNameValuePair("id_kons", Id_kons));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://antarayam.000webhostapp.com/app_serv/del_transaksi.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equalsIgnoreCase("success")){

                   Toast.makeText(getApplication(),"Data "+result,Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplication(),"Gagal Menghapus Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Kode, Id_ayam, Id_kons);

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, Menu_pelanggan.class);
        startActivity(intent);
    }


}
