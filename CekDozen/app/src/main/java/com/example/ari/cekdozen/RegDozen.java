package com.example.ari.cekdozen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegDozen extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://pstiubl.com/cekDozen/create_dozen.php";

    private Bitmap bitmap;

    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_dozen);
    }

    public void startDua(View view)
    {
        startActivity(new Intent(this, LoginApps.class));
    }
}
