package ASTEROID_FIGHTER_FREE.Audio;

import android.content.Context;
import android.media.MediaPlayer;

import ASTEROID_FIGHTER_FREE.R;

public class Audio_Activity_Game_Over extends Audio_Abstract_Class{
    public enum MEDIA_PLAYERS
    {
        SFX_LOST_ALL_LIVES
    }

    public Audio_Activity_Game_Over(Context theContext)
    {
        super(theContext, 0,
                R.raw.sfx_rocket_lost_all_lives);
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
