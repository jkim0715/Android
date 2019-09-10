package com.example.hello;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostResume() {
        Toast.makeText(this, "Resume...", Toast.LENGTH_SHORT).show();
        super.onPostResume();
    }


    //App이 화면에서 사라지는 시점에 실행
    @Override
    protected void onPause() {
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }



    public void clickButton(View view){

        Toast.makeText(this, "click button", Toast.LENGTH_SHORT).show();
        Log.d("[Debug]....","OKBARY");
        Log.i("[Info..........","Information");
        Log.e("[Error]........","Error");

    }
}
