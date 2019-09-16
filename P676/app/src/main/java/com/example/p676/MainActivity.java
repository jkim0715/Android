package com.example.p676;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myLocationMarker,myLocationMarker1;
    Traveltask traveltask;
    Double lat;
    Double log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
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

        //Ask Permission
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        ActivityCompat.requestPermissions(this, permissions, 101);

        int check = PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (check == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Accept", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "권한 부여가 안되어 있습니당", Toast.LENGTH_SHORT).show();
            return;
        }
    }//END onCreate();

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
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;
                showCurrentLocation(latitude,longitude);
                textView.setText(message);
            }
            // Ready to listen & response
            GPSListener gpsListener = new GPSListener();
            //interval
            long minTime = 0;
            float minDistance = 0;

            //keep on updating current loc
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime, minDistance, gpsListener);


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
            showCurrentLocation(lat,log);
            String message = "내 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;
            textView.setText(message);
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}
        @Override
        public void onProviderEnabled(String s) {}
        @Override
        public void onProviderDisabled(String s) {}

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

    public void letsgo(View view){
        String url ="http://70.12.60.106/Server/map.jsp?lat="+lat+"&log="+log;
        traveltask = new Traveltask(url);
        traveltask.execute();
    }
    class Traveltask extends AsyncTask<String,Void,LatLng>{
        String url;

        public Traveltask(String url) {
            this.url = url;
        }


        @Override
        protected LatLng doInBackground(String... strings) {
            LatLng str = HttpHandler.getString(url);

            return str;
        }

        @Override
        protected void onPreExecute() {
            map.clear();
        }

        @Override
        protected void onPostExecute(LatLng s) {
            System.out.println(s);

            lat = s.latitude;
            log = s.longitude;
            showMyLocationMarker(s);
             showCurrentLocation(s.latitude,s.longitude);

        }
    }
}
