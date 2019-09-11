package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText1;
    LoginTask loginTask;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);
    }

    public void clickbt(View view){
        String id  = editText.getText().toString();
        String pwd = editText1.getText().toString();
        String url ="http://70.12.60.106/Server/login.jsp?id="+id+"&pwd="+pwd;
        loginTask = new LoginTask(url);
        loginTask.execute();
    }

// LOGIN용
    class LoginTask extends AsyncTask<String,String,String> {
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
        protected void onPostExecute(String s) {
            String id = editText.getText().toString();
            if(s.equals("0")){
                intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }else{
            }
        }
    }


}
