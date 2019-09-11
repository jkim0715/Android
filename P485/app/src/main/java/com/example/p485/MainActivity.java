package com.example.p485;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyThread myThread;
    MyHandler myHandler;
    //Main Thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        myThread = new MyThread();
        myHandler = new MyHandler();

    }
    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int cnt = bundle.getInt("cnt");
            textView.setText(cnt+"");
        }
    }

    public void clickBt(View view){
        myThread.setCnt(20);
        myThread.start();

    }

    // Sub Thread
    class MyThread extends Thread{
        int cnt;

        public MyThread(){
        }
        public void setCnt(int cnt){
            this.cnt = cnt;
        }
        @Override
        public void run() {
            for(int i = 0; i< cnt ; i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T1]","------------------------"+i);
                final int temp = i;
                Message message = myHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("cnt",i);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        }
    }
}
