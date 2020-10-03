
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.GameView.screenRatioX;
import static com.example.myfirstapp.GameView.screenRatioY;

public class Rocket {

    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, birdCounter = 1;
    Bitmap bird1, bird2, bird3, bird4;

    Rocket (Resources res) {
        bird4 = BitmapFactory.decodeResource(res, R.drawable.one);

        width = bird4.getWidth();
        height = bird4.getHeight();

//        width /= 6;
//        height /= 6;
        width /= 6;
        height /= 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird4 = Bitmap.createScaledBitmap(bird4, width, height, false);

        y = -height;
    }

    Bitmap getBird () {
        birdCounter = 1;
        return bird4;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}