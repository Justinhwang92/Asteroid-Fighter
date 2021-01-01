
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Game_Display.screenRatioX;
import static com.example.myfirstapp.Game_Display.screenRatioY;

public class Game_Bullet {

    int x, y, width, height;
    Bitmap bullet;
    int speed = 150;

    Game_Bullet(Resources res) {
        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet2);

        width = bullet.getWidth() / 1;
        height = bullet.getHeight() / 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bullet = Bitmap.createScaledBitmap(bullet, width, height, false);
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

    Bitmap getBullet() {
        return bullet;
    }

}
