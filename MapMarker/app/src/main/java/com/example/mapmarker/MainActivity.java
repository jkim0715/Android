package com.example.mapmarker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Login Page
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

    }
    //Click Login Button
    public void clickLogin (View view){
        String id  = editText.getText().toString();
        String pwd = editText1.getText().toString();
        String url ="http://70.12.60.106/Server/login.jsp?id="+id+"&pwd="+pwd;
        loginTask = new LoginTask(url);
        loginTask.execute();
    }
    //ID & PWD CHECKED by SERVER
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
