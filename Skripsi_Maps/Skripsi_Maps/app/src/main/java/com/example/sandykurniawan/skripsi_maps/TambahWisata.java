package com.example.sandykurniawan.skripsi_maps;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import com.example.sandykurniawan.skripsi_maps.app.AppController;

/**
 * Created by Sandy Kurniawan on 2/23/2018.
 */

public class TambahWisata extends Fragment {

    public TambahWisata() {

    }

    View map;
    MapView mMapView;
    private GoogleMap mMap;
    private LocationManager locManager;
    private LocationListener locListener;

    Button buttonChoose;
    FloatingActionButton buttonUpload;
    Toolbar toolbar;
    ImageView imageView;
    EditText txt_name,txt_alamat,txt_jenis,txt_info;
    Bitmap bitmap, decode;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60;

    private static final String TAG = TambahWisata.class.getSimpleName ();
    private String UPLOAD_URL = "https://sandy13421065.000webhostapp.com/upload.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String KEY_ALAMAT = "alamat";
    private String KEY_JENIS = "jenis";
    private String KEY_INFO = "info";

    String tag_json_obj = "json_obj_reg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        map = inflater.inflate (R.layout.tambahwisata, container, false);
        getActivity ().setTitle ("Tambah Objek Wisata");

        toolbar = map.findViewById (R.id.toolbar);
        ((AppCompatActivity)getActivity ()).setSupportActionBar(toolbar);

        buttonChoose = map.findViewById (R.id.buttonChoose);
        buttonUpload = map.findViewById (R.id.buttonUpload);

        txt_name = map.findViewById (R.id.namawisata);
        txt_alamat = map.findViewById (R.id.alamatwisata);
        txt_jenis = map.findViewById (R.id.jeniswisata);
        txt_info = map.findViewById (R.id.informasiwisata);
        imageView = map.findViewById (R.id.fotowisata);


        buttonChoose.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                showFileChooser ();
            }
        });

        buttonUpload.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                uploadImage ();
            }
        });

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

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size,baos);
        byte[] imageBytes = baos.toByteArray ();
        String encodeImage = Base64.encodeToString (imageBytes,Base64.DEFAULT);
        return encodeImage;
    }
    public void uploadImage(){
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show (getActivity (),"Upload.....","Please wait....",false,false);
        StringRequest stringRequest = new StringRequest (Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                        Log.e (TAG, "Response: " + response.toString ());

                        try {
                            JSONObject jobj = new JSONObject (response);
                            success = jobj.getInt (TAG_SUCCESS);
                            if (success == 1) {
                                Log.e ("v Add", jobj.toString ());
                                Toast.makeText (getActivity (), jobj.getString (TAG_MESSAGE), Toast.LENGTH_LONG).show ();
                                kosong ();
                            } else {
                                Toast.makeText (getActivity (), jobj.getString (TAG_MESSAGE), Toast.LENGTH_LONG).show ();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace ();
                        }
                        //menghilangkan progress dialog
                        loading.dismiss ();
                    }
                },
                new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //menghapuskan progressdialog
                        loading.dismiss ();
                        //menampilkan toast
                        Toast.makeText (getActivity (),error.getMessage ().toString (),Toast.LENGTH_LONG).show ();
                        Log.e(TAG, error.getMessage ().toString ());
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                //membuat parameters
                Map<String,String> params = new HashMap<String,String> ();
                //membuat parameters yang di kirim ke web servis
                params.put(KEY_IMAGE,getStringImage (decode));
                params.put(KEY_NAME,txt_name.getText ().toString ().trim ());
                params.put(KEY_ALAMAT,txt_alamat.getText ().toString ().trim ());
                params.put(KEY_JENIS,txt_jenis.getText ().toString ().trim ());
                params.put(KEY_INFO,txt_info.getText ().toString ().trim ());

                //kembali ke parameters
                Log.e(TAG,""+params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue (stringRequest,tag_json_obj);
    }
    private void showFileChooser(){
        Intent intent= new Intent ();
        intent.setType ("image/*");
        intent.setAction (Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser (intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult (requestCode,resultCode,data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData () != null){
            Uri filePath = data.getData ();
            try{
                //mengambil gambar dari gallery
                bitmap = MediaStore.Images.Media.getBitmap (getActivity ().getContentResolver (),filePath);
                //512 adalaah resolusi tertinggi setelah image di resize , bisa di ganti
                setToImageView(getResizedBitmap(bitmap,512));
            }catch (IOException e){
                e.printStackTrace ();
            }

        }

    }
    private  void kosong(){
        imageView.setImageResource (0);
        txt_name.setText (null);
    }

    private  void setToImageView(Bitmap bmp){
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream ();
        bmp.compress (Bitmap.CompressFormat.JPEG,bitmap_size,bytes);
        decode = BitmapFactory.decodeStream (new ByteArrayInputStream (bytes.toByteArray ()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap (decode);
    }
    //fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image,int maxSize){
        int width = image.getWidth ();
        int height = image.getHeight ();
        float bitmapRatio = (float) width/ (float) height;
        if(bitmapRatio >1){
            width = maxSize;
            height = (int)(width/bitmapRatio);
        }else{
            height = maxSize;
            width = (int)(height*bitmapRatio);
        }
        return Bitmap.createScaledBitmap (image,width,height,true);
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
