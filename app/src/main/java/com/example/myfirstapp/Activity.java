/*
 * this class represents the activities regarding the game.
 */
package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

public class Activity extends AppCompatActivity {
    private Display display;
    private MediaPlayer myConstantSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        display = new Display(this, point.x +5000, point.y);
        setContentView(display);

        myConstantSong = MediaPlayer.create(this, R.raw.constant_ibragame);
        myConstantSong.setLooping(true);
        MainActivity.myMenuPlayer.stop();
        myConstantSong.start();
    }

    public MediaPlayer getMyConstantSong()
    {
        return myConstantSong;
    }

    @Override
    protected void onPause() {
        super.onPause();
        display.donePlaying();
        myConstantSong.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        display.resume();
        myConstantSong.start();
    }
}