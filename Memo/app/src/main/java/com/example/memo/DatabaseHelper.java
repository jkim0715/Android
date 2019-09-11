package com.example.memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//DB_TABLE을 생성하는 Helper class임..
public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "employee.db";
    public static int VERSION = 1;  //우리가 임의로 DB버전을 바꿀 수 있음.

    //DB를 사용하는 Mainactivity의 context
    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        println("onCreate 호출됨");

        //emp 라는 테이블 생성..
        String sql = "create table if not exists memo("
                + " num integer PRIMARY KEY autoincrement, "
                + " id text, "
                + " title text, "
                + " date text, "
                + " content text)";

        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db) {
        println("onOpen 호출됨");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion);

        //업그레이드 방식은.. 삭제 후 다시 만드는 방식으로 진행...
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    public void println(String data) {
        Log.d("DatabaseHelper", data);
    }
}
