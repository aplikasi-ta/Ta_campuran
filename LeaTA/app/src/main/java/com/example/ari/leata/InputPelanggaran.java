package com.example.ari.leata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class InputPelanggaran extends AppCompatActivity {
    TableLayout table;
    Button btn_next;
    CheckBox cb1,cb2,cb3,cb4a,cb4b,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13;
    String point1,point2,point3,point4a,point4b,point5,point6,point7,point8,point9,point10,
    point11,point12,point13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pelanggaran);
        table = (TableLayout) findViewById(R.id.tablelayoutid);
        btn_next = (Button) findViewById(R.id.btn_next);
        cb1 = (CheckBox) findViewById(R.id.cb_1);
        cb2 = (CheckBox) findViewById(R.id.cb_2);
        cb3 = (CheckBox) findViewById(R.id.cb_3);
        cb4a = (CheckBox) findViewById(R.id.cb_4a);
        cb4b = (CheckBox) findViewById(R.id.cb_4b);
        cb5 = (CheckBox) findViewById(R.id.cb_5);
        cb6 = (CheckBox) findViewById(R.id.cb_6);
        cb7 = (CheckBox) findViewById(R.id.cb_7);
        cb8 = (CheckBox) findViewById(R.id.cb_8);
        cb9 = (CheckBox) findViewById(R.id.cb_9);
        cb10 = (CheckBox) findViewById(R.id.cb_10);
        cb11 = (CheckBox) findViewById(R.id.cb_11);
        cb12 = (CheckBox) findViewById(R.id.cb_12);
        cb13 = (CheckBox) findViewById(R.id.cb_13);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked()){
                    point1 = "4";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb2.isChecked()){
                    point2 = "4";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb3.isChecked()){
                    point3 = "2";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb4a.isChecked()){
                    point4a = "3";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb4b.isChecked()){
                    point4b = "3";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb5.isChecked()){
                    point5 = "0";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb6.isChecked()){
                    point6 = "6";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb7.isChecked()){
                    point7 = "10";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb8.isChecked()){
                    point8 = "7";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb9.isChecked()){
                    point9 = "3";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb10.isChecked()){
                    point10 = "10";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb11.isChecked()){
                    point11 = "4";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb12.isChecked()){
                    point12 = "10";
                    //Toast.makeText(getApplication(),"Hallo "+point1,Toast.LENGTH_LONG).show();
                }if(cb13.isChecked()){
                    point13 = "10";
                }else{
                    Toast.makeText(getApplication(),"Anda Belum Memilih",Toast.LENGTH_LONG).show();
                }

                lg1(); lg2(); lg3(); lg4a(); lg4b(); lg5(); lg6(); lg7(); lg8(); lg9(); lg10();
                lg11(); lg12(); lg13();

                Intent intent = new Intent(InputPelanggaran.this, input_pelanggaran2.class);
                startActivity(intent);

            }
        });
    }


    private void lg1(){

        final String langgar = "1. 1-10 menit setelah bel tanda masuk dibunyikan,siswa ndiperbolehkan Masuk dengan izin guru";
        final String poin = point1;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point1);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    //========== LG1 ========================

    //=======================================
    private void lg2(){

        final String langgar = "2. Lebih dari 10 menit setelah bel masuk di bunyikan, diperbolehkan masuk setelah jam pelajaran pertama selesai.";
        final String poin = point2;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point2);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    //======================= LG 3 ======================

    //===================================================
    private void lg3(){

        final String langgar = "3. Setelah bel istirahat / saat pergantian pelajaran";
        final String poin = point3;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point3);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    //==============================================  LG 4 ==============
    //===================================================================
    private void lg4a(){

        final String langgar = "4a. Sampai dengan 10 menit";
        final String poin = point4a;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point4a);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    //===================================================================
    private void lg4b(){

        final String langgar = "4b. Lebih dari 10 menit";
        final String poin = point4b;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point4b);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg5(){

        final String langgar = "5. Ketidakhadiran dengan alasan izin dan sakit harus melampirkan surat keterangan dari pihak yang berwenang (orang tua, dokter, dll)";
        final String poin = point5;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point5);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    //===================================================================
    private void lg6(){

        final String langgar = "6. Tidak masuk tanpa keterangan (alpa)";
        final String poin = point6;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point6);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg7(){

        final String langgar = "7. Tidak masuk dengan membuat keterangan palsu / meragukan";
        final String poin = point7;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point7);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg8(){

        final String langgar = "8. Tidak masuk dengan membuat keterangan palsu / meragukan";
        final String poin = point8;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point8);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg9(){

        final String langgar = "9. Tidak masuk karena izin";
        final String poin = point9;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point9);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg10(){

        final String langgar = "10. Memakai seragam tidak sesuai dengan ketentuan sekolah";
        final String poin = point10;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point10);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg11(){

        final String langgar = "11. Tidak memakai badge lokasi / OSIS";
        final String poin = point11;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point11);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg12(){

        final String langgar = "12. Berpakaian ketat, rok mini, baju jankis";
        final String poin = point12;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point12);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //===================================================================
    private void lg13(){

        final String langgar = "13. Memakai celana gombor, pensil, celana begok (pelanggaran poin 1,2,3,4 dipulangkan untuk mengganti pakaian standar yang ditentukan sekolah)";
        final String poin = point13;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputPelanggaran.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputPelanggaran.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point13);
                params.put(KonekDB.KEY_NIP, nip);
                params.put(KonekDB.KEY_GURU, guru);
                params.put(KonekDB.KEY_NIS, nis);
                params.put(KonekDB.KEY_SISWA, siswa);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_LANGGAR1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

}