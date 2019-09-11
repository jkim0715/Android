package com.example.p488;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView,textView2,textView3;
    Sthread sthread;
    MyHandler myHandler;
    MyHandler2 myHandler2;
    MyHandler3 myHandler3;
    Tthread tthread;
    MyThread myThread;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        sthread = new Sthread();
        myHandler = new MyHandler();
        tthread = new Tthread();
        myHandler2 = new MyHandler2();
        myHandler3 = new MyHandler3();
        button = findViewById(R.id.button);
        myThread = new MyThread();
    }


    public void clickbt(View view){

        sthread.start();
        tthread.start();
        button.setText("STOP");
        button.setEnabled(false);
    }

    //Speed
    class Sthread extends Thread{
        public Sthread() {
        }
        @Override
        public void run() {
            Random random = new Random();
            while(true){
                int k = random.nextInt(200)+1;
                myThread.setCnt(k);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T1]","------------------------"+k);
                final int temp = k;
                Message message = myHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("cnt",k);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        }
    }
    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int cnt = bundle.getInt("cnt");
            textView.setText(cnt+"");
        }
    }


    class Tthread extends Thread{
        public Tthread() {
        }
        @Override
        public void run() {
            Random random = new Random();
            while(true){
                int k = random.nextInt(6000)+1000;
                myThread.setCnt2(k);
                myThread.run();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T1]","------------------------"+k);
                final int temp = k;
                Message message2 = myHandler2.obtainMessage();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("cnt",k);
                message2.setData(bundle2);
                myHandler2.sendMessage(message2);
            }
        }
    }

    class MyHandler2 extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int cnt = bundle.getInt("cnt");
            textView2.setText(cnt+"");
        }
    }

    class MyThread extends Thread {
        int cnt;
        int cnt2;

        public MyThread(){
        }
        public void setCnt(int cnt){
            this.cnt = cnt;
        }
        public void setCnt2(int cnt2){
            this.cnt2 =cnt2;
        }

        public void run() {

                final int temp = cnt2 / cnt ;
                Message message3 = myHandler.obtainMessage();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("cnt",temp);
                message3.setData(bundle3);
                myHandler3.sendMessage(message3);

        }
    }

    class MyHandler3 extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int cnt = bundle.getInt("cnt");
            textView3.setText(cnt+"");
        }
    }


}
