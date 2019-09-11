package com.example.p200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {


    View view,view2;
    TextView textView;

    //제스쳐
    GestureDetector gestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI(){
        view = findViewById(R.id.view);
        view2=findViewById(R.id.view2);
        textView=findViewById(R.id.textView2);
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                printMsg("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                printMsg("on Show press() 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                printMsg("onSingleTapUp() 호출됨");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMsg("onScroll() 호출됨:"+v+","+v1);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                printMsg("onLongPress() 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMsg("onFling() 호출됨:"+v+","+v1);
                return true;
            }
        });
        // Touch
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //action의 종류 받아오기
                int action = motionEvent.getAction();
                //x,y 좌표 따오기
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();
                //action의 종류에 따라 Msg 출력
                if(action == MotionEvent.ACTION_DOWN){
                    printMsg("Dpwn"+curX +" "+ " "+curY);
                }else if(action == MotionEvent.ACTION_MOVE){
                    printMsg("MOCE:"+curX+" "+curY);
                }else if(action == MotionEvent.ACTION_UP){
                    printMsg("UP:"+curX+" "+curY);
                }
                return true;
            }
        });

        //제스쳐
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }


    private void printMsg(String str){
        //기존에 것을 가져오고  그앞에다 새로운거 붙이기
        textView.setText(str+"\n"+textView.getText());
    }

    //키 이벤트
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }


    //핸드폰을 가로 세로로 뉘였을떄 작동
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("ORIENTATION_LANDSCAPE");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("ORIENTATION_PORTRAIT");
        }
        super.onConfigurationChanged(newConfig);
    }

    public void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


}
