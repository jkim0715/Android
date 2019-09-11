package com.example.p287;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    View1Fragment view1Fragment;
    View2Fragment view2Fragment;
    View3Fragment view3Fragment;
    Button button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view1Fragment = (View1Fragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        // new로 생성하면 Mainactivity가 fragment를 컨트롤 할 수 없음.
        //view1Fragment = new View1Fragment();
        view2Fragment = new View2Fragment();
        view3Fragment = new View3Fragment();
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


    }
    public void setBt(){

//        button1.setBackgroundColor(Color.BLUE);
//        button2.setBackgroundColor(Color.BLUE);
//        button3.setBackgroundColor(Color.BLUE);
        Log.i("*","-------------------------");
    }


    public void clickbt (View view){

        if(view.getId()== R.id.button){
            onFragmentChange(1);
        }else if(view.getId()== R.id.button2){
            onFragmentChange(2);
        }else if(view.getId()== R.id.button3){
            onFragmentChange(3);
        }
    }

    public void onFragmentChange(int index){
        if(index == 1){
            //화면 바꾸기(replace) fragment 자체는 못바꾸고 fragment를 담고있는 layout을 바꿀 수 있다.
            getSupportFragmentManager().beginTransaction().replace(R.id.container,view1Fragment).commit();
            view1Fragment.setT();
        }else if(index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,view2Fragment).commit();
        }else if(index == 3){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,view3Fragment).commit();
        }
    }



}
