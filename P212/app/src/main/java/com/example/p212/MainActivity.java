package com.example.p212;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    String appID;
    EditText editText;
    Button button,button2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =findViewById(R.id.editText);
        button =findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        //
        //번들을 써야하기 때문에 안에다가 만들어야함
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editText.getText().toString();
                savedInstanceState.putString("Save",data);
            }
        });
        //
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = null;

                if(savedInstanceState != null) {
                  data = savedInstanceState.getString("ID");

                }else{
                    data ="Empty";
                }
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();

            }
        });
        //
    }

    //번들에 설정 저장 (번들은 앱이 살아있는 동안만 저장, 맨처음에는 비어있음)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
        outState.putString("ID","id01");
    }



}
