package com.example.p353;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

// 액티비티랑 비슷하지만. 화면이 없는 액티비티라고 생각하면 편함...
public class MyService extends Service {
    boolean flag;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("[MyService]","oncreate---------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("[MyService]","ondestroy---------------");

    }


    // 액티비티가 서비스를 호출하게 되면 onreate랑 같이 호출됨 Main에서 실행된 intent를 포함하고 있음
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("[MyService]","onStartCommand---------------");

        final Intent sendintent = new Intent(getApplicationContext(),MainActivity.class);
        //이래야 service에서 Main으로 intent를 보낼 수 있음.
        sendintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Thread 임.
        Runnable run = new Runnable() {
          boolean  flag = true;
            @Override
            public void run() {
                for(int i =0; i <=10 ; i++){
                    try {
                        Log.d("[MyService]","while---------------");
                        sendintent.putExtra("cmd",i);
                        startActivity(sendintent);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(flag == false){
                        break;
                    }
                } //END for
            } //END run()
        };
       Thread t = new Thread(run);
       t.start();
       // new Thread(run).start();
        return super.onStartCommand(intent, flags, startId);
    } //end onstartcommand



}
