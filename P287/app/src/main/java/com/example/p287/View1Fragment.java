package com.example.p287;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class View1Fragment extends Fragment {

    Button button4,button5,button1,button2,button3;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup =(ViewGroup)inflater.inflate(R.layout.fragment_view1, container, false);
        button4 = viewGroup.findViewById(R.id.button4);
        button5 = viewGroup.findViewById(R.id.button5);
        textView = viewGroup.findViewById(R.id.textView);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ma = new MainActivity();
                ma.setBt();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("View1 Fragment");
            }
        });

        return viewGroup;
    }

    public void setT(){
        textView.setText("Main");
    }

}
