package com.example.p474;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView,textView2;
    Button button,button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

    }
    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<=100;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T]","----------------------"+i);
//              메인 스레드를 서브 스레드가 제어할 수 없음 ~!
//                textView.setText(i);
               //UiThread용 final변수 만들기
                final int temp = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 여기 안에 변수는 final이 필요함.
                        textView.setText(""+temp);
                    }
                });//END runOnUiThread
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    button.setEnabled(true);
                }
            });

        }
    });

    // 이게 맞는 거임 !
//    Runnable r1 = new Runnable() {
//        @Override
//        public void run() {
//            for(int i=0; i<=100;i++){
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Log.d("[T]","----------------------"+i);
////              메인 스레드를 서브 스레드가 제어할 수 없음 ~!
////                textView.setText(i);
//                final int temp = i;
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 여기 안에 변수는 final이 필요함.
//                        textView.setText(""+temp);
//                    }
//                });//END runOnUiThread
//            }
//        }
//    };

    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<=100;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T]","**********************"+i);
//               메인쓰레드를 건들일 수 없음
//                textView2.setText(i);
                final int temp = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 여기 안에 변수는 final이 필요함.
                        textView2.setText(""+temp);
                    }
                });//END runOnUiThread
            }
            //버튼 다시 활성화
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    button2.setEnabled(true);
                }
            });
        }
    });


    public void clickbt1(View view){
        //t1 안에 run이라는 함수가 동작
      //  t1.start();
//        Thread t1 = new Thread(r1);
        t1.start();
        //버튼 비활성화
        button.setEnabled(false);
    }

    public void clickbt2(View view){
        t2.start();
        button2.setEnabled(false);
    }
}
