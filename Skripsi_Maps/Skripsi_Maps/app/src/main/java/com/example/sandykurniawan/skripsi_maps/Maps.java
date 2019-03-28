package com.example.sandykurniawan.skripsi_maps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.app.Fragment;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class Maps extends Fragment {

    private GoogleMap mMap;

    public Maps() {

    }

    View map;
    MapView mMapView;
    private LocationManager locManager;
    private LocationListener locListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        map = inflater.inflate (R.layout.maps, container, false);
        getActivity ().setTitle ("Maps");

        mMapView = (MapView) map.findViewById (R.id.map_);
        mMapView.onCreate (savedInstanceState);

        mMapView.onResume ();

        try {
            MapsInitializer.initialize (getActivity ().getApplicationContext ());
        } catch (Exception e) {
            e.printStackTrace ();
        }

        mMapView.getMapAsync (new OnMapReadyCallback () {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                initLocationManager ();

                LatLng sydney = new LatLng (-5.379516, 105.251742);
                mMap.addMarker (new MarkerOptions ().position (sydney).title ("Universitas Bandar Lampung"));
                mMap.moveCamera (CameraUpdateFactory.newLatLngZoom (new LatLng (-5.379516, 105.251742), 12.0f));


                LatLng dshg = new LatLng (-5.381585, 105.256478); //Titik Posisi Akhir
                mMap.addMarker (new MarkerOptions ().position (dshg).title ("Yussy Akmal"));
                mMap.addPolyline (new PolylineOptions ().add (
                        sydney,
                        /*new LatLng (-5.378592, 105.251960),
                        new LatLng (-5.377434, 105.250581),
                        new LatLng (-5.377358, 105.250664),
                        new LatLng (-5.378904, 105.252576),
                        new LatLng (-5.380451, 105.254756),
                        new LatLng (-5.381350, 105.256744),
                        new LatLng (-5.381695, 105.257512),
                        new LatLng (-5.381155, 105.258310),
                        new LatLng (-5.380811, 105.258641),
                        new LatLng (-5.380605, 105.258963),
                        new LatLng (-5.380089, 105.259032),
                        new LatLng (-5.379277, 105.259699),
                        new LatLng (-5.378864, 105.260630),
                        new LatLng (-5.378784, 105.262286),
                        new LatLng (-5.378830, 105.262746),
                        new LatLng (-5.379070, 105.263321),*/
                        dshg).width (10)
                        .color (Color.RED)
                );

                /*mMap.addPolyline (new PolylineOptions ().add (
                        sydney,
                        new LatLng (-5.378592, 105.251960),
                        new LatLng (-5.377434, 105.250581),
                        new LatLng (-5.377358, 105.250664),
                        new LatLng (-5.378904, 105.252576),
                        new LatLng (-5.380451, 105.254756),
                        new LatLng (-5.381350, 105.256744),
                        new LatLng (-5.381695, 105.257512),
                        new LatLng (-5.381155, 105.258310),
                        new LatLng (-5.380811, 105.258641),
                        new LatLng (-5.380605, 105.258963),
                        new LatLng (-5.380089, 105.259032),
                        new LatLng (-5.379277, 105.259699),
                        new LatLng (-5.382288, 105.258588),
                        new LatLng (-5.383112, 105.259870),
                        new LatLng (-5.383561, 105.260299),
                        new LatLng (-5.383956, 105.260589),
                        new LatLng (-5.383785, 105.261329),
                        new LatLng (-5.383507, 105.262155),
                        new LatLng (-5.383525, 105.262488),
                        new LatLng (-5.383632, 105.262906),
                        new LatLng (-5.383424, 105.263051),
                        new LatLng (-5.382044, 105.262349),
                        new LatLng (-5.380277, 105.260527),
                        new LatLng (-5.380202, 105.260557),
                        new LatLng (-5.380101, 105.260883),
                        new LatLng (-5.379969, 105.261233),
                        new LatLng (-5.378952, 105.262108),
                        new LatLng (-5.378755, 105.262194),
                        new LatLng (-5.378800, 105.262504),
                        new LatLng (-5.378815, 105.262723),
                        new LatLng (-5.379053, 105.263297),

                        dshg).width (10)
                        .color (Color.BLUE)
                );*/
            }
        });
        return map;
    }

    private void initLocationManager() {
        locManager = (LocationManager) getActivity ().getSystemService (Context.LOCATION_SERVICE);
        locListener = new LocationListener () {
            @Override
            public void onLocationChanged(Location newlocation) {
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission (getActivity (), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission (getActivity (), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locManager.requestLocationUpdates (LocationManager.GPS_PROVIDER, 0, 1000, locListener);
        }

    @Override
    public void onResume() {
        super.onResume ();
        mMapView.onResume ();
    }

    @Override
    public void onPause() {
        super.onPause ();
        mMapView.onPause ();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory ();
        mMapView.onDestroy ();
    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        mMapView.onLowMemory ();
    }
}
