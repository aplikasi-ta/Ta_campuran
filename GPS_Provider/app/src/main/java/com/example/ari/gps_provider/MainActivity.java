package com.example.ari.gps_provider;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView osm;
    private IMapController mapController;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        osm = (MapView)  findViewById(R.id.map);
        osm.setBuiltInZoomControls(true);
        mapController = osm.getController();
        mapController.setZoom(12);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(locationManager != null){

        }

        }catch (SecurityException a){
            a.printStackTrace();
        }

        @Override
        protected void onResume() {
            // TODO Auto-generated method stub
            super.onResume();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);
        }
    }
}
