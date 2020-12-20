package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Display.screenRatioX;
import static com.example.myfirstapp.Display.screenRatioY;

public class Boss {
    private boolean isHit;
    private int xLocation, yLocation;
    private int height, width;
    private int speed;

    Boss (Resources res) {

    }

    Rect getCollisionShape () {
        return null;
    }

}
