package com.example.p217;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Toast(View view){
        LayoutInflater inflater = getLayoutInflater();
        View tview = inflater.inflate(R.layout.toast,(ViewGroup)findViewById(R.id.tlayout));
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER,0,-100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(tview);
        toast.show();

//       Toast toast = Toast.makeText(this, "위치가 바뀐 토스트 메시지", Toast.LENGTH_LONG);
//       toast.setGravity(Gravity);
    }
    public void Snack(View view){
        // new version
    }
    public void Dialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("my dialog");
//        builder.setMessage("Exit?");
        builder.setIcon(R.drawable.dog1);
        // Dialog 내 버튼 추가 긍정/부정 버튼 기능정의.
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Continue", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "BYE", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



        AlertDialog dialog = builder.create();

        //
        LayoutInflater inflater = getLayoutInflater();
        View dview = inflater.inflate(R.layout.toast,(ViewGroup)findViewById(R.id.tlayout));
        TextView tv = dview.findViewById(R.id.textView);
        tv.setText("EXIT?????");

        builder.setView(dview);
        builder.show();
    }
    public void Progress(View view){

    }

}
