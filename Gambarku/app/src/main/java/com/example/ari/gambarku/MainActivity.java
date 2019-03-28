package com.example.ari.gambarku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
Picasso picasso;
    ArrayList<WonderModel> listitems = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_card);
        ImageView imageView = (ImageView) findViewById(R.id.coverImageView);
        recyclerView = (RecyclerView) findViewById(R.id.cardView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(MainActivity.this);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//Loading image from below url into imageViewddtt

        /*picasso.with(this)
                .load("http://probalam.com/probal_server/uploads/12.png")
                .into(imageView);*/
    }
}
