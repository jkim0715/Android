package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    ListView listView;
    ArrayList<Item> list;
    LinearLayout listcontainer;
    MemoAdapter memoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView = findViewById(R.id.listview);
        // ArrayList에 Item  정보 넣기..
        Intent intent = getIntent();
        list = (ArrayList<Item>) intent.getSerializableExtra("list");
        memoAdapter = new MemoAdapter(list);
        listView.setAdapter(memoAdapter);
    }
    class MemoAdapter extends BaseAdapter{
        ArrayList<Item> alist;

        public MemoAdapter(ArrayList<Item> alist){
            this.alist = alist;
        }
        @Override
        public int getCount() {
            return  alist.size();
        }
        @Override
        public Object getItem(int i) {
            return alist.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           Log.i("tttttt",alist.get(i).title);
            Log.i("tttttt",alist.get(i).date);
            Log.i("tttttt",alist.get(i).content);
            View myview = null;
            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = layoutInflater.inflate(R.layout.layout,listcontainer,true);
            TextView tx4 =findViewById(R.id.textView4);
            TextView tx5 =findViewById(R.id.textView5);
            TextView tx6 = findViewById(R.id.textView6);
//            tx4.setText(alist.get(i).num+"");
            tx4.setText(alist.get(i).title+"");
            tx5.setText(alist.get(i).date+"");
            tx6.setText(alist.get(i).content+"");
            return myview;
        }
    }
}
