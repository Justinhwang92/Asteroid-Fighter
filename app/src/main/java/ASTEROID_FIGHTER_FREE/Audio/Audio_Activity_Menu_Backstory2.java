package ASTEROID_FIGHTER_FREE.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import ASTEROID_FIGHTER_FREE.R;

public class Audio_Activity_Menu_Backstory2 extends Audio_Abstract_Class{
    public enum MEDIA_PLAYERS
    {
        BACKSTORY2,
        BGM_MODES
    }

    public Audio_Activity_Menu_Backstory2(Context theContext)
    {
        super(theContext, 1,
                R.raw.bgm_modes_loop,
                R.raw.backstory2);
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
