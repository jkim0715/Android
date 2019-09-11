package com.example.memo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class Main3Activity extends AppCompatActivity {
    CalendarView calendarView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        calendarView =findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
               intent = new Intent();
                System.out.println("calinder="+i);
                String date = i +"년" +i1 +"월"+i2+"일";
                intent.putExtra("date",date);
                setResult(RESULT_OK,intent);

            }
        });
    }

    public void clicksubmit(View view){
        finish();
    }
}
