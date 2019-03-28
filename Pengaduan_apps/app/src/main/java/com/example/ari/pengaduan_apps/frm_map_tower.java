package com.example.ari.pengaduan_apps;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class frm_map_tower extends FragmentActivity {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    public static String ID_TOWER="";
    public static TextView txt_id_tower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_map_tower);
        txt_id_tower = (TextView) findViewById(R.id.txt_id_tower);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //Toast.makeText(getApplication(),"Data Kecamtan "+Frm_menu_plg.txt_kecamatan.getText().toString().trim(),Toast.LENGTH_LONG).show();
        getJSON();
    }

    private void showDetail(String json){
        //Toast.makeText(getApplication(),"Data Camat "+json,Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String id_tower  = c.getString("id_tower");
                String nomor  = c.getString("nomor_tower");
                String kec  = c.getString("kecamatan");
                String alamat  = c.getString("alamat");
                String latitude  = c.getString("latitude");
                String longitude  = c.getString("longitude");

                //Toast.makeText(getApplication(),"Data "+alamat,Toast.LENGTH_LONG).show();
                Double lats = Double.parseDouble(latitude);
                Double longs = Double.parseDouble(longitude);

                petaTower(nomor,alamat,lats,longs);
                txt_id_tower.setText(id_tower);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            //Toast.makeText(getApplication(),"Data Slaah "+e,Toast.LENGTH_LONG).show();
        }

    }



    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(frm_map_tower.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //JSON_STRING = s;
                showDetail(s);
                //Toast.makeText(getApplication(),"Data "+s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(config.URL_TOWER);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    void petaTower(final String No_tower, final String Alamat, final Double Lati, final Double Longi){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-5.397140, 105.266789), 12.0f));

                LatLng ubl = new LatLng(Lati, Longi);
                //Toast.makeText(getApplication(),"Data "+lats+"\n"+longs,Toast.LENGTH_LONG).show();
                MarkerOptions marker = null;
                marker = new MarkerOptions()
                        .position(ubl)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.tower))
                        .title("Nomor Tower : "+No_tower)
                .snippet(Alamat);
                mMap.addMarker(marker);
                //mMap.getUiSettings().setCompassEnabled(true);
                //mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                //mMap.getUiSettings().setMyLocationButtonEnabled(true);

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Intent intent = new Intent(frm_map_tower.this, Frm_pengaduan.class);
                        startActivity(intent);
                        return false;
                    }
                });


            }
        });
    }


}
