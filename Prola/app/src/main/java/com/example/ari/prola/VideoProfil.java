package com.example.ari.prola;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class VideoProfil extends AppCompatActivity {
    Button clk;
    VideoView videov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_profil);
        clk = (Button) findViewById(R.id.btnPlay);
        videov = (VideoView) findViewById(R.id.videoView);
    }

    public void videoplay(View v){
        String videopat = "android.resource://com.example.ari.prola/"+R.raw.prola;
        //Uri uri = new Uri.parse(videopat);
        videov.setVideoPath(videopat);
        videov.start();
    }
}
