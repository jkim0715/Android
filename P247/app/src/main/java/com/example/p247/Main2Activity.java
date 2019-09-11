package com.example.p247;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;
    int id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        id1 = intent.getIntExtra("id1",0);
        imageView.setImageResource(id1);

    }

    public void clickbt(View view){
        Intent intent = new Intent();
        intent.putExtra("dd",id1);
        setResult(RESULT_OK,intent);
        finish();


    }
}
