/**
 * Represents the flight class
 */
package ASTEROID_FIGHTER_FREE.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import ASTEROID_FIGHTER_FREE.R;

import static ASTEROID_FIGHTER_FREE.Game.Game_Display.screenRatioX;

/**
 * spaceship bitmap
 */
public class Game_Spaceship {

    //self explanatory fields
    public boolean hasShot = false;
    int x, y, width, height;
    Bitmap spaceship, laser;
    private Game_Display gameDisplay;

    // initializes the image and size for the spaceship,
    Game_Spaceship(Game_Display gameDisplay, int screenY, Resources res) {
        this.gameDisplay = gameDisplay;

        spaceship = BitmapFactory.decodeResource(res, R.drawable.ufo);

        width = spaceship.getWidth();
        height = spaceship.getHeight();

        width /= 6;
        height /= 6;

        spaceship = Bitmap.createScaledBitmap(spaceship, width, height, false);

        laser = BitmapFactory.decodeResource(res, R.drawable.ufo);
        laser = Bitmap.createScaledBitmap(laser, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

    public Bitmap getFlight () {
        return spaceship;
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
}
