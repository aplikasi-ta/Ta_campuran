package design.ws.com.flightbooking;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rp on 6/14/2016.
 */
public class MyRecycleAdapter_Flightbooking extends RecyclerView.Adapter<MyRecycleAdapter_Flightbooking.MyViewHolder> {
Context context;

    boolean showingFirst = true;

    private List<BeanClassForRecyclerView_Flightbooking> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {



        ImageView image;
        TextView departure;
        TextView city;
        TextView airport;
        TextView departure1;
        TextView city1;
        TextView airport1;
        TextView time;
        TextView price;



        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            departure = (TextView) view.findViewById(R.id.departure);
            city = (TextView) view.findViewById(R.id.city);
            airport= (TextView) view.findViewById(R.id.airport);
            departure1 = (TextView) view.findViewById(R.id.departure1);
            city1 = (TextView) view.findViewById(R.id.city1);
            airport1= (TextView) view.findViewById(R.id.airport1);
            time= (TextView) view.findViewById(R.id.time);
            price= (TextView) view.findViewById(R.id.price);






        }



    }




    public MyRecycleAdapter_Flightbooking(MainActivity mainActivityContacts, List<BeanClassForRecyclerView_Flightbooking> moviesList) {
        this.moviesList = moviesList;
       this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_recycler_view, parent, false);



        return new MyViewHolder(itemView);


    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        BeanClassForRecyclerView_Flightbooking movie = moviesList.get(position);
        holder.departure.setText(movie.getDeparture());
        holder.city.setText(movie.getCity());
        holder.airport.setText((movie.getAirport()));
        holder.departure1.setText(movie.getDeparture1());
        holder.city1.setText(movie.getCity1());
        holder.airport1.setText((movie.getAirport1()));
        holder.time.setText(movie.getTime());
        holder.price.setText(movie.getPrice());
        holder.image.setImageResource(movie.getImage());




    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }






}


