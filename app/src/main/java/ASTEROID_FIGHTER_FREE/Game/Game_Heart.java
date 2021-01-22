package ASTEROID_FIGHTER_FREE.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ASTEROID_FIGHTER_FREE.R;

import static ASTEROID_FIGHTER_FREE.Game.Game_Display.screenRatioX;

/**
 * drawing of life/heart
 */
public class Game_Heart {
    int x, y, width, height;
    Bitmap heart;
    public int lives;

    //initializes and decodes resources
    public Game_Heart(int screenY, Resources res) {
        lives = 3;
        heart = BitmapFactory.decodeResource(res, R.drawable.game_heart);
        width = heart.getWidth();
        height = heart.getHeight();

        width /= 6;
        height /= 6;

        heart = Bitmap.createScaledBitmap(heart, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }
}
