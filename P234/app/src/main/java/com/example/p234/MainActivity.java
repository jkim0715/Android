package com.example.p234;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    ProgressBar progressBar;
    RadioButton rb1,rb2,rb3;
    EditText editText;
    ImageView imageView;
    ProgressDialog  progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);

    }

    public void clickbt(View view){
        if(view.getId() == R.id.button){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LayoutInflater inflater = getLayoutInflater();
            final View oview= inflater.inflate(R.layout.choice, (ViewGroup) findViewById(R.id.choose));

            builder.setView(oview);
            builder.setPositiveButton("Click", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    rb1 = oview.findViewById(R.id.rb1);
                    rb2 = oview.findViewById(R.id.rb2);
                    rb3 = oview.findViewById(R.id.rb3);

                    if(rb1.isChecked() == true){
                        imageView.setImageResource(R.drawable.dog1);
                    }else if(rb2.isChecked() == true){
                        imageView.setImageResource(R.drawable.dog2);
                    }else if (rb3.isChecked() == true){
                        imageView.setImageResource(R.drawable.dog3);
                    }else{
                        imageView.setImageResource(R.drawable.ic_launcher_foreground);
                    }

                    editText = oview.findViewById(R.id.editText);
                    String num = editText.getText().toString();

                    if(num.equals("")){
                        progressBar.setProgress(0);
                    }else{
                        progressBar.setProgress(Integer.parseInt(num));
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();
        }

    }




}
