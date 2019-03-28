package com.example.ari.delive_ayam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class Pesan_ayam extends AppCompatActivity {
public static String id_ay,id_kons;
    public static String JSON_STRING="";
    EditText txtUkuran,txtHarga,txtJumlah,txtSubtotal;
    ProgressDialog loading;
    NiftyDialogBuilder dialogs;
    Button btn_pesan;
    TextView txt_user,txtStk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_ayam);
        txtUkuran = (EditText) findViewById(R.id.ukuran);
        txtHarga = (EditText) findViewById(R.id.harga);
        txtJumlah = (EditText) findViewById(R.id.jumlah);
        txtSubtotal = (EditText) findViewById(R.id.subtotal);
        btn_pesan = (Button) findViewById(R.id.btn_pesan);
        txt_user = (TextView) findViewById(R.id.id_user);
        txtStk = (TextView) findViewById(R.id.txtStk);

        dialogs = NiftyDialogBuilder.getInstance(this);
        txtUkuran.setEnabled(false);
        txtHarga.setEnabled(false);
        txtSubtotal.setEnabled(false);

        txtJumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtSubtotal.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    txtSubtotal.setText("0");
                }else{
                    Integer harga = Integer.parseInt(txtHarga.getText().toString());
                    Integer jumlah = Integer.parseInt(txtJumlah.getText().toString());
                    Integer subtotal = harga*jumlah;
                    txtSubtotal.setText(Integer.toString(subtotal));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==0){
                    txtSubtotal.setText("0");
                }else{
                    Integer harga = Integer.parseInt(txtHarga.getText().toString());
                    Integer jumlah = Integer.parseInt(txtJumlah.getText().toString());
                    Integer subtotal = harga*jumlah;
                    txtSubtotal.setText(Integer.toString(subtotal));
                }
            }
        });

        final Intent intent = getIntent();
        id_ay = intent.getStringExtra(Config.TAG_ID);

        getJSON(id_ay);

        //Toast.makeText(getApplication(),"Data "+stoks+"\n"+jumbel,Toast.LENGTH_LONG).show();
        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer stoks = Integer.parseInt(txtStk.getText().toString().trim());
                Integer jumbel = Integer.parseInt(txtJumlah.getText().toString().trim());
                if(stoks<jumbel){
                    Toast.makeText(getApplication(),"Maaf stok ayam kurang atau telah habis",Toast.LENGTH_LONG).show();
                }else{
                    addCart(id_ay,txtUkuran.getText().toString().trim(),txtHarga.getText().toString().trim(),txtJumlah.getText().toString().trim(),txtSubtotal.getText().toString().trim(),txt_user.getText().toString().trim());
                }

            }
        });

        getJSONUser(MainActivity.txtUser.getText().toString().trim());

    }


    private void getJSONUser(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pesan_ayam.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
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
                loading = ProgressDialog.show(Pesan_ayam.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
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

            String ukuran = c.getString(Config.TAG_UKURAN);
            String harga = c.getString(Config.TAG_HARGA);
            String stok = c.getString(Config.TAG_STOCK);

            //Toast.makeText(getApplication(),"Data Stok"+stok,Toast.LENGTH_LONG).show();

            txtStk.setText(stok);
            txtUkuran.setText(ukuran);
            txtHarga.setText(harga);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void addCart(final String Id_ayam, final String Ukuran, final String Harga, final String Jumlah, final String Sub, final String Id_kons){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_ayam", Id_ayam));
                nameValuePairs.add(new BasicNameValuePair("ukuran", Ukuran));
                nameValuePairs.add(new BasicNameValuePair("harga", Harga));
                nameValuePairs.add(new BasicNameValuePair("jumlah", Jumlah));
                nameValuePairs.add(new BasicNameValuePair("subtotal", Sub));
                nameValuePairs.add(new BasicNameValuePair("id_kons", Id_kons));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://antarayam.000webhostapp.com/app_serv/dt_transaksi.php");
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
                            .withMessage("Berhasil menambahkan item ke daftar pemesanan")
                            .withDialogColor("#f1c40f")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(false);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                            Intent intent = new Intent(Pesan_ayam.this, Menu_pelanggan.class);
                            startActivity(intent);
                        }
                    });
                    dialogs.show();

                }else{
                    Toast.makeText(getApplication(),"Gagal Menyimpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(Id_ayam,Ukuran,Harga,Jumlah,Sub,Id_kons);

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, Menu_pelanggan.class);
        startActivity(intent);
    }

}
