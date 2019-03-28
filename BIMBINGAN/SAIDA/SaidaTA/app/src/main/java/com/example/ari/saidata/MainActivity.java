package com.example.ari.saidata;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static EditText editTextIdMhs;
    public static EditText editTextPassword;
    Button btn_logs, btn_regis, btn_staff;
    public static String npm;
    Spinner spinnerData;
    String dataLog;


    public static final String USER_NAME = "USERNAME";
    String username, password,username2, password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextIdMhs = (EditText) findViewById(R.id.txtMailMhs);
        editTextPassword = (EditText) findViewById(R.id.txtPassword);
        btn_logs = (Button) findViewById(R.id.btnLog);
        //btn_staff = (Button) findViewById(R.id.btn_staff);
        //spinnerData = (Spinner) findViewById(R.id.spinUSser);
        btn_regis = (Button) findViewById(R.id.btnReg);

        //spinnerData.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        btn_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeLogin();
            }
        });

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PilihRegistrasi.class);
                startActivity(intent);
            }
        });




    }


    public void invokeLogin(){
        username = editTextIdMhs.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        login(username, password);

    }

    private void login(final String username, final String password){
        class LoginAsync extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Mohon Tunggu", "Loading...",false,false);
            }

            @Override
            protected String doInBackground(String... params) {
                InputStream is =  null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("Id_mhs", username));
                nameValuePairs.add(new BasicNameValuePair("Password", password));
                String result=null;

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://pstiubl.com/saida/login.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while((line = reader.readLine()) != null){
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                }catch (ClientProtocolException e){
                    e.printStackTrace();
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                String s = result.trim();
                loadingDialog.dismiss();
                if(s.equals("success")){
                    Intent intent = new Intent(MainActivity.this, PilihTAQuiz.class);
                    intent.putExtra(USER_NAME, username);
                    npm = username;
                    intent.putExtra(npm, npm);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Username atau Password Anda Salah", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }


    //==============================================


    private void login2(final String username, final String password){
        class LoginAsync extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Mohon Tunggu", "Loading...",false,false);
            }

            @Override
            protected String doInBackground(String... params) {
                InputStream is =  null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("Username", username));
                nameValuePairs.add(new BasicNameValuePair("Password", password));
                String result=null;

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://pstiubl.com/saida/login2.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while((line = reader.readLine()) != null){
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                }catch (ClientProtocolException e){
                    e.printStackTrace();
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                String s = result.trim();
                loadingDialog.dismiss();
                if(s.equals("success")){
                    Intent intent = new Intent(MainActivity.this, Tampil2.class);
                    intent.putExtra(USER_NAME, username);
                    npm = username;
                    intent.putExtra(npm, npm);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Username atau Password Anda Salah", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }
}
