package com.example.ari.delive_ayam;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Regis_customer extends AppCompatActivity {
EditText txtMail,txtNama,txtHp,txtPasw,txtPos,txtAlamat;
    Button btn_regis;
    NiftyDialogBuilder dialogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_customer);
        txtNama = (EditText) findViewById(R.id.namas);
        txtHp = (EditText) findViewById(R.id.hp);
        txtPasw = (EditText) findViewById(R.id.pass_reg);
        //txtPos = (EditText) findViewById(R.id.kodepos);
        txtAlamat = (EditText) findViewById(R.id.alamat);
        dialogs = NiftyDialogBuilder.getInstance(this);
        btn_regis = (Button) findViewById(R.id.btn_regs);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanRegister(txtNama.getText().toString().trim(),txtHp.getText().toString().trim(),
                        txtPasw.getText().toString().trim(),txtAlamat.getText().toString().trim());
            }
        });
    }


    public void simpanRegister(final String Nama, final String Hp, final String Pass, final String Alamat){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nama", Nama));
                nameValuePairs.add(new BasicNameValuePair("password", Pass));
                nameValuePairs.add(new BasicNameValuePair("alamat", Alamat));
                //nameValuePairs.add(new BasicNameValuePair("kodepos", Pos));
                nameValuePairs.add(new BasicNameValuePair("notelp", Hp));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://antarayam.000webhostapp.com/app_serv/add_cust.php");
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
                            .withMessage("Registrasi Berhasil, Silahkan gunakan No. Handphone dan Password anda untuk login")
                            .withDialogColor("#f1c40f")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(false);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                            Intent intent = new Intent(Regis_customer.this, MainActivity.class);
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
        sendPostReqAsyncTask.execute(Nama, Pass,Alamat,Hp);

    }
}
