package com.trans.lampung.driver;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mvc.imagepicker.ImagePicker;
import com.trans.lampung.driver.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements LocationListener {

    TextView tvEmail, tvPhoneNumber;
    EditText Nama, Email, Noktp, Nohp, Alamat;
    RadioGroup rgJekel;
    RadioButton rbLaki, rbPerempuan;
    Button ubahProfile;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FirebaseUser mFirebaseUser;
    ProgressDialog mProgressDialog;
    ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private String mUserId;
    ArrayList<Profile> profiles = new ArrayList<>();

    private LocationManager locationManager;
    private String provider;

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
        setContentView(R.layout.activity_profile);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(getString(R.string.app_name));
//        setSupportActionBar(toolbar);

        //get firebase auth instance
//        ImagePicker.setMinQuality(600, 600);
        auth = FirebaseAuth.getInstance();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        tvEmail = (TextView) findViewById(R.id.tvEmail);

        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);

        Nama = (EditText) findViewById(R.id.nama);
        Noktp = (EditText) findViewById(R.id.noKTP);
        Nohp = (EditText) findViewById(R.id.NoHP);
        Email = (EditText) findViewById(R.id.email);
        Alamat = (EditText) findViewById(R.id.alamat);

        progressBar = (ProgressBar) findViewById(R.id.progressBarP);
        ubahProfile = (Button) findViewById(R.id.UbahProfile);

        rgJekel = (RadioGroup) findViewById(R.id.rgPJekel);
        rbLaki = (RadioButton) findViewById(R.id.rbPLaki);
        rbPerempuan = (RadioButton) findViewById(R.id.rbPPerempuan);

        imageView = (ImageView) findViewById(R.id.ivProfile);

        mFirebaseUser = auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        mUserId = mFirebaseUser.getUid();

        setPhotoProfile();

        getDataFromServer();

    }

    // getting the data from DriverNode at Firebase and then adding the users in Arraylist and setting it to Listview
    public void getDataFromServer() {
        showProgressDialog();
        mDatabase.child("DriverNode").child(mUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    tvEmail.setText("Nama : " + dataSnapshot.child("nama").getValue()
                            + "\n\nNomor SIM : " + dataSnapshot.child("nsim").getValue()
                            + "\n\nJenis Kelamin : " + dataSnapshot.child("jekel").getValue()
                            + "\n\nEmail : " + dataSnapshot.child("email").getValue());
                    tvPhoneNumber.setText("Nomor Telepon/Handphone : " + dataSnapshot.child("pnumber").getValue());

                    Nama.setText(""+dataSnapshot.child("nama").getValue());
                    Alamat.setText(""+dataSnapshot.child("alamat").getValue());
                    Noktp.setText(""+dataSnapshot.child("nsim").getValue());
                    Email.setText(""+dataSnapshot.child("email").getValue());
                    Nohp.setText(""+dataSnapshot.child("pnumber").getValue());

                    if(dataSnapshot.child("jekel").getValue().equals("Laki-laki")){
                        rbLaki.setChecked(true);
                    }
                    else if(dataSnapshot.child("jekel").getValue().equals("Perempuan")){
                        rbPerempuan.setChecked(true);
                    }

/*                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                        Profile profile=postSnapShot.getValue(Profile.class);
                        profiles.add(profile);
                        Profile profile1=profiles.get(0);
                        tvEmail.setText("Name : "+profile1.getFirstname()+" "+profile1.getLastname()
                        +"\n\nEmail : "+profile1.getEmail());
                        tvPhoneNumber.setText("Phone Number : "+profile1.getPnumber());
                        //                        adapter.notifyDataSetChanged();
                    }
*/
                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
/*        mDatabase.child("DriverNode").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int i = 0;
                    profiles.clear();
                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                        Profile profile=postSnapShot.getValue(Profile.class);
                        profiles.add(profile);
                        Profile profile1=profiles.get(i);
                        System.out.println("Nama : "+profile1.getNama());
                        System.out.println("Nomor KTP/KTM : "+profile1.getNktp());
                        System.out.println("Jenis Kelamin : "+profile1.getJekel());
                        System.out.println("Email : "+profile1.getEmail());
                        System.out.println("Phone Number : "+profile1.getPnumber());
                        System.out.println("Koordinat : "+profile1.getLatitude()+","+profile1.getLongitude());
                        //                        adapter.notifyDataSetChanged();
                        i++;
                    }

                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
*/    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(ProfileActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void AddLocation(View v) {
        showProgressDialog();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 400, 1, this);
    }

    public void UbahProfile(View view){
        ubahProfile.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        AddLocation(view);
        mUserId = mFirebaseUser.getUid();
        if(validateForm()) {
            String nama = Nama.getText().toString().trim();
            String nktp = Noktp.getText().toString().trim();
            String ae = Nohp.getText().toString().trim();
            String ema = Email.getText().toString().trim();
            String ala = Alamat.getText().toString().trim();
            String jekel = "";
            if (rbLaki.isChecked()) {
                jekel = "Laki-laki";
            }
            if (rbPerempuan.isChecked()) {
                jekel = "Perempuan";
            }
            // create user object and set all the properties
            final Profile profile = new Profile();
            profile.setNama(nama);
            profile.setNsim(nktp);
            profile.setPnumber(ae);
            profile.setEmail(ema);
            profile.setAlamat(ala);
            profile.setJekel(jekel);
//                                            user.setLatitude("0");
//                                            user.setLongitude("0");
            progressBar.setVisibility(View.VISIBLE);
            if(mFirebaseUser.getEmail().toString().equals(Email.getText().toString())){
                if (auth.getCurrentUser() != null) {
                    // save the user at DriverNode under user UID
                    mDatabase.child("DriverNode").child(auth.getCurrentUser().getUid()).setValue(profile, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(ProfileActivity.this, "Data is saved successfully",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }else {
                if (mFirebaseUser != null && !Email.getText().toString().trim().equals("")) {
                    mFirebaseUser.updateEmail(Email.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        if (auth.getCurrentUser() != null) {
                                            // save the user at DriverNode under user UID
                                            mDatabase.child("DriverNode").child(auth.getCurrentUser().getUid()).setValue(profile, new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    if (databaseError == null) {
                                                        Toast.makeText(ProfileActivity.this, "Data is saved successfully",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                        Toast.makeText(ProfileActivity.this, "Email berhasil diperbarui. Silahkan masuk dengan email baru Anda!", Toast.LENGTH_LONG).show();
                                        signOut();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(ProfileActivity.this, "Gagal memperbarui email Anda!"+task.getException()
                                                , Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else if (Email.getText().toString().trim().equals("")) {
                    Email.setError("Masukkan Email");
                    progressBar.setVisibility(View.GONE);
                }

            }
        }
    }

    public void Deleting(View v) {
        mDatabase.child("DriverNode").child(mUserId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void onLocationChanged(Location location) {
        final String lat = "" + location.getLatitude();
        final String lng = "" + location.getLongitude();
        mDatabase.child("DriverNode").child(mUserId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        dataSnapshot.getRef().child("latitude").setValue(lat);
                        dataSnapshot.getRef().child("longitude").setValue(lng);
                        progressBar.setVisibility(View.GONE);
                        ubahProfile.setVisibility(View.VISIBLE);
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        finish();
//                        }
                        if (ActivityCompat.checkSelfPermission(ProfileActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ProfileActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        locationManager.removeUpdates(ProfileActivity.this);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        hideProgressDialog();
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

    public void UploadPhoto(View view){

        // Get the data from an ImageView as bytes
        final ProgressDialog progressDialog = new ProgressDialog(this);
        try{
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();

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

/*        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            String picName = "PP-"+mUserId+".jpg";

            StorageReference riversRef = mStorageRef.child("images/"+picName);
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
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
        //if there is not any file
        else {
            //you can display an error toast
        }
*/    }catch (Exception e){
            Toast.makeText(this, "Tidak dapat mengupload Photo", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            }
        }

    @Override
    public void onResume(){
        super.onResume();
//        setPhotoProfile();
    }

    public void setPhotoProfile(){
        String picName = "PP-"+mUserId+".jpg";
        StorageReference islandRef = mStorageRef.child("images/"+picName);

        Glide.with(ProfileActivity.this /* context */)
                .using(new FirebaseImageLoader())
                .load(islandRef)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public boolean validateForm()
    {
        boolean alldone=true;
        String nama=Nama.getText().toString().trim();
        String nktp=Noktp.getText().toString().trim();
        String ae=Nohp.getText().toString().trim();
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
            Noktp.setError("Masukkan Nomor KTP/KTM Anda");
            return false;
        }else
        {
            alldone=true;
            Noktp.setError(null);
        }
        if(TextUtils.isEmpty(ae))
        {
            Nohp.setError("Masukkan Nomor Telepon/Handphone Anda");
            return false;
        }else
        {
            alldone=true;
            Nohp.setError(null);
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

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

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

    //sign out method
    public void signOut() {
        auth.signOut();
    }

}
