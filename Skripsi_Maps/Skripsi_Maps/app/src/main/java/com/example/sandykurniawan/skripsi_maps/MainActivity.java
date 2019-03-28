package com.example.sandykurniawan.skripsi_maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    public static final int LOGIN_GOOGLE_NUMBER = 012;

    private Button btnSignInGoogle;
    private TextView tvName;
    private TextView tvEmail;
    private ImageView ivProfile;
    private ProgressDialog progressDialog;


    //google attribute
    private GoogleApiClient googleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignInGoogle = (Button) findViewById(R.id.btn_sign_in_google);
        tvName = (TextView) findViewById(R.id.textview_name);
        tvEmail = (TextView) findViewById(R.id.textview_email);
        ivProfile = (ImageView) findViewById(R.id.imageview_profile_pict);


        //set progressDialog
        progressDialog = new ProgressDialog(this);


        //google signin uption
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        //set google api
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this) /*activity, onConnectionFailedListener*/
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Login Using Google");
                progressDialog.show();
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);

                startActivityForResult(intent, LOGIN_GOOGLE_NUMBER);
            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getCache();
    }

    /**
     * this method used to get the cache if you have logged or not before.
     */
    private void getCache() {
        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (optionalPendingResult.isDone()) {
            GoogleSignInResult result = optionalPendingResult.get();
            handleSignInResult(result);
        } else {
            optionalPendingResult.setResultCallback(new ResultCallback<GoogleSignInResult> () {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_GOOGLE_NUMBER) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    /**
     * this method used to handle of your account after logged
     *
     * @param result
     */
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();

            //set name and photo profile
            tvName.setText(account.getDisplayName());
            tvEmail.setText(account.getEmail());
            Glide.with(this)
                    .load(account.getPhotoUrl())
                    .into(ivProfile);



            progressDialog.dismiss();

            //hide and show button login and logout
            btnSignInGoogle.setVisibility(View.GONE);
            if(tvEmail.getText().toString().equals("sandy.13421065@student.ubl.ac.id") ||
                    tvEmail.getText().toString().equals("rizky19.15@gmail.com")){
                Intent intent = new Intent(MainActivity.this, Menu_Activity.class);
                intent.putExtra("nama_admin",tvName.getText().toString());
                intent.putExtra("email",tvEmail.getText().toString());
                startActivity(intent);
                btnSignInGoogle.setVisibility(View.GONE);
            }else {
                updateUI(true);
            }



        } else {
            progressDialog.dismiss();
        }
    }




    /**
     * this method used to update visible or gone of user interface
     *
     * @param statusLogin
     */
    private void updateUI(boolean statusLogin) {
        if (statusLogin == true) {
            Intent intent = new Intent(MainActivity.this, Menu_Activity.class);
            intent.putExtra("nama",tvName.getText().toString());
            intent.putExtra("email",tvEmail.getText().toString());
            startActivity(intent);
            btnSignInGoogle.setVisibility(View.GONE);
        } else {
            btnSignInGoogle.setVisibility(View.VISIBLE);
        }
    }

}
