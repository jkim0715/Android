package com.example.p641;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    int position =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void releaseMedia(){
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
    public void play(View view){
        releaseMedia();
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.kalimba);
//        try {
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mediaPlayer.start();

    }
    public void stop(View view){
        mediaPlayer.stop();
    }
    public void pause(View view){

    }
    public void resume(View view){

    }
}
