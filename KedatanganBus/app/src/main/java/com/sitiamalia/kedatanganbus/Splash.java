package com.sitiamalia.kedatanganbus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.os.Handler;

public class Splash extends AppCompatActivity {


        private static int splashInterval = 3000;
        ProgressBar progressBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.splash);

            progressBar = (ProgressBar)findViewById(R.id.progresBar1);
            progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

            new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, Login.class);
                    startActivity(i);

                    this.finish();

                }

                private void finish() {

                }
            },
                    splashInterval);
        }
    }

