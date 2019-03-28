package com.polda.ari.panicbutton;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Mdl_alarm extends AppCompatActivity {
ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdl_alarm);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.title_pol);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        btn = (ImageButton) findViewById(R.id.btn_alarm_k);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alarm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    btn.setBackgroundResource(R.drawable.alarm);
                }else{
                    mp.start();
                    mp.setLooping(true);
                    btn.setBackgroundResource(R.drawable.alarm_off);
                }

            }
        });
    }
}
