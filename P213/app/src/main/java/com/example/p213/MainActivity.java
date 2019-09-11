package com.example.p213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 환경정보 저장 (App을 꺼도 남아있음)
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickbt(View view){


        if(view.getId() == R.id.button){
            //save
            //MODE_PRIVATE: App에서 설정한것들은 나만 보겠다는 의미
            sp = getSharedPreferences("ma",MODE_PRIVATE);
            SharedPreferences.Editor editor =sp.edit();
            editor.putString("key01","id01");
            editor.commit();
        }else if(view.getId() == R.id.button2){
            //load
            sp = getSharedPreferences("ma",MODE_PRIVATE);
            //sp의 argument가 2개있으니 채워줘야함.... 뒤에값은 default값이라고 생각하면 됨
            String id = sp.getString("key01","default");
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        }


    }
}
