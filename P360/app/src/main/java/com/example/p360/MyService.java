package com.example.p360;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
//    Intent intent = new Intent();
    //binder:service의 reference를 activity가 가지고 이썽야함
    class MyBinder extends Binder{
        public MyService getService(){
//            intent = new Intent(getApplicationContext(),MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            return MyService.this;

        }
    } //END Binder

    IBinder iBinder = new MyBinder();
    // service conn 이 동작할 때 자도응로 onBind가 작동.
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
//service가 시작될 때 가장먼저 실행.
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d("[ms]","--------------------------------------");
//        return super.onStartCommand(intent, flags, startId);
//    }
    //Thread 만들어서 Activity로 보내보기


    public void bt1(){

        Runnable run = new Runnable() {
            @Override
                        public void run() {

                            for(int i =0; i <10 ; i++){
                                Intent sintent = new Intent(getApplicationContext(),MainActivity.class);
                                sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Log.d("[ms]","함수------------------------------------------");
                                sintent.putExtra("cmd",i);
                                startActivity(sintent);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                    }
                } //END for
            } //END run()
        };
        new Thread(run).start();
    }
    public void bt2(){

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for(int i =0; i <=10 ; i++){
                    Intent sintent = new Intent(getApplicationContext(),MainActivity.class);
                    sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    sintent.putExtra("cmd1",i);
                    startActivity(sintent);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } //END for
            } //END run()
        };
        new Thread(run).start();
    }

}//END
