package com.example.p351;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    //textview 에 날짜와 시간 넣어보기
    TextView textView;
    CalendarView calendarView;
    TimePicker timePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i+"/" +i1 +"/"+i2;
                textView.setText(date);
            }

        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String time = i+":"+i1;
                textView.setText(time);
            }
        });



    }
}

