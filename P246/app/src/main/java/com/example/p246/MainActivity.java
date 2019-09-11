package com.example.p246;
//여러화면 만들고 화면 간 전환하기
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //클릭시 다음 activity로 이동.
    public void clickbt(View view){
        //매개체 App과 App간의 연동
        // Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        //intent에 값을 담아서 보낼때
        intent.putExtra("num",100);
        intent.putExtra("str","HI,JY");
        startActivity(intent);
    }
}
