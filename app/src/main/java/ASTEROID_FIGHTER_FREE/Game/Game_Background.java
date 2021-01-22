package ASTEROID_FIGHTER_FREE.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ASTEROID_FIGHTER_FREE.R;

/**
 * extracts and provides background image
 */
public class Game_Background {

    int x = 0, y = 0;
    //stores background bitmap image
    Bitmap background;

    /**
     * make the background - decode then make
     * @param screenX screen's x dimension
     * @param screenY screen's y dimension
     * @param res resources used to decode image in raw
     */
    public Game_Background(int screenX, int screenY, Resources res) {
        //extracts and decodes image to put into game
        background = BitmapFactory.decodeResource(res, R.drawable.game_background2);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}
