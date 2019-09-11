package com.example.p490;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button,button2,button3;
    TextView textView;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    Mytask mytask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        textView =findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(this);
    }
    public void clickbt(View view){
        mytask = new Mytask(20);
        //실행
        mytask.execute();

    }

    public void clickbt2(View view){
        mytask.cancel(true);
    }

    public void clickbt3(View view){
        mytask = new Mytask(20);
        //실행
        mytask.execute();
    }

    class Mytask extends AsyncTask<Integer,Integer,String>{
        int cnt;

        public Mytask(int cnt) {
            this.cnt = cnt;
        }

        @Override
        protected String doInBackground(Integer... integers) {
            //Thread가 동작되는 부분
            String result = " ";
            int sum = 0;
            for(int i =0; i<=cnt;i++){
                if(isCancelled() == true){
                    break;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum +=i;
                publishProgress(i);
            }
            result = sum + "";
            return result;
        }

        @Override
        protected void onPreExecute() {
        //Thread 동작 전에 작동
            progressBar.setMax(cnt);
            button.setEnabled(false);
            textView.setText("Start Task");
            progressDialog.setTitle("progress");
            progressDialog.show();
//            progressDialog.setCancelable(false);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
        //Thread 발생되고 있는 내용을 그때 그때 받아서 처리부분
        //publishProgress(i); 이걸로 i값을 받아올 수 있음 (단 배열로 저장되서 들어옴)
        progressBar.setProgress(values[0].intValue());
        textView.setText(values[0].intValue()+"");

        }

        @Override
        protected void onPostExecute(String s) {
        //Thread 동작 끝난 후 동작부분.
        // s는 Thread의 리턴값임 (doInBackground)
            button.setEnabled(true);
            textView.setText("End Task:"+s);
            progressDialog.dismiss();
        }
    }
}
