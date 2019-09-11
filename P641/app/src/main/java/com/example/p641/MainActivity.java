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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            releaseMedia();
        }
    }

    public void releaseMedia(){
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
    public void playAudio(){
        releaseMedia();
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.kalimba);
        mediaPlayer.start();

    }
    public void play(View view){
    playAudio();
    }
    public void stop(View view){
        mediaPlayer.stop();
    }
    public void pause(View view){

    }
    public void resume(View view){

    }
}
