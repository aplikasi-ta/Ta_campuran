package com.example.ari.myapplication;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-5.379516, 105.251742);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Universitas Bandar Lampung"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-5.379516, 105.251742), 12.0f));


        LatLng dshg = new LatLng(-5.382854, 105.282103); //Titik Posisi Akhir
        mMap.addMarker(new MarkerOptions().position(dshg).title("Tango Hostel"));
        mMap.addPolyline(new PolylineOptions().add(
                sydney,
                new LatLng(-5.378592, 105.251960),
                new LatLng(-5.377434, 105.250581),
                new LatLng(-5.377358, 105.250664),
                new LatLng(-5.378904, 105.252576),
                new LatLng(-5.380451, 105.254756),
                new LatLng(-5.381350, 105.256744),
                new LatLng(-5.381695, 105.257512),
                new LatLng(-5.381155, 105.258310),
                new LatLng(-5.380811, 105.258641),
                new LatLng(-5.380605, 105.258963),
                new LatLng(-5.380089, 105.259032),
                new LatLng(-5.379277, 105.259699),
                new LatLng(-5.378864, 105.260630),
                new LatLng(-5.378784, 105.262286),
                new LatLng(-5.378830, 105.262746),
                new LatLng(-5.379070, 105.263321),
                dshg).width(10)
                .color(Color.RED)
        );

        mMap.addPolyline(new PolylineOptions().add(
                sydney,
                new LatLng(-5.378592, 105.251960),
                new LatLng(-5.377434, 105.250581),
                new LatLng(-5.377358, 105.250664),
                new LatLng(-5.378904, 105.252576),
                new LatLng(-5.380451, 105.254756),
                new LatLng(-5.381350, 105.256744),
                new LatLng(-5.381695, 105.257512),
                new LatLng(-5.381155, 105.258310),
                new LatLng(-5.380811, 105.258641),
                new LatLng(-5.380605, 105.258963),
                new LatLng(-5.380089, 105.259032),
                new LatLng(-5.379277, 105.259699),
                new LatLng(-5.382288, 105.258588),
                new LatLng(-5.383112, 105.259870),
                new LatLng(-5.383561, 105.260299),
                new LatLng(-5.383956, 105.260589),
                new LatLng(-5.383785, 105.261329),
                new LatLng(-5.383507, 105.262155),
                new LatLng(-5.383525, 105.262488),
                new LatLng(-5.383632, 105.262906),
                new LatLng(-5.383424, 105.263051),
                new LatLng(-5.382044, 105.262349),
                new LatLng(-5.380277, 105.260527),
                new LatLng(-5.380202, 105.260557),
                new LatLng(-5.380101, 105.260883),
                new LatLng(-5.379969, 105.261233),
                new LatLng(-5.378952, 105.262108),
                new LatLng(-5.378755, 105.262194),
                new LatLng(-5.378800, 105.262504),
                new LatLng(-5.378815, 105.262723),
                new LatLng(-5.379053, 105.263297),

                dshg).width(10)
                .color(Color.BLUE)
        );

    }

}