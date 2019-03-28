package com.example.ari.crowdgamifikasi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

Button btn_regis,btn_logs, btn_beranda;
    EditText txt_user, txt_pass;
    private String JSON_STRING;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_regis = (Button) findViewById(R.id.btn_regis);
        btn_logs = (Button) findViewById(R.id.btn_login);
        txt_user = (EditText) findViewById(R.id.txt_username);
        txt_pass = (EditText) findViewById(R.id.txtPass);
        button = (Button) findViewById(R.id.btn_beranda);

        btn_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJSON();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Beranda2.class);
                startActivity(intent);
            }
        });

    }


    private void getJSON(){
        final String usr = txt_user.getText().toString().trim();
        final String pas= txt_pass.getText().toString().trim();

        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Login Proses","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAkses(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> logins = new HashMap<>();
                logins.put(Koneksi.KEY_EMP_USER, usr);
                logins.put(Koneksi.KEY_EMP_PASS, pas);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Koneksi.URL_GET_LOGIN, logins);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    void showAkses(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Koneksi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

            String name = c.getString(Koneksi.TAG_NAMA);
            String latt = c.getString(Koneksi.TAG_LAT);
            String longg = c.getString(Koneksi.TAG_LONGI);

            Double lt = Double.parseDouble(latt);
            Double lgs = Double.parseDouble(longg);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}