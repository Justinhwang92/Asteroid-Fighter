/**
 * Represents the flight class
 */
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Display.screenRatioX;

public class Flight {

    int toShoot = 0;
    boolean isGoingUp = false;
    int x, y, width, height, shootCounter = 1;
    Bitmap flight, shoot;
    private Display display;

    // initializes the image and size for the spaceship,
    Flight (Display display, int screenY, Resources res) {
        this.display = display;

        flight = BitmapFactory.decodeResource(res, R.drawable.ufo);

        width = flight.getWidth();
        height = flight.getHeight();

        width /= 6;
        height /= 6;

        flight = Bitmap.createScaledBitmap(flight, width, height, false);

        shoot = BitmapFactory.decodeResource(res, R.drawable.ufo);
        shoot = Bitmap.createScaledBitmap(shoot, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

    // if we want to shoot manually
    Bitmap getFlight () {
        if (toShoot != 0) {
            shootCounter = 1;
            toShoot--;
            display.newBullet();
            return shoot;
        }
        return flight;
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
}
