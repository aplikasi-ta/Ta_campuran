package com.trans.lampung.driver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.trans.lampung.driver.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    EditText Nama,Nktp,Pnumber,Email, Alamat;
    RadioGroup rgJekel;
    RadioButton rbLaki, rbPerempuan;
    Spinner spinner;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseUser mFirebaseUser;

    private DatabaseReference mDatabase;
    private String mUserId;

    private static final int PICK_IMAGE_REQUEST = 234;

    //Buttons
    private Button buttonChoose;
    private Button buttonUpload;

    //ImageView
    private ImageView imageView;

    //a Uri object to store file path
    private Uri filePath;

    private StorageReference mStorageRef;

    Bitmap bmImagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        Nama=(EditText)findViewById(R.id.fname);
        Nktp=(EditText)findViewById(R.id.nktp);
        rgJekel = (RadioGroup) findViewById(R.id.rgJekel);
        rbLaki = (RadioButton) findViewById(R.id.rbLaki);
        rbPerempuan = (RadioButton) findViewById(R.id.rbPerempuan);
        Pnumber=(EditText)findViewById(R.id.pnumber);
        Email=(EditText)findViewById(R.id.email);
        Alamat=(EditText)findViewById(R.id.alamat);

        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
//        spinner.setOnItemSelectedListener(SignupActivity.this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Bandara Raden Inten II - Graha Wangsa");
        categories.add("Graha Wangsa - Bandara Raden Inten II");
        categories.add("Unila - Itera");
        categories.add("Itera - Unila");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        imageView = (ImageView) findViewById(R.id.ivProfile);


        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
///                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // Initialize Firebase Auth and Database Reference
                                    auth = FirebaseAuth.getInstance();
                                    mFirebaseUser = auth.getCurrentUser();
                                    mDatabase = FirebaseDatabase.getInstance().getReference();

                                    if (mFirebaseUser == null) {
                                        // Not logged in, launch the Log In activity
                                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                        Toast.makeText(SignupActivity.this, "Please Login or Register first",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        mUserId = mFirebaseUser.getUid();
                                        mStorageRef = FirebaseStorage.getInstance().getReference();
                                        if(validateForm())
                                        {
                                            String nama=Nama.getText().toString().trim();
                                            String nktp=Nktp.getText().toString().trim();
                                            String ae=Pnumber.getText().toString().trim();
                                            String ema=inputEmail.getText().toString().trim();
                                            String ala=Alamat.getText().toString().trim();
                                            String jekel = "";
                                            if(rbLaki.isChecked()){
                                                jekel = "Laki-laki";
                                            }
                                            if(rbPerempuan.isChecked()){
                                                jekel = "Perempuan";
                                            }
                                            // create user object and set all the properties
                                            Profile profile=new Profile();
                                            profile.setNama(nama);
                                            profile.setNsim(nktp);
                                            profile.setPnumber(ae);
                                            profile.setEmail(ema);
                                            profile.setAlamat(ala);
                                            profile.setJekel(jekel);
                                            profile.setStatus("off");
                                            profile.setJurusan(spinner.getSelectedItem().toString());
//                                            user.setLatitude("0");
//                                            user.setLongitude("0");
                                            if(auth.getCurrentUser()!=null)
                                            {
                                                // save the user at NodeNode under user UID
                                                mDatabase.child("DriverNode").child(auth.getCurrentUser().getUid()).setValue(profile, new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                        if(databaseError==null)
                                                        {
                                                            UploadPhoto();
                                                            Toast.makeText(SignupActivity.this, "Data is saved successfully",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                        }
/*                                        Toast.makeText(SignupActivity.this, inputEmail.getText().toString(), Toast.LENGTH_LONG).show();
                                        mDatabase.child("users").child(mUserId).child("items").push().child("email").setValue("abc@abcd.com");
                                        mDatabase.child("users").child(mUserId).child("items").push().child("address").setValue("Jalan Raya Natar");
                                        mDatabase.child("users").child(mUserId).child("items").push().child("phone_number").setValue("081271951296");
                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                        finish();
*/                                    }
                                }
                            }
                        });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    // to check if user filled all the required fieds
    public boolean validateForm()
    {
        boolean alldone=true;
        String nama=Nama.getText().toString().trim();
        String nktp=Nktp.getText().toString().trim();
        String ae=Pnumber.getText().toString().trim();
        String ema=Email.getText().toString().trim();
        if(TextUtils.isEmpty(nama))
        {
            Nama.setError("Masukkan Nama Anda");
            return false;
        }else
        {
            alldone=true;
            Nama.setError(null);
        }
        if(TextUtils.isEmpty(nktp))
        {
            Nktp.setError("Masukkan Nomor SIM Anda");
            return false;
        }else
        {
            alldone=true;
            Nktp.setError(null);
        }
        if(TextUtils.isEmpty(ae))
        {
            Pnumber.setError("Masukkan Nomor Telepon/Handphone Anda");
            return false;
        }else
        {
            alldone=true;
            Pnumber.setError(null);
        }
        if(TextUtils.isEmpty(ema))
        {
            Email.setError("Masukkan Email Anda");
            return false;
        }else
        {
            alldone=true;
            Email.setError(null);
        }
        return alldone;
    }

    public void ChooseFile(View view){
        com.mvc.imagepicker.ImagePicker.pickImage(this, "Select your image:");
/*        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
*/    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (/*requestCode == PICK_IMAGE_REQUEST && */resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
            try {
//                Toast.makeText(getApplicationContext(), "IN + "+filePath, Toast.LENGTH_LONG).show();
                Bitmap bitmap = com.mvc.imagepicker.ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                Bitmap scaledBitmap = scaleDown(bitmap, 150, true);
                imageView.setImageBitmap(bitmap);
                bmImagePicker = bitmap;

            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(), "OUT", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
//        }
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public void UploadPhoto(){

        // Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.show();

        String picName = "PP-"+mUserId+".jpg";

        Bitmap bitmap = null;
        Bitmap scaledBitmap = null;
        try {
//            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            bitmap = bmImagePicker;
            scaledBitmap = scaleDown(bitmap, 500, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
//                Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference riversRef = mStorageRef.child("images/"+picName);

        UploadTask uploadTask = riversRef.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //if the upload is successfull
                //hiding the progress dialog
                progressDialog.dismiss();

                //and displaying a success toast
                Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        //if the upload is not successfull
                        //hiding the progress dialog
                        progressDialog.dismiss();

                        //and displaying error message
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        //calculating progress percentage
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                        //displaying percentage in progress dialog
                        progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                    }
                });

    }

}