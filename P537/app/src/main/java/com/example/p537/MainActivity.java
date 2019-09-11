package com.example.p537;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText1;
    LoginTask loginTask;

    LinearLayout loginlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);
        loginlayout = findViewById(R.id.loginlayout);
    }

    public void clickbt(View view){
        String id  = editText.getText().toString();
        String pwd = editText1.getText().toString();
        String url ="http://70.12.60.106/Server/login.jsp?id="+id+"&pwd="+pwd;
        loginTask = new LoginTask(url);
        loginTask.execute();
    }

    class LoginTask extends AsyncTask<String,String,String>{

        String url;

        public LoginTask(String url) {
            this.url = url;
        }

        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler.getString(url);
            return str;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            String id = editText.getText().toString();
               if(s.equals("0")){
                   View myview = null;
                   LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   myview = layoutInflater.inflate(R.layout.loginok,loginlayout,true);
                   TextView textView =myview.findViewById(R.id.textView);

                   textView.setText(id+"님 환영 합니다");

                   setContentView(myview);
               }else{

               }
           }
    }



}
