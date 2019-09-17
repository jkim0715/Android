package com.example.mapmarker;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpHandler2 {
    public static String getString(String urlstr){
       String result = null;
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

    public static String convertStr(InputStream is){

        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb =new StringBuilder();


       String temp;
        try{
            while((temp = br.readLine()) != null) {
                sb.append(temp);
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
        return sb.toString();
    }

}
