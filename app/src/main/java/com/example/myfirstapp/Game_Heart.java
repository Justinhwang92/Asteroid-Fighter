package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.example.myfirstapp.Game_Display.screenRatioX;

public class Game_Heart {
    int x, y, width, height;
    Bitmap heart;
    private Game_Display gameDisplay;
    public int lives;

    Game_Heart(Game_Display gameDisplay, int screenY, Resources res) {
        lives = 3;
        this.gameDisplay = gameDisplay;
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
