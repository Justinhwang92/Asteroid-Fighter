package ASTEROID_FIGHTER_FREE.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import ASTEROID_FIGHTER_FREE.R;

public class Audio_Activity_Menu_Instructions extends Audio_Abstract_Class{
    public enum MEDIA_PLAYERS
    {
        BGM_INSTRUCTIONS
    }

    public Audio_Activity_Menu_Instructions(Context theContext)
    {
        super(theContext, 1,
                R.raw.bgm_instructions_loop);
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
