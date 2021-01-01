package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Game_Background {

    int x = 0, y = 0;
    Bitmap background;

    /**
     * make the background - decode then make
     * @param screenX screen's x dimension
     * @param screenY screen's y dimension
     * @param res resources used to decode image in raw
     */
    Game_Background(int screenX, int screenY, Resources res) {
        //background = BitmapFactory.decodeResource(res, R.drawable.panorama);
        background = BitmapFactory.decodeResource(res, R.drawable.panoramav3);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}
