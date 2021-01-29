
package ASTEROID_FIGHTER_FREE.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

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
    public int bossLife = 4;

    /**
     * if the boss stage has begun or not
     */
    public boolean bossStageBegins = false;

    int x = 0, y, width, height, asteroidCounter = 1;

    int ScreenWidth, ScreenHeight;
    boolean isEndlessMode = false;

    /**
     * bitmaps for the asteroid and the explosion
     */
    Bitmap asteroid, asteroid_explosion, boss_explosion;

    Bitmap asteroid2, asteroid3, asteroid4;
    /**
     * bitmaps for the boss and minions
     */
    Bitmap boss, minion;

    int choice;

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
        asteroid2 = BitmapFactory.decodeResource(res, R.drawable.ast2);
        asteroid3 = BitmapFactory.decodeResource(res, R.drawable.asteroid3);
        asteroid4 = BitmapFactory.decodeResource(res, R.drawable.at4);
        asteroid_explosion = BitmapFactory.decodeResource(res, R.drawable.asteroid_explosion);
        boss_explosion = BitmapFactory.decodeResource(res, R.drawable.boss_check2);
        boss = BitmapFactory.decodeResource(res, R.drawable.big_boss);
        minion = BitmapFactory.decodeResource(res, R.drawable.small_minion);


            /*
            Asteroid 2
            */
            width = asteroid2.getWidth() ;
            height = asteroid2.getHeight()/2;

            //width is same for all, height /3 for asteroid 4 and for others /2
            width = (int) (width * screenRatioX);
            height = (int) (height * screenRatioY)/2;
            asteroid2 = Bitmap.createScaledBitmap(asteroid2, width, height, false);

            /*
            Asteroid 1 - Original
             */
            //For original bitmap of asteroid
            //will keep the same margin and size as below
            width = asteroid.getWidth() / 2;
            height = asteroid.getHeight() / 6;

            width = (int) (width * screenRatioX);
            height = (int) (height * screenRatioY);

            asteroid = Bitmap.createScaledBitmap(asteroid, width, height, false);

            /*
            Asteroid 3
             */
            width = asteroid3.getWidth() ;
            height = asteroid3.getHeight();

            //width is same for all, height /3 for asteroid 4 and for others /2
            width = (int) (width * screenRatioX);
            height = (int) (height * screenRatioY)/3;
            asteroid3 = Bitmap.createScaledBitmap(asteroid3, width, height, false);

            /*
            Asteroid 4
             */
            width = asteroid4.getWidth() ;
            height = asteroid4.getHeight();

            //width is same for all, height /3 for asteroid 4 and for others /2
            width = (int) (width * screenRatioX);
            height = (int) (height * screenRatioY)/2;

            asteroid4 = Bitmap.createScaledBitmap(asteroid4, width, height, false);


        y = -height;

        this.isMinion = isMinion;

        choice = (new Random()).nextInt(4);


//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
    }

    public Bitmap getAsteroid() {

        //To get the height and width of the running screen
//        DisplayMetrics displayMetrics = new DisplayMetrics();
////        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int widthScreen = displayMetrics.widthPixels;

        asteroidCounter = 1;
        if (crashed) {
            if (!bossStageBegins) {
                choice = (new Random()).nextInt(4);
                return asteroid_explosion;
            } else {
                if(bossLife >= 1) {
//                    x = x + 120;

//                    System.out.println(x + boss.getWidth() + " screenW : " + ScreenWidth);
                    //Inside the screen
                    if(x + boss.getWidth() < ScreenWidth){
//                        System.out.println("1. " + x+120);
                        if(x+120 > ScreenWidth){
                            x = ScreenWidth - boss.getWidth();
                        }else{
                            x = x + 120;
                        }
                    }
                    else{
                        x = x + 120;
                    }
                }
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


        if(isEndlessMode) {
            if (choice == 0)
                return asteroid;
            else if (choice == 1)
                return asteroid2;
            else if (choice == 2)
                return asteroid3;
            else
                return asteroid4;
        }

        return asteroid;

    }

    //get hitbox
    public Rect getCollisionShape () {
        return new Rect(x, y - 2000, x + width, y + 3000);
    }
}