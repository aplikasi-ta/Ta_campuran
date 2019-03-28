package com.example.ari.delive_ayam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Menu_pelanggan extends AppCompatActivity {
ImageButton im_ayambersih,im_ayamdaging,imlogout,imgpengaturan;
    public static String id_kons;
    FloatingActionButton fbCart;
    public static TextView txtTest,txt_usr;
    FloatingActionButton fab, fab1, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    boolean isFABOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pelanggan);
        im_ayambersih = (ImageButton) findViewById(R.id.ayam_hidup);
        im_ayamdaging = (ImageButton) findViewById(R.id.ayam_daging);
        txtTest = (TextView) findViewById(R.id.txtTest);
        txt_usr = (TextView) findViewById(R.id.user_app);
        imlogout = (ImageButton) findViewById(R.id.logout);
        imgpengaturan = (ImageButton) findViewById(R.id.pengaturan);

        imlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_pelanggan.this, MainActivity.class);
                startActivity(intent);
            }
        });


        im_ayambersih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_pelanggan.this, Dt_ayam_bersih.class);
                startActivity(intent);
            }
        });

        im_ayamdaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_pelanggan.this, Dt_ayam_daging.class);
                startActivity(intent);
            }
        });


        imgpengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_pelanggan.this, Setting_akun.class);
                startActivity(intent);
            }
        });

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });*/

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_pelanggan.this, Pesan_detail.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_pelanggan.this, Pesan_history.class);
                startActivity(intent);
            }
        });


        final Intent intent = getIntent();
        id_kons = intent.getStringExtra(MainActivity.USER_NAME);

        txtTest.setText(id_kons);
        getJSONUser(MainActivity.txtUser.getText().toString().trim());
        //Toast.makeText(getApplication(),"Data "+MainActivity.txtUser.getText().toString().trim(),Toast.LENGTH_LONG).show();

    }


    private void getJSONUser(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Menu_pelanggan.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
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
            txt_usr.setText(id_user);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void showFABMenu(){
        isFABOpen=true;
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_90));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, Menu_pelanggan.class);
        startActivity(intent);
    }
}
