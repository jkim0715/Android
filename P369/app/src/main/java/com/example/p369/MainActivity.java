package com.example.p369;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------------------------------------
        // network이 change 할때 받을 거고
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //---------------------------------------------------------------------------------------------
        BroadcastReceiver broadcastReceiver;
        broadcastReceiver = new BroadcastReceiver() {
            //onReceive가 OS에서 날라오는 정보를 받는 것임.
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                ConnectivityManager connectivityManager = null;
                NetworkInfo mobile = null;
                NetworkInfo wifi = null;
                if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (mobile != null && mobile.isConnected()) {
                        Toast.makeText(context, "MOBILE", Toast.LENGTH_SHORT).show();
                    } else if (wifi != null && wifi.isConnected()) {
                        Toast.makeText(context, "WIFI", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "NOT CONN", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        //broadcastReceiver에서 intentFilter 받을 준비.
        registerReceiver(broadcastReceiver, intentFilter);
        //---------------------------------------------------------------------------------------------
        String[] permissions ={
               Manifest.permission.CALL_PHONE
        };

        checkMyPermissions(permissions);
    }//END onCreate

    private void checkMyPermissions(String[] permissions) {
//        ArrayList<String> targetlist = new ArrayList<>();
//        for(String str : permissions){
//            int check = ContextCompat.checkSelfPermission(this,str);
//            if(check == PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
//                if(ActivityCompat.shouldShowRequestPermissionRationale(this,str) ){
//                    Toast.makeText(this, "DESC", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//        String [] targets = new String [targetlist.size()];
//        targetlist.toArray(targets);
        ActivityCompat.requestPermissions(this,permissions,101);
    }


    public void clickText(View view) {
        Intent intent = null;
        int check = PermissionChecker.checkSelfPermission(this,Manifest.permission.CALL_PHONE);

        if(check == PermissionChecker.PERMISSION_GRANTED) {
            intent = new Intent(intent.ACTION_CALL, Uri.parse("tel:01047764831"));
            startActivity(intent);
        }else{
            Toast.makeText(this, "권한 부여가 안되어 있습니당", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}
