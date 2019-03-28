package com.example.ari.lokasi;

/**
 * Created by Ari on 1/22/2018.
 */

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class LocationService extends Service implements LocationListener {

    protected LocationManager locationManager;
    Location location;
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;

    public LocationService(Context context) {
        locationManager= (LocationManager) context
                .getSystemService(LOCATION_SERVICE);
    }

    public Location getLocation(String provider) {
try{
    if (locationManager.isProviderEnabled(provider))
    {
        locationManager.requestLocationUpdates
                (provider,MIN_TIME_FOR_UPDATE,MIN_DISTANCE_FOR_UPDATE, this);
        if (locationManager != null)
        {
            location= locationManager.getLastKnownLocation(provider);
            return location;
        }
    }
}catch (SecurityException a){

}

        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
