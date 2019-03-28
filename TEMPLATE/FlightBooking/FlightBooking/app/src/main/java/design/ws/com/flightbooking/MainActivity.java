package design.ws.com.flightbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  Integer []IMAGE={R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,
            R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company,R.drawable.ic_commercial_air_company};

    private  String [] DEPARTURE={"Departure 12.40", "Departure 10.20", "Departure 9.25",
            "Departure 4.40", "Departure 11.20", "Departure 8.30", "Departure 8.00", "Departure 5.50"};

    private  String [] CITY={"Belgrade","Milan","Jet","Ethihad","Kingfishier","Indigo","Spice","Jety"};

    private  String [] AIRPORT={"Tesla Airport","Linalt Airport","Delhi Airport","Belgium Airport","Torrento Airport",
            "Captown Airport","Swiden Airport","Dubai Airport"};

    private  String [] DEPARTURE1={"Departure 12.40", "Departure 10.20", "Departure 9.25",
            "Departure 4.40", "Departure 11.20", "Departure 8.30", "Departure 8.00", "Departure 5.50"};

    private  String [] CITY1={"Belgrade","Milan","Jet","Ethihad","Kingfishier","Indigo","Spice","Jety"};

    private  String [] AIRPORT1={"Tesla Airport","Linalt Airport","Delhi Airport","Belgium Airport","Torrento Airport",
            "Captown Airport","Swiden Airport","Dubai Airport"};

    private  String [] TIME= {"6h 21m - 1 stop , DXB","8h 11m - 2 stop , DXB","4h 40m - 1 stop , DXB","1h 30m - 1 stop , DXB",
            "7h 21m - 1 stop , DXB","4h 11m - 1 stop , DXB","9h 45m - 1 stop , DXB","12h 21m - 1 stop , DXB"};

    private  String [] PRICE={"21,685 USD","45,421 USD","22,500 USD","10,450 USD","32,752 USD","60,625 USD","50,875 USD","55,452 USD"};

    private ArrayList<BeanClassForRecyclerView_Flightbooking> beanClassForListViewArrayRecyclerHistroy;

    private RecyclerView recyclerView;
    private MyRecycleAdapter_Flightbooking mAdapter;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        beanClassForListViewArrayRecyclerHistroy = new ArrayList<>();


        for (int i = 0; i < IMAGE.length; i++) {
            BeanClassForRecyclerView_Flightbooking beanClassForRecyclerView_contacts = new BeanClassForRecyclerView_Flightbooking(IMAGE[i],DEPARTURE[i],CITY[i],AIRPORT[i],DEPARTURE1[i],CITY1[i],AIRPORT1[i],TIME[i],PRICE[i]);

            beanClassForListViewArrayRecyclerHistroy.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new MyRecycleAdapter_Flightbooking(MainActivity.this,beanClassForListViewArrayRecyclerHistroy);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
    }

