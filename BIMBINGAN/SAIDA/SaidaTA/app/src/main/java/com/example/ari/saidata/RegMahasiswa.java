package com.example.ari.saidata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class RegMahasiswa extends AppCompatActivity {
EditText txtNpm,txtMail,txtPass;
    Spinner cbProdi;
    public static String prodi;
    Button btn_reg_mhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regmahasiswa);
        txtNpm = (EditText) findViewById(R.id.txtNPM);
        txtMail = (EditText) findViewById(R.id.txtMailMhs);
        cbProdi = (Spinner) findViewById(R.id.spinnerProdi);
        txtPass =  (EditText) findViewById(R.id.txtPassMhs);
        btn_reg_mhs = (Button) findViewById(R.id.btnSimpanMHS);

        cbProdi.setOnItemSelectedListener(new RegMahasiswa.CustomOnItemSelectedListener2());

        btn_reg_mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanMahasiswa();
            }
        });
    }


    public class CustomOnItemSelectedListener2 implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(cbProdi.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(cbProdi.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
               prodi = parent.getItemAtPosition(pos).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

    void simpanMahasiswa(){

        final String npm = txtNpm.getText().toString().trim();
        final String mail = txtMail.getText().toString().trim();
        final String programStudi = prodi;
        final String passwords = txtPass.getText().toString().trim();

        class AddEmployee2 extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegMahasiswa.this,"Mohon Tunggu","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegMahasiswa.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_EMP_NPM_MHS, npm);
                params.put(KonekDB.KEY_EMP_MAIL_MHS, mail);
                params.put(KonekDB.KEY_EMP_PRODI, programStudi);
                params.put(KonekDB.KEY_EMP_PASS_MHS, passwords);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_CREATE_MAHASISWA, params);
                return res;
            }
        }

        AddEmployee2 ae = new AddEmployee2();
        ae.execute();
    }
}
