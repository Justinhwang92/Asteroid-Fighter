
package ASTEROID_FIGHTER_FREE.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import ASTEROID_FIGHTER_FREE.R;

import static ASTEROID_FIGHTER_FREE.Game.Game_Display.screenRatioX;
import static ASTEROID_FIGHTER_FREE.Game.Game_Display.screenRatioY;

public class Game_Enemy {

    /**
     * speed of enemy
     */
    public int speed = 8;

    /**
     * amount of life boss has
     */
    public int bossLife = 7;

    /**
     * if the boss stage has begun or not
     */
    public boolean bossStageBegins = false;

    int x = 0, y, width, height, asteroidCounter = 1;

    /**
     * bitmaps for the asteroid and the explosion
     */
    Bitmap asteroid, asteroid_explosion, boss_explosion;

    /**
     * bitmaps for the boss and minions
     */
    Bitmap boss, minion;

    /**
     * boolean for if the enemy has collided with an object
     */
    public boolean crashed = false;

    /**
     * if the enemy is a minion
     */
    public boolean isMinion;

    public Game_Enemy(Resources res, boolean isMinion) {
        asteroid = BitmapFactory.decodeResource(res, R.drawable.asteroid);
        asteroid_explosion = BitmapFactory.decodeResource(res, R.drawable.asteroid_explosion);
        boss_explosion = BitmapFactory.decodeResource(res, R.drawable.boss_check2);
        boss = BitmapFactory.decodeResource(res, R.drawable.big_boss);
        minion = BitmapFactory.decodeResource(res, R.drawable.small_minion);

        width = asteroid.getWidth() / 2;
        height = asteroid.getHeight() / 6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        asteroid = Bitmap.createScaledBitmap(asteroid, width, height, false);

        y = -height;

        this.isMinion = isMinion;
    }

    public Bitmap getAsteroid() {
        asteroidCounter = 1;
        if (crashed) {
            if (!bossStageBegins) {
                return asteroid_explosion;
            } else {
                if(bossLife >= 1)
                    x = x + 120;
                else{
                    return boss_explosion;
                }
                return boss;
            }
        }
        if (bossStageBegins) {
            if (isMinion) {
                return minion;
            }
            return boss;
        }
        return asteroid;
    }

    //get hitbox
    public Rect getCollisionShape () {
        return new Rect(x, y - 2000, x + width, y + 3000);
    }
}