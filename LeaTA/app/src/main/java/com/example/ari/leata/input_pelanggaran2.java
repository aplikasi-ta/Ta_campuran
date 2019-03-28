package com.example.ari.leata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.HashMap;

public class input_pelanggaran2 extends AppCompatActivity {
    TableLayout table;
    Button btn_next;
    CheckBox cb14,cb15,cb16,cb17,cb18,cb19,cb20,cb21,cb22,cb23,cb24,cb25,cb26,cb27,cb28,cb29,cb30,cb31,cb32,cb33,cb34,cb35;
    String point14,point15,point16,point17,point18,point19,point20,point21,point22,point23,point24,
            point25,point26,point27,point28,point29,point30,point31,point32,point33,point34,point35;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pelanggaran2);
        table = (TableLayout) findViewById(R.id.tablelayoutid);
        btn_next = (Button) findViewById(R.id.btn_next);
        cb14 = (CheckBox) findViewById(R.id.cb_14);
        cb15 = (CheckBox) findViewById(R.id.cb_15);
        cb16 = (CheckBox) findViewById(R.id.cb_16);
        cb17 = (CheckBox) findViewById(R.id.cb_17);
        cb18 = (CheckBox) findViewById(R.id.cb_18);
        cb19 = (CheckBox) findViewById(R.id.cb_19);
        cb20 = (CheckBox) findViewById(R.id.cb_20);
        cb21 = (CheckBox) findViewById(R.id.cb_21);
        cb22 = (CheckBox) findViewById(R.id.cb_22);
        cb23 = (CheckBox) findViewById(R.id.cb_23);
        cb24 = (CheckBox) findViewById(R.id.cb_24);
        cb25 = (CheckBox) findViewById(R.id.cb_25);
        cb26 = (CheckBox) findViewById(R.id.cb_26);
        cb27 = (CheckBox) findViewById(R.id.cb_27);
        cb28 = (CheckBox) findViewById(R.id.cb_28);
        cb29 = (CheckBox) findViewById(R.id.cb_29);
        cb30 = (CheckBox) findViewById(R.id.cb_30);
        cb31 = (CheckBox) findViewById(R.id.cb_31);
        cb32 = (CheckBox) findViewById(R.id.cb_32);
        cb33 = (CheckBox) findViewById(R.id.cb_33);
        cb34 = (CheckBox) findViewById(R.id.cb_34);
        cb35 = (CheckBox) findViewById(R.id.cb_35);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb14.isChecked()){
                    point14 = "5";
                }if(cb15.isChecked()){
                    point15 = "10";
                }if(cb16.isChecked()){
                    point16 = "5";
                }if(cb17.isChecked()){
                    point17 = "10";
                }if(cb18.isChecked()){
                    point18 = "10";
                }if(cb19.isChecked()){
                    point19 = "3";
                }if(cb20.isChecked()){
                    point20 = "3";
                }if(cb21.isChecked()){
                    point21 = "3";
                }if(cb22.isChecked()){
                    point22 = "1";
                }if(cb23.isChecked()){
                    point23 = "1";
                }if(cb24.isChecked()){
                    point24 = "3";
                }if(cb25.isChecked()){
                    point25 = "3";
                }if(cb26.isChecked()){
                    point26 = "5";
                }if(cb27.isChecked()){
                    point27 = "7";
                }if(cb28.isChecked()){
                    point28 = "10";
                }if(cb29.isChecked()){
                    point29 = "5";
                }if(cb30.isChecked()){
                    point30 = "3";
                }if(cb31.isChecked()){
                    point31 = "10";
                }if(cb32.isChecked()){
                    point32 = "15";
                }if(cb33.isChecked()){
                    point33 = "25";
                }if(cb34.isChecked()){
                    point34 = "10";
                }if(cb35.isChecked()){
                    point35 = "25";
                }else{
                    Toast.makeText(getApplication(),"Anda Belum Memilih",Toast.LENGTH_LONG).show();
                }

                lg14(); lg15(); lg16(); lg17(); lg18(); lg19(); lg20(); lg21(); lg22();lg23();lg24();lg25();lg26();
                lg27();lg28();lg29();lg30();lg31();lg32();lg34();lg35();

                Intent intent = new Intent(input_pelanggaran2.this, input_pelanggaran3.class);
                startActivity(intent);
            }
        });
    }

    private void lg14(){

        final String langgar = "5. Celana diinjak bagian bawahnya / dengan sengaja disobek bagian bawahnya";
        final String poin = point14;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point14);
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

    //==========================================================

    private void lg15(){

        final String langgar = "6. Berpakaian tidak rapih / tidak dimasukan";
        final String poin = point15;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point15);
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

    //==========================================================

    private void lg16(){

        final String langgar = "7. Memakai atribut tidak sesuai dengan ketentuan";
        final String poin = point16;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point16);
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


    //==========================================================

    private void lg17(){

        final String langgar = "8. Memakai sepatu sandal";
        final String poin = point17;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point17);
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


    //==========================================================

    private void lg18(){

        final String langgar = "9. Memakai sepatu di injak bagian belakangnya / tumitnya";
        final String poin = point18;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point18);
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


    //==========================================================

    private void lg19(){

        final String langgar = "10. Memakai jaket saat KBM (kecuali sakit)";
        final String poin = point19;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point19);
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


    //==========================================================

    private void lg20(){

        final String langgar = "11. Memakai topi seragam sekolah";
        final String poin = point20;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point20);
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


    //==========================================================

    private void lg21(){

        final String langgar = "12. Memakai jilbab tidak sesuai ketentuan sekolah";
        final String poin = point21;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point21);
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



    //==========================================================

    private void lg22(){

        final String langgar = "13. Tidak memakai kaos kaki";
        final String poin = point22;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point22);
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


    //==========================================================

    private void lg23(){

        final String langgar = "14. Tidak memakai kaos singlet";
        final String poin = point23;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point23);
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



    //==========================================================

    private void lg24(){

        final String langgar = "15. Tidak memakai ikat pinggang";
        final String poin = point24;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point24);
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


    //==========================================================

    private void lg25(){

        final String langgar = "16. Tidak memakai topi saat upacara";
        final String poin = point25;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point25);
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

    //==========================================================

    private void lg26(){

        final String langgar = "1. Memakai aksesoris gelang, kalung, anting (pria)";
        final String poin = point26;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point26);
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


    //==========================================================

    private void lg27(){

        final String langgar = "2. Rambut panjang (pria)";
        final String poin = point27;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point27);
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


    //==========================================================

    private void lg28(){

        final String langgar = "3. Rambut di cat";
        final String poin = point28;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point28);
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


    //==========================================================

    private void lg29(){

        final String langgar = "4. Make up yang berlebihan";
        final String poin = point29;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point29);
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


    //==========================================================

    private void lg30(){

        final String langgar = "5. Kuku panjang (kuku di potong saat itu)";
        final String poin = point30;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point30);
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


    //==========================================================

    private void lg31(){

        final String langgar = "6. Berbohong / memberikan keterangan palsu";
        final String poin = point31;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point31);
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


    //==========================================================

    private void lg32(){

        final String langgar = "7. Tidak patuh mengeluarkan kata-kata tidak senonoh / tidak sopan dihadapan guru, orang tua, siswa";
        final String poin = point32;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point32);
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


    //==========================================================

    private void lg33(){

        final String langgar = "8. Berkata kasar / membentak guru, orang tua, siswa";
        final String poin = point33;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point33);
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


    private void lg34(){

        final String langgar = "9. Menyakiti perasaan orang lain";
        final String poin = point34;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point34);
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


    private void lg35(){

        final String langgar = "10. Mengintimidasi, mengancam siswa, teman lain";
        final String poin = point35;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran2.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point35);
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
