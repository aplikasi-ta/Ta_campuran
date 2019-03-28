package com.example.ari.rutemap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Address;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.PathOverlay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private IMapController mc;
    private MapView osm;
    private LocationManager locationManager;
    private PathOverlay po;
    private  Road road;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        osm = (MapView) findViewById(R.id.mapview);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);

        mc = (IMapController) osm.getController();
        mc.setZoom(18);

        GeoPoint center = new GeoPoint(40.796788, -73.949232);
        mc.animateTo(center);

        initPathOverlay();
        addMaker(center);

        osm.setMapListener(new MapListener() {
            @Override
            public boolean onScroll(ScrollEvent scrollEvent) {
                Log.i("Script","onScroll()");
                return false;
            }

            @Override
            public boolean onZoom(ZoomEvent zoomEvent) {
                Log.i("Script","onZoom()");
                return false;
            }
        });

    }


    public void initPathOverlay(){
        po = new PathOverlay(0, this);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        po.setPaint(p);
    }

    public PathOverlay addPointsLine(GeoPoint gp){
        po.addPoint(gp);
        return(po);
    }

    public void addMaker(GeoPoint center){
        Marker marker = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setImage(getResources().getDrawable(R.drawable.hp));

        marker.setDraggable(true);

        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Log.i("Script","onMarkerClick()");
                return false;
            }
        });

        marker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {
                Log.i("Script","onMarkerDrag()");
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Log.i("Script","onMarkerDragEnd()");
            }

            @Override
            public void onMarkerDragStart(Marker marker) {
                Log.i("Script","onMarkerDragStart()");
            }
        });

        osm.getOverlays().clear();
        osm.getOverlays().add(addPointsLine(center));
        osm.getOverlays().add(new MapOverlay(this));
        //osm.getOverlays().add(marker);
        osm.invalidate();
    }

    class MapOverlay extends Overlay{
        public MapOverlay(Context ctx){
            super(ctx);
        }

        @Override
        protected void draw(Canvas canvas, MapView mapView, boolean arg2) {

        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent me, MapView mapView) {
            Projection p = osm.getProjection();
            GeoPoint gp = (GeoPoint) p.fromPixels((int) me.getX(), (int) me.getY());
            addMaker(gp);
            return (false);
        }

        public void getRoute(View view){
            EditText etorigin = (EditText) findViewById(R.id.origin);
            EditText etDestination = (EditText) findViewById(R.id.destination);
            final  String o = etorigin.getText().toString();
            final String d = etDestination.getText().toString();

            new Thread(){
                public void run(){
                    GeoPoint start = getLocation(o);
                    GeoPoint end = getLocation(d);

                    if(start != null && end != null){
                        drawRoute(start, end);
                    }else{
                }
            }.start();

        }

        public GeoPoint getLocation(String location){
            GeocoderNominatim gn = new GeocoderNominatim(MainActivity.this);
            GeoPoint gp = null;
            List<Address> al = new ArrayList<Address>();

            try{
                al = gn.getFromLocationName(location, 1);

                if(al != null && al.size() > 0){
                    Log.i("Script","Rua : "+al.get(0).getThoroughfare());
                    Log.i("Script","Cidade : "+al.get(0).getSubAdminArea());
                    Log.i("Script","Estado : "+al.get(0).getAdminArea());
                    Log.i("Script","Pais : "+al.get(0).getCountryName());

                    gp = new GeoPoint(al.get(0).getLatitude(), al.get(0).getLongitude());
                }

            }catch (IOException a){
                a.printStackTrace();
            }

            return(null);
        }

        public void drawRoute(GeoPoint start, GeoPoint end){
            RoadManager roadManager = new OSRMRoadManager();
            ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
            points.add(start);
            points.add(end);
            Road road = roadManager.getRoad(points);
            final Polyline roadOverlay = RoadManager.buildRoadOverlay(road, MainActivity.this);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    osm.getOverlays().add(roadOverlay);
                }
            });

        }

    }

}
