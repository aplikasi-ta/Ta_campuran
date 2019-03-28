package com.example.ari.pengaduan_apps;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Frm_reg_plg extends AppCompatActivity {
EditText txt_no, txt_email,txt_nama,txt_alamat;
    String[] SPINNERLIST = {"Pilih Kecamatan","Bumi Waras","Langkapura","Tanjung Karang Pusat","Teluk Betung Utara",
            "Enggal","Panjang","Tanjung Karang Timur","Way Halim","Kedamaian","Rajabasa",
            "Tanjung Senang","Kedaton","Sukabumi","Teluk Betung Barat","Kemiling",
            "Sukarame","Teluk Betung Selatan","Labuhan Ratu","Tanjung Karang Barat",
            "Teluk Betung Timur"};
    Spinner spKecamatan;
    String kec;
    NiftyDialogBuilder dialogs;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_reg_plg);
        txt_no = (EditText) findViewById(R.id.no_speedy);
        txt_email = (EditText) findViewById(R.id.email);
        txt_nama = (EditText) findViewById(R.id.nama_pelanggan);
        txt_alamat = (EditText) findViewById(R.id.alamat);
        btn_signup = (Button) findViewById(R.id.btnSignUp);
        dialogs = NiftyDialogBuilder.getInstance(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        spKecamatan = (Spinner) findViewById(R.id.kecamatan);
        spKecamatan.setAdapter(arrayAdapter);
        spKecamatan.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanRegister(txt_no.getText().toString().trim(), txt_email.getText().toString().trim(),
                        txt_nama.getText().toString().trim(),kec,txt_alamat.getText().toString().trim());
            }
        });

    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spKecamatan.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spKecamatan.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                /*Toast.makeText(parent.getContext(),
                        "Kamu memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();*/
                kec = parent.getItemAtPosition(pos).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }
    }


    public void simpanRegister(final String Nomor, final String Email, final String Nama, final String Kecamatan, final String Alamat){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("no_telkom", Nomor));
                nameValuePairs.add(new BasicNameValuePair("email", Email));
                nameValuePairs.add(new BasicNameValuePair("nama", Nama));
                nameValuePairs.add(new BasicNameValuePair("kecamatan", Kecamatan));
                nameValuePairs.add(new BasicNameValuePair("alamat", Alamat));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://kartikaparamitha.000webhostapp.com/telkom/reg_user.php");
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
                            .withMessage("Registrasi Berhasil, Silahkan gunakan Email dan Nomor Speedy anda untuk login")
                            .withDialogColor("#e74c3c")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(false);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                            Intent intent = new Intent(Frm_reg_plg.this, MainActivity.class);
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
        sendPostReqAsyncTask.execute(Nomor, Email, Nama, Kecamatan, Alamat);

    }

}
