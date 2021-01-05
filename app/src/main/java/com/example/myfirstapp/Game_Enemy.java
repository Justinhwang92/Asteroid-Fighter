
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Game_Display.screenRatioX;
import static com.example.myfirstapp.Game_Display.screenRatioY;

public class Game_Enemy {

    public int speed = 8;
    public int bossLife = 5;
    // not necessary
   // public boolean wasShot = true;
    public boolean bossStageBegins = false;
    int x = 0, y, width, height, asteroidCounter = 1;
    Bitmap ship, destroyed;
    Bitmap boss, minion;
    public boolean crashed = false;
    public boolean isMinion;

    Game_Enemy(Resources res, boolean isMinion) {
        ship = BitmapFactory.decodeResource(res, R.drawable.one);
        destroyed = BitmapFactory.decodeResource(res, R.drawable.explosion);
        boss = BitmapFactory.decodeResource(res, R.drawable.boss3b4);
        minion = BitmapFactory.decodeResource(res, R.drawable.small_minion4);

        width = ship.getWidth() / 2;
        height = ship.getHeight() / 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        ship = Bitmap.createScaledBitmap(ship, width, height, false);

        y = -height;

        this.isMinion = isMinion;
    }
    Bitmap getAsteroid() {
        asteroidCounter = 1;
        if (crashed) {
            if (!bossStageBegins) {
                return destroyed;
            } else {
                x = x + 100;
                return boss;
            }
        }
        if (bossStageBegins) {
            if (isMinion) {
                return minion;
            }
            return boss;
        }
        return ship;
    }

    Rect getCollisionShape () {
        //return new Rect(x, y, x + width, y + height);
        return new Rect(x, y - 2000, x + width, y + 3000);
    }
}