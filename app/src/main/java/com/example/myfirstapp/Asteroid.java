
package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.myfirstapp.Display.screenRatioX;
import static com.example.myfirstapp.Display.screenRatioY;

public class Asteroid {

    public int speed = 8;
    public int bossLife = 5;
    // not necessary
   // public boolean wasShot = true;
    public boolean bossStageBegins = false;
    int x = 0, y, width, height, asteroidCounter = 1;
    Bitmap ship, destroyed;
    Bitmap boss, boss2, boss3, boss4, boss5, boss6;
    public boolean crashed = false;

    Asteroid (Resources res) {
        ship = BitmapFactory.decodeResource(res, R.drawable.one);
        destroyed = BitmapFactory.decodeResource(res, R.drawable.explosion);

        boss = BitmapFactory.decodeResource(res, R.drawable.boss3a);
        boss2 = BitmapFactory.decodeResource(res, R.drawable.boss3b2);
        boss3 = BitmapFactory.decodeResource(res, R.drawable.boss3b3);
        boss4 = BitmapFactory.decodeResource(res, R.drawable.boss3b4);
        boss5 = BitmapFactory.decodeResource(res, R.drawable.boss3b5);
        boss6 = BitmapFactory.decodeResource(res, R.drawable.boss3b6);

        width = ship.getWidth() / 2;
        height = ship.getHeight() / 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        ship = Bitmap.createScaledBitmap(ship, width, height, false);

        y = -height;
    }
    Bitmap getAsteroid() {
        asteroidCounter = 1;
        if (crashed) {
            if (!bossStageBegins) {
                return destroyed;
            } else {
                x = x + 100;
                //return boss;
                return correspondingBossSize();
            }
        }
        if (bossStageBegins) {
            return correspondingBossSize();
        }
        return ship;
    }

    public Bitmap correspondingBossSize() {
        if (bossLife == 5) {
            return boss2;
        } else if (bossLife == 4){
            return boss3;
        } else if (bossLife == 3) {
            return boss4;
        } else if (bossLife == 2) {
            return boss5;
        } else if (bossLife == 2) {
            return boss6;
        }
        return boss6;
    }

    Rect getCollisionShape () {
        //return new Rect(x, y, x + width, y + height);
        return new Rect(x, y - 2000, x + width, y + 3000);
    }
}