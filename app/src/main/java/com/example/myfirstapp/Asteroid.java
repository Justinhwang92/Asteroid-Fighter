
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Display.screenRatioX;
import static com.example.myfirstapp.Display.screenRatioY;

public class Asteroid {

    public int speed = 8;
    public boolean wasShot = true;
    int x = 0, y, width, height, asteroidCounter = 1;
    Bitmap ship;

    Asteroid (Resources res) {
        ship = BitmapFactory.decodeResource(res, R.drawable.one);
        width = ship.getWidth() / 2;
        height = ship.getHeight() / 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        ship = Bitmap.createScaledBitmap(ship, width, height, false);

        y = -height;
    }

    Bitmap getAsteroid() {
        asteroidCounter = 1;
        return ship;
    }
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
}