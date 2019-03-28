package com.example.ari.leata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;

public class RegisterGuru extends AppCompatActivity {
    Spinner spinerGuru;
    String guruku;
    Button btn_regis;
    ImageView imageGuru;
    RadioGroup rdGroup;
    RadioButton rdGuru;
    EditText txt_nip, txt_nama, txt_alamat, txt_telp, txt_mail, txt_pass;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://pstiubl.com/Lea/create_guru.php";

    private Bitmap bitmap;

    private Uri filePath;
    String KEY_NIP = "nip";
    String KEY_NAMA = "nama_guru";
    String KEY_GURU = "guru";
    String KEY_JENKEL = "jenkel";
    String KEY_ALAMAT = "alamat";
    String KEY_TELP = "telp";
    String KEY_MAIL = "email";
    String KEY_PASS = "pass";
    String KEY_PHOTO = "photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guru);
        spinerGuru = (Spinner) findViewById(R.id.spinGuru);
        btn_regis = (Button) findViewById(R.id.btn_reg_guru);
        txt_nip = (EditText) findViewById(R.id.txtNIP) ;
        txt_nama = (EditText) findViewById(R.id.txtNama);
        txt_alamat = (EditText) findViewById(R.id.txtAlamat);
        txt_telp = (EditText) findViewById(R.id.txtPhone);
        txt_mail = (EditText) findViewById(R.id.txtMail);
        txt_pass = (EditText) findViewById(R.id.txtPass);
        imageGuru = (ImageView) findViewById(R.id.imgGuru);

        rdGroup = (RadioGroup) findViewById(R.id.radioGroupNb);

        spinerGuru.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        imageGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jk = rdGroup.getCheckedRadioButtonId();
                rdGuru = (RadioButton) findViewById(jk);

                uploadImage();
            }
        });

    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinerGuru.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinerGuru.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "Kamu memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                guruku =  parent.getItemAtPosition(pos).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageGuru.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Registrasi User...","Mohon Tunggu...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(RegisterGuru.this, s , Toast.LENGTH_LONG).show();
                        Intent loggin = new Intent(RegisterGuru.this, MainActivity.class);
                        startActivity(loggin);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(RegisterGuru.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                final String nips = txt_nip.getText().toString().trim();
                final String namas = txt_nama.getText().toString().trim();
                final String gurus = guruku;
                final String kelamin = rdGuru.getText().toString().trim();
                final String alamats = txt_alamat.getText().toString().trim();
                final String telps = txt_telp.getText().toString().trim();
                final String emails = txt_mail.getText().toString().trim();
                final String passs = txt_pass.getText().toString().trim();


                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_NIP, nips);
                params.put(KEY_NAMA, namas);
                params.put(KEY_GURU, gurus);
                params.put(KEY_JENKEL, kelamin);
                params.put(KEY_ALAMAT, alamats);
                params.put(KEY_TELP, telps);
                params.put(KEY_MAIL, emails);
                params.put(KEY_PASS, passs);
                params.put(KEY_PHOTO, image);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


}
