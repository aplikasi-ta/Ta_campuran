package com.example.ari.saidata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class RegStaff extends AppCompatActivity {
EditText txtNPM,txtUsername,txtMail,txtPass;
    Button btn_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_staff);
        txtNPM = (EditText) findViewById(R.id.txtNPM);
        txtUsername = (EditText) findViewById(R.id.txtMailMhs);
        txtMail = (EditText) findViewById(R.id.txtMail);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btn_reg = (Button) findViewById(R.id.btnRegstaff);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanStaff();
            }
        });
    }


    void simpanStaff(){

        final String npm = txtNPM.getText().toString().trim();
        final String username = txtUsername.getText().toString().trim();
        final String mail = txtMail.getText().toString().trim();
        final String password = txtPass.getText().toString().trim();

        class AddEmployee2 extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegStaff.this,"Mohon Tunggu","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegStaff.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_EMP_NPM,npm);
                params.put(KonekDB.KEY_EMP_USER, username);
                params.put(KonekDB.KEY_EMP_MAIL, mail);
                params.put(KonekDB.KEY_EMP_PASS, password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_CREATE_STAFF, params);
                return res;
            }
        }

        AddEmployee2 ae = new AddEmployee2();
        ae.execute();
    }
}
