package com.example.ari.proball;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class Menu_Utama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView rm_makan;
    private ImageView rm_sakit;
    private ImageView wisata;
    private ImageView pendidikan;
    private ImageView hotel;

    private ImageView passmall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__utama);
        passmall = (ImageView)findViewById(R.id.image_passar);
        rm_sakit = (ImageView)findViewById(R.id.btn_r_sakit);
        wisata = (ImageView)findViewById(R.id.btn_wisata);
        pendidikan = (ImageView)findViewById(R.id.btn_pendidikan);
        hotel = (ImageView)findViewById(R.id.btn_hotel);
        rm_makan = (ImageView)findViewById(R.id.btn_makan);

        rm_makan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this, GPS_LBS.class);
                startActivity(intent);

            }
        });
        //=================================================
        rm_sakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this, GPS_LBS.class);
                startActivity(intent);

            }
        });

        //=================================================
        wisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this, GPS_LBS.class);
                startActivity(intent);

            }
        });
        //=================================================
        pendidikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this, GPS_LBS.class);
                startActivity(intent);

            }
        });
        //=================================================
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this, GPS_LBS.class);
                startActivity(intent);

            }
        });
        //=================================================
        passmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this, GPS_LBS.class);
                startActivity(intent);

            }
        });
        //=====================================================



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu__utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
            // Handle the camera action

            Intent intent = new Intent(Menu_Utama.this, Beranda.class);
            startActivity(intent);

        } else if (id == R.id.nav_video) {
            Intent intent = new Intent(Menu_Utama.this,video_profil.class);
            startActivity(intent);

        } else if (id == R.id.nav_tentang) {
            Intent intent = new Intent(Menu_Utama.this,tentang.class);
            startActivity(intent);

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(Menu_Utama.this, Profil_User.class);
            startActivity(intent);

        } else if (id == R.id.nav_exit) {
            Intent intent = new Intent(Menu_Utama.this,Login.class);
            startActivity(intent);
        }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }