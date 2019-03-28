package com.polda.ari.abdi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Md_Ijin extends AppCompatActivity {
TextView txt_nip, txt_termin;
    Button btn_submits;
    EditText txt_alasan;
    NiftyDialogBuilder dialogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md__ijin);
        dialogs = NiftyDialogBuilder.getInstance(this);
        txt_nip = (TextView) findViewById(R.id.txt_nip_ijin);
        txt_termin = (TextView) findViewById(R.id.txt_termin_ijin);
        txt_alasan = (EditText) findViewById(R.id.txt_alasan);
        btn_submits = (Button) findViewById(R.id.btnSubmmits);

        txt_nip.setText(MenuApps.txt_nips.getText().toString().trim());
        txt_termin.setText(MenuApps.txt_termin.getText().toString().trim());

        btn_submits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt_termin.getText().toString().trim().equals("TextView") || MenuApps.txt_hari.getText().toString().trim().equals("TextView")){
                    dialogs
                            .withTitle("Informasi")
                            .withMessage("Waktu IJIN sudah habis !")
                            .withDialogColor("#c0392b")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Shake);
                    dialogs.isCancelableOnTouchOutside(true);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                        }
                    });
                    dialogs.show();
                }else {
                    //simpanAbsensi(txt_nips.getText().toString().trim(), txt_termin.getText().toString().trim(), contents);
                    if(txt_alasan.getText().toString().trim().equals("")){
                        Toast.makeText(getApplication(),"Alasan pengajuan anda wajib diisi !",Toast.LENGTH_LONG).show();
                    }else{
                        simpanIjin(txt_nip.getText().toString().trim(),txt_termin.getText().toString().trim(),txt_alasan.getText().toString().trim());
                    }

                }

            }
        });

    }




    public void simpanIjin(final String Nip, final String Termin, final String Alasan){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nip", Nip));
                nameValuePairs.add(new BasicNameValuePair("termin", Termin));
                nameValuePairs.add(new BasicNameValuePair("keterangan", Alasan));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://ppikubl.com/api_absensi/ijin.php");
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
                            .withMessage("Pengajuan Ijin berhasil\ncek status ijin di menu riwayat absen")
                            .withDialogColor("#3da8c9")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(true);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                            Intent intent = new Intent(Md_Ijin.this, MenuApps.class);
                            startActivity(intent);
                        }
                    });
                    dialogs.show();

                }else{
                    Toast.makeText(getApplication(),"Terjadi kesalahan Sistem",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Nip, Termin, Alasan);

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Md_Ijin.this, MenuApps.class);
        startActivity(intent);
    }


}
