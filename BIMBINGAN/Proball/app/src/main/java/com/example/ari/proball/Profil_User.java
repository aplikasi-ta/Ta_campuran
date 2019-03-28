package com.example.ari.proball;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Profil_User extends AppCompatActivity {
    private String JSON_STRING, JSON_STRING2;
    TextView txt_b_share, txt_b_like, txt_level;
    String b_share,  b_like;
    RatingBar ratingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil__user);
        txt_b_share = (TextView) findViewById(R.id.txt_b_share);
        txt_b_like = (TextView) findViewById(R.id.txtLikes);
        txt_level = (TextView)findViewById(R.id.txt_level);
        ratingBar = (RatingBar)findViewById(R.id.id_reward);
        getJSON();
        getJSONLIKE();
    }


    private void showProfil() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Koneksi.TAG_JSON_ARRAY3);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                //String id = jo.getString(Koneksi.TAG_ID);
                b_share = jo.getString(Koneksi.TAG_B_SHARE);

                HashMap<String, String> employees = new HashMap<>();
                //employees.put(Koneksi.TAG_ID,id);
                employees.put(Koneksi.TAG_B_SHARE, b_share);

                list.add(employees);
                txt_b_share.setText(b_share);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Profil_User.this, "Menampilkan Data", "Tunggu Sebentar...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showProfil();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Koneksi.URL_GET_B_SHARE);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void showLIke() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING2);
            JSONArray result = jsonObject.getJSONArray(Koneksi.TAG_JSON_ARRAY_LIKE);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                //String id = jo.getString(Koneksi.TAG_ID);
                b_like = jo.getString(Koneksi.TAG_B_LIKE);

                HashMap<String, String> employees = new HashMap<>();
                //employees.put(Koneksi.TAG_ID,id);
                employees.put(Koneksi.TAG_B_LIKE, b_like);

                list.add(employees);
                txt_b_like.setText(b_like);
                Quest(b_share, b_like);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void getJSONLIKE() {
        class GetJSON2 extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Profil_User.this, "Menampilkan Data", "Tunggu Sebentar...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING2 = s;
                showLIke();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Koneksi.URL_GET_HASIL_LIKE);
                return s;
            }
        }
        GetJSON2 gj2 = new GetJSON2();
        gj2.execute();
    }


    void Quest(String badges, String likes){
        Integer bg = Integer.parseInt(badges);
        Integer lks = Integer.parseInt(likes);
        Integer hasil = ((bg+lks)/5)*100;
        if (hasil  >= 1000){
            txt_level.setText("Newbie");
            ratingBar.setRating(1.0f);
        }

        if(hasil >= 1001){
            txt_level.setText("Addict");
            ratingBar.setRating(2.0f);
        }
        if (hasil >= 10001){
            txt_level.setText("Geek");
            ratingBar.setRating(3.0f);
        }
        if (hasil >= 50000){
            txt_level.setText("Freak");
            ratingBar.setRating(4.0f);
        }

        if (hasil >= 50001){
            txt_level.setText("Freak");
            ratingBar.setRating(5.0f);
        }
    }
}
