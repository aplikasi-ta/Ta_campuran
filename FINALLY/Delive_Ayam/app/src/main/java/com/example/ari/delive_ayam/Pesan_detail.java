package com.example.ari.delive_ayam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class Pesan_detail extends AppCompatActivity {
    private String JSON_STRING;
    public static TextView txtkd,txtTgl,txtNamaPesan,txtAlamat,txt_usr,txt_grand,txtStatus;
    String kd_tr,kd_tr2, tgl_tr, grand;
    NiftyDialogBuilder dialogs;
    Button btn_transaksi;
    ProgressDialog loading;
    TableRow row;
    TableLayout tablelayoutid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_detail);
        txtkd = (TextView) findViewById(R.id.txtKd);
        txtTgl = (TextView) findViewById(R.id.txtTanggal);
        txtNamaPesan = (TextView) findViewById(R.id.txtNamaPesan);
        txtAlamat = (TextView) findViewById(R.id.txtAlamat);
        txt_grand = (TextView) findViewById(R.id.txtGrand);
        btn_transaksi = (Button) findViewById(R.id.btn_transaksi);
        //txtStatus = (TextView) findViewById(R.id.status);

        dialogs = NiftyDialogBuilder.getInstance(this);

        getJSON(Menu_pelanggan.txt_usr.getText().toString().trim());
        //getData(Menu_pelanggan.txt_usr.getText().toString().trim(),kd_tr);
        //Toast.makeText(getApplication(),"Data "+Menu_pelanggan.txt_usr.getText().toString().trim(),Toast.LENGTH_LONG).show();

        btn_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTransaksi(kd_tr,tgl_tr,grand);
            }
        });


    }


    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pesan_detail.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
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
                nama_lok.put(Config.KEY_ID_KONS, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_DETAIL_PESAN, nama_lok);
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

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String ukuran = c.getString(Config.TAG_UK);
                String harga = c.getString(Config.TAG_HRG);
                String jumlah = c.getString(Config.TAG_JUMLAH);
                String sub = c.getString(Config.TAG_SUBTOTAL);
                kd_tr = c.getString(Config.TAG_KD);
                //kd_tr2 = c.getString(Config.TAG_KD);
                tgl_tr = c.getString(Config.TAG_TGL);
                String nama = c.getString(Config.TAG_NAMAS);
                String alamat = c.getString(Config.TAG_ALAMATS);

                txtkd.setText("Kode Transaksi : "+kd_tr);
                txtTgl.setText("Tanggal : "+tgl_tr);
                txtNamaPesan.setText("Nama Pemesan : "+nama);
                txtAlamat.setText("Alamat : "+alamat);

            }
            getJSONTabe(kd_tr, Menu_pelanggan.txt_usr.getText().toString().trim());
            //Toast.makeText(getApplication(),"Data "+kd_tr,Toast.LENGTH_LONG).show();
            getJSONGrand(Menu_pelanggan.txt_usr.getText().toString().trim());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void getJSONTabe(String kd_tr, String id_kons){

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        final String url = Config.URL_DETAIL_TABEL+kd_tr+"&id_kons="+id_kons;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showtabel(response);
                //Toast.makeText(getApplication(),"Data : "+response,Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Pesan_detail.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showtabel(String json){

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String id_det = c.getString(Config.TAG_ID_DET_TR);
                //String kode = c.getString(Config.TAG_ID_AYAM);
                String ukuran = c.getString(Config.TAG_UK);
                String harga = c.getString(Config.TAG_HRG);
                String jumlah = c.getString(Config.TAG_JUMLAH);
                String sub = c.getString(Config.TAG_SUBTOTAL);
                //String status = c.getString(Config.TAG_STATUS);

                detail_pesan(id_det,ukuran,harga,jumlah,sub);
                //Toast.makeText(getApplication(),"Data "+jsonObject,Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void detail_pesan(String Kode, String Ukuran, String Harga, String Jumlah, String Subtotal){
        tablelayoutid = (TableLayout)this.findViewById(R.id.tablelayoutid);
        // Inflate your row "template" and fill out the fields.
        row = (TableRow)getLayoutInflater().inflate(R.layout.layout_row, null);
        ((TextView)row.findViewById(R.id.kodeT)).setText(Kode);
        ((TextView)row.findViewById(R.id.ukuranT)).setText(Ukuran);
        ((TextView)row.findViewById(R.id.hargaT)).setText(Harga);
        ((TextView)row.findViewById(R.id.jumlahT)).setText(Jumlah);
        ((TextView)row.findViewById(R.id.subtotalT)).setText(Subtotal);
        tablelayoutid.addView(row);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row = (TableRow) view;
                TextView firstTextView = (TextView) row.getChildAt(0);

                final String id_tr = firstTextView.getText().toString();

                dialogs
                        .withTitle("Informasi")
                        .withMessage("Apakah anda yakin ingin menghapus pesanan ini ?")
                        .withDialogColor("#2ecc71")
                        .withButton1Text("Ya")
                        .withButton2Text("Tidak")
                        .withIcon(getResources().getDrawable(R.drawable.logo_app2))
                        .withEffect(Effectstype.Fall);
                dialogs.isCancelableOnTouchOutside(false);
                dialogs.setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogs.dismiss();
                        delCart(id_tr);
                    }
                });

                dialogs.setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogs.cancel();
                    }
                });

                dialogs.show();
            }
        });

    }



    private void getJSONGrand(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pesan_detail.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showGrand(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(Config.KEY_ID_KONS, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_DETAIL_GRAND, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void showGrand(String json){

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                grand = c.getString(Config.TAG_GRANDTOTAL);
                txt_grand.setText(grand);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void addTransaksi(final String KodeTr, final String Tgl, final String Grand){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("kode_tr", KodeTr));
                nameValuePairs.add(new BasicNameValuePair("tgl_tr", Tgl));
                nameValuePairs.add(new BasicNameValuePair("grand_tot", Grand));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://antarayam.000webhostapp.com/app_serv/add_transaksi.php");
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
                            .withMessage("Transaksi pemesanan berhasil, silahkan menunggu\nadmin kami akan segera menghubungi anda")
                            .withDialogColor("#f1c40f")
                            .withButton1Text("OK")
                            .withEffect(Effectstype.Fall);
                    dialogs.isCancelableOnTouchOutside(false);
                    dialogs.setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogs.dismiss();
                            Intent intent = new Intent(Pesan_detail.this, Menu_pelanggan.class);
                            startActivity(intent);
                            btn_transaksi.setEnabled(false);
                        }
                    });
                    dialogs.show();

                }else{
                    Toast.makeText(getApplication(),"Gagal Menyimpan Transaksi Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(KodeTr,Tgl,Grand);

    }



    public void delCart(final String ID_Det_Tr){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_det_tr", ID_Det_Tr));

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

                    Toast.makeText(getApplication(),"Data Pesanan Berhasil Dihapus",Toast.LENGTH_LONG).show();
                    Intent refresh = new Intent(Pesan_detail.this, Pesan_detail.class);
                    startActivity(refresh);

                }else{
                    Toast.makeText(getApplication(),"Gagal Menghapus Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(ID_Det_Tr);

    }



    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, Menu_pelanggan.class);
        startActivity(intent);
    }

}