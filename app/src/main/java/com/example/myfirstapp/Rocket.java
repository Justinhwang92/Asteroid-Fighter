
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Display.screenRatioX;
import static com.example.myfirstapp.Display.screenRatioY;

public class Rocket {

    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, rocketCounter = 1;
    Bitmap ship;

    Rocket (Resources res) {
        ship = BitmapFactory.decodeResource(res, R.drawable.one);

        width = ship.getWidth();
        height = ship.getHeight();

        width /= 6;
        height /= 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        ship = Bitmap.createScaledBitmap(ship, width, height, false);

        y = -height;
    }

    Bitmap getRocket() {
        rocketCounter = 1;
        return ship;
    }
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
}
