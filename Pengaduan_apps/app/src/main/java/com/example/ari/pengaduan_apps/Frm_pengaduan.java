package com.example.ari.pengaduan_apps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class Frm_pengaduan extends AppCompatActivity {
    NiftyDialogBuilder dialogs;
    Button btn_simpan;
    EditText txt_laporan, txt_id_tower, txt_lokasi;
    CheckBox k1,k2,k3;
    String k_1="",k_2="",k_3="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_pengaduan);
        dialogs = NiftyDialogBuilder.getInstance(this);
        btn_simpan = (Button) findViewById(R.id.btnSubmit);
        txt_id_tower= (EditText) findViewById(R.id.no_tower);
        txt_laporan = (EditText) findViewById(R.id.laporan);
        txt_lokasi = (EditText) findViewById(R.id.lokasi);
        k1 = (CheckBox) findViewById(R.id.k1);
        k2 = (CheckBox) findViewById(R.id.k2);
        k3 = (CheckBox) findViewById(R.id.k3);

        //getJSON(frm_map_tower.txt_id_tower.getText().toString().trim());

        //Toast.makeText(getApplication(),"Data "+Frm_menu_plg.txt_id.getText().toString().trim(),Toast.LENGTH_LONG).show();

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k1.isChecked()){
                    k_1 = "Koneksi internet tidak stabil";
                }else if(!k1.isChecked()){
                    k_1 ="-";
                }

                if(k2.isChecked()){
                    k_2 = "Sinyal edge";
                }else if(!k2.isChecked()){
                    k_2 ="-";
                }

                if(k3.isChecked()){
                    k_3 = "Koneksi internet mati total";
                }else if(!k3.isChecked()){
                    k_3 ="-";
                }

                //Toast.makeText(getApplication(),"Data "+k1.getText().toString().trim(),Toast.LENGTH_LONG).show();
                simpanPengaduan(k_1,k_2,k_3,txt_laporan.getText().toString().trim(), frm_map_tower.txt_id_tower.getText().toString().trim(), Frm_menu_plg.txt_id.getText().toString().trim());

            }
        });
    }


    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Frm_pengaduan.this,"Loading Aplikasi","Tunggu Sebentar...",false,false);
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
                nama_lok.put(config.TAG_ID_TOWER, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(config.URL_DETAIL_TOWER, nama_lok);
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

                String nomor  = c.getString("nomor_tower");
                String kec  = c.getString("kecamatan");
                txt_id_tower.setText(nomor);
                txt_lokasi.setText(kec);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(getApplication(),"Data Slaah "+e,Toast.LENGTH_LONG).show();
        }

    }

    public void simpanPengaduan(final String K1, final String K2, final String K3, final String Laporan, final String Id_tower, final String Id_pelanggan){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_tower", Id_tower));
                nameValuePairs.add(new BasicNameValuePair("id_pelanggan", Id_pelanggan));
                nameValuePairs.add(new BasicNameValuePair("k1", K1));
                nameValuePairs.add(new BasicNameValuePair("k2", K2));
                nameValuePairs.add(new BasicNameValuePair("k3", K3));
                nameValuePairs.add(new BasicNameValuePair("laporan", Laporan));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://kartikaparamitha.000webhostapp.com/telkom/pengaduan.php");
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

                    dialogs
                            .withTitle("Informasi")
                            .withMessage("Pengaduan berhasil terkirim")
                            .withDialogColor("#e74c3c")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(false);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                            Intent intent = new Intent(Frm_pengaduan.this, Frm_menu_plg.class);
                            startActivity(intent);
                        }
                    });
                    dialogs.show();

                }else{
                    Toast.makeText(getApplication(),"Gagal Registrasi Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Laporan, Id_tower, Id_pelanggan);

    }


}
