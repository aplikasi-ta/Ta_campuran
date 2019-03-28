package com.trans.lampung.driver;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trans.lampung.driver.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuteActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private FirebaseAuth auth;
    private FirebaseUser mFirebaseUser;
    ProgressDialog mProgressDialog;
    private DatabaseReference mDatabase;
    private String mUserId;
    ArrayList<Profile> profiles = new ArrayList<>();
    private SupportMapFragment mapFragment;

    private Polyline newPolyline;
    String startAdd;
    String endAdd;
    int durationV;
    int distanceV, SetMode;

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rute);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        auth = FirebaseAuth.getInstance();
        mFirebaseUser = auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUserId = mFirebaseUser.getUid();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        showProgressDialog();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Semua");
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
    public void onResume(){
        super.onResume();
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
        mMap.setMyLocationEnabled(true);
        getDataFromServer("semua");

/*        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
*/    }

    public void getDataFromServer(final String jurusan){
        mDatabase.child("UserNode").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int i = 0;
                    profiles.clear();
                    for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                    {
                        Profile profile=postSnapShot.getValue(Profile.class);
                        profiles.add(profile);
                        tambahBus(jurusan);
                        i++;
                    }

                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
    }

    public void tambahBus(String jurusan){
        mMap.clear();
        int i = profiles.size();
        for(int x=0; x<i;x++){
            Profile profile1=profiles.get(x);
/*                        System.out.println("Nama : "+profile1.getNama());
                        System.out.println("Nomor KTP/KTM : "+profile1.getNktp());
                        System.out.println("Jenis Kelamin : "+profile1.getJekel());
                        System.out.println("Email : "+profile1.getEmail());
                        System.out.println("Phone Number : "+profile1.getPnumber());
                        System.out.println("Koordinat : "+profile1.getLatitude()+","+profile1.getLongitude());
*/                        //                        adapter.notifyDataSetChanged();
            if(jurusan.equalsIgnoreCase("semua")){
//                if(profile1.getStatus().equalsIgnoreCase("on")){
                    try{
                        LatLng user = new LatLng(Double.parseDouble(profile1.getLatitude()), Double.parseDouble(profile1.getLongitude()));
                        mMap.addMarker(new MarkerOptions().position(user).title(profile1.getNama())
                                .snippet("Email : "+profile1.getEmail()+"\nNomor Telepon : "+profile1.getPnumber())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_loc_new)));
                        try{
                            LatLng userPos = new LatLng(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(userPos));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                            mMap.setOnMarkerClickListener(this);
                        }catch (Exception e){
//                    System.out.print(e.getMessage());
                            Profile profileFirst=profiles.get(x);

                            LatLng user1 = new LatLng(Double.parseDouble(profileFirst.getLatitude()), Double.parseDouble(profileFirst.getLongitude()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(user1));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                            mMap.setOnMarkerClickListener(this);
                        }
                    }catch (Exception e){
                        System.out.print(e.getMessage());
                    }
 //               }
            }
            //Tampilkan sesuai jurusan
            else{
//                if(profile1.getJurusan().equalsIgnoreCase(jurusan)&&profile1.getStatus().equalsIgnoreCase("on")){
                    try{
                        LatLng user = new LatLng(Double.parseDouble(profile1.getLatitude()), Double.parseDouble(profile1.getLongitude()));
                        mMap.addMarker(new MarkerOptions().position(user).title(profile1.getNama())
                                .snippet("Email : "+profile1.getEmail()+"\nNomor Telepon : "+profile1.getPnumber())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_loc_new)));
                        try{
                            LatLng userPos = new LatLng(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(userPos));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                            mMap.setOnMarkerClickListener(this);
                        }catch (Exception e){
//                    System.out.print(e.getMessage());
                            Profile profileFirst=profiles.get(x);

                            LatLng user1 = new LatLng(Double.parseDouble(profileFirst.getLatitude()), Double.parseDouble(profileFirst.getLongitude()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(user1));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                            mMap.setOnMarkerClickListener(this);
                        }
                    }catch (Exception e){
                        System.out.print(e.getMessage());
                    }
 //               }
            }
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(RuteActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void Search(View view){
        getDataFromServer(spinner.getSelectedItem().toString());
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints
            ,String duration, String distance, String startAdd, String endAdd) {
        PolylineOptions rectLine = new PolylineOptions().width(4).color(Color.BLUE);

        for(int i = 0 ; i < directionPoints.size() ; i++)
        {
            rectLine.add(directionPoints.get(i));
        }
        if (newPolyline != null)
        {
            newPolyline.remove();
        }
        newPolyline = mMap.addPolyline(rectLine);

        TextView detail = (TextView) findViewById(R.id.tvEstimasi);
        String details = "Jarak : "+distance
                +"\nDurasi : "+duration
//			+"\nAlamat Mulai : "+startAdd
//			+"\nAlamat Akhir : "+endAdd
                ;
        detail.setText(details);
        hideProgressDialog();
//		latlngBounds = createLatLngBoundsObject(fromPosition, toPosition);
//        map.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBounds, width, height, 150));

    }


    public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);

        GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(RuteActivity.this);
        asyncTask.execute(map);
//	duration = asyncTask.getDuration();
//	distance = asyncTask.getDistance();
        startAdd = asyncTask.getStartAdd();
        endAdd = asyncTask.getEndAdd();
        durationV = asyncTask.getDurationV();
        distanceV = asyncTask.getDistanceV();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        showProgressDialog();
        try{
            Location location = mMap.getMyLocation();
            findDirections( /*Dari*/marker.getPosition().latitude, marker.getPosition().longitude
                /*Ke*/,location.getLatitude(), location.getLongitude(), GMapV2Direction.MODE_DRIVING);
        }catch (Exception e){
            hideProgressDialog();
        }
        return false;
    }
}
