package com.trans.lampung.driver;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.trans.lampung.driver.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstimasiActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, LocationListener {

    ProgressDialog mProgressDialog;
    private LocationManager locationManager;
    String item;
    TextView tvEWaktu, tvEHarga;

    String startAdd;
    String endAdd;
    int durationV;
    int distanceV, SetMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimasi);

        tvEHarga = (TextView) findViewById(R.id.tvEstimasiHarga);
        tvEWaktu = (TextView) findViewById(R.id.tvEstimasiWaktu);

        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
// Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Bandara Raden Inten II - Graha Wangsa");
        categories.add("Graha Wangsa - Bandara Raden Inten II");
        categories.add("Unila - Itera");
        categories.add("Itera - Unila");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 400, 1, this);
        locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 400, 1, this);
        showProgressDialog();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(EstimasiActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        if (item.equalsIgnoreCase("Graha Wangsa - Bandara Raden Inten II")) {
            double latRadenInten = -5.242599;
            double lngRadenInten = 105.175761;
            tvEHarga.setText("Tarif : Rp25.000");
            findDirections( /*Dari*/location.getLatitude(), location.getLongitude()
                /*Ke*/, latRadenInten, lngRadenInten, GMapV2Direction.MODE_DRIVING);
        }
        if (item.equalsIgnoreCase("Bandara Raden Inten II - Graha Wangsa")) {
            double latGrahaWangsa = -5.444373;
            double lngGrahaWangsa = 105.292335;
            tvEHarga.setText("Tarif : Rp25.000");
            findDirections( /*Dari*/location.getLatitude(), location.getLongitude()
                /*Ke*/, latGrahaWangsa, lngGrahaWangsa, GMapV2Direction.MODE_DRIVING);
        }
        if (item.equalsIgnoreCase("Unila - Itera")) {
            double latItera = -5.358195;
            double lngItera = 105.314724;
            tvEHarga.setText("Tarif : Rp10.000");
            findDirections( /*Dari*/location.getLatitude(), location.getLongitude()
                /*Ke*/, latItera, lngItera, GMapV2Direction.MODE_DRIVING);
        }
        if (item.equalsIgnoreCase("Itera - Unila")) {
            double latUnila = -5.363849;
            double lngUnila = 105.242436;
            tvEHarga.setText("Tarif : Rp10.000");
            findDirections( /*Dari*/location.getLatitude(), location.getLongitude()
                /*Ke*/, latUnila, lngUnila, GMapV2Direction.MODE_DRIVING);
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);

    }

    public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints
            ,String duration, String distance, String startAdd, String endAdd) {

        tvEWaktu.setText("Estimasi" +
                "\n\nJarak Menuju Tujuan : "+distance
                +"\n\nWaktu Menuju Tujuan : "+duration);

        hideProgressDialog();

    }


    public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);

        GetDirectionsAsyncTaskEs asyncTask = new GetDirectionsAsyncTaskEs(EstimasiActivity.this);
        asyncTask.execute(map);
//	duration = asyncTask.getDuration();
//	distance = asyncTask.getDistance();
        startAdd = asyncTask.getStartAdd();
        endAdd = asyncTask.getEndAdd();
        durationV = asyncTask.getDurationV();
        distanceV = asyncTask.getDistanceV();
    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
