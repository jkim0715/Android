package com.example.p247;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    public void clickbt1(View view){
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        if(view.getId() == R.id.button) {
            intent.putExtra("id1", R.drawable.dog1);
        }else if(view.getId() == R.id.button2){
            intent.putExtra("id1", R.drawable.dog2);
        }else if(view.getId() == R.id.button3){
            intent.putExtra("id1", R.drawable.dog3);
        }
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode == RESULT_OK){
            int dd = data.getIntExtra("dd",0);
            textView.setText(dd);

        }

    }
}
