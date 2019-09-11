package com.example.p440;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ArrayList<Item> list;
    ArrayList<Integer> slist;
    ListView listView ;
    LinearLayout container;
    ItemAdapter itemAdapter;
    Spinner spinner;
    EditText editText,editText2;
    int imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        container = findViewById(R.id.container);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        spinner = findViewById(R.id.spinner);
        //리스트 뷰
        listView.setOnItemClickListener(this);



       // 스피너
        getData();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item,slist
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner에다 adapter 붙이기
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //give permission
        String [] permissions = {
                Manifest.permission.CALL_PHONE
        };
        ActivityCompat.requestPermissions(this, permissions,101);

    }




    public void getData(){
        list = new ArrayList<>();

        slist = new ArrayList<>();
        slist.add(R.drawable.p10);
        slist.add(R.drawable.p9);
        slist.add(R.drawable.p8);
        slist.add(R.drawable.p7);
        slist.add(R.drawable.p6);
}


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String str =  list.get(i).getPhone();
        Intent intent = null;
        int check = PermissionChecker.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
        if(check == PermissionChecker.PERMISSION_GRANTED) {
            intent = new Intent(intent.ACTION_CALL, Uri.parse("tel:"+str));
            startActivity(intent);
        }else{
            Toast.makeText(this, "권한 부여가 안되어 있습니당", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        imageId = slist.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    class ItemAdapter extends BaseAdapter{
        ArrayList<Item> alist ;

        public ItemAdapter( ArrayList<Item> alist){
            this.alist = alist;

        }

        public void additem(Item items){
            alist.add(items);
            list = alist;
        }

        @Override
        public int getCount() {
            return alist.size();
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
            //각각의 셀의 화면을 만들어서 리턴해줘야함
            View myview = null;
            //View를 어떻게 만든다고 했나여  정답:LayoutInflater
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = inflater.inflate(R.layout.layout,container,true);

            //data를 화면에 넣어줘야함 .
            ImageView iv =  myview.findViewById(R.id.imageView);
            TextView tx1 =  myview.findViewById(R.id.textView);
            TextView tx2 =  myview.findViewById(R.id.textView2);

            iv.setImageResource(alist.get(i).getImgId());

            tx1.setText(alist.get(i).getName());
            tx2.setText(alist.get(i).getPhone());

            return myview;
        }


    }
    public void clickbt(View view){
        String nm = String.valueOf(editText.getText());
        String num = String.valueOf(editText2.getText());

        System.out.println(nm);
//        String num = String.valueOf(editText2.getText());
//        System.out.println(num);

        itemAdapter = new ItemAdapter(list);
        itemAdapter.additem(new Item(nm,num,imageId));
        itemAdapter.notifyDataSetChanged();
        listView.setAdapter(itemAdapter);

    }
}
