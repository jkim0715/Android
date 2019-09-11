package com.example.p246;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        //intent로 보낸 값은 intent에서 끄집어 낸다.
        Intent intent = getIntent();
        // getintExtra : int값 설정한거 받아오기
        int num = intent.getIntExtra("num",0);
        // getStringExtra : String 값 저장한거 가져오기
        String str = intent.getStringExtra("str");
        //textview에 텍스트 뿌리기
        textView.setText(num+" "+str);
    }

    public void clickbt(View view){
        finish();
    }


    // 누르면 Main3으로 화면전환
    public void clickbt2(View view){
        Intent intent = new Intent(getApplicationContext(),Main3Activity.class
        );
        //requestCode는 100으로 설정  Main 3으로 갔다가 돌아올때 필요한 번호 (값을 가지고 올 수 있음)
        startActivityForResult(intent,100);

    }

    // 먼길 갔다온 친구(requestCode 100 가져간 친구)가 가져온 값을 받는 부분
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            int nn = data.getIntExtra("nm",0);
            String st = data.getStringExtra("st");
            textView2.setText(nn+" "+st);
        }

    }
}
