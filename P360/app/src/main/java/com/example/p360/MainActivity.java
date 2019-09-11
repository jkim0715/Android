package com.example.p360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    MyService ms;
    ProgressBar progressBar,progressBar2;
    SeekBar seekBar;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder mb = (MyService.MyBinder)iBinder;
            ms = mb.getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };//END conn
    //Activity가 Service를 컨트롤 할 예정..
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        seekBar = findViewById(R.id.seekBar);
        setContentView(R.layout.activity_main);
        //binding. service의 reference를 activity가 가지고 이썽야함
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }//END onCreate

    public void clickBt1(View view){
        ms.bt1();

    }//END
    public void clickBt2(View view){
        ms.bt2();
    }//END


    @Override
  protected void onNewIntent(Intent intent) {
        System.out.println(intent.getIntExtra("cmd",0));
        process(intent);
  }


    public void process(Intent intent){

            int data = intent.getIntExtra("cmd", 0);

            int data2 = intent.getIntExtra("cmd1", 0);
            if(data != 0){
//                 progressBar.setProgress(data);
            System.out.println(data);
         
            }
            else if (data2 != 0) {
//                progressBar2.setProgress(data2);
                System.out.println(data2);
            }

    }// END process
}
