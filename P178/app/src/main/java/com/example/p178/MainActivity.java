package com.example.p178;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


//Button Listener 처리 add implements
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt;
    RadioButton radioButton,radioButton2;
    CheckBox checkBox,checkBox2;
    Switch switch1;
    ToggleButton toggleButton;

    EditText editText,editText2,editText3,editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.button);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        switch1 = findViewById(R.id.switch1);
        toggleButton = findViewById(R.id.toggleButton);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        //focus 맞추기 true/false
        editText3.setFocusable(false);

        //edittext에 focusevent 주기
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                //focus가 됐다면
                if(b == true){
                    editText.setHint("Multicampus");
                }else {
                    editText.setHint("");
                }
            }
        });


        //스위치가 이벤트를 처리하는 listener(), 괄호 안에서 이벤트를 처리하겠다는 말임
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    bt.setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Switch", Toast.LENGTH_SHORT).show();
                }else{
                    bt.setBackgroundColor(Color.BLUE);
                }
            }
        });

        //토글 버튼
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    bt.setBackgroundColor(Color.RED);
                }else{
                    bt.setBackgroundColor(Color.BLUE);
                }
            }
        });
        //button에 Onclick 처리 this : main activity,
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Click:"+ checkBox.isChecked()+radioButton.isChecked(), Toast.LENGTH_SHORT).show();
        checkBox.isChecked();

    }

}
