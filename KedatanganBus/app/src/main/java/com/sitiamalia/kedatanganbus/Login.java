package com.sitiamalia.kedatanganbus;


/**
 * Created by Siti Amalia
  */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sitiamalia.kedatanganbus.app.AppController;
import com.sitiamalia.kedatanganbus.app.Server;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText txt_username, txt_password;
    private String username, password;
    private ProgressDialog pDialog;
    Button BtnLogin, BtnRegistrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pDialog = new ProgressDialog(Login.this);

        BtnLogin = (Button)findViewById(R.id.btnLogin);
        BtnRegistrasi = (Button)findViewById(R.id.btnRegistered);
        txt_username = (EditText) findViewById(R.id.editUsername);
        txt_password = (EditText) findViewById(R.id.editPassword);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MenuUtama.class);
                startActivity(i);
                finish();
            }
        });

       BtnRegistrasi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i =  new Intent(Login.this, regist.class);
               startActivity(i);
               finish();
           }
       });

    }

    private void login() {
        //Getting values from edit texts
        username = txt_username.getText().toString().trim();
        password = txt_password.getText().toString().trim();

        if(username.equals("") || password.equals("")){
            //Beranda();
            Toast.makeText(getApplicationContext(), "Email atau Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

            return;

        }
        pDialog.setMessage("Login Process...");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Login_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(Config.Hasil)) {
                            hideDialog();
                            //Toast.makeText(context, "Login Sukses", Toast.LENGTH_LONG).show();


                        } else {
                            hideDialog();
                            //Displaying an error message on toast
                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "The server unreachable", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.key_email, username);
                params.put(Config.key_password, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }

  private void showDialog() {
      if(!pDialog.isShowing())
          pDialog.show();
        }
private void hideDialog(){
    if (pDialog.isShowing())
        pDialog.dismiss();
}
}