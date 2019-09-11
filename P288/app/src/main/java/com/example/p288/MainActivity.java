package com.example.p288;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    View1Fragment view1Fragment;
    View2Fragment view2Fragment;

    Button button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1Fragment = (View1Fragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        view2Fragment = new View2Fragment();

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
    }

    public void clickbt (View view){

        if(view.getId()== R.id.button){

            onFragmentChange(1);
        }else if(view.getId()== R.id.button2){
            onFragmentChange(2);
        }
    }

    public void onFragmentChange(int index){
        if(index == 1){

            //화면 바꾸기(replace) fragment 자체는 못바꾸고 fragment를 담고있는 layout을 바꿀 수 있다.
            getSupportFragmentManager().beginTransaction().replace(R.id.container,view1Fragment).commit();
        }else if(index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,view2Fragment).commit();
        }
    }


}
