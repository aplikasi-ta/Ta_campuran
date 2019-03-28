package com.example.ari.saidata;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Tampil2 extends AppCompatActivity {
    TableLayout tb_hasil, tb_hasil2;
    private String JSON_STRING, prodi,tahun_ajar;
    Spinner spinnerTA, spinnerProdi, spinnerData;
    Button btn_jajal;
    public static Double jmlh=0.0, hsl=0.0, jmlhRP=0.0, hslRP=0.0,jmlhResponP=0.0, hslResponP=0.0,
            jmlhAssuranceP=0.0, hslAssuranceP=0.0,jmlhEmpathyP=0.0, hslEmpathyP=0.0;

    public static Double jmlhH=0.0, hslH=0.0, jmlhRH=0.0, hslRH=0.0,jmlhResponH=0.0, hslResponH=0.0,
            jmlhAssuranceH=0.0, hslAssuranceH=0.0,jmlhEmpathyH=0.0, hslEmpathyH=0.0;


    public static Double hitungTangibleP=0.0, hitungRekiabiklityP=0.0, hitungnilaiResponP=0.0, hitungNilaiAsscuranceP=0.0, hitungNilaiEmpahty=0.0;

    public static Double hitungTangibleH=0.0, hitungRekiabiklityH=0.0, hitungnilaiResponH=0.0, hitungNilaiAsscuranceH=0.0, hitungNilaiEmpahtyH=0.0;

    public static  Double nilaiD=0.0;
    public static Double hasilA = 0.0, hasilA2 = 0.0, rataTangibleH = 0.0, rataReliabH = 0.0, rataResponH = 0.0, rataAssuranceH = 0.0, rataempathyH = 0.0, nilaiB1 = 0.0, nilaiB2 = 0.0, nilaiB3 = 0.0, nilaiB4 = 0.0, nilaiB5 = 0.0, nilaiCH = 0.0, nilaiDH = 0.0, hasilservqual = 0.0;
    Double hasilAP =0.0, hasilA2P = 0.0,rataTangibleP = 0.0,rataReliabP = 0.0,rataResponP = 0.0,rataAssP = 0.0,rataEmpathyP = 0.0,nilaiB1P = 0.0,nilaiB2P = 0.0,
            nilaiB3P = 0.0, nilaiB4P = 0.0,nilaiB5P = 0.0, nilaiCP = 0.0;

    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    String pilihData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil2);
        spinnerTA = (Spinner) findViewById(R.id.spinTA);
        spinnerProdi = (Spinner) findViewById(R.id.spinPR);
        //spinnerData = (Spinner) findViewById(R.id.spinPV);
        btn_jajal = (Button) findViewById(R.id.btn_hasil);
        tb_hasil = (TableLayout) findViewById(R.id.tablelayoutid);
        //tb_hasil2 = (TableLayout) findViewById(R.id.tablelayoutid2);
         //chart = (BarChart) findViewById(R.id.chart);
         chart = (BarChart) findViewById(R.id.chart);


        btn_jajal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BarData data = new BarData(getXAxisValues(), getDataSet());
                //  BarData datas = new BarData();
                chart.setData(data);
                chart.setDescription("My Chart");
                chart.animateXY(2000, 2000);
                chart.invalidate();
            }
        });
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinnerTA.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinnerTA.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                /*Toast.makeText(parent.getContext(),
                        "Kamu memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();*/
                tahun_ajar =  parent.getItemAtPosition(pos).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }


    public class CustomOnItemSelectedListener2 implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinnerProdi.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinnerProdi.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                /*Toast.makeText(parent.getContext(),
                        "Kamu memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();*/
                prodi =  parent.getItemAtPosition(pos).toString();
                //getJSON(prodi, tahun_ajar);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

    public class CustomOnItemSelectedListener4 implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinnerData.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinnerData.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                /*Toast.makeText(parent.getContext(),
                        "Kamu memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();*/
                pilihData =  parent.getItemAtPosition(pos).toString();
                //getJSON(prodi, tahun_ajar);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

    //================================================================ DATA TANGIBLE P ========================================================


    private void getJSONTangibleP(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showTangibleP(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_TANGIBLE_P, prodis);
                nama_lok.put(KonekDB.KEY_TA_TANGIBLE_P, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_TANG_P, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showTangibleP(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_TANGIBLE_P);
                String hasil = c.getString(KonekDB.KEY_HASIL_TANGIBLE_P);

                jmlh = Double.parseDouble(jumlah);
                hsl = Double.parseDouble(hasil);
                hitungTangibleP = hsl/jmlh;
                //Toast.makeText(getApplication(),"Hasil Tangible P =  "+hitungTangibleP,Toast.LENGTH_LONG).show();
                hitungnilaiPersepsi(hitungTangibleP, hitungRekiabiklityP,hitungnilaiResponP, hitungNilaiAsscuranceP, hitungNilaiEmpahty);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }

    //=============================================== DATA REALIBILITY P ===============================================================

    private void getJSONRealibillityP(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showRealibillityP(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_RELIABILITY_P, prodis);
                nama_lok.put(KonekDB.KEY_TA_RELIABILITY_P, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_REAL_P, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showRealibillityP(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_RELIABILITY_P);
                String hasil = c.getString(KonekDB.KEY_HASIL_RELIABILITY_P);

                jmlhRP = Double.parseDouble(jumlah);
                hslRP = Double.parseDouble(hasil);
                hitungRekiabiklityP = hslRP/jmlhRP;
                //Toast.makeText(getApplication(),"Hasil Realibility P =  "+hitungRekiabiklityP,Toast.LENGTH_LONG).show();
                hitungnilaiPersepsi(hitungTangibleP, hitungRekiabiklityP,hitungnilaiResponP, hitungNilaiAsscuranceP, hitungNilaiEmpahty);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }

//=============================================== DATA RESPONSIV P ===============================================================

    private void getJSONResponsivP(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showResponsiveP(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_RESPONSIVENESS_P, prodis);
                nama_lok.put(KonekDB.KEY_TA_RESPONSIVENESS_P, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_RESPON_P, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showResponsiveP(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_RESPONSIVENESS_P);
                String hasil = c.getString(KonekDB.KEY_HASIL_RESPONSIVENESS_P);

                jmlhResponP = Double.parseDouble(jumlah);
                hslResponP = Double.parseDouble(hasil);
                hitungnilaiResponP = hslResponP/jmlhResponP;

                hitungnilaiPersepsi(hitungTangibleP, hitungRekiabiklityP,hitungnilaiResponP, hitungNilaiAsscuranceP, hitungNilaiEmpahty);
                //Toast.makeText(getApplication(),"Nilai Respon P : "+hitungnilaiResponP,Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }

    //=================================================== DATA ASSURANCE P =======================================

    private void getJSONAssuranceP(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAssuranceP(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_ASSURANCE_P, prodis);
                nama_lok.put(KonekDB.KEY_TA_ASSURANCE_P, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_ASSURANCE_P, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showAssuranceP(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_ASSURANCE_P);
                String hasil = c.getString(KonekDB.KEY_HASIL_ASSURANCE_P);

                jmlhAssuranceP = Double.parseDouble(jumlah);
                hslAssuranceP = Double.parseDouble(hasil);
                hitungNilaiAsscuranceP = hslAssuranceP/jmlhAssuranceP;

                //Toast.makeText(getApplication(),"Hasil Assurance P =  "+hitungNilaiAsscuranceP,Toast.LENGTH_LONG).show();

                hitungnilaiPersepsi(hitungTangibleP, hitungRekiabiklityP,hitungnilaiResponP, hitungNilaiAsscuranceP, hitungNilaiEmpahty);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }


    //====================================================== DATA EMPATHY P ==========================================

    private void getJSONEmpathyP(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmpathyP(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_EMPATHY_P, prodis);
                nama_lok.put(KonekDB.KEY_TA_EMPATHY_P, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_EMPATHY_P, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showEmpathyP(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_EMPATHY_H);
                String hasil = c.getString(KonekDB.KEY_HASIL_EMPATHY_P);

                jmlhEmpathyP = Double.parseDouble(jumlah);
                hslEmpathyP = Double.parseDouble(hasil);
                hitungNilaiEmpahty = hslEmpathyP/jmlhEmpathyP;

                hitungnilaiPersepsi(hitungTangibleP, hitungRekiabiklityP,hitungnilaiResponP, hitungNilaiAsscuranceP, hitungNilaiEmpahty);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }
    }



    //================================================================ DATA TANGIBLE H ========================================================


    private void getJSONTangibleH(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showTangibleH(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_TANGIBLE_H, prodis);
                nama_lok.put(KonekDB.KEY_TA_TANGIBLE_H, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_TANG_H, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showTangibleH(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_TANGIBLE_H);
                String hasil = c.getString(KonekDB.KEY_HASIL_TANGIBLE_H_);

                jmlhH = Double.parseDouble(jumlah);
                hslH = Double.parseDouble(hasil);
                hitungTangibleH = hslH/jmlhH;

                hitungHarapan(hitungTangibleH, hitungRekiabiklityH,hitungnilaiResponH, hitungNilaiAsscuranceH, hitungNilaiEmpahtyH);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }


//=============================================== DATA REALIBILITY H ===============================================================

    private void getJSONRealibillityH(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showRealibillityH(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_RELIABILITY_H, prodis);
                nama_lok.put(KonekDB.KEY_TA_RELIABILITY_H, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_REAL_H, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showRealibillityH(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_RELIABILITY_H);
                String hasil = c.getString(KonekDB.KEY_HASIL_RELIABILITY_H);

                jmlhRH = Double.parseDouble(jumlah);
                hslRH = Double.parseDouble(hasil);
                hitungRekiabiklityH = hslRH/jmlhRH;
                //Toast.makeText(getApplication(),"Hasil Realibility P =  "+hitungRekiabiklityP,Toast.LENGTH_LONG).show();
                hitungHarapan(hitungTangibleH, hitungRekiabiklityH,hitungnilaiResponH, hitungNilaiAsscuranceH, hitungNilaiEmpahtyH);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }


    //=============================================== DATA RESPONSIV H ===============================================================

    private void getJSONResponsivH(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showResponsiveH(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_RESPONSIVENESS_H, prodis);
                nama_lok.put(KonekDB.KEY_TA_RESPONSIVENESS_H, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_RESPON_H, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showResponsiveH(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_RESPONSIVENESS_H);
                String hasil = c.getString(KonekDB.KEY_HASIL_RESPONSIVENESS_H);

                jmlhResponH = Double.parseDouble(jumlah);
                hslResponH = Double.parseDouble(hasil);
                hitungnilaiResponH = hslResponH/jmlhResponH;
                //Toast.makeText(getApplication(),"Hasil Responsivness P =  "+hitungnilaiResponP,Toast.LENGTH_LONG).show();
                hitungHarapan(hitungTangibleH, hitungRekiabiklityH,hitungnilaiResponH, hitungNilaiAsscuranceH, hitungNilaiEmpahtyH);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }


    //=================================================== DATA ASSURANCE H =======================================

    private void getJSONAssuranceH(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAssuranceH(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_ASSURANCE_H, prodis);
                nama_lok.put(KonekDB.KEY_TA_ASSURANCE_H, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_ASSURANCE_H, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showAssuranceH(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_ASSURANCE_H);
                String hasil = c.getString(KonekDB.KEY_HASIL_ASSURANCE_H);

                jmlhAssuranceH = Double.parseDouble(jumlah);
                hslAssuranceH = Double.parseDouble(hasil);
                hitungNilaiAsscuranceH = hslAssuranceH/jmlhAssuranceH;
                Toast.makeText(getApplication(),"Hasil Assurance P =  "+hitungNilaiAsscuranceP,Toast.LENGTH_LONG).show();
                hitungHarapan(hitungTangibleH, hitungRekiabiklityH,hitungnilaiResponH, hitungNilaiAsscuranceH, hitungNilaiEmpahtyH);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }

    }



    //====================================================== DATA EMPATHY H ==========================================

    private void getJSONEmpathyH(final String p, final String t){
        final String prodis = p;
        final String ta = t;
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Tampil2.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmpathyH(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(KonekDB.KEY_PRODI_EMPATHY_H, prodis);
                nama_lok.put(KonekDB.KEY_TA_EMPATHY_H, ta);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(KonekDB.URL_GET_NILAI_EMPATHY_H, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showEmpathyH(String json){
        if(json !=null){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(KonekDB.TAG_JSON_ARRAY_HITUNG_TANGIBLE_P);
                JSONObject c = result.getJSONObject(0);

                String jumlah = c.getString(KonekDB.KEY_JUMLAH_EMPATHY_H);
                String hasil = c.getString(KonekDB.KEY_HASIL_EMPATHY_H);

                jmlhEmpathyH = Double.parseDouble(jumlah);
                hslEmpathyH = Double.parseDouble(hasil);
                hitungNilaiEmpahtyH = hslEmpathyH/jmlhEmpathyH;
                //Toast.makeText(getApplication(),"Hasil Empathy P =  "+hitungNilaiEmpahty,Toast.LENGTH_LONG).show();
                hitungHarapan(hitungTangibleH, hitungRekiabiklityH,hitungnilaiResponH, hitungNilaiAsscuranceH, hitungNilaiEmpahtyH);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplication(),"Pencarian Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }
    }


    public void hitungnilaiPersepsi(Double NTangibleP, Double NReliabP, Double NResponP, Double NAssuranceP, Double NEmpathyP){

        //================================================== HITUNG VARIABEL A ===============================================
        hasilAP = hitungTangibleP+hitungRekiabiklityP+hitungnilaiResponP+hitungNilaiAsscuranceP+hitungNilaiEmpahty;
        hasilA2P = hasilA/5;

        rataTangibleP = hsl/100;
        rataReliabP = hslRP/100;
        rataResponP = hslResponP/100;
        rataAssP = hslAssuranceP/100;
        rataEmpathyP = hslEmpathyP/100;


        //================================================== HITUNG VARIABEL B ===============================================
        nilaiB1P = hasilA*rataTangibleP;
        nilaiB2P = hasilA*rataReliabP;
        nilaiB3P = hasilA*rataResponP;
        nilaiB4P = hasilA*rataAssP;
        nilaiB5P = hasilA*rataEmpathyP;

        //================================================== HITUNG VARIABEL C ===============================================
        nilaiCP = nilaiB1+nilaiB2+nilaiB3+nilaiB4+nilaiB5;

        //================================================== HITUNG VARIABEL D ===============================================
        nilaiD = nilaiCP/22;
        tabel_persepsi();

    }

    public void hitungHarapan(Double nTangibleH, Double nReliabH, Double nResponH, Double nAssuranceH, Double nEmpathyH){

        //===================================================== HITUNG VARIABEL A =============================================
        hasilA = hitungTangibleH+ hitungRekiabiklityH+hitungnilaiResponH + hitungNilaiAsscuranceH+ hitungNilaiEmpahtyH;
        hasilA2 = hasilA/5;

        rataTangibleH = hslH/100;
        rataReliabH = hslRH/100;
        rataResponH = hslResponH/100;
        rataAssuranceH = hslAssuranceH/100;
        rataempathyH = hslEmpathyH/100;

        //===================================================== HITUNG VARIABEL B =============================================
        nilaiB1 = hasilA*rataTangibleH;
        nilaiB2 = hasilA*rataReliabH;
        nilaiB3 = hasilA*rataResponH;
        nilaiB4 = hasilA*rataAssuranceH;
        nilaiB5 = hasilA*rataempathyH;

        //===================================================== HITUNG VARIABEL C =============================================
        nilaiCH = nilaiB1+nilaiB2+nilaiB3+nilaiB4+nilaiB5;

        //===================================================== HITUNG VARIABEL D =============================================
        nilaiDH = nilaiCH/22;

        //====================================================== HITUNG VARIABEL SERVQUAL ============================================
        hasilservqual = nilaiD-nilaiDH;

        //String servqual = String.valueOf(hasilservqual).replaceAll(",",",").substring(0,3);

        // Toast.makeText(getApplication(),"Hasil CH :"+nilaiCH+"\nHasil Servqual : "+hasilservqual,Toast.LENGTH_LONG).show();



        /*Toast.makeText(getApplication(),"Data Hasil A : "+hasilA+
                "\n Data hasilA2 "+ hasilA2+"\n nilaiB1 : "+ nilaiB1+
                "\n nilai B2 : "+nilaiB2+"\n nilai B3 : "+nilaiB3+
                "\n nilai B3 : "+nilaiB3+"\n nilai B4 : "+nilaiB4+"\n nilai B5 : "+nilaiB5+"\n nilai CH : "+nilaiCH+
                "\n Hasil Servqual : "+hasilservqual,Toast.LENGTH_LONG).show();*/

        //tabel_harapan();
    }



    void tabel_persepsi(){
        TableRow newRow = new TableRow(Tampil2.this);

        TextView HasilA = new TextView(Tampil2.this);
        TextView HasilA2 = new TextView(Tampil2.this);
        TextView n_b1 = new TextView(Tampil2.this);
        TextView n_b2 = new TextView(Tampil2.this);
        TextView n_b3 = new TextView(Tampil2.this);
        TextView n_b4 = new TextView(Tampil2.this);
        TextView n_b5 = new TextView(Tampil2.this);
        TextView n_ch = new TextView(Tampil2.this);
        TextView n_dh = new TextView(Tampil2.this);



        HasilA.setText(hasilAP.toString());
        HasilA2.setText(hasilA2P.toString());
        n_b1.setText(nilaiB1P.toString());
        n_b2.setText(nilaiB2P.toString());
        n_b3.setText(nilaiB3P.toString());
        n_b4.setText(nilaiB4P.toString());
        n_b5.setText(nilaiB5P.toString());
        n_ch.setText(nilaiCP.toString());
        n_dh.setText(nilaiD.toString());

        newRow.addView(HasilA);
        newRow.addView(HasilA2);
        newRow.addView(n_b1);
        newRow.addView(n_b2);
        newRow.addView(n_b3);
        newRow.addView(n_b4);
        newRow.addView(n_b5);
        newRow.addView(n_ch);
        newRow.addView(n_dh);

        tb_hasil.addView(newRow);
    }



    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1);
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2);
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3);
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4);
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5);
        valueSet1.add(v1e6);
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0);
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1);
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2);
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3);
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Bakso");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Mie Ayam");
        barDataSet2.setColor(Color.rgb(193, 37, 82));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }

}