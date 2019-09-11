package com.example.p436;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> list;
    ArrayList<Integer> list2;
    Spinner spinner,spinner2;
    ImageView imageView;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //spinner 안에는 사진같은거 넣지 못함.
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        imageView =findViewById(R.id.imageView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setStepSize(1);
        ratingBar.setMax(5);
        ratingBar.setRating(0);
        ratingBar.setNumStars(5);

        getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,list
        );

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item,list2
        );

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner에다 adapter 붙이기
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        //spinner에 selected 가 일어나면 내가 처리하게따
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        //Ask permission
        String [] permissions = {
                Manifest.permission.CALL_PHONE
        };
        ActivityCompat.requestPermissions(this, permissions,101);

    }
    public void getData(){
        list = new ArrayList<>();
        list.add("01012345671");
        list.add("01012345672");
        list.add("01012345673");
        list.add("01012345674");
        list.add("01012345675");

        list2 = new ArrayList<>();
        list2.add(R.drawable.p10);
        list2.add(R.drawable.p9);
        list2.add(R.drawable.p8);
        list2.add(R.drawable.p7);
        list2.add(R.drawable.p2);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       String str =  list.get(i);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
//
//        Intent intent = null;
//        int check = PermissionChecker.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
//        if(check == PermissionChecker.PERMISSION_GRANTED) {
//            intent = new Intent(intent.ACTION_CALL, Uri.parse("tel:"+str));
//            startActivity(intent);
//        }else{
//            Toast.makeText(this, "권한 부여가 안되어 있습니당", Toast.LENGTH_SHORT).show();
//            return;
//        }

        int imgcode = list2.get(i);
        imageView.setImageResource(imgcode);
        float temp = ratingBar.getRating()+1;
        ratingBar.setRating(temp);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
