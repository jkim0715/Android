package com.example.p158;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    ConstraintLayout login_Layer,register_Layer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();

    }

    private void setUI() {
        img = findViewById(R.id.img);
        login_Layer =findViewById(R.id.login_Layer);
        register_Layer =findViewById(R.id.register_Layer);

    }

    public void disable(){
        login_Layer.setVisibility(View.INVISIBLE);
        register_Layer.setVisibility(View.INVISIBLE);
    }

    public void clickBt(View view){
        if(view.getId() == R.id.bt1){
            disable();
            img.setImageResource(R.drawable.dog1);
        }else if(view.getId() == R.id.bt2){
            disable();
            img.setImageResource(R.drawable.dog2);
        }else if(view.getId() == R.id.bt3){
            login_Layer.setVisibility(View.VISIBLE);
            register_Layer.setVisibility(View.INVISIBLE);
        }else if(view.getId() == R.id.bt4){
            login_Layer.setVisibility(View.INVISIBLE);
            register_Layer.setVisibility(View.VISIBLE);
        }
    }
}
