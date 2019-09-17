package com.example.mapmarker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Movie;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//GOOGLE MAP DISPLAY
public class Main2Activity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myLocationMarker, myLocationMarker1;
    Traveltask traveltask;
    ArrayList<Coordinate> list;
    Double lat;
    Double log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Prepare Map
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map", "지도 준비됨.");
                map = googleMap;
                map.setMyLocationEnabled(true);
                startLocationService();
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//END onCreate

    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            //Firstly getting  a current location by latitude / longitude
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                lat = latitude;
                log = longitude;
                showCurrentLocation(latitude, longitude);

            }
            // Ready to listen & response
            GPSListener gpsListener = new GPSListener();
            //interval
            long minTime = 0;
            float minDistance = 0;

            //keep on updating current loc
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);


            Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
                    Toast.LENGTH_SHORT).show();

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }//END StartLocServ

    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            showCurrentLocation(latitude, longitude);
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

    private void showCurrentLocation(Double latitude, Double longitude) {
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        showMyLocationMarker(curPoint);
    }

    private void showMyLocationMarker(LatLng curPoint) {
        myLocationMarker = new MarkerOptions();
        myLocationMarker.position(curPoint);
        myLocationMarker.title("● 내 위치\n");
        myLocationMarker.snippet("● GPS로 확인한 위치");
        myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker1));
        map.addMarker(myLocationMarker);

    }

    public void letsgo(View view) {

        String url = "http://70.12.60.106/Server/marker.jsp";
        traveltask = new Traveltask(url);
        traveltask.execute();

    }

    class Traveltask extends AsyncTask<String, Void, String> {
        String url;

        public Traveltask(String url) {
            this.url = url;
        }


        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler2.getString(url);

            return str;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            list = new ArrayList<Coordinate>();

            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for(int i = 0; i < ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String lati = jo.getString("latitude");
                    String logi = jo.getString("longitude");
                    list.add(new Coordinate(Double.parseDouble(lati),Double.parseDouble(logi)));
                }
                System.out.println(list.get(0).getLat());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(int i=0; i<list.size();i++){
                LatLng pt = new LatLng(list.get(i).getLat(),list.get(i).getLog());
                showMyLocationMarker(pt);
            }
        }

    }
}
