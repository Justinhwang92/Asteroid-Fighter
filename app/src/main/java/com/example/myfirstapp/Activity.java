/**
 * this class represents the activities regarding the game.
 */
package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

public class Activity extends AppCompatActivity {
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        display = new Display(this, point.x, point.y);
        setContentView(display);
    }

    @Override
    protected void onPause() {
        super.onPause();
        display.donePlaying();
    }

    @Override
    protected void onResume() {
        super.onResume();
        display.resume();
    }
}