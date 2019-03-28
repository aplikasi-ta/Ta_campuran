package com.sitiamalia.kedatanganbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.text.DecimalFormat;


public class Maps extends AppCompatActivity {
    MapView map;
    IMapController mapController;
    double latA= -5.378511;
    double longA=105.251716;
    double latB=-5.382212;
    double longB=105.257727;
    String textA="BUS", textB="PENUMPANG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        map=(MapView)findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        mapController=map.getController();
        mapController.setZoom(15);
        distance();

    }
    public void markerPenumpang(double lat, double longi, String textMarker){
        GeoPoint point = new GeoPoint(lat,longi);
        mapController.setCenter(point);

        Marker marker=new Marker(map);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(marker);

        map.invalidate();
        marker.setIcon(getResources().getDrawable(R.drawable.bus));
        marker.setTitle(textMarker);

    }

    public void markerBus(double lat, double longi, String textMarker){
        GeoPoint point = new GeoPoint(lat,longi);
        mapController.setCenter(point);

        Marker marker=new Marker(map);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(marker);

        map.invalidate();
        marker.setIcon(getResources().getDrawable(R.drawable.marker));
        marker.setTitle(textMarker);

    }

    public void distance(){

        double haversine, distance;
        double dLat, dLon;
        dLat = (latB - latA) * 0.01745329251994;
        dLon = (longB - longA) * 0.01745329251994;
        dLat   = Math.toRadians(latB - latA);
        dLon   = Math.toRadians(longB - longA);

        haversine = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) *
                        Math.cos(latA * 0.01745329251994) *
                        Math.cos(latB * 0.01745329251994);

        // radius bumi 6371 atau 6367.45
        distance = Math.asin(Math.sqrt(haversine)) * 6371 * 2.0;

        double speed = 20;
        //long time = Tim
        double time =  distance / speed ;

        DecimalFormat df = new DecimalFormat("#.#");

        TextView text = (TextView) findViewById(R.id.sitiamalia);
        text.setText(String.format("Jarak   : " + df.format(distance)).
                concat(" " + "km\n"+ "Tiba dalam  : "+time +" jam lagi" ));


        /*
        rumus a = 2.0633;
        rumus b = ;
         */

        markerPenumpang(latA, longA, textA);
        markerBus(latB, longB, textB);

    }

}
