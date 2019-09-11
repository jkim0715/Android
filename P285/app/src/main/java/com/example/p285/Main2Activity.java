package com.example.p285;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void clickbt(View view){
        if(view.getId() == R.id.button3){
            Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
            startActivityForResult(intent,101);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode == RESULT_OK){
            String dd = data.getStringExtra("loc");
            Toast.makeText(this, dd, Toast.LENGTH_SHORT).show();
        }else if(requestCode==101 && resultCode == RESULT_OK){
            finish();
        }
    }
}
