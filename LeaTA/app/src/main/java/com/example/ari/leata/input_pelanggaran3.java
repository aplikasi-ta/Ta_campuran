package com.example.ari.leata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.HashMap;

public class input_pelanggaran3 extends AppCompatActivity {
    TableLayout table;
    Button btn_next;
    CheckBox cb36,cb37,cb38,cb39,cb40,cb41,cb42,cb43,cb44,cb45,cb46,cb47,cb48,cb49,cb50,cb51,cb52,cb53,cb54,cb55,cb56;
    String point36,point37,point38,point39,point40,point41,point42,point43,point44,point45,point46,
            point47,point48,point49,point50,point51,point52,point53,point54,point55,point56;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pelanggaran3);
        table = (TableLayout) findViewById(R.id.tablelayoutid);
        btn_next = (Button) findViewById(R.id.btn_next);
        cb36 = (CheckBox) findViewById(R.id.cb_36);
        cb37 = (CheckBox) findViewById(R.id.cb_37);
        cb38 = (CheckBox) findViewById(R.id.cb_38);
        cb39 = (CheckBox) findViewById(R.id.cb_39);
        cb40 = (CheckBox) findViewById(R.id.cb_40);
        cb41 = (CheckBox) findViewById(R.id.cb_41);
        cb42 = (CheckBox) findViewById(R.id.cb_42);
        cb44 = (CheckBox) findViewById(R.id.cb_43);
        cb45 = (CheckBox) findViewById(R.id.cb_44);
        cb46 = (CheckBox) findViewById(R.id.cb_45);
        cb47 = (CheckBox) findViewById(R.id.cb_46);
        cb48 = (CheckBox) findViewById(R.id.cb_47);
        cb49 = (CheckBox) findViewById(R.id.cb_48);
        cb50 = (CheckBox) findViewById(R.id.cb_49);
        cb51 = (CheckBox) findViewById(R.id.cb_50);
        cb52 = (CheckBox) findViewById(R.id.cb_51);
        cb52 = (CheckBox) findViewById(R.id.cb_52);
        cb53 = (CheckBox) findViewById(R.id.cb_53);
        cb54 = (CheckBox) findViewById(R.id.cb_54);
        cb55 = (CheckBox) findViewById(R.id.cb_55);
        cb56 = (CheckBox) findViewById(R.id.cb_56);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb36.isChecked()){
                    point36 = "10";
                }if(cb37.isChecked()){
                    point37 = "20";
                }if(cb38.isChecked()){
                    point38 = "8";
                }if(cb39.isChecked()){
                    point39 = "10";
                }if(cb40.isChecked()){
                    point40 = "20";
                }if(cb41.isChecked()){
                    point41 = "20";
                }if(cb42.isChecked()){
                    point42 = "30";
                }if(cb43.isChecked()){
                    point43 = "25";
                }if(cb44.isChecked()){
                    point44 = "15";
                }if(cb45.isChecked()){
                    point45 = "15";
                }if(cb46.isChecked()){
                    point46 = "10";
                }if(cb47.isChecked()){
                    point47 = "25";
                }if(cb48.isChecked()){
                    point48 = "5";
                }if(cb49.isChecked()){
                    point49 = "10";
                }if(cb50.isChecked()){
                    point50 = "5";
                }if(cb51.isChecked()){
                    point51 = "50";
                }if(cb52.isChecked()){
                    point52 = "120";
                }if(cb53.isChecked()){
                    point53 = "40";
                }if(cb54.isChecked()){
                    point54 = "5";
                }if(cb55.isChecked()){
                    point55 = "4";
                }if(cb56.isChecked()){
                    point56 = "3";
                }else{
                    Toast.makeText(getApplication(),"Anda Belum Memilih",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void lg36(){

        final String langgar = "11. Makan/minum/berada dikantin saat KBM";
        final String poin = point36;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point36);
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


    private void lg37(){

        final String langgar = "12. Mengaktifkan HP dalam kelas dan saat KBM Handphone berbunyi saat KBM";
        final String poin = point37;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point37);
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


    private void lg38(){

        final String langgar = "13. Menggunakan Hanphone saat KBM";
        final String poin = point38;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point38);
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


    private void lg39(){

        final String langgar = "14. Menyontek saat ulangan";
        final String poin = point39;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point39);
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


    private void lg40(){

        final String langgar = "15. Bermusuhan dengan teman baik didalam maupun diluar sekolah";
        final String poin = point40;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point40);
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


    private void lg41(){

        final String langgar = "16. Menyebarkan isu/informasi yang tidak benar";
        final String poin = point41;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point41);
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


    private void lg42(){

        final String langgar = "17. Tidak membuat organisasi selain OSIS";
        final String poin = point42;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point42);
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


    private void lg43(){

        final String langgar = "18. Bertato, bertindik";
        final String poin = point43;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point43);
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



    private void lg44(){

        final String langgar = "1. Merusak benda milik teman, guru, sekolah dengan sengaja maupun tidak tidak sengaja (wajib mengganti)";
        final String poin = point44;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point44);
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


    private void lg45(){

        final String langgar = "2. Corat-coret mengotori sekolah, milik orang lain";
        final String poin = point45;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point45);
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


    private void lg46(){

        final String langgar = "3. Membuat kegaduhan dalam kelas pada saat KBM";
        final String poin = point46;
        final String nip = "999";
        final String guru= "AAA";
        final String nis = "9999";
        final String siswa = "LALA";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(input_pelanggaran3.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(input_pelanggaran3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_LANGGAR, langgar);
                params.put(KonekDB.KEY_POIN, point46);
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
