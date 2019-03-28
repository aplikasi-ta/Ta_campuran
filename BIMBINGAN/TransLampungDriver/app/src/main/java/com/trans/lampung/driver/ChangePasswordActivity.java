package com.trans.lampung.driver;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.trans.lampung.driver.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser mFirebaseUser;
    private ProgressBar progressBar;
    private EditText pwdLama, pwdBaru, pwdConfirm;
    private FirebaseAuth.AuthStateListener authListener;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        pwdLama = (EditText) findViewById(R.id.passwordOld);
        pwdBaru = (EditText) findViewById(R.id.passwordNew);
        pwdConfirm = (EditText) findViewById(R.id.passwordConfirm);
        progressBar = (ProgressBar) findViewById(R.id.progressBarGP);

        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));
        }

        btnReset = (Button) findViewById(R.id.btn_reset_password);
        auth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePasswordActivity.this, ResetPasswordActivity.class));
            }
        });

    }

    public void GantiPassword(View view){
        progressBar.setVisibility(View.VISIBLE);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            mFirebaseUser = auth.getCurrentUser();

            Toast.makeText(ChangePasswordActivity.this, mFirebaseUser.getEmail(),
                    Toast.LENGTH_SHORT).show();

            auth.signInWithEmailAndPassword(mFirebaseUser.getEmail(), pwdLama.getText().toString())
                    .addOnCompleteListener(ChangePasswordActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
///                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(ChangePasswordActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            } else {
                                // Initialize Firebase Auth and Database Reference
                                if (mFirebaseUser != null && !pwdBaru.getText().toString().trim().equals("")) {
                                    if (pwdBaru.getText().toString().trim().length() < 6) {
                                        pwdBaru.setError("Password terlalu pendek, masukkan minimal 6 karakter");
                                        progressBar.setVisibility(View.GONE);
                                    } else if(pwdBaru.getText().toString().equals(pwdConfirm.getText().toString())){
                                        mFirebaseUser.updatePassword(pwdBaru.getText().toString().trim())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(ChangePasswordActivity.this, "Password berhasil diperbarui, silahkan masuk dengan password baru Anda!", Toast.LENGTH_SHORT).show();
                                                            signOut();
                                                            progressBar.setVisibility(View.GONE);
                                                        } else {
                                                            Toast.makeText(ChangePasswordActivity.this, "Gagal memperbarui Password", Toast.LENGTH_SHORT).show();
                                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    }
                                                });
                                    }else if(!pwdBaru.getText().toString().equals(pwdConfirm.getText().toString())){
                                        pwdConfirm.setError("Konfirmasi Password Tidak Sesuai");
                                        progressBar.setVisibility(View.GONE);
                                    }
                                } else if (pwdBaru.getText().toString().trim().equals("")) {
                                    pwdBaru.setError("Masukkan password");
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        }
                    });
        }

    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
