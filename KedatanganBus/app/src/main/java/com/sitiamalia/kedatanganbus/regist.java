package com.sitiamalia.kedatanganbus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class regist extends AppCompatActivity {

    ProgressDialog pDialog;
    Button BtnRegistered, BtnLogin;
    String Username, Password, Nama, Email;
    EditText editUsername, editPassword, editEmail, editNama;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        pDialog = new ProgressDialog(regist.this);

        BtnLogin= (Button)findViewById(R.id.BtnLogin);
        BtnRegistered = (Button)findViewById(R.id.BtnRegister);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editNama = (EditText) findViewById(R.id.editNama);

        BtnRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(regist.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(regist.this, regist.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void regist() {
        //Getting values from edit texts
        Username = editUsername.getText().toString().trim();
        Password = editPassword.getText().toString().trim();
        Email = editEmail.getText().toString().trim();
        Nama = editNama.getText().toString().trim();

        if(Username.equals("") || Password.equals("") || Nama.equals("")|| Email.equals("")){
            //Beranda();
            Toast.makeText(getApplicationContext(), "Email atau Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

            return;

        }
        pDialog.setMessage("regist Process...");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.regist_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(Config.Hasil)) {
                            hideDialog();
                            //Toast.makeText(context, "regist Sukses", Toast.LENGTH_LONG).show();

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
                params.put(Config.key_email, Username);
                params.put(Config.key_password, Password);

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



