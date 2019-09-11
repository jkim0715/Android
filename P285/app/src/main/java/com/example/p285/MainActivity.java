package com.example.p285;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2;
    CheckBox checkBox;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        checkBox = findViewById(R.id.checkBox);
        sp = getSharedPreferences("ma",MODE_PRIVATE);
        if(sp.contains("id01")){
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            startActivityForResult(intent,100);
        }
    }

    public void clickbt(View view){
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        sp = getSharedPreferences("ma",MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        //조건 ID= id01  // PWD = 1111
        System.out.println(editText1.getText()+" "+editText2.getText());
       if(editText1.getText().toString().equals("id01") && editText2.getText().toString().equals("1111")){
            //화면전환
           if(checkBox.isChecked()){
               editor.putString("id01","1111");
               editor.commit();
           }else{
               editor.clear();
               editor.commit();
           }
             startActivityForResult(intent,100);
        }else{
            Toast.makeText(this, "Wrong LogIn Info", Toast.LENGTH_SHORT).show();
        }
    }

}
