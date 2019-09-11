package com.example.p285;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Main4Activity extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void clickbt(View view){

        if(view.getId() == R.id.button5){
            Intent intent = new Intent();
            intent.putExtra("loc","매출관리");
            setResult(RESULT_OK,intent);
            finish();
        }else if(view.getId() == R.id.button6){
            sp = getSharedPreferences("ma",MODE_PRIVATE);
            SharedPreferences.Editor editor =sp.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent();
            intent.putExtra("loc","매출관리");
            setResult(RESULT_OK,intent);
            finish();
        }
    }

}
