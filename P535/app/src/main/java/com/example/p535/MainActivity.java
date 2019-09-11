package com.example.p535;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    LinearLayout linearLayout;
    HttpTask httpTask;
    ArrayList<Movie> list;
    MovieItemAdapter movieItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        linearLayout = findViewById(R.id.container);
        list = new ArrayList<>();
        getData();
    }

    public void getData(){
        String url ="http://70.12.60.108/webview/movieItem.jsp";
        httpTask = new HttpTask(url);
        httpTask.execute();
    }

    class HttpTask extends AsyncTask<String,Void,String>{
        String url;

        public HttpTask(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(String s) {
            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for(int i = 0; i < ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);

                    String title = jo.getString("title");
                    String actor = jo.getString("actor");
                    int rating = jo.getInt("rating");
                    String img =jo.getString("img");
                    list.add(new Movie(title,rating,actor,img));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            movieItemAdapter = new MovieItemAdapter(list);
            listView.setAdapter(movieItemAdapter);


        }

        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler.getString(url);
            return str;
        }
    }



    //Sub인가??
    class MovieItemAdapter extends BaseAdapter{
        ArrayList<Movie> alist ;

        public MovieItemAdapter( ArrayList<Movie> alist) {
            this.alist = alist;
        }


        @Override
        public int getCount() {
            return alist.size();
        }
        @Override
        public Object getItem(int i) {
            return i;
        }
        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myview = null;
            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = layoutInflater.inflate(R.layout.layout,linearLayout,true);

           final ImageView iv =myview.findViewById(R.id.imageView);
            TextView tx1 =myview.findViewById(R.id.textView);  //Title
            TextView tx2 =myview.findViewById(R.id.textView2);  // Actor
            RatingBar rt = myview.findViewById(R.id.ratingBar);

            String img =alist.get(i).getImg();
            img = "http://70.12.60.108/webview/"+img;

            final String temp = img ;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url =null;
                    InputStream is = null;

                    try {
                        url = new URL(temp);
                        is =url.openStream();
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
            tx1.setText(alist.get(i).getTitle());
            tx2.setText(alist.get(i).getActor());
            rt.setNumStars(5);
            rt.setRating((alist.get(i).getRating()));
            rt.setIsIndicator(true);

            return myview;
        }
    }
}
