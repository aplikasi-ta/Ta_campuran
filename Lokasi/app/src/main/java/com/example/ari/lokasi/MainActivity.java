package com.example.ari.lokasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvAddress;

    LocationService appLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location networkLocation= appLocationService.getLocation                                                                                                              (LocationManager.NETWORK_PROVIDER);
        if (networkLocation!= null)
        {
            double latitude = networkLocation.getLatitude();
            double longitude = networkLocation.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude , longitude ,
                    getApplicationContext(), new GeocoderHandler());
        }
    }


    }
