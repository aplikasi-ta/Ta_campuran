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

public class Setting_akun extends AppCompatActivity {
EditText txtNama,txtPassword, txtAlamat;
    TextView txtID;
    NiftyDialogBuilder dialogs;
    Button btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_akun);
        txtID = (TextView) findViewById(R.id.txtID);
        txtNama = (EditText) findViewById(R.id.nama);
        txtPassword = (EditText) findViewById(R.id.password);
        txtAlamat = (EditText) findViewById(R.id.alamats);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        dialogs = NiftyDialogBuilder.getInstance(this);


        getJSONUser(MainActivity.txtUser.getText().toString().trim());
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingAkun(txtID.getText().toString().trim(),txtNama.getText().toString().trim(),txtPassword.getText().toString().trim(),txtAlamat.getText().toString().trim());
            }
        });

    }

    private void getJSONUser(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Setting_akun.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
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
            String nama = c.getString(Config.TAG_NAMAS);
            String pass = c.getString(Config.TAG_PASS);
            String alamat = c.getString(Config.TAG_ALAMATS);

            txtID.setText(id_user);
            txtNama.setText(nama);
            txtPassword.setText(pass);
            txtAlamat.setText(alamat);

            //Toast.makeText(getApplication(),"Data User "+id_user, Toast.LENGTH_LONG).show();
            //txt_usr.setText(id_user);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void settingAkun(final String Id, final String Nama, final String Pass, final String Alamat){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_konsumen", Id));
                nameValuePairs.add(new BasicNameValuePair("nama", Nama));
                nameValuePairs.add(new BasicNameValuePair("password", Pass));
                nameValuePairs.add(new BasicNameValuePair("alamat", Alamat));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://antarayam.000webhostapp.com/app_serv/dt_setting.php");
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
                            .withMessage("Perubahan Akun Telah Berhasil,\nUntuk Pengoptimalan Aplikasi Akan Keluar Otomatis")
                            .withDialogColor("#f1c40f")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(false);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                            Intent intent = new Intent(Setting_akun.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialogs.show();

                }else{
                    Toast.makeText(getApplication(),"Gagal Setting Akun",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Id,Nama,Alamat,Pass);

    }

}
