package com.example.sandykurniawan.skripsi_maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Daftar_wisata extends Fragment {

    private String TAG = Daftar_wisata.class.getSimpleName();
    private ProgressDialog pDialog;
    private RecyclerView RV;
    View tampil;

    private static String url = "https://sandy13421065.000webhostapp.com/data.php?lat=-5.458710&lng=104.513879";


    ArrayList<HashMap<String, String>> ListWisata;
    private FragmentActivity listener;

    public Daftar_wisata() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tampil= inflater.inflate(R.layout.fragment_daftar_wisata, container, false);
        getActivity().setTitle("Daftar Wisata");

        ListWisata = new ArrayList<>();
        RV = tampil.findViewById(R.id.list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity ().getApplicationContext());
        RV.setLayoutManager(linearLayoutManager);

        RV.addOnItemTouchListener(new RecyclerTouchListener(getActivity (), RV, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getActivity ().getBaseContext(), Maps.class);
                i.putExtra("lat", ListWisata.get(position).get("lat"));
                i.putExtra("lng", ListWisata.get(position).get("lng"));
                i.putExtra ("nama",ListWisata.get(position).get("nama"));



                startActivity(i);
            }
            @Override
            public void onLongClick(View view, final int position) {
                return;
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

        }));

        new GetContacts().execute();
        return  tampil;
    }



    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity ());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray wisata = jsonObj.getJSONArray("wisata");

                    // looping through All Contacts
                    for (int i = 0; i < wisata.length(); i++) {
                        JSONObject c = wisata.getJSONObject(i);

                        String hsl = c.getString("hsl");
                        String nama = c.getString("nama");
                        String lat = c.getString("lat");
                        String lng = c.getString("lng");


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("hsl", hsl);
                        contact.put("nama", nama);
                        contact.put("lat", lat);
                        contact.put("lng", lng);

                        // adding contact to contact list
                        ListWisata.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity ().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity ().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity ().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity ().getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            CustomAdapter customAdapter = new CustomAdapter(getActivity (), ListWisata);
            RV.setAdapter(customAdapter);
        }

    }





}
