package com.example.p676;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    public static LatLng getString(String urlstr){
       LatLng result = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream is = null;


        try {
            url = new URL(urlstr);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            //10초동안 기다린다 넘으면 Exception
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            is = new BufferedInputStream(httpURLConnection.getInputStream());
            result = convertStr(is);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //반드시 끊어 줘야 함
            httpURLConnection.disconnect();
        }

        return result;
    }

    public static LatLng convertStr(InputStream is){

        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb =new StringBuilder();

        String lat;
        String log;
        LatLng pt = null;
        try{
            while(br.readLine() != null) {
                lat = br.readLine();
                System.out.println("lat:" + lat);
                log = br.readLine();
                System.out.println("log:" + log);
                pt = new LatLng(Double.parseDouble(lat), Double.parseDouble(log));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                //반드시 끊어 줘야 함
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pt;
    }

}
