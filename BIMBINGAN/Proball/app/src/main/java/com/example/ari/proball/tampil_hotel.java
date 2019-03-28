package com.example.ari.proball;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.HashMap;

public class tampil_hotel extends AppCompatActivity {
    private String JSON_STRING;
    MapView map;
    TextView txt_tampil;
    Button btn;

    Button btn_hotell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_hotel);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        map = (MapView) findViewById(R.id.map);
        btn = (Button) findViewById(R.id.btn_profil);
        btn_hotell = (Button) findViewById(R.id.btn_share);

        btn_hotell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tampil_hotel.this, tambah_lokasi.class);
                startActivity(intent);
            }
        });

        getJSON();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapp = new Intent(tampil_hotel.this, tampil_hotel.class);
                startActivity(mapp);
            }
        });
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

                //  Double alt = Double.parseDouble(alamat);
                // Double prm = Double.parseDouble(param);
                //  Double nm = Double.parseDouble(name);

                HashMap<String,String> employees = new HashMap<>();
                //employees.put(Koneksi.TAG_ID,id);
                employees.put(Koneksi.TAG_NAMA,name);
                employees.put(Koneksi.TAG_LAT, latt);
                employees.put(Koneksi.TAG_LONGI, longg);
                employees.put(Koneksi.TAG_ALAMAT, alamat);
                employees.put(Koneksi.TAG_PARAM, param);


                list.add(employees);

                tampilPeta(lt,lgs, name,alamat,param );
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



        /*ListAdapter adapter = new SimpleAdapter(
                ViewAllEmployee.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_NAME},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);*/
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampil_hotel.this,"Menampilkan Data","sedang diperoses...",false,false);
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
                String s = rh.sendGetRequest(Koneksi.URL_GET_hotel);
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
        startMarker.setTitle(param+"\n"+marks+"\n"+lats+"\n"+longs+"\n"+alamat+"\n");
        startMarker.setIcon(getResources().getDrawable(R.mipmap.ht));
        map.getOverlays().add(startMarker);
    }
}
