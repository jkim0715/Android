package com.example.p537;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
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

            while((temp = br.readLine()) != null){
                //여기서 각각의 object들을 붙여서 JSON Array형식으로 만들어준거임
                sb.append(temp);
            };
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
