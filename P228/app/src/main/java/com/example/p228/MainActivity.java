package com.example.p228;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar2);
    }

    //progress bar 증가/ 감소 시키기 (빨간색 바)
    public void bar(View view){
        if(view.getId() == R.id.button){
          progressBar.setProgress(progressBar.getProgress()+10);

        }else if(view.getId() == R.id.button2){
            progressBar.setProgress(progressBar.getProgress()-10);
        }
    }

    public void dialog(View view){
        if(view.getId() == R.id.button3){
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Progress....");
            progressDialog.setCancelable(false);





            progressDialog.setButton(progressDialog.BUTTON_NEGATIVE, "end", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog.dismiss();
                }
            });



            progressDialog.show();
        }else if(view.getId() == R.id.button4){
            progressDialog.dismiss();
       }
    }


}
