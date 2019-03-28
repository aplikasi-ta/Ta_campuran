package com.example.sandykurniawan.skripsi_maps;

/**
 * Created by user on 13/02/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<HashMap<String, String>> Wisata;
    Context context;

    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> Wisata) {
        this.context = context;
        this.Wisata = Wisata;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.nama.setText(Wisata.get(position).get("nama"));
        holder.lat.setText(Wisata.get(position).get("lat"));
        holder.lng.setText(Wisata.get(position).get("lng"));
        holder.hsl.setText(Wisata.get(position).get("hsl"));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, Wisata.get(position).get("nama"), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return Wisata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, lat, lng, hsl;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            nama = itemView.findViewById(R.id.nama);
            lat = itemView.findViewById(R.id.lat);
            lng = itemView.findViewById(R.id.lng);
            hsl = itemView.findViewById(R.id.hsl);

        }
    }
}
