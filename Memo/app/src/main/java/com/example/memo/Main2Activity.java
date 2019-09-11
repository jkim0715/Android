package com.example.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    TextView textView, textView0;
    EditText editText0, editText4;
    ArrayList<Item> arrayList;

    //DB와 TABLE을 만드는 역할 : dbHelper
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        editText0 = findViewById(R.id.edittext0);
        textView0 = findViewById(R.id.Textview0);
        editText4 = findViewById(R.id.editText4);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        System.out.println(id);
        textView.setText(id);

        // 이때 DB 생성.
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(database,1,1);
        dbHelper.onCreate(database);

    }


    public void clickCalinder(View view) {
        Intent gotocal = new Intent(Main2Activity.this, Main3Activity.class);
        startActivityForResult(gotocal, 101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String date = data.getStringExtra("date");
        textView0.setText(date);
    }

    public void savamemo(View view) {
        String id = textView.getText().toString();
        String title = editText0.getText().toString();
        String date = textView0.getText().toString();
        String content = editText4.getText().toString();
        database.execSQL("insert into " + "memo"
                + "(id,title,date,content) "
                + " values "
                + "('" + id + "','" + title + "','" + date + "','" + content + "')");
    }

    public void list(View view) {
        Cursor cursor = database.rawQuery("select title,date,content from memo", null);
        int recordCount = cursor.getCount();
        arrayList = new ArrayList<>();
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
//            int num = cursor.getInt(0);

            String title = cursor.getString(0);
         
            String date = cursor.getString(1);
            String content = cursor.getString(2);

            arrayList.add(new Item(title,date, content));
        }
        Intent intent = new Intent(this, Main4Activity.class);
        intent.putExtra("list", arrayList);
        startActivityForResult(intent,109);
        //should be closed by using finally.
        cursor.close();
    }
}
