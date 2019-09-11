package com.example.p427;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<item> list;
    ListView listView;
    LinearLayout container;
    ItemAdapter itemAdapter;
    HttpTask httpTask;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] permissions ={
                Manifest.permission.CALL_PHONE
        };
        ActivityCompat.requestPermissions(this, permissions,101);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        container = findViewById(R.id.container);
        listView.setOnItemClickListener(this);
        progressDialog = new ProgressDialog(this);
        list = new ArrayList<>();
    }

    public void getdata(){

//        list = new ArrayList<>();
////        list.add(new item("이경헌","070-1234-5671",R.drawable.p1));
//        list.add(new item("보근","070-1234-5672",R.drawable.p2));
//        list.add(new item("도형","070-1234-5673",R.drawable.p3));
//        list.add(new item("경헌","070-1234-5674",R.drawable.p4));
//        list.add(new item("김도형","070-1234-5675",R.drawable.p5));
//        list.add(new item("공유","070-1234-5676",R.drawable.p6));
//        list.add(new item("이동욱","070-1234-5677",R.drawable.p7));
//        list.add(new item("여진구","070-1234-5678",R.drawable.p8));
//        list.add(new item("김수현","070-1234-5679",R.drawable.p9));
//        list.add(new item("이종석","070-1234-5670",R.drawable.p10));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        item items = list.get(i);

        Toast.makeText(this, ""+items.getPhone(), Toast.LENGTH_SHORT).show();
        Intent intent = null;
        int check = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if(check == PackageManager.PERMISSION_GRANTED) {
            intent = new Intent(intent.ACTION_CALL, Uri.parse("tel:"+items.getPhone()));
            startActivity(intent);
        }else{
            Toast.makeText(this, "권한 부여가 안되어 있습니당", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void getDataWeb(){
        String url = "http://70.12.60.106/Server/item.jsp";
        httpTask = new HttpTask(url);
        httpTask.execute();
    }

    class HttpTask extends AsyncTask<String,Void,String> {
        String url;

        public HttpTask(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setTitle("Http Connecting");
            progressDialog.setMessage("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String string) {
            progressDialog.dismiss();
            Log.d("[JSON]",string);

            try {
                JSONArray ja = new JSONArray(string);
                for(int i =0 ; i<ja.length() ; i++){
                    JSONObject jo = ja.getJSONObject(i);

                    String name = jo.getString("name");
                    String phone = jo.getString("phone");
                    String img = jo.getString("img");
                    Log.d("[JO]","-------------------------"+name+phone+img);
                    list.add(new item(name,phone,img));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            itemAdapter = new ItemAdapter(list);
            listView.setAdapter(itemAdapter);

        }

        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler.getString(url);
            return str;
        }
    }
    //listview에 데이터를 곧바로 뿌릴 수 없다.
    class ItemAdapter extends BaseAdapter{
        ArrayList<item> alist ;

        public ItemAdapter( ArrayList<item> alist){
            this.alist = alist;
        }

        public void additem(item items){
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

        //data 개수만큼 getView 호출..
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           //각각의 셀의 화면을 만들어서 리턴해줘야함
            View myview = null;
            //View를 어떻게 만든다고 했나여  정답:LayoutInflater
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = inflater.inflate(R.layout.layout,container,true);

            //data를 화면에 넣어줘야함 .
           final ImageView iv =  myview.findViewById(R.id.imageView);
            TextView tx1 =  myview.findViewById(R.id.textView);
            TextView tx2 =  myview.findViewById(R.id.textView2);
            String img = alist.get(i).getImgId();
            img = "http://70.12.60.106/Server/"+img;

            final String temp = img;

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    InputStream is = null;

                    try {

                        url = new URL(temp);
                        is = url.openStream();
                        //Image를 서버에서 (is를 통해서) 가져와서 bm에 넣음.
                        final Bitmap bm = BitmapFactory.decodeStream(is);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(bm);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
//            iv.setImageResource(alist.get(i).getImgId());
            tx1.setText(alist.get(i).getName());
            tx2.setText(alist.get(i).getPhone());

            return myview;
        }
         //우리가 가져온 데이터를 여기다 넣고 listview에 붙일거임

    }

    public void clickbt(View view){
        getDataWeb();

        //가져온 데이터 list view 에다가 뿌리기
//        setContentView(R.layout.activity_main);
//        listView = findViewById(R.id.listview);
    }

    public void clickbt2(View view){
//        itemAdapter.additem(new item("이말숙","010-1234-5454",R.mipmap.ic_launcher));
//        itemAdapter.notifyDataSetChanged();
    }

    //phone call

}
