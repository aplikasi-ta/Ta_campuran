package com.sitiamalia.kedatanganbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;


public class MainActivity extends AppCompatActivity {
    MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //buat nampilin map
        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);


        location1();
        location2();
        location3();
        location4();
        location5();

    }

    public void location1() {
        //untuk nentuin koordinat
        GeoPoint startPoint = new GeoPoint(-5.382212, 105.257727);
        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);

        //buat marker
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);

        map.invalidate();
        startMarker.setIcon(getResources().getDrawable(R.drawable.marker));
        startMarker.setTitle("teknokrat");

    }

    public void location2() {
        //untuk nentuin koordinat
        GeoPoint startPoint = new GeoPoint(-5.383635, 105.217713);
        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);


        //buat marker
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);

        map.invalidate();

        startMarker.setIcon(getResources().getDrawable(R.drawable.marker));
        startMarker.setTitle("malahayati");
    }

    public void location3() {
        //untuk nentuin koordinat
        GeoPoint startPoint = new GeoPoint(-5.379534, 105.251704);
        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);


        //buat marker
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);

        map.invalidate();

        startMarker.setIcon(getResources().getDrawable(R.drawable.marker));
        startMarker.setTitle("Universitas Bandar Lampung");
    }

    public void location4() {
        //untuk nentuin koordinat
        GeoPoint startPoint = new GeoPoint(-5.377405, 105.249666);
        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);


        //buat marker
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);

        map.invalidate();

        startMarker.setIcon(getResources().getDrawable(R.drawable.marker));
        startMarker.setTitle("darmajaya");
    }

    public void location5() {
        //untuk nentuin koordinat
        GeoPoint startPoint = new GeoPoint(-5.364725, 105.242997);
        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);


        //buat marker
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);

        map.invalidate();

        startMarker.setIcon(getResources().getDrawable(R.drawable.marker));
        startMarker.setTitle("unila");
    }
}