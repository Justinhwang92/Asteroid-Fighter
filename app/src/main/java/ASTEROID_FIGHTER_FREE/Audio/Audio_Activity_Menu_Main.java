package ASTEROID_FIGHTER_FREE.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import ASTEROID_FIGHTER_FREE.R;

public class Audio_Activity_Menu_Main extends Audio_Abstract_Class{

    public enum MEDIA_PLAYERS
    {
        BGM_MENU,
        SFX_MENU_CLICK,
    }

    public Audio_Activity_Menu_Main(Context theContext)
    {
        super(theContext, 1,
                R.raw.bgm_menu_loop,
                R.raw.sfx_menu_click);
    }

    @Override
    public MediaPlayer getMediaPlayer(Object thePlayer) {
        return myPlayers[((MEDIA_PLAYERS)thePlayer).ordinal()];
    }

    @Override
    public void startMedia(Object thePlayer) {
        getMediaPlayer(thePlayer).start();
    }

    @Override
    public void stopMedia(Object thePlayer) {
        getMediaPlayer(thePlayer).stop();
    }

}
