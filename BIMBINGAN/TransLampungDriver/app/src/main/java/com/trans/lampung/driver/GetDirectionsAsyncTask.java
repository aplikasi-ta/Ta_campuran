package com.trans.lampung.driver;

import java.util.ArrayList;
import java.util.Map;
import org.w3c.dom.Document;
import com.google.android.gms.maps.model.LatLng;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetDirectionsAsyncTask extends AsyncTask<Map<String, String>, Object, ArrayList<LatLng>>
{
    public static final String USER_CURRENT_LAT = "user_current_lat";
    public static final String USER_CURRENT_LONG = "user_current_long";
    public static final String DESTINATION_LAT = "destination_lat";
    public static final String DESTINATION_LONG = "destination_long";
    public static final String DIRECTIONS_MODE = "directions_mode";
    private RuteActivity activity;
    private Exception exception;
    private ProgressDialog progressDialog;
    public String distance, duration, startAdd, endAdd;
    public int durationV, distanceV;
 
    public GetDirectionsAsyncTask(RuteActivity activity)
    {
        super();
        this.activity = activity;
    }
 
    public void onPreExecute()
    {
        progressDialog = new ProgressDialog(activity, android.R.style.Theme_DeviceDefault_Light_Dialog);
        progressDialog.setMessage("Calculating directions");
        progressDialog.show();
    }
 
    @Override
    public void onPostExecute(ArrayList result)
    {
        progressDialog.dismiss();
        if (exception == null)
        {
            activity.handleGetDirectionsResult(result, getDuration(), getDistance(), getStartAdd(), getEndAdd());
        }
        else
        {
            processException();
        }
    }
 
    @Override
    protected ArrayList<LatLng> doInBackground(Map<String, String>... params)
    {
        Map<String, String> paramMap = params[0];
        try
        {
            LatLng fromPosition = new LatLng(Double.valueOf(paramMap.get(USER_CURRENT_LAT)) , Double.valueOf(paramMap.get(USER_CURRENT_LONG)));
            LatLng toPosition = new LatLng(Double.valueOf(paramMap.get(DESTINATION_LAT)) , Double.valueOf(paramMap.get(DESTINATION_LONG)));
            GMapV2Direction md = new GMapV2Direction();
            Document doc = md.getDocument(fromPosition, toPosition, paramMap.get(DIRECTIONS_MODE));
            ArrayList<LatLng> directionPoints = md.getDirection(doc);
            duration = md.getDurationText(doc);
            Log.i("Duration MD", duration);
            distance = md.getDistanceText(doc);
            startAdd = md.getStartAddress(doc);
            endAdd = md.getEndAddress(doc);
            durationV = md.getDurationValue(doc);
            distanceV = md.getDistanceValue(doc);
            return directionPoints;
        }
        catch (Exception e)
        {
            exception = e;
            return null;
        }
    }
 
    private void processException()
    {
        Toast.makeText(activity, "Error retriving data", 3000).show();
    }
    
    public String getDuration(){
    	return duration;
    }
    public String getDistance(){
    	return distance;
    }
    public String getStartAdd(){
    	return startAdd;
    }
    public String getEndAdd(){
    	return endAdd;
    }
    public int getDurationV(){
    	return durationV;
    }
    public int getDistanceV(){
    	return distanceV;
    }
    
}