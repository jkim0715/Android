package com.example.p169;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContent = findViewById(R.id.mContent);
        mByte = findViewById(R.id.mByte);
        mContent.addTextChangedListener(editEvent);
    }

    EditText mContent;
    EditText mByte;


    private TextWatcher editEvent = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(mContent.isFocusable())
            {
                try
                {
                    byte[] bytetext = mContent.getText().toString().getBytes("KSC5601");
                    mByte.setText(Integer.toString(bytetext.length)+" Byte"+ "/80 Byte");
                }catch(Exception ex){}
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            String after_text = s.toString();
            try
            {
                byte[] getbyte = after_text.getBytes("KSC5601");
                if(getbyte.length > 80)
                {
                    s.delete(s.length()-2, s.length()-1);
                }
            }catch (Exception e) {}
        }
    };


}
