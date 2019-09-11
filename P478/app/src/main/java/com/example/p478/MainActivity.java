package com.example.p478;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    CountHandler countHandler,countHandler2;
    Button button;

    //우리가 구현한게 아님
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        countHandler = new CountHandler();
        countHandler2 = new CountHandler();
        button = findViewById(R.id.button);
        handler = new Handler();
    }
    Runnable r = new Runnable() {
        @Override
        public void run() {

            for(int i=1; i<=10;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T]","----------------------"+i);

                //subThread에서 Main thread 변경할떄 Handler를 사용
                Message message = countHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("cnt",i);
                message.setData(bundle);
                countHandler.sendMessage(message);
            }
//            //버튼 멈출 때
            Message message2 = countHandler2.obtainMessage();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("cmd",1);
            message2.setData(bundle2);
            countHandler2.sendMessage(message2);

            //handler의 Reference는 MainThread기 때문에 subThread에 존재해도 Main을 건들일 수 있음.
            //but 유지보수에는 취약한 부분이 있음.
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    button.setEnabled(true);
//                }
//            });

        }
    };
    class CountHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
          Bundle bundle = msg.getData();
          int value = bundle.getInt("cnt");
          textView.setText(value+"");
//
//          // 버튼 다시 활성화.
//          int val = bundle.getInt("cmd");
//          if(val == 1) {
//              button.setEnabled(true);
//          }
        }
    }
    class CountHandler2 extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle2 = new Bundle();
            int val = bundle2.getInt("cmd");
            if (val == 1) {
                button.setEnabled(true);
            }
        }
    }
    public void clickbt(View view){
        Thread t = new Thread(r);
        t.start();
        button.setEnabled(false);
    }
    public void clickbt2(View view ){
        final ProgressDialog progressDialog = new ProgressDialog(this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("dialog");
        dialog.setMessage("5 secs .........");
        dialog.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        textView.setText("Next OK");
                    }
                },5000);
            }
        });
        dialog.show();
    }
}
