package com.example.p499;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView,textView2,textView3,textView4,textView5;
    ImageView imageView;
    SpeedAT speedAT;
    RpmAT rpmAT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        imageView = findViewById(R.id.imageView);
    }

    public void clickbt(View view){
        speedAT = new SpeedAT(200);
        speedAT.execute();
        rpmAT = new RpmAT(6000);
        rpmAT.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class SpeedAT extends AsyncTask<Integer,Integer,Integer>{
        int speed;
        public SpeedAT(int speed) {
            this.speed = speed;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            Random rd = new Random();
            int k =0;
           while(isCancelled() == false) {
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               k = rd.nextInt(speed) + 30;
               publishProgress(k);
           }

            return k;
        }

        @Override
        protected void onPreExecute() {
            textView.setText("Start Task");
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(values[0].intValue()+"");

            if(values[0].intValue()<80){
                textView3.setText("저속");
                imageView.setImageResource(R.drawable.icon1);
            }else if(values[0].intValue()<110){
                textView3.setText("정속");
                imageView.setImageResource(R.drawable.icon2);
            }else{
                textView3.setText("과속");
                imageView.setImageResource(R.drawable.p5);
            }
        }
        @Override
        protected void onPostExecute(Integer integer) {
        }
    }

    class RpmAT extends  AsyncTask<Integer,Integer,Integer>{
        int rpm;
        public RpmAT(int rpm) {
            this.rpm = rpm;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            Random rd = new Random();
            int k = 0;
            while(isCancelled() == false){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                k= rd.nextInt(rpm)+1000;
                publishProgress(k);
            }

            return k;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            System.out.println(values[0].intValue());
            textView4.setText(values[0].intValue()+"");
        }
    }

}
