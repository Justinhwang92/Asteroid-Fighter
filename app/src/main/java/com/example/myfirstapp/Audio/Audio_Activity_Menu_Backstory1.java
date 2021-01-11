package com.example.myfirstapp.Audio;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.myfirstapp.R;

public class Audio_Activity_Menu_Backstory1 extends Audio_Abstract_Class{

    public enum MEDIA_PLAYERS
    {
        BACKSTORY1,
        BGM_MODES,
        SFX_MENU_CLICK,
    }

    public Audio_Activity_Menu_Backstory1(Context theContext)
    {
        super(theContext, 1,
                R.raw.bgm_modes_loop,
                R.raw.backstory1,
                R.raw.sfx_menu_click);
    }

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
