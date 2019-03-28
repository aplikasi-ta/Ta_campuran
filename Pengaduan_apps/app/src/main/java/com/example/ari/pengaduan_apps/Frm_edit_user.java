package com.example.ari.pengaduan_apps;

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

public class Frm_edit_user extends AppCompatActivity {
EditText txt_no, txt_email, txt_nama;
    Button btn_edit;
    NiftyDialogBuilder dialog;
    TextView txt_id_pelanggan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_edit_user);
        txt_no = (EditText) findViewById(R.id.txt_no);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_nama = (EditText) findViewById(R.id.txt_nama);
        txt_id_pelanggan = (TextView) findViewById(R.id.txt_id_pelanggan);
        btn_edit = (Button) findViewById(R.id.btnEdit);

        dialog = NiftyDialogBuilder.getInstance(this);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog
                        .withTitle("Informasi")
                        .withMessage("Mengganti Nomor Telkom atau Email akan merubah akun login anda\nApakah ingin melanjutkan ?")
                        .withDialogColor("#e74c3c")
                        .withButton1Text("Ya")
                        .withButton2Text("Tidak")
                        .withEffect(Effectstype.Fall);
                dialog.isCancelableOnTouchOutside(false);
                dialog.setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        ubahAkun(txt_id_pelanggan.getText().toString().trim(), txt_no.getText().toString().trim(), txt_email.getText().toString().trim());
                    }
                });

                dialog.setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
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
                loading = ProgressDialog.show(Frm_edit_user.this,"Loading Aplikasi","Tunggu Sebentar...",false,false);
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

                String id_pengguna  = c.getString(config.TAG_PELANGGAN);
                String no_telkom  = c.getString(config.TAG_NOTELKOM);
                String email  = c.getString(config.TAG_EMAIL);
                String nama  = c.getString(config.TAG_NAMA_PELANGGAN);

                txt_id_pelanggan.setText(id_pengguna);
                txt_no.setText(no_telkom);
                txt_email.setText(email);
                txt_nama.setText(nama);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(getApplication(),"Data Slaah "+e,Toast.LENGTH_LONG).show();
        }

    }




    public void ubahAkun(final String Id_pelanggan, final String Nomor, final String Email){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_pelanggan", Id_pelanggan));
                nameValuePairs.add(new BasicNameValuePair("no_telkom", Nomor));
                nameValuePairs.add(new BasicNameValuePair("email", Email));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://kartikaparamitha.000webhostapp.com/telkom/ubah_user.php");
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

                    dialog
                            .withTitle("Informasi")
                            .withMessage("Perubahan akun berhasil, Silahkan gunakan Email dan Nomor Speedy anda untuk login")
                            .withDialogColor("#e74c3c")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialog.isCancelableOnTouchOutside(false);
                    dialog.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent = new Intent(Frm_edit_user.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();

                }else{
                    Toast.makeText(getApplication(),"Gagal Registrasi Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Id_pelanggan,Nomor, Email);

    }

}
