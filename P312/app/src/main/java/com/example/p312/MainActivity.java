package com.example.p312;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.dog1);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //xml가져올려면 다 inflator를 이용한다 (main 제외)
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }
    //Menu에 action 붙이기


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.msetting){
            Toast.makeText(this, "Bonobono", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.mlogin){
            Toast.makeText(this, "Porori", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
