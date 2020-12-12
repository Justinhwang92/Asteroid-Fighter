package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.example.myfirstapp.Display.screenRatioX;

public class Heart {
    int x, y, width, height;
    Bitmap heart;
    private Display display;
    public static int lives;

    Heart(Display display, int screenY, Resources res) {
        lives = 3;
        this.display = display;
        heart = BitmapFactory.decodeResource(res, R.drawable.heart3);
        width = heart.getWidth();
        height = heart.getHeight();

        width /= 6;
        height /= 6;

        heart = Bitmap.createScaledBitmap(heart, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

}
