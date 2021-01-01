/**
 * Represents the flight class
 */
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Game_Display.screenRatioX;

public class Game_Spaceship {

    boolean hasShot = false;
    int x, y, width, height;
    Bitmap flight, shoot;
    private Game_Display gameDisplay;

    // initializes the image and size for the spaceship,
    Game_Spaceship(Game_Display gameDisplay, int screenY, Resources res) {
        this.gameDisplay = gameDisplay;

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

    Bitmap getFlight () {
        return flight;
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
        //return new Rect(x, y - 2000, x + width, y + height);
    }
}
