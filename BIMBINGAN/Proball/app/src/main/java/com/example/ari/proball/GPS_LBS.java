package com.example.ari.proball;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class GPS_LBS extends AppCompatActivity {
    private String JSON_STRING;
    MapView map;
    Button btn_cari;
    EditText txt_cari;
    ImageView pendidikan;
    private String id;
    ImageView imageView;


    ArrayList<OverlayItem> overlayItemArray;

    LocationManager locationManager;
    private IMapController myMapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps__lbs);
        map = (MapView) findViewById(R.id.myOpenMapView);
        imageView = (ImageView) findViewById(R.id.add_pendidikan);
        map.setBuiltInZoomControls(true);
        myMapController = map.getController();
        myMapController.setZoom(12);
        //getJSON();

        //--- Create Overlay
        overlayItemArray = new ArrayList<OverlayItem>();

        DefaultResourceProxyImpl defaultResourceProxyImpl
                = new DefaultResourceProxyImpl(this);
        MyItemizedIconOverlay myItemizedIconOverlay
                = new MyItemizedIconOverlay(
                overlayItemArray, null, defaultResourceProxyImpl);
        map.getOverlays().add(myItemizedIconOverlay);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GPS_LBS.this, tambah_lokasi.class);
                startActivity(intent);
            }
        });
        //---

        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            //for demo, getLastKnownLocation from GPS only, not from NETWORK
            Location lastLocation
                    = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                updateLoc(lastLocation);
            }
        } catch (SecurityException a) {

        }

        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        map.getOverlays().add(myScaleBarOverlay);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);
        } catch (SecurityException a) {
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        try {
            super.onPause();
            locationManager.removeUpdates(myLocationListener);
        } catch (SecurityException a) {
        }
    }

    private void updateLoc(Location loc) {
        GeoPoint locGeoPoint = new GeoPoint(loc.getLatitude(), loc.getLongitude());
        myMapController.setCenter(locGeoPoint);

        setOverlayLoc(loc);

        map.invalidate();
    }

    private void setOverlayLoc(Location overlayloc) {
        GeoPoint overlocGeoPoint = new GeoPoint(overlayloc);
        //---
        overlayItemArray.clear();

        OverlayItem newMyLocationItem = new OverlayItem(
                "My Location", "My Location", overlocGeoPoint);
        overlayItemArray.add(newMyLocationItem);
        //---
    }

    private LocationListener myLocationListener
            = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            updateLoc(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    };

    private class MyItemizedIconOverlay extends ItemizedIconOverlay<OverlayItem> {

        public MyItemizedIconOverlay(
                List<OverlayItem> pList,
                org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener<OverlayItem> pOnItemGestureListener,
                ResourceProxy pResourceProxy) {
            super(pList, pOnItemGestureListener, pResourceProxy);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void draw(Canvas canvas, MapView mapview, boolean arg2) {
            // TODO Auto-generated method stub
            super.draw(canvas, mapview, arg2);

            if (!overlayItemArray.isEmpty()) {

                //overlayItemArray have only ONE element only, so I hard code to get(0)
                GeoPoint in = overlayItemArray.get(0).getPoint();

                Point out = new Point();
                mapview.getProjection().toPixels(in, out);

                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.pd);
                canvas.drawBitmap(bm,
                        out.x - bm.getWidth() / 2,    //shift the bitmap center
                        out.y - bm.getHeight() / 2,    //shift the bitmap center
                        null);
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event, MapView mapView) {
            // TODO Auto-generated method stub
            //return super.onSingleTapUp(event, mapView);
            return true;


        }
    }


    private void showMap(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Koneksi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                //String id = jo.getString(Koneksi.TAG_ID);
                String name = jo.getString(Koneksi.TAG_NAMA);
                String latt = jo.getString(Koneksi.TAG_LAT);
                String longg = jo.getString(Koneksi.TAG_LONGI);
                String alamat = jo.getString(Koneksi.TAG_ALAMAT);
                String param = jo.getString(Koneksi.TAG_PARAM);

                Double lt = Double.parseDouble(latt);
                Double lgs = Double.parseDouble(longg);

                // Double alt = Double.parseDouble(alamat);
                // Double prm = Double.parseDouble(param);
                //Double nm = Double.parseDouble(name);

                HashMap<String,String> employees = new HashMap<>();
                //employees.put(Koneksi.TAG_ID,id);
                employees.put(Koneksi.TAG_NAMA,name);
                employees.put(Koneksi.TAG_LAT, latt);
                employees.put(Koneksi.TAG_LONGI, longg);
                employees.put(Koneksi.TAG_ALAMAT, alamat);
                employees.put(Koneksi.TAG_PARAM, param);


                list.add(employees);

                tampilPeta(lt, lgs, name,alamat,param );
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GPS_LBS.this,"Menampilkan Data","sedang diperoses...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMap();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Koneksi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    void tampilPeta(Double lats, Double longs, String marks, String alamat, String param){
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(lats, longs);
        IMapController mapController = map.getController();
        mapController.setZoom(12);
        mapController.setCenter(startPoint);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle(marks+"\n"+lats+","+longs+","+alamat+","+param+",");
        startMarker.setIcon(getResources().getDrawable(R.drawable.marker_destination));
        map.getOverlays().add(startMarker);


        imageView =(ImageView)findViewById(R.id.image_GPS);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GPS_LBS.this,tambah_lokasi.class);
                startActivity(intent);
            }
        });
    }


}