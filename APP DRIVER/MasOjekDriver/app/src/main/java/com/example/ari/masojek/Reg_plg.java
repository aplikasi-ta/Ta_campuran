package com.example.ari.masojek;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Reg_plg extends AppCompatActivity {
EditText txtMails, txtNamas, txtPhones, txtPasss;
    Button btn_register;
    TextView txtLogTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_plg);
        txtMails = (EditText) findViewById(R.id.txtMail);
        txtNamas = (EditText) findViewById(R.id.txtNama);
        txtPhones = (EditText) findViewById(R.id.txtPhone);
        txtPasss = (EditText) findViewById(R.id.txtPass);
        txtLogTo = (TextView) findViewById(R.id.txtLogTo);
        btn_register = (Button) findViewById(R.id.btn_regs);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMails.getText().toString().trim().equals("") || txtNamas.getText().toString().trim().equals("") || txtPhones.getText().toString().trim().equals("")||txtPasss.getText().toString().trim().equals("")){
                    Toast.makeText(getApplication(),"Data Harus Lengkap",Toast.LENGTH_LONG).show();
                }else{
                    register_user();
                }
            }
        });
        txtLogTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reg_plg.this, LogApps.class);
                startActivity(intent);
            }
        });
    }


    public void register_user(){
        final String Email = txtMails.getText().toString().trim();
        final String Nama = txtNamas.getText().toString().trim();
        final String Phone = txtPhones.getText().toString().trim();
        final String Pass = txtPasss.getText().toString().trim();

        class AddSeen extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Reg_plg.this,"Register Process","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Reg_plg.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(KonekDB.KEY_EMP_EMAIL,Email);
                params.put(KonekDB.KEY_EMP_NAMA, Nama);
                params.put(KonekDB.KEY_EMP_PHONE,Phone);
                params.put(KonekDB.KEY_EMP_PASS,Pass);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(KonekDB.URL_GET_PELANGGAN, params);
                return res;
            }
        }

        AddSeen ae = new AddSeen();
        ae.execute();
    }
}
