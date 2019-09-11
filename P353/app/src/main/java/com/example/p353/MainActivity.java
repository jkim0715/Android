package com.example.p353;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Activity 들 끼리 연동할 때 필요한거 intent , 서비스 호출 할떄도 intent 사용
    Intent intent;
    TextView textView;
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageView= findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10);
    }

    public void clickbt(View view){
        intent = new Intent(this,MyService.class);
        startService(intent);
    }//END clickbt

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null){
            stopService(intent);
        }
    } // END onDestroy


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        process(intent);
    }// END onNewIntent


    public void process(Intent intent){
        if(intent != null){
            int data = intent.getIntExtra("cmd",0);
            textView.setText(data+"");
            progressBar.setProgress(data);
            if(data%2 == 0){
                imageView.setImageResource(R.drawable.dog2);
            }else{
                imageView.setImageResource(R.drawable.do1);
            }

        }
    }// END process

}
