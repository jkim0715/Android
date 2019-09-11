package com.example.p168;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView img1;
    ImageView img2;

    LinearLayout topLayer,midLayer,botLayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI() {
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        topLayer = findViewById(R.id.topLayer);
        midLayer = findViewById(R.id.midLayer);
        botLayer = findViewById(R.id.botLayer);

        img1.setImageResource(R.drawable.dog1);
        img2.setImageResource(R.drawable.dog2);
    }
    public void clickbt(View view){
        if(view.getId() == R.id.bt2 ){
        img1.setImageResource(R.drawable.dog2);
        img2.setImageDrawable(null);
    }else if(view.getId() == R.id.bt1){
            img2.setImageResource(R.drawable.dog2);
            img1.setImageDrawable(null);
        }
    }


}
